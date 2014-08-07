/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxNamed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class CplxNamedUtil {
	public static <E extends CplxNamed> List<E> addAllWithNameNot(
			final List<E> compareList, final List<E> newElements) {
		final List<E> result = new ArrayList<>(newElements.size());
		final Set<String> allCompareListNames = new HashSet<>();
		for (final E e : compareList) {
			allCompareListNames.add(e.getName());
		}
		for (final E e : newElements) {
			if (!allCompareListNames.contains(e.getName())) {
				result.add(e);
			}
		}
		return result;
	}
}
