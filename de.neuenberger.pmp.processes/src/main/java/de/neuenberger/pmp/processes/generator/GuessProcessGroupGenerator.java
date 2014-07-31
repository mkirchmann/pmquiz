/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxProcessGroup;
import generated.CplxProcessGroup.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class GuessProcessGroupGenerator implements QuestionGenerator {
	private final CplxProcessGroup processGroup;
	private final List<CplxProcessGroup> allProcessGroups;

	GuessProcessGroupGenerator(final CplxProcessGroup processGroup,
			final List<CplxProcessGroup> allProcessGroups) {
		this.processGroup = processGroup;
		this.allProcessGroups = allProcessGroups;
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
		final List<Process> process = processGroup.getProcess();

		final Process drawnProcess = RandomDrawer.drawRandomSingle(process);

		final LinkedList<CplxProcessGroup> allAnswers = new LinkedList<>();
		allAnswers.addAll(allProcessGroups);
		allAnswers.remove(processGroup);

		List<CplxProcessGroup> drawRandom = RandomDrawer.drawRandom(allAnswers,
				3);
		drawRandom.add(processGroup);
		drawRandom = RandomDrawer.randomizeList(drawRandom);

		final List<String> allOptionsAsString = new ArrayList<>();
		String correctAnswer = "";
		for (final CplxProcessGroup cplxProcessGroup : drawRandom) {
			final String result = cplxProcessGroup.getName();
			allOptionsAsString.add(result);
			if (drawRandom == processGroup) {
				correctAnswer = result;
			}
		}

		final String qString = "To which process group does the process '"
				+ drawnProcess.getName() + "' belong?";
		final Question question = new Question(qString, allOptionsAsString,
				correctAnswer);

		return question;
	}
}
