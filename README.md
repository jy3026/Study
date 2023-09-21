## 지원자의 성명
최진영

## 애플리케이션 실행 방법
레포지토리 클론을 하고 application.yml에서 DB를 연결합니다 그 이후 빌드를 하고 실행 합니다.

## 엔드포인트

회원가입
```text
POST /member/signup
```
로그인
```text
POST /login
```
게시글 생성 
```text
POST /board
```
게시글 목록 조회
```text
POST board?page=1&size=10
```
특정 게시글 조회 (토큰 o)
```text
GET /api/board/{board-id}
```
특정 게시글 수정 (토큰 o)
```text
PATCH /board/{board-id}
```
특정 게시글 삭제 (토큰 o)
```text
DELETE /board/{board-id}
```

## 구현한 API 동작을 촬영한 데모 영상 링크
https://youtu.be/OLYUNGholVs

## 구현 방법 및 이유에 대한 간략한 설명
Spring Security를 통해 사용자 인증 로직을 구현했습니다. 
JWT(Json Web Token) 토큰을 활용하여 사용자 정보를 안전하게 포장하고, 
이를 헤더를 통해 통신함으로써 사용자를 식별하고 처리합니다. 
또한, 사용자와 게시글을 효과적으로 연결하는 일대다 관계를 구현하여 사용자가 다양한 게시글을 작성할 수 있도록 하였습니다.

## 데이터베이스 테이블 구조
![image](https://github.com/jy3026/wanted-pre-onboarding-backend/assets/89833446/97ffa41f-46ab-4b92-9650-147649afdf33)


## API 명세서
### 사용자 회원가입
- ### Request
    - METHOD : POST
    - API : /member/signup
    ```json
    {
      "email": "wefew@fdewr",
      "password": "higfdsfd12"
    }
    ```
- ### Response
    - 200 OK
    ```text
    계정 생성을 완료 했습니다.
    ```
    - 400 Bad Request
    ```json
    {
      "status": 0,
      "message": null,
      "fieldErrors": [
          {
              "field": "email",
              "rejectedValue": "wefewfde",
              "reason": "유효한 이메일 형식이어야 합니다."
          },
          {
              "field": "password",
              "rejectedValue": "higfdsf",
              "reason": "비밀번호는 최소 8자 이상이어야 합니다."
          }
      ]
    }
    ```

### 사용자 로그인
- ### Request
    - METHOD : POST
    - API : /login
    ```json
    {
      "email": "wefew@fdewr",
      "password": "higfdsfd12"
    }
    ```
- ### Response
    - 200 OK (성공)
    - 401 Unauthorized
    ```json
    {
      "timestamp": "2023-08-06T13:02:25.619+00:00",
      "status": 401,
      "error": "Unauthorized",
      "path": "/login"
    }
    ```

### 게시글 생성
- ### Request
    - METHOD : POST
    - API : /board
    - header : 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoid2VmZXdAZmRld3IiLCJzdWI...'
    ```json
    {
        "title": "제목이여",
        "contents": "내용이여"
    }  
    ```
    
- ### Response
    - 200 OK
    ```text
    게시글 생성 완료
    ```
  - 401 Unauthorized
  ```json
  {
      "status": 401,
      "message": "로그인 하세요.",
      "fieldErrors": null
  }
  ```

### 게시글 목록 조회
- ### Request
    - METHOD : GET
    - API : /board?page=1&size=10
- ### Response
    - 200 OK
    ```json
    {
        "data": [
            {
                "id": "3",
                "title": "제목이여2",
                "contents": "내용이여2",
                "email": "wefew@fdewr"
            },
            {
                "id": "2",
                "title": "제목이여1",
                "contents": "내용이여1",
                "email": "wefew@fdewr"
            },
            {
                "id": "1",
                "title": "제목이여",
                "contents": "내용이여",
                "email": "wefew@fdewr"
            }
        ],
        "pageInfo": {
            "page": 1,
            "size": 10,
            "totalElements": 3,
            "totalPages": 1
        }
    }
    ```

### 특정 게시글 조회
- ### Request
  - METHOD : GET
  - API :  /board/{board-id}

- ### Response
  - 200 OK (성공)
    ```json
    {
        "id": "1",
        "title": "제목이여",
        "contents": "내용이여",
        "email": "wefew@fdewr"
    }
    ```
  - 404 Not Found
    ```json
    {
        "status": 404,
        "message": "Board not found",
        "fieldErrors": null
    }
    ```

### 특정 게시글 수정
- ### Request
  - METHOD : PATCH
  - API : board/{board-id}
  - header : 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoid2VmZXdAZmRld3IiLCJzdWI...'
    ```json
    {
        "title": "제목수정",
        "contents": "내용수정"
    }  
    ```
- ### Response
  - 200 OK
    ```json
    {
        "id": "1",
        "title": "제목수정",
        "contents": "내용수정",
        "email": "wefew@fdewr"
    }
    ```
  - 401 Unauthorized
    ```json
    {
        "status": 401,
        "message": "로그인 하세요.",
        "fieldErrors": null
    }
    ```
  - 404 Not Found
    ```json
    {
        "status": 404,
        "message": "Board not found",
        "fieldErrors": null
    }
    ```

### 특정 게시글 삭제
- ### Request
  - METHOD : DELETE
  - API : board/{board_id}
  - header : 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoid2VmZXdAZmRld3IiLCJzdWI...'

- ### Response
  - 200 OK
    ```text
    게시글 삭제 완료.
    ```
  - 401 Unauthorized
    ```json
    {
        "status": 401,
        "message": "로그인 하세요.",
        "fieldErrors": null
    }
    ```
  - 404 Not Found
    ```json
    {
        "status": 404,
        "message": "Board not found",
        "fieldErrors": null
    }
    ```
