package unsw.dungeon.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EntityTest {

	int arr[] = { 1, 3 };
	int arr2[] = { 1, 3 };

	@Test
	void test() {
		assertEquals("hello", 1, 1);
	}

	@Test
	void testArray() {
		assertArrayEquals(arr, arr2);
	}

}
