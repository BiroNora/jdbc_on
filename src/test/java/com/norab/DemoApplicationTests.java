package com.norab;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		String death_date = "0000-00-00";
		Boolean valami = death_date == null || death_date.compareTo("") == 0
			|| death_date.compareTo("0000-00-00") ==0;
		assertEquals(true, valami);
	}

	@Test
	void contextLoads1() {
		String death_date = "0000-00-00";
		int valami = death_date.compareTo("0000-00-00");
		assertEquals(0, valami);
	}

}
