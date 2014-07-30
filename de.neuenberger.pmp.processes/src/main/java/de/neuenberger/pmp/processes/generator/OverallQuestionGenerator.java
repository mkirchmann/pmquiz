/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxProcessGroups;
import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class OverallQuestionGenerator implements QuestionGenerator {
	private final CplxProcessGroups processGroups;

	public OverallQuestionGenerator(final CplxProcessGroups processGroups) {
		this.processGroups = processGroups;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neuenberger.pmp.processes.generator.QuestionGenerator#generateQuestion
	 * ()
	 */
	@Override
	public Question generateQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

}
