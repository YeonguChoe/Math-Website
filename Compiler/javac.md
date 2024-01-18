# Javac
* javac는 Java 언어로 작성된 소스 코드를 컴파일하여 바이트 코드(.class 파일)로 변환해주는 컴파일러입니다.
* .class 파일은 C++의 .o 파일과 같이 컴파일러에 의해 생성된 바이너리 파일입니다.

## 순서
### 소스코드
```java
public class typeA {

    public static void main(String[] args) {
        System.out.println("This is Type A.");
        System.out.println("ID: " + args[0]);
    }
}
```

1. .java 파일 컴파일
```bash
javac <파일 이름>.java
```

2. .class 파일 실행
```bash
java <파일 이름> <argument 1> <argument 2> ...
```
* 결과
```bash
├── medical_info
│   └── bloodtype
│       ├── typeA.class
│       ├── typeA.java
│       └── typeO.class
```

## 2.1 패키지안에 .java 파일을 만드는 경우
### 소스코드
```java
package medical_info.bloodtype;

public class typeA {

    public static void main(String[] args) {
        System.out.println("This is Type A.");
        System.out.println("ID: " + args[0]);
    }
}

class typeO {
    public static void main(String[] args) {
        System.out.println("This is Type O.");
    }
}
```

```bash
├── medical_info
│   └── bloodtype
│       └── typeA.java
```
### 1단계 컴파일
```bash
javac medical_info/bloodtype/typeA.java
```
### 2단계 .class 파일 실행
```bash
java <패키지 이름1>.<패키지 이름2>.<클래스 이름>
# 예
java medical_info.bloodtype.typeA
```

## javac로 결과물의 위치를 지정하는 경우
### 1단계 결과물의 위치를 지정해서 컴파일
### -d <결과물이 저장되는 위치>
* 결과물 바이너리 파일이 저장되는 위치를 지정해 준다.
```bash
javac -d <결과물이 저장되는 위치> <소스코드 파일>.java
# 예
javac -d theFolder medical_info/bloodtype/typeA.java
```
### 2단계 -cp를 이용해서 프로그램 실행
* java는 directory와 패키지를 구분을 할 수 없기 때문에 실행 명령어에서 구분을 해줘야 한다.
```bash
java -cp <결과물 패키지가 있는 위치> <패키지 이름1>.<패키지 이름2>.<클래스 이름> <argument 1> <argument 2> ...
# 예
java -cp Folder medical_info.bloodtype.typeA
```