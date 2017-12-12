package test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestStartWith {

	@Test
	public void test() {
		CheckName app = new CheckName();
		List<String> userNames = Arrays.asList(
				"sclb admin",
				"customer admin",
				"Kim Arsenault",
				"Reginald Arsenault",
				"Mark Arsenault",
				"Tyler Arsenault",
				"Kim Arsenault SC");
		assertTrue(app.verifyLastNameStartsWith(userNames, "A"));
	}

}
