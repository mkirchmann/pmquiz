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
import de.neuenberger.pmp.processes.model.QuestionGroup;

/**
 * @author Michael Kirchmann
 * 
 */
public class ProcessRelatedQuestionGenerator extends
		AbstractLazyQuestionContainer {
	private final CplxProcessGroup processGroup;
	QuestionFactory<Process> questionFactory;

	ProcessRelatedQuestionGenerator(final CplxProcessGroup processGroup,
			final QuestionFactory<Process> questionFactory) {
		super(questionFactory.toString());
		this.processGroup = processGroup;
		this.questionFactory = questionFactory;
	}

	/**
	 * @return
	 */
	@Override
	protected List<Question> createQuestions() {
		final List<Process> process = processGroup.getProcess();
		final List<Question> result = new ArrayList<>(process.size());
		for (final Process p : process) {
			final Question question = questionFactory
					.createQuestionForProcess(p);
			if (question != null) {
				result.add(question);
			}
		}
		return result;
	}

	public static class GuessProcessGroupQuestionFactory implements
			QuestionFactory<Process> {

		private final CplxProcessGroup processGroup;
		private final List<CplxProcessGroup> allProcessGroups;
		private final QuestionGroup questionGroup;

		/**
		 * Constructor for
		 * {@link ProcessRelatedQuestionGenerator.GuessProcessGroupQuestionFactory}
		 */
		public GuessProcessGroupQuestionFactory(
				final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			this.allProcessGroups = allProcessGroups;

			questionGroup = new QuestionGroup(
					"To which process group does the process belong ('"
							+ processGroup.getName() + "')");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator
		 * .QuestionFactory#createQuestionForProcess(generated .CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			final String qString = "To which process group does the process '"
					+ drawnProcess.getName() + "' belong?";

			final Question question = QuestionUtil.createQuestion(
					questionGroup, qString, processGroup, allProcessGroups);
			return question;
		}

		@Override
		public String toString() {
			return "Guess Process group for " + processGroup.getName();
		}

	}

	public static class GuessPreviousProcessQuestionFactory implements
			QuestionFactory<Process> {
		private final List<Process> allProcesses;
		private final QuestionGroup questionGroup;

		public GuessPreviousProcessQuestionFactory(
				final List<Process> allProcesses) {
			this.allProcesses = allProcesses;
			questionGroup = new QuestionGroup("Previous process");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator
		 * .QuestionFactory#createQuestionForProcess(generated .CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			Question result;
			final int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn <= 0) {
				result = null;
			} else {
				final String qString = "Which process is the direct predecessor of "
						+ drawnProcess.getName() + "?";
				final Process correctAnswer = allProcesses
						.get(indexOfDrawn - 1);
				result = QuestionUtil.createQuestion(questionGroup, qString,
						correctAnswer, allProcesses);
			}
			return result;
		}

		@Override
		public String toString() {
			return "Guess Previous Process";
		}

	}

	public static class GuessNextProcessQuestionFactory implements
			QuestionFactory<Process> {
		private final List<Process> allProcesses;
		private final QuestionGroup questionGroup;

		public GuessNextProcessQuestionFactory(final List<Process> allProcesses) {
			this.allProcesses = allProcesses;
			questionGroup = new QuestionGroup("Next process");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator
		 * .QuestionFactory#createQuestionForProcess(generated .CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			Question result;
			final int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn < 0 || indexOfDrawn > allProcesses.size() - 2) {
				result = null;
			} else {
				final String qString = "Which process is the direct successor of "
						+ drawnProcess.getName() + "?";
				final Process correctAnswer = allProcesses
						.get(indexOfDrawn + 1);
				result = QuestionUtil.createQuestion(questionGroup, qString,
						correctAnswer, allProcesses);
			}
			return result;
		}

		@Override
		public String toString() {
			return "Guess Next Process";
		}

	}

	public static List<Process> produceAllProcessNotInThisProcessGroup(
			final CplxProcessGroup processGroup,
			final List<CplxProcessGroup> allProcessGroups) {
		final List<Process> tempAllProcessesNotInThisProcessGroup = new LinkedList<>();
		for (final CplxProcessGroup cplxProcessGroup : allProcessGroups) {
			if (cplxProcessGroup != processGroup) {
				tempAllProcessesNotInThisProcessGroup.addAll(cplxProcessGroup
						.getProcess());
			}
		}
		return new ArrayList<>(tempAllProcessesNotInThisProcessGroup);
	}

	public static class GuessProcessOfProcessGroup implements
			QuestionFactory<Process> {

		private final CplxProcessGroup processGroup;
		private final List<Process> allProcessesNotInThisProcessGroup;
		private final QuestionGroup questionGroup;

		public GuessProcessOfProcessGroup(final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			allProcessesNotInThisProcessGroup = produceAllProcessNotInThisProcessGroup(
					processGroup, allProcessGroups);
			questionGroup = new QuestionGroup("Which process is in "
					+ processGroup.getName());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#
		 * createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			final String qString = "Which process is in the "
					+ processGroup.getName() + "?";
			return QuestionUtil.createQuestion(questionGroup, qString,
					drawnProcess, allProcessesNotInThisProcessGroup);
		}

		@Override
		public String toString() {
			return "Guess Process in Process Group " + processGroup.getName();
		}

	}

	public static class GuessProcessNotInThisProcessGroup implements
			QuestionFactory<Process> {

		private final CplxProcessGroup processGroup;
		private final List<Process> allProcessesNotInThisProcessGroup;
		private final QuestionGroup questionGroup;

		public GuessProcessNotInThisProcessGroup(
				final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			allProcessesNotInThisProcessGroup = produceAllProcessNotInThisProcessGroup(
					processGroup, allProcessGroups);
			questionGroup = new QuestionGroup("Which process is NOT in the "
					+ processGroup.getName() + "?");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#
		 * createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public Question createQuestionForProcess(final Process drawnProcess) {
			final Process processNotHere = RandomDrawer
					.drawRandomSingle(allProcessesNotInThisProcessGroup);
			final String qString = "Which process is NOT in the "
					+ processGroup.getName() + "?";
			return QuestionUtil.createQuestion(questionGroup, qString,
					processNotHere, processGroup.getProcess());
		}

		@Override
		public String toString() {
			return "Guess Process not in Process Group "
					+ processGroup.getName();
		}

	}
}
