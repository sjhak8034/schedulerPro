# SchedularPro
# 목차
* [일정 관리 API 명세서](#1-일정-관리-API-명세서)
* [ERD](#2-ERD)
* [Postman API 명세서](#4-PostMan-API-명세서)
## 1. 일정 관리 API 명세서
### 요약
|기능|	method|	url	|request	|response	|status|
|-------------|----|---------------|-----------|---------|--------------|
|일정 작성|POST|	/schedules/|	요청 body	|등록 정보	|200: 정상 등록|
|일정 목록 조회|GET|/schedules/|	요청 param	|다건 응답 정보	|200: 정상 조회|
|특정 일정 조회|GET|/schedules/{scheduleId}|	요청 param|	단건 응답 정보|	200: 정상 조회|
|일정 수정|PUT|/schedules/{scheduleId}|	요청 body	|수정 정보	|200: 정상 수정|
|일정 삭제|DELETE|/schedules/{scheduleId}|	요청 param	|삭제 정보	|200: 정상 삭제|
|유저 등록|POST|/users/signup|요청 body|등록 정보|200: 정상 등록|
|유저 조회|GET|/users/user-profile/{userId}|요청 body|등록 정보|200: 정상 조회|
|유저 수정|PUT|/users/user-profile/{userId}|요청 body|수정 정보|200: 정상 수정|
|유저 로그인|GET|/users/signin|요청 body|수정 정보|200: 정상 로그인|
### 일정 작성 
* POST /schedules/
* 요청 Request
  * Header:
     * Content-Type: application/json
     * Cookie: SessionId
   
       
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|세션 아이디|SessionId|String|x|JSESSIONID|

  * Body:
 
 
 ```
{
    "title": "제목목",
    "content": "할일",
    "userName": "이름"
}
```


|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|제목|title|String|x|20글자 제한|
|2|할일|content|String|x|200글자 제한|
|3|작성자명|userName|String|x|20글자 제한|


* 응답 Response
  * Status Code: 200 Created

  * Body:

```

{
    "scheduleId": 식별자
    "title": "제목",
    "content": "할일",
    "userName": "이름",
    "createdAt": "2024-11-14",
    "modifiedAt": "2024-11-14",
}

```


|#|변수 설명|변수 이름|타입|Nullable|description|
|-|-|-|-|-|-|
|1|작성된 게시글 식별자|scheduleId|long| x	|AUTO-INCREMENT|
|2|제목|title|String|x|20글자 제한|
|3|할일|content|String|x|200글자 제한|
|4|작성자명|userName|String|x|20글자 제한|
|5|작성일|createAt|String|x|YYYY-MM-DD|
|6|수정일|modifiedAt|String|x|YYYY-MM-DD|

### 특정기간 일정 목록 조회
* GET /schedules/?startDate=2024-11-05&endDate=2024-11-11&schedulePage=1&pageSize=5
* 요청 Request
  * Header:
    * Content-Type: application/json
    * request parameters:
       * startDate (optional) : 조회할 작성일 (시작) (YYYY-MM-DD)
       * endDate (optional) : 조회할 작성일 (끝) (YYYY-MM-DD)
       * schedulePage  : 조회할 페이지
       * pageSize : 한 페이지당 게시글 수
     * Cookie: SessionId
  * 정렬: 작성일 기준 내림차순
 
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|시작 일|startDate|String|x|YYYY-MM-DD|
|2|마지막 일|endDate|String|x|YYYY-MM-DD|
|4||조회할 페이지|long|x|최소 1|
|5|게시글 수|pageSize|long|x|최소 1|
|6|세션 아이디|SessionId|String|x|JSESSIONID|


* 응답
  * Status Code: 200 OK
  * Body


```
[
    {
        "scheduleId": "게시글 고유 번호",
        "title": "제목",
        "userName": "작성자명",
        "createdAt": "YYYY-MM-DD HH:mm:ss",
        "editedAt": "YYYY-MM-DD HH:mm:ss"
    },
    ...
]
```
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|게시글 고유 번호|scheduleId|int|x|AUTO-INCREMENT|
|2|제목|title|String|x|20글자 제한|
|3|작성자명|userName|String|x|20글자 제한|
|4|작성일|createdAt|String|x|YYYY-MM-DD HH:mm:ss|
|5|수정일|editedAt|String|x|YYYY-MM-DD HH:mm:ss|



### 특정 일정 조회
* GET /schedules/{scheduleId}
* 요청 Request
   * Header:
    * Content-Type: application/json
    * Path Variable: scheduleId
    * Cookie: SessionId
 
|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|게시글 고유 번호|scheduleId|int|x|AUTO-INCREMENT|
|2|세션 아이디|SessionId|String|x|JSESSIONID|
* 응답
   * Status Code: 200 OK
   * Body:
```
{
    "scheduleId": "게시글 고유 번호",
    "title": "제목",
    "content": "할일 내용",
    "userName": "작성자명",
    "createdAt": "YYYY-MM-DD HH:mm:ss",
    "editedAt": "YYYY-MM-DD HH:mm:ss"
}
```

|#|변수 설명|변수 이름|타입|Nullable|description|
|-|-|-|-|-|-|
|1|게시글 고유 번호|scheduleId|int|x|AUTO-INCREMENT|
|2|제목|title|String|x|20글자 제한|
|3|할일 내용|content|String|x||
|4|작성자명|userName|String|x|20글자 제한|
|5|작성일|createdAt|String|x|YYYY-MM-DD HH:mm:ss|
|6|수정일|editedAt|String|x|YYYY-MM-DD HH:mm:ss|

### 일정 수정
* PUT /schedules/{scheduleId}
* 요청
   * Header:
     * Content-Type: application/json
     * path Variable: scheduleid
     * Cookie: SessionId
    
       
|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|게시글 고유 번호|scheduleId|int|x|AUTO-INCREMENT|
|2|세션 아이디|SessionId|String|x|JSESSIONID|

   * Body:
```
{
    "title": "제목",
    "content": "할일",
    "userName": "작성자명"
}
```
 
|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|제목|password|String|x|20글자 제한|
|2|할 일|password|String|x||
|3|작성자명|userName|String|x|20글자 제한|

* 응답
  * Status Code: 200 OK
  * Body:
```
{
    "scheduleId": "게시글 고유 번호",
    "title": "제목",
    "content": "할일 내용",
    "userName": "작성자명",
    "createdAt": "YYYY-MM-DD HH:mm:ss",
    "editedAt": "YYYY-MM-DD HH:mm:ss"
}
```


|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|게시글 고유 번호|scheduleId|int|x|AUTO-INCREMENT|
|2|제목|title|String|x|20글자 제한|
|3|할일 내용|content|String|x||
|4|작성자명|userName|String|x|20글자 제한|
|5|작성일|createdAt|String|x|YYYY-MM-DD HH:mm:ss|
|6|수정일|editedAt|String|x|YYYY-MM-DD HH:mm:ss|


### 일정 삭제
* DELETE /schedules/{scheduleId}
* 요청
   *Header:
     * Content-Type: application/json
     * Path Variable: scheduleId
     * Cookie: SessionId
 
       
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|게시글 고유 번호|scheduleId|long|x|AUTO_INCREMENT|
|2|세션 아이디|SessionId|String|x|JSESSIONID|
 

* 응답
   * Status Code: 200 OK
   * Body:
```  
{
    "scheduleId": 식별자
}
```
|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|삭제된 게시글 식별자|scheduleId|long|x|AUTO-INCREMENT|


### 유저 등록
* Post /users/signup
* 요청
   * Header:
    * Content-Type: application/json
    * sessionId: 세션 아이디
 
      
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|-|-|-|-|-|
|1|세션 아이디|SessionId|String|x|JSESSIONID|
    
```
{
    "userName" : "이름",
    "email" : "이메일"
    "password":"비밀번호"
}
```
 
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|유저 이름|userName|String|x|20글자 제한|
|2|이메일|email|String|x|@xxxx.com형식|
|3|비밀번호|password|String|x|20글자 제한|


* 응답
   * header:
     * cookie: SessionId  
   * Status Code: 200 OK
   * Body:
```
{
    "userId": 식별자 번호,
    "userName": "유저이름",
    "email": "이메일",
    "createdAt": "생성일",
    "modifiedAt": "수정일"
}
```
|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|생성된 유저 식별자|userId|long|x|AUTO-INCREMENT|
|2|유저 이름|userName|String|x|20글자 제한|
|3|이메일|email|String|x|@xxxx.com형식|
|4|생성일|createdAt|String|x|YYYY-MM-DD HH:mm:ss|
|5|수정일|editedAt|String|x|YYYY-MM-DD HH:mm:ss|


### 유저 수정
* PUT /users/user-profile/
* 요청
    * Header:
       * Content-Type: application/json
       * cookie: SessionId
     
         
|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|세션 아이디|SessionId|String|x|JSESSIONID|
 
   *Body:
```
{
    "userName" : "이름",
    "password":"비밀번호",
    "email" : "이메일"
}
```
 
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|수정한 이름|userName|String|x|20글자 제한|
|2|수정한 비밀번호|password|String|x|20글자 제한|
|3|수정한 이메일|email|String|x|@xxxx.com 형식|

* 응답
   * header:
     * cookie: SessionId  
   * Status Code: 200 OK
   * Body:
```
{
    "userName" : "이름",
    "email" : "이메일",
    "createdAt": "생성일",
    "modifiedAt": "수정일"
}
```

|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|유저 이름|userName|String|x|20글자 제한|
|2|이메일|email|String|x|@xxxx.com형식|
|3|생성일|createdAt|String|x|YYYY-MM-DD HH:mm:ss|
|4|수정일|editedAt|String|x|YYYY-MM-DD HH:mm:ss|


### 유저 로그인
* PUT /users/signin
* 요청
    * Header:
       * Content-Type: application/json
       * cookie: SessionId
     
         
|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|세션 아이디|SessionId|String|x|JSESSIONID|
 
   *Body:
```
{
     "email" : "이메일"
     "password":"비밀번호",
}
```
 
|#|변수 설명|변수 이름|타입|Nullable|description|
|-|--------|---------|---|---|--|
|1|이메일|email|String|x|@xxxx.com 형식|
|2|비밀번호|password|String|x|20글자 제한|


* 응답
   * header:
     * cookie: SessionId  
   * Status Code: 200 OK
   * Body:
```
{
    "userName" : "이름",
    "email" : "이메일",
    "createdAt": "생성일",
    "modifiedAt": "수정일"
}
```

|#|변수 설명|변수 이름|	타입|	Nullable|description|
|-|-|-|-|-|-|
|1|유저 이름|userName|String|x|20글자 제한|
|2|이메일|email|String|x|@xxxx.com형식|
|3|생성일|createdAt|String|x|YYYY-MM-DD HH:mm:ss|
|4|수정일|editedAt|String|x|YYYY-MM-DD HH:mm:ss|


## 2. ERD
![image](https://github.com/user-attachments/assets/5739eab7-6dc6-4cab-8cf8-8d210f92440a)




## 4. Postman API 명세서
https://web.postman.co/workspace/dc9a47b3-2ced-4fb4-818d-ef16a3835a45/documentation/39355348-3b4eb661-11b8-4878-8829-ce1b5fd04a5a
