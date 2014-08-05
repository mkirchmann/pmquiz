/**
 * 
 */
package de.neuenberger.pmp.processes.model;

/**
 * @author Michael Kirchmann
 * 
 */
public class DefaultQuestionGroup implements Named {
	private final String name;

	public DefaultQuestionGroup(final String name) {
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
