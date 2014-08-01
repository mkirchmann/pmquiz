/**
 *
 */
package de.neuenberger.pmp.processes.generator;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Class for ...
 * 
 * @author Michael Kirchmann
 */
public class RandomDrawerTest {

	/**
	 * 
	 */
	private static final String	CHOICE_D	= "D";

	/**
	 * 
	 */
	private static final String	CHOICE_C	= "C";
	/**
	 * 
	 */
	private static final String	CHOICE_B	= "B";
	/**
	 * 
	 */
	private static final String	CHOICE_A	= "A";
	List<String>				draws		= Arrays.asList(CHOICE_A, CHOICE_B, CHOICE_C, CHOICE_D);

	@Test
	public void testDraw() {
		List<String> drawRandom = RandomDrawer.drawRandom(draws, 1);
		Assert.assertEquals(1, drawRandom.size());
		String string = drawRandom.get(0);
		Assert.assertTrue(string + " not contained in original list", string == CHOICE_A || string == CHOICE_B
				|| string == CHOICE_C || string == CHOICE_D);
	}

	@Test
	public void testRandom() {
		List<String> randomizeList = RandomDrawer.randomizeList(draws);
		for (String string : draws) {
			Assert.assertTrue(randomizeList + " does not contain " + string, randomizeList.contains(string));
		}
	}
}
