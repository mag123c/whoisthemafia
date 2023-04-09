# WebSocket 심화 학습을 위한 개인 ProJect
<details>
<summary><h3>개발 관련 정보 보기</h3></summary>

 ### 개발 관련 포스팅

 - 기초설계 및 세팅 : <https://mag1c.tistory.com/293>

### 개발 동기

 - 학원 수강 당시 OTT 서비스 프로젝트 개발 중 WebSocket을 활용한 실시간 알림 및 1:N 채팅방 구현 경험에서 흥미를 느꼈음
 - 관련 링크 : <https://mag1c.tistory.com/222> <https://mag1c.tistory.com/223> <https://mag1c.tistory.com/233>

### 개발 목적

 - 웹 소켓을 더 능동적이고 심도있게 다루기 위함

### 개발 환경

    언어 및 프레임워크 : Java 8 / Spring framework 5.2.18.RELEASE - Mybatis
    프론트 엔드 : HTML5 / CSS3 / JavaScript / J-Query / JSP
    서버 및 DB : Apache-Tomcate 9.0 / MySQL 8.0.28

 ### 개발 예상 기간
 
     2023.04 ~ 2023.05.12

</details>
<hr>

## 작업 일자별로 Ver관리 및 검수 후 Version up (1.x -> 2.0 ...)

<details>
<summary><h3>Ver1.0 ( 2023. 04. 02 )</h3></summary>

    로그인, 회원가입 기능
    ws를 통해 게임방 입장, 퇴장 시 DB 및 Lobby(View)에서 인원 수 변동
 
</details>

<details>
<summary><h3>Ver1.1 ( 2023. 04. 08~09 )</h3></summary>
    
    설정 추가
    transaction - aspectjweaver - cglib  /  HiddenHttpMethodFilter
    
    코드 수정 ( Con - 로직 호출 / Service - 로직 작성 / DAO - JDBC Conn only )
    서비스단 트랜잭션 단위로 Commit Rollback 구현
    GameRoom 입.퇴장 시 Lobby update 완료
    미흡사항 : GameRoom 입장 시 div 안에 값이 들어있는거 체크 어떻게 할것인가 / 기존 입장유저는 모든정보 다갖고옴
 
</details>

