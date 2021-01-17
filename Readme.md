# Tumblbug 과제 프로젝트

## 서버 실행

```shell
$ ./gradlew bootRun    
```

## 실행 후 접속

- http://localhost:8080/swagger-ui.html

## 디렉토리 구조

클린 아키텍쳐를 차용하였습니다.

```text
- commons
  - configurations   // spring boot 등에 필요한 설정
  - utils            // modelMapper 를 사용하기 위한 util 등
- core
  - adapters 
    - in             // controllers
    - out            // out port 의 구현체 adapter
  - applications
    - port           // application 외부와 통신하는 포트
      - in           // useCase (interface) - 어플리케이션 사용 시나리오
      - out          // 어플리케이션에 -> 외부로의 out port. ex) db
  - domains
  - infrastructures  // db 등 외부와 통신하는부분의 구현체
    - entities       // db 의 entity 정의 (궁극적으로는 DB의 entity 는 domain과는 다르므로)
    - repositories   // entity 에 접근하기 위한 repository
```
