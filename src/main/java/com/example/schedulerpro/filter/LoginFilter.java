package com.example.schedulerpro.filter;

import com.example.schedulerpro.Common.Const;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = { "/users/sign-in", "/users/sign-up", "/users/sign-out" };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        log.info("로그인 필터 로직 실행");

        try {
            // whitelist 아닌 경우
            if (!isWhiteList(requestURI)) {
                log.info("화이트 리스트가 아닙니다");
                HttpSession session = httpServletRequest.getSession(false);
                if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 해주세요.");
                }
                log.info("로그인에 성공했습니다.");
            }
            // 다음 필터 호출
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ResponseStatusException e) {
            // 에러 발생 시 상태 코드와 메시지만 반환
            httpServletResponse.setStatus(e.getStatusCode().value());
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(e.getMessage());
        }
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
