# JPA & MyBatis Difference

### 발표자료: JPA 개발자 시각에서 바라본
- 날짜: 2024.12.16
- 발표자: R&D센터 김강민

---

## 1. 스프링 프레임워크가 제공하는 다양한 기능

### [Spring Framework 특징]

#### 1. POJO (Plain Old Java Object)
- 상속이나 구현 없이 속성과 기능에 집중.

#### 2. DI (Dependency Injection)
- 객체를 생성하지 않고 주입받음.
- Bean(객체)을 통해 의존성 관리.

#### 3. IoC (Inversion of Control)
- 제어권이 프레임워크로 이동.
- 컨테이너를 통해 객체 생명주기 관리.

#### 4. AOP (Aspect Oriented Programming)
- 공통 관심 사항(보안, 로그)과 핵심 관심 사항(비즈니스 로직)을 분리.

---

## 2. 글로벌 트렌드 및 다양한 시나리오
- 한국과 중국을 제외한 대부분의 국가는 JPA 사용 중.

---

## 3. O/R Mapper 사용 이전

<img width="500" alt="image" src="https://github.com/user-attachments/assets/151ecf6d-03ea-47d6-bdd6-0715431f80bb" />

## 4. O/R Mapper 사용 이후

<img width="500" alt="image" src="https://github.com/user-attachments/assets/df178dbc-f2b6-4617-bfc6-0a9d24168e63" />

<img width="500" alt="image" src="https://github.com/user-attachments/assets/9f18e287-4eec-4191-a62a-402efe54ab4d" />

## 5. MyBatis

### • 프로세스
1. Mapper Interface 생성.
2. Mapper.xml 작성 (XML에서 테이블 지정).

### • 단점
1. 데이터베이스에 종속적.
   - DB 변경 시 쿼리문 재작성 필요.
2. 반복 수작업 필수.
   - 단순 CRUD 작업에도 반복 필요.

**SQL Mapper 프레임워크**: 개발자가 작성한 SQL 문과 Java 코드를 매핑.

---

## 6. JPA

### • 프로세스
1. Repository Interface 생성.
2. 구현되어 있는 JPA 사용 (Entity 클래스와 테이블 매핑).

### • 단점
1. 복잡한 SQL 생성의 어려움.
2. 성능에 대한 고려 필요.

**ORM 기술**: SQL 문을 자동 생성하며 테이블을 Java 객체와 매핑.

---

## 7. ORM(Object-Relational Mapping)이란?
- 객체가 하나의 레코드가 되도록 매핑하는 기법.
- 프로그래밍 언어마다 각자의 스펙이 결정됨.

### JPA(Java Persistence API)
- JPA는 스펙이며, 이를 구현한 다양한 제품과 라이브러리가 존재.
- JPA는 인터페이스로, 주요 구현체는 다음과 같음:
  - Hibernate (주요 구현체).
  - DataNucleus.
  - EclipseLink.

### Spring Data JPA
- 스프링에서 JPA를 편리하게 사용하도록 만든 모듈.
1. CRUD 처리를 위한 공통 인터페이스 제공.
2. 인터페이스 작성 후 공통 인터페이스 상속.
3. CRUD 메서드들을 자유롭게 사용 가능.

---

## 8. JPA vs MyBatis

### • 공통점
- 둘 다 DB와 연동 및 데이터 저장을 위한 기술.

### • 차이점
1. SQL Mapper:
   - 개발자가 작성한 SQL 실행 결과를 객체에 매핑.
   - 대표 기술: MyBatis.

2. ORM:
   - 객체와 DB 데이터를 자동으로 매핑.
   - 대표 기술: JPA (Java Persistence API).

---

## 9. 언제 무엇을 선택해야 할까?

### MyBatis가 적합한 경우
- 복잡한 쿼리와 SQL 제어가 필요한 경우.
- SQL 경험이 풍부하거나 특정 DB에 최적화된 SQL 작성이 필요한 경우.

### JPA가 적합한 경우
- 간단한 매핑 및 객체 지향적인 접근이 필요한 경우.
- 표준화된 인터페이스와 자동 매핑 필요.
- 다양한 데이터베이스에 대응해야 하는 경우.

---

## 10. 성능 및 속도 비교

### • MyBatis가 JPA보다 빠른 이유
- 여러 연구 결과에 따르면 MyBatis는 JPA보다 최대 30% 높은 성능을 보임.
- JPA는 엔티티 관리, 캐시 처리, 트랜잭션 등의 오버헤드로 인해 속도가 느려짐.
- 복잡한 조인 및 필터링 조건이 있는 쿼리에서는 MyBatis가 더 유리함.



# 유저 생성(저장) 속도 비교 

## 1천명, 사용자 저장
<img width="500" alt="image" src="https://github.com/user-attachments/assets/b766701f-e5ba-4d56-aa90-f861b6797912" />
<img width="500" alt="image" src="https://github.com/user-attachments/assets/ee5307a7-2247-4a0a-8152-1b8b3f7e6fba" />

- JPA 2.49 / Mybatis 2.43 초


## 1만명, 사용자 저장
<img width="500" alt="image" src="https://github.com/user-attachments/assets/4f8685b7-5602-4e86-b285-9359e154560b" />
<img width="500" alt="image" src="https://github.com/user-attachments/assets/50ef5bf2-74b8-4e59-844f-37efc4c0617f" />

- JPA 6.92 초 / Mybatis 3.71 초 

## 10만명, 사용자 저장 
<img width="500" alt="image" src="https://github.com/user-attachments/assets/6b1459de-0ec9-4f28-b54b-5d851b15e468" />
<img width="500" alt="image" src="https://github.com/user-attachments/assets/3584a7e5-78a6-4d96-9c21-3bc21123c936" />

- JPA 20초, Mybatis 14초


