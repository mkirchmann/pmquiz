/**
 * 
 */
package de.neuenberger.pmp.processes.model;

/**
 * @author Michael Kirchmann
 * 
 */
public class QuestionGroup implements Named {
	private final String name;

	public QuestionGroup(final String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}
}
