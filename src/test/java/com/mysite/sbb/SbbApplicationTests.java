// package com.mysite.sbb;

// import org.aspectj.lang.annotation.Before;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.mysite.sbb.question.Question;
// import com.mysite.sbb.question.QuestionRepository;
// import com.mysite.sbb.question.QuestionService;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.fail;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// @SpringBootTest
// class SbbApplicationTests {

// 	@Autowired
// 	private QuestionService qs;

// 	@Autowired
// 	private QuestionRepository qr;

// 	@AfterEach
// 	void checkAfter() {
// 		// fail("끝");
// 	}

// 	@BeforeEach
// 	void setUp() {
// 		// fail("시작");
// 	}

// 	@Test
// 	void t1() {
// 		assertEquals(1, 1);
// 	}

// 	@Test
// 	void testJpa1() {
// 		// // row 1개를 생성한다.
// 		// Question q1 = new Question();
// 		// // Subject 속성을 정한다
// 		// q1.setSubject("3");
// 		// // Content 속성을 정한다
// 		// q1.setContent("3");
// 		// // CreateDate 속성을 정한다
// 		// q1.setCreateDate(LocalDateTime.now());
// 		// this.qr.save(q1);

// 		// // 서비스 이용하는 방법
// 		// this.qs.create("4", "4", null);
// 	}

// 	@Test
// 	void testJpa2() {
// 		// List<Question> all = this.qr.findAll();
// 		// assertNotEquals(2, all.size());
// 	}

// 	@Test
// 	void testJpa3() {
// 		// Optional<Question> filtered = this.qr.findById(300);
// 		// assertEquals(300, filtered.get().getId());
// 	}

// 	@Test
// 	void testJpa4() {
// 		// Question q = this.qr.findBySubjectAndContent(
// 		// "1", "1");
// 		// assertEquals(2399, q.getId());
// 	}

// 	@Test
// 	void testJpa() {
// 		// for (int i = 1; i <= 300; i++) {
// 		// String subject = String.format("테스트 데이터: [%03d]", i);
// 		// String content = "공백 내용";
// 		// this.qs.create(subject, content, null);
// 		// }
// 	}

// }
