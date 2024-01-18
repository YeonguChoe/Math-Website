# jdb 사용법
* 자바 프로그램을 디버깅할 때 사용하는 command line 디버거입니다. jdb는 Java 플랫폼에 내장되어 있어서 별도의 설치 없이 사용할 수 있습니다.

## 순서
1. javac를 -g 옵션을 사용해서 .class 파일 컴파일
```bash
javac -g <소스 파일>.java
```
2. jdb 실행
```bash
jdb <클래스 이름>
# 예
jdb theProgram
```
3. Breakpoint 지정
```bash
# n번째 줄에 breakpoint 지정
stop at <클래스 이름>:<줄 번호>
# 예
stop at Sample:4

# 특정 함수에 breakpoint 지정
stop in <클래스 이름>.<함수의 signature>
# 예
stop in Sample.someFunc(java.lang.String)

# 지정한 모든 breakpoint 출력
stop
```
* breakpoint를 지정한 후 디버거에서 프로그램을 실행한다.
```bash
run
```
4. 변수값을 출력하기
* dump 명령어는 object의 정보를 출력해 준다.
```bash
dump <변수 이름>
# 예
dump AreaCode
```
5. 다음 breakpoint로 이동
```bash
cont
```
6. breakpoint 제거
```bash
# 줄로 지정한 경우
clear <클래스 이름>:<줄 번호>

# 함수로 지정한 경우
clear <클래스 이름>.<함수의 signature>
```
7. 다음 줄로 이동 (step over)
```bash
next (<횟수>)
```
8. 함수 안으로 들어가기 (step in)
```bash
step (<횟수>)
```
9. 디버깅 도중 변수 값 변경하기
```bash
set <변수 이름> = <새로운 변수 값>
```
10. 디버깅 도중에 새로운 expression 실행
* expression은 함수를 호출 하는 것이다.
```bash
eval <함수 호출>
# 예
eval Sample.someFunc("one example")
```
11. 생성된 모든 변수 출력
```bash
locals
```
12. jdb 종료
```bash
quit
```