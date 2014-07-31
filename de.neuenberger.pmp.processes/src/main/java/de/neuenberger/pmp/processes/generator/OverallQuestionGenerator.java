/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxProcessGroup;
import generated.CplxProcessGroups;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class OverallQuestionGenerator implements QuestionGenerator {
	private final CplxProcessGroups processGroups;
	List<QuestionGenerator> generators = new ArrayList<>();

	public OverallQuestionGenerator(final CplxProcessGroups processGroups) {
		this.processGroups = processGroups;

		final List<CplxProcessGroup> processGroup = processGroups
				.getProcessGroup();
		for (final CplxProcessGroup cplxProcessGroup : processGroup) {
			generators.add(new GuessProcessGroupGenerator(cplxProcessGroup,
					processGroups.getProcessGroup()));
		}
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
		final int genIndex = new Random().nextInt(generators.size());
		return generators.get(genIndex).generateQuestion();
	}
}
