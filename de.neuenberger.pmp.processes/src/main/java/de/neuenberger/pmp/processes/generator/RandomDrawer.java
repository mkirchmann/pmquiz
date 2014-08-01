/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Michael Kirchmann
 * 
 */
public class RandomDrawer {
	public static <E> List<E> drawRandom(final List<E> list, final int draws) {
		if (list.size() < draws) {
			throw new IllegalStateException("Not so many draws (" + draws
					+ ") possible from list with size=" + list.size());
		}
		final List<E> drawList = new LinkedList<>();
		final List<E> drawResult = new ArrayList<>(draws);
		drawList.addAll(list);
		final Random random = new Random();
		for (int i = 0; i < draws; i++) {
			final int nextInt = random.nextInt(drawList.size());
			final E remove = drawList.remove(nextInt);
			drawResult.add(remove);
		}
		return drawResult;
	}

	public static <E> List<E> randomizeList(final List<E> list) {
		return drawRandom(list, list.size());
	}

	public static <E> E drawRandomSingle(final List<E> list) {
		return drawRandom(list, 1).get(0);
	}
}
