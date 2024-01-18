# Java

### 이런식으로 정리

- 자료형
- 제어 흐름
- 구조체와 이넘
- 클래스와 객체
- 포인터와 레퍼런스
- 힙과 스택 메모리
- 헤더 파일
- 템플릿

## 자바 버전
- [자바 SE 튜토리얼](https://docs.oracle.com/javase/tutorial/)
- [자바 SE 패키지](https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/overview-summary.html)

## 자료형
* byte
* short
* int
* long
* float
* double
* char
* boolean

## if문
```java
if (x > 0 && y == true) {
    System.out.println("Positive");
} else if (x == 0 || y != false) {
    System.out.println("Zero");
} else {
    System.out.println("Negative");
}
```
### 삼항 연산자
```java
int number = 3;
boolean isPositive = false;
isPositive = (number > 0) ? true : false;
```
### 비교 연산자
```java
==, !=, >, <, >=, <=
```
### 논리 연산자
|Logic|Java|
|---|---|
|그리고|&&|
|또는|\|\||
|아닌|!|
## switch문
```java
switch (<변수>) {
    case <변수와 비교하는 값>:
        <코드>
        break;
    default:
        <코드>
        break;
}

```
## while문
```java
int cnt = 0;
while (cnt < 5) {
    System.out.println(cnt);
    cnt++;
}
// 0, 1, 2, 3, 4
```
## do while문
```Java
int cnt = 0;
do {
    System.out.println(cnt);
    cnt++;
} while (cnt < 5);
// 0, 1, 2, 3, 4
```

## for문
```Java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
// Print: 0, 1, 2, 3, 4
```
## for each문
```Java
int[] numbers = { 0, 0, 1, 2, 3 };
for (int i : numbers) { // i's data type should be the same as elements in the list
    System.out.println(numbers[i]);
}
// Print: 0, 0, 0, 1, 2
```
## 함수
```Java
public String message(int a) {
    return "" + a;
}
```
## 클래스
Class declaration
```Java
public class Person {
    // field
    public String nationality;

    // constructor
    public Person(String countryOfBirth) {
        this.nationality = countryOfBirth;
    }

    // methods
    public void immigration(String country) {
        this.nationality = country;
    }
}
```
Object declaration
```Java
Person student = new Person("South Korea");
```