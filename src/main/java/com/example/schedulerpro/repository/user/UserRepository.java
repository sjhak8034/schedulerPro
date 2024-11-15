package com.example.schedulerpro.repository.user;

import com.example.schedulerpro.entity.Schedule;
import com.example.schedulerpro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User findByUserId(Long userId) {
        return findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not exist id = " + userId
                        )
                );
    }

    Optional<User> findByEmail(String email);

}
