# 🚕 스뮤풀(SMU Pool) - 상명대 택시 쉐어링 서비스

![포스터.png](src%2Fmain%2Fresources%2Fstatic%2F%ED%8F%AC%EC%8A%A4%ED%84%B0.png)
-
- 배포 URL : https://

<br>

## 프로젝트 소개

- 우리학교 상명대학교는 너무 높은 언덕에 위치해 있어 대중교통 이용이 불편한 점이 많습니다.
- 캠퍼스 정문까지 오는 버스는 7016번 단 한 대 뿐이라 기다리는 시간도 길고, 버스가 많이 붐비는 편입니다.
- 택시를 이용하자니 요금 부담이 크고, 혼자 타기엔 아깝다는 생각이 듭니다.
- 이런 상명대 학생들의 불편함을 해소하고자 스뮤풀 서비스를 기획하게 되었습니다.
- 스뮤풀은 등하굣길에 학생들을 매칭해 택시를 함께 탈 수 있도록 도와주는 서비스입니다.
<br>

## 팀원 구성

### IOS 파트
|                                                                          **문정현**                                                                           |                                                                    **임진영**                                                                    |                                                                  **김민지**                                                                  |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------:|
| <img src="https://avatars.githubusercontent.com/u/162976163?v=4![img.png](img.png)" height="150" width="150"> <br> [@jhhyuen](https://github.com/jhhyuen/) |                           <img src="https://avatars.githubusercontent.com/u/102199863?v=4" height="150" width="150"> <br> [@JYLeem](https://github.com/JYLeem)                           |                                         <img src="" height="150" width="150"> <br> [@]()                                         |

### Server 파트
|                                                               **고민영**                                                               |                                                                    **김진호**                                                                     |                                                                          **박세은**                                                                          |                                                              **김지민**                                                              |
|:-----------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------:|
| <img src="https://avatars.githubusercontent.com/u/80516484?v=4" height="150" width="150"> <br> [@gomin0](https://github.com/gomin0) | <img src="https://avatars.githubusercontent.com/u/69239293?v=4" height="150" width="150"> <br> [@jinho7](https://github.com/jinho7) | <img src="https://avatars.githubusercontent.com/u/109071820?v=4" height="150" width="150"> <br> [@pse10307](https://github.com/pse10307)                  | <img src="https://avatars.githubusercontent.com/u/106303671?v=4" height="150" width="150"> <br> [@jinnieusLab](https://github.com/jinnieusLab) |  



## 3. 패키지 구조
~~~
com
┗ example
┗ nuribank
┣ domain
│   ┣ account
│   │ ┣ controller
│   │ ┣ dto
│   │ ┣ entity
│   │ ┣ exception
│   │ ┣ repository
│   │ ┗ service
│   ┗ user
│     ┣ controller
│     ┣ dto
│     ┣ entity
│     ┣ exception
│     ┣ repository
│     ┗ service
│ ...
┗ global
┣ auth
┣ common
┣ config
┣ error
┣ infra
┗ util
~~~

## 4. 브랜치 전략
~~~
브랜치 전략
|-- main
    |-- develop ✅ gitaction target
        |-- feat/<# issue_number>
~~~

## 5. 커밋 메시지
| 이모티콘 | 문자 | 커밋 유형 | 의미 |
| --- | --- | --- | --- |
|  ✨ | :sparkles: | feat | 새로운 기능 추가 |
| 🐛 | :bug: | fix | 버그 수정 |
| 📝 | :memo: | docs | 문서 수정 (md 파일) |
| ♻️ | :recycle: | refactor | 코드 리팩토링 |
| 💄 | :lipstick: | style | 코드 formatting, 세미콜론 누락, 코드 자체의 변경이 없는 경우 |
| ✅ | :white_check_mark: | test | 테스트 코드, 리팩토링 테스트 코드 추가 |
| 🚀 | :rocket: | chore | 패키지 매니저 수정 (Dockerfile, gradle, sh, yml) |
| 🚑 | :ambulance: | !hotfix | 급하게 치명적인 버그를 고쳐야 하는 경우 |

### 예시
**✨ feat : 기능 추가**