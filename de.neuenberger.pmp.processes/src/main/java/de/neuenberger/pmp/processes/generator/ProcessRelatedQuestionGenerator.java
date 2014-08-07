/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxInputOutput;
import generated.CplxProcessGroup;
import generated.CplxProcessGroup.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.model.DefaultQuestionGroup;
import de.neuenberger.pmp.processes.model.Question;

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
			questionFactory.createQuestionForProcess(p, result);
		}
		return result;
	}

	public static class GuessProcessGroupQuestionFactory implements
			QuestionFactory<Process> {

		private final CplxProcessGroup processGroup;
		private final List<CplxProcessGroup> allProcessGroups;
		private final DefaultQuestionGroup questionGroup;

		/**
		 * Constructor for
		 * {@link ProcessRelatedQuestionGenerator.GuessProcessGroupQuestionFactory}
		 */
		public GuessProcessGroupQuestionFactory(
				final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			this.allProcessGroups = allProcessGroups;

			questionGroup = new DefaultQuestionGroup(
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
		public void createQuestionForProcess(final Process drawnProcess,
				final List<Question> list) {
			final String qString = "To which process group does the process '"
					+ drawnProcess.getName() + "' belong?";

			final Question question = QuestionUtil.createQuestion(
					questionGroup, qString, processGroup, allProcessGroups);
			list.add(question);
		}

		@Override
		public String toString() {
			return "Guess Process group for " + processGroup.getName();
		}

	}

	public static class GuessPreviousProcessQuestionFactory implements
			QuestionFactory<Process> {
		private final List<Process> allProcesses;
		private final DefaultQuestionGroup questionGroup;

		public GuessPreviousProcessQuestionFactory(
				final List<Process> allProcesses) {
			this.allProcesses = allProcesses;
			questionGroup = new DefaultQuestionGroup("Previous process");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator
		 * .QuestionFactory#createQuestionForProcess(generated .CplxNamed)
		 */
		@Override
		public void createQuestionForProcess(final Process drawnProcess,
				final List<Question> list) {
			final int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn <= 0) {
			} else {
				final String qString = "Which process is the direct predecessor of "
						+ drawnProcess.getName() + "?";
				final Process correctAnswer = allProcesses
						.get(indexOfDrawn - 1);
				Question result;
				result = QuestionUtil.createQuestion(questionGroup, qString,
						correctAnswer, allProcesses);
				list.add(result);
			}
		}

		@Override
		public String toString() {
			return "Guess Previous Process";
		}

	}

	public static class GuessNextProcessQuestionFactory implements
			QuestionFactory<Process> {
		private final List<Process> allProcesses;
		private final DefaultQuestionGroup questionGroup;

		public GuessNextProcessQuestionFactory(final List<Process> allProcesses) {
			this.allProcesses = allProcesses;
			questionGroup = new DefaultQuestionGroup("Next process");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.neuenberger.pmp.processes.generator.ProcessRelatedQuestionGenerator
		 * .QuestionFactory#createQuestionForProcess(generated .CplxNamed)
		 */
		@Override
		public void createQuestionForProcess(final Process drawnProcess,
				final List<Question> list) {
			final int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn < 0 || indexOfDrawn > allProcesses.size() - 2) {
			} else {
				final String qString = "Which process is the direct successor of "
						+ drawnProcess.getName() + "?";
				final Process correctAnswer = allProcesses
						.get(indexOfDrawn + 1);
				final Question result = QuestionUtil.createQuestion(
						questionGroup, qString, correctAnswer, allProcesses);
				list.add(result);
			}
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
		private final DefaultQuestionGroup questionGroup;

		public GuessProcessOfProcessGroup(final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			allProcessesNotInThisProcessGroup = produceAllProcessNotInThisProcessGroup(
					processGroup, allProcessGroups);
			questionGroup = new DefaultQuestionGroup("Which process is in "
					+ processGroup.getName());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#
		 * createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public void createQuestionForProcess(final Process drawnProcess,
				final List<Question> list) {
			final String qString = "Which process is in the "
					+ processGroup.getName() + "?";
			list.add(QuestionUtil.createQuestion(questionGroup, qString,
					drawnProcess, allProcessesNotInThisProcessGroup));
		}

		@Override
		public String toString() {
			return "Guess Process in Process Group " + processGroup.getName();
		}

	}

	public static class GuessInputOutputOfProcess implements
			QuestionFactory<Process> {

		private final CplxProcessGroup processGroup;
		private final DefaultQuestionGroup questionGroup;

		public GuessInputOutputOfProcess(final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			questionGroup = new DefaultQuestionGroup("Which input/output"
					+ processGroup.getName());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#
		 * createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public void createQuestionForProcess(final Process drawnProcess,
				final List<Question> list) {
			final List<CplxInputOutput> input = drawnProcess.getInput();
			final List<CplxInputOutput> output = drawnProcess.getInput();
			final List<Process> process = processGroup.getProcess();
			final List<CplxInputOutput> allWrongInputAnswers = new ArrayList<>();
			final List<CplxInputOutput> allWrongOutputAnswers = new ArrayList<>();
			for (final Process p : process) {
				if (p != drawnProcess) {
					allWrongInputAnswers.addAll(CplxNamedUtil
							.addAllWithNameNot(input, p.getInput()));
					allWrongInputAnswers.addAll(CplxNamedUtil
							.addAllWithNameNot(input, p.getOutput()));
				}
			}
			createInputOutputQuestion(drawnProcess, list, input,
					allWrongInputAnswers, "input");
			createInputOutputQuestion(drawnProcess, list, output,
					allWrongOutputAnswers, "output");
		}

		private void createInputOutputQuestion(final Process drawnProcess,
				final List<Question> list, final List<CplxInputOutput> input,
				final List<CplxInputOutput> allWrongInputAnswers,
				final String type) {
			if (allWrongInputAnswers.size() > 3) {
				for (final CplxInputOutput cplxInputOutput : input) {
					final String qStringI = "Which is an " + type + " to the '"
							+ drawnProcess.getName() + "' process?";
					list.add(QuestionUtil.createQuestion(questionGroup,
							qStringI, cplxInputOutput, allWrongInputAnswers));
				}
			}
			if (input.size() > 3) {
				// you can create NOT questions
				for (final CplxInputOutput cplxInputOutput : allWrongInputAnswers) {
					final String qStringI = "Which is an " + type + " to the '"
							+ drawnProcess.getName() + "' process?";
					list.add(QuestionUtil.createQuestion(questionGroup,
							qStringI, cplxInputOutput, input));
				}
			}
		}

		@Override
		public String toString() {
			return "Guess Input/Output in Process Group "
					+ processGroup.getName();
		}

	}

	public static class GuessProcessNotInThisProcessGroup implements
			QuestionFactory<Process> {

		private final CplxProcessGroup processGroup;
		private final List<Process> allProcessesNotInThisProcessGroup;
		private final DefaultQuestionGroup questionGroup;

		public GuessProcessNotInThisProcessGroup(
				final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			allProcessesNotInThisProcessGroup = produceAllProcessNotInThisProcessGroup(
					processGroup, allProcessGroups);
			questionGroup = new DefaultQuestionGroup(
					"Which process is NOT in the " + processGroup.getName()
							+ "?");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#
		 * createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public void createQuestionForProcess(final Process drawnProcess,
				final List<Question> list) {
			final Process processNotHere = RandomDrawer
					.drawRandomSingle(allProcessesNotInThisProcessGroup);
			final String qString = "Which process is NOT in the "
					+ processGroup.getName() + "?";
			list.add(QuestionUtil.createQuestion(questionGroup, qString,
					processNotHere, processGroup.getProcess()));
		}

		@Override
		public String toString() {
			return "Guess Process not in Process Group "
					+ processGroup.getName();
		}

	}
}
