/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxProcessGroup;
import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class ProcessGroupQuestionGenerator implements QuestionGenerator {
	private final CplxProcessGroup processGroup;

	public ProcessGroupQuestionGenerator(final CplxProcessGroup processGroup) {
		this.processGroup = processGroup;

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
