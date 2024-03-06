package com.mysite.sbb;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@AfterEach
	void checkAfter() {
		// fail("끝");
	}

	@BeforeEach
	void setUp() {
		// fail("시작");
	}

	@Test
	void t1() {
		assertEquals(1, 1);
	}

	@Test
	void testJpa1() {
		// row 1개를 생성한다.
		Question q1 = new Question();
		// Subject 속성을 정한다
		q1.setSubject("3");
		// Content 속성을 정한다
		q1.setContent("3");
		// CreateDate 속성을 정한다
		q1.setCreateDate(LocalDateTime.now());

		Question q2 = new Question();
		q2.setSubject("4");
		q2.setContent("4");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

	@Test
	void testJpa2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());
	}

	@Test
	void testJpa3() {
		Optional<Question> filtered = this.questionRepository.findById(2);
		assertEquals(2, filtered.get().getId());
	}

	@Test
	void testJpa4() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"1", "1");
		assertEquals(1, q.getId());
	}

}
