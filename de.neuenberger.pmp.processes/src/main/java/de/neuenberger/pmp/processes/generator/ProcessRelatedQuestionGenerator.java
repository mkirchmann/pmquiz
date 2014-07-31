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
public class ProcessRelatedQuestionGenerator extends AbstractQuestionGenerator {
	private final CplxProcessGroup	processGroup;
	QuestionFactory<Process>		questionFactory;

	ProcessRelatedQuestionGenerator(final CplxProcessGroup processGroup, final QuestionFactory<Process> questionFactory) {
		this.processGroup = processGroup;
		this.questionFactory = questionFactory;
	}

	/**
	 * @return
	 */
	@Override
	protected List<Question> createQuestions() {
		List<Process> process = processGroup.getProcess();
		List<Question> result = new ArrayList<>(process.size());
		for (Process p : process) {
			Question question = questionFactory.createQuestionForProcess(p);
			if (question != null) {
				result.add(question);
			}
		}
		return result;
	}

	public static class GuessProcessGroupQuestionFactory implements QuestionFactory<Process> {

		private final CplxProcessGroup			processGroup;
		private final List<CplxProcessGroup>	allProcessGroups;

		/**
		 * Constructor for {@link ProcessRelatedQuestionGenerator.GuessProcessGroupQuestionFactory}
		 */
		public GuessProcessGroupQuestionFactory(final CplxProcessGroup processGroup, final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			this.allProcessGroups = allProcessGroups;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator.QuestionFactory#createQuestionForProcess(generated
		 * .CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			final String qString = "To which process group does the process '" + drawnProcess.getName() + "' belong?";

			final Question question = QuestionUtil.createQuestion(qString, processGroup, allProcessGroups);
			return question;
		}

	}

	public static class GuessPreviousProcessQuestionFactory implements QuestionFactory<Process> {
		private final List<Process>	allProcesses;

		public GuessPreviousProcessQuestionFactory(final List<Process> allProcesses) {
			this.allProcesses = allProcesses;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator.QuestionFactory#createQuestionForProcess(generated
		 * .CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			Question result;
			int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn <= 0) {
				result = null;
			} else {
				final String qString = "Which process is the direct predecessor of " + drawnProcess.getName() + "?";
				Process correctAnswer = allProcesses.get(indexOfDrawn - 1);
				result = QuestionUtil.createQuestion(qString, correctAnswer, allProcesses);
			}
			return result;
		}

	}

	public static class GuessNextProcessQuestionFactory implements QuestionFactory<Process> {
		private final List<Process>	allProcesses;

		public GuessNextProcessQuestionFactory(final List<Process> allProcesses) {
			this.allProcesses = allProcesses;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator.QuestionFactory#createQuestionForProcess(generated
		 * .CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			Question result;
			int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn < 0 || indexOfDrawn > allProcesses.size() - 2) {
				result = null;
			} else {
				final String qString = "Which process is the direct successor of " + drawnProcess.getName() + "?";
				Process correctAnswer = allProcesses.get(indexOfDrawn + 1);
				result = QuestionUtil.createQuestion(qString, correctAnswer, allProcesses);
			}
			return result;
		}

	}

	public static class GuessProcessOfProcessGroup implements QuestionFactory<Process> {

		private final CplxProcessGroup	processGroup;
		private final List<Process>		allProcessesNotInThisProcessGroup;

		public GuessProcessOfProcessGroup(final CplxProcessGroup processGroup, final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			List<Process> tempAllProcessesNotInThisProcessGroup = new LinkedList<>();
			for (CplxProcessGroup cplxProcessGroup : allProcessGroups) {
				if (cplxProcessGroup != processGroup) {
					tempAllProcessesNotInThisProcessGroup.addAll(cplxProcessGroup.getProcess());
				}
			}
			allProcessesNotInThisProcessGroup = new ArrayList<>(tempAllProcessesNotInThisProcessGroup);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			final String qString = "Which process is in the " + processGroup.getName() + "?";
			return QuestionUtil.createQuestion(qString, drawnProcess, allProcessesNotInThisProcessGroup);
		}

	}

}
