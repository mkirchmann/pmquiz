/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxProcessGroup;
import generated.CplxProcessGroup.Process;
import generated.CplxProcessGroups;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class OverallQuestionGenerator implements QuestionDrawer {
	private final CplxProcessGroups	processGroups;
	List<QuestionDrawer>			generators	= new ArrayList<>();

	public OverallQuestionGenerator(final CplxProcessGroups processGroups) {
		this.processGroups = processGroups;

		final List<CplxProcessGroup> processGroup = processGroups.getProcessGroup();
		for (final CplxProcessGroup cplxProcessGroup : processGroup) {
			List<Process> allProcesses = cplxProcessGroup.getProcess();
			QuestionFactory<Process> factory1 = new ProcessRelatedQuestionGenerator.GuessNextProcessQuestionFactory(allProcesses);
			QuestionFactory<Process> factory2 = new ProcessRelatedQuestionGenerator.GuessPreviousProcessQuestionFactory(
					allProcesses);
			QuestionFactory<Process> factory3 = new ProcessRelatedQuestionGenerator.GuessProcessGroupQuestionFactory(
					cplxProcessGroup, processGroup);
			QuestionFactory<Process> factory4 = new ProcessRelatedQuestionGenerator.GuessProcessOfProcessGroup(cplxProcessGroup,
					processGroup);

			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory1));
			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory2));
			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory3));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neuenberger.pmp.processes.generator.QuestionDrawer#generateQuestion
	 * ()
	 */
	@Override
	public Question drawQuestion() {
		final int genIndex = new Random().nextInt(generators.size());
		return generators.get(genIndex).drawQuestion();
	}

}
