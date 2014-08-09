/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxNamed;
import generated.CplxProcess;
import generated.CplxProcessGroup;
import generated.CplxProcessParameters;
import generated.CplxProcessResult;
import generated.CplxToolOrTechnique;

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
	QuestionFactory<CplxProcess> questionFactory;

	ProcessRelatedQuestionGenerator(final CplxProcessGroup processGroup,
			final QuestionFactory<CplxProcess> questionFactory) {
		super(questionFactory.toString());
		this.processGroup = processGroup;
		this.questionFactory = questionFactory;
	}

	/**
	 * @return
	 */
	@Override
	protected List<Question> createQuestions() {
		final List<CplxProcess> process = processGroup.getProcess();
		final List<Question> result = new ArrayList<>(process.size());
		for (final CplxProcess p : process) {
			questionFactory.createQuestionForProcess(p, result);
		}
		return result;
	}

	public static class GuessProcessGroupQuestionFactory implements
			QuestionFactory<CplxProcess> {

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
					"To Which Process group does the CplxProcess belong ('"
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
		public void createQuestionForProcess(final CplxProcess drawnProcess,
				final List<Question> list) {
			final String qString = "To Which Process group does the CplxProcess '"
					+ drawnProcess.getName() + "' belong?";

			final Question question = QuestionUtil.createQuestion(
					questionGroup, qString, processGroup, allProcessGroups);
			list.add(question);
		}

		@Override
		public String toString() {
			return "Guess CplxProcess group for " + processGroup.getName();
		}

	}

	public static class GuessPreviousProcessQuestionFactory implements
			QuestionFactory<CplxProcess> {
		private final List<CplxProcess> allProcesses;
		private final DefaultQuestionGroup questionGroup;

		public GuessPreviousProcessQuestionFactory(
				final List<CplxProcess> allProcesses) {
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
		public void createQuestionForProcess(final CplxProcess drawnProcess,
				final List<Question> list) {
			final int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn <= 0) {
			} else {
				final String qString = "Which Process is the direct predecessor of "
						+ drawnProcess.getName() + "?";
				final CplxProcess correctAnswer = allProcesses
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
			QuestionFactory<CplxProcess> {
		private final List<CplxProcess> allProcesses;
		private final DefaultQuestionGroup questionGroup;

		public GuessNextProcessQuestionFactory(
				final List<CplxProcess> allProcesses) {
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
		public void createQuestionForProcess(final CplxProcess drawnProcess,
				final List<Question> list) {
			final int indexOfDrawn = allProcesses.indexOf(drawnProcess);
			if (indexOfDrawn < 0 || indexOfDrawn > allProcesses.size() - 2) {
			} else {
				final String qString = "Which Process is the direct successor of "
						+ drawnProcess.getName() + "?";
				final CplxProcess correctAnswer = allProcesses
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

	public static List<CplxProcess> produceAllProcessNotInThisProcessGroup(
			final CplxProcessGroup processGroup,
			final List<CplxProcessGroup> allProcessGroups) {
		final List<CplxProcess> tempAllProcessesNotInThisProcessGroup = new LinkedList<>();
		for (final CplxProcessGroup cplxProcessGroup : allProcessGroups) {
			if (cplxProcessGroup != processGroup) {
				tempAllProcessesNotInThisProcessGroup.addAll(cplxProcessGroup
						.getProcess());
			}
		}
		return new ArrayList<>(tempAllProcessesNotInThisProcessGroup);
	}

	public static class GuessProcessOfProcessGroup implements
			QuestionFactory<CplxProcess> {

		private final CplxProcessGroup processGroup;
		private final List<CplxProcess> allProcessesNotInThisProcessGroup;
		private final DefaultQuestionGroup questionGroup;

		public GuessProcessOfProcessGroup(final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			allProcessesNotInThisProcessGroup = produceAllProcessNotInThisProcessGroup(
					processGroup, allProcessGroups);
			questionGroup = new DefaultQuestionGroup("Which Process is in "
					+ processGroup.getName());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#
		 * createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public void createQuestionForProcess(final CplxProcess drawnProcess,
				final List<Question> list) {
			final String qString = "Which Process is in the "
					+ processGroup.getName() + "?";
			list.add(QuestionUtil.createQuestion(questionGroup, qString,
					drawnProcess, allProcessesNotInThisProcessGroup));
		}

		@Override
		public String toString() {
			return "Guess CplxProcess in CplxProcess Group "
					+ processGroup.getName();
		}

	}

	public static class GuessInputOutputOfProcess implements
			QuestionFactory<CplxProcess> {

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
		public void createQuestionForProcess(final CplxProcess drawnProcess,
				final List<Question> list) {

			final List<CplxProcess> process = processGroup.getProcess();
			if (drawnProcess.getProcessParameters() != null) {
				final AcquireInputsOutputsAndToolsOrTechniques acq = new AcquireInputsOutputsAndToolsOrTechniques(
						process, drawnProcess);
				acq.createQuestions(list);
			} else {
				for (final CplxProcess cplxProcess : drawnProcess
						.getSubProcess()) {
					createQuestionForProcess(cplxProcess, list);
				}
			}
		}

		public class AcquireInputsOutputsAndToolsOrTechniques {
			private final List<CplxProcessResult> allWrongInputAnswers = new ArrayList<>();
			private final List<CplxProcessResult> allWrongOutputAnswers = new ArrayList<>();
			private final List<CplxToolOrTechnique> allWrongToolOrTechniqueAnswers = new ArrayList<>();
			private final List<CplxProcessResult> input;
			private final List<CplxProcessResult> output;
			private final List<CplxToolOrTechnique> toolsOrTechniques;
			private final CplxProcess drawnProcess;
			private final List<CplxProcess> processes;

			/**
			 * 
			 */
			public AcquireInputsOutputsAndToolsOrTechniques(
					final List<CplxProcess> processes,
					final CplxProcess drawnProcess) {
				this.processes = processes;
				this.drawnProcess = drawnProcess;
				final CplxProcessParameters drawnProcessParameters = drawnProcess
						.getProcessParameters();
				input = drawnProcessParameters.getInput();
				output = drawnProcessParameters.getInput();
				toolsOrTechniques = drawnProcessParameters.getToolOrTechnique();
				collectAllInformation(processes);
			}

			/**
			 * 
			 */
			public void createQuestions(final List<Question> list) {
				createInputOutputQuestion(drawnProcess, list, input,
						allWrongInputAnswers, "input");
				createInputOutputQuestion(drawnProcess, list, output,
						allWrongOutputAnswers, "output");
				createInputOutputQuestion(drawnProcess, list,
						toolsOrTechniques, allWrongToolOrTechniqueAnswers,
						"tool or technique");
			}

			private void collectAllInformation(final List<CplxProcess> process) {
				for (final CplxProcess p : process) {
					if (p != drawnProcess) {
						final CplxProcessParameters pp = p
								.getProcessParameters();
						if (pp != null) {
							allWrongInputAnswers.addAll(CplxNamedUtil
									.addAllWithNameNot(input, pp.getInput()));
							allWrongOutputAnswers.addAll(CplxNamedUtil
									.addAllWithNameNot(output, pp.getOutput()));
							allWrongToolOrTechniqueAnswers.addAll(CplxNamedUtil
									.addAllWithNameNot(toolsOrTechniques,
											pp.getToolOrTechnique()));
						} else {
							collectAllInformation(p.getSubProcess());
						}
					}
				}
			}
		}

		private <P extends CplxNamed> void createInputOutputQuestion(
				final CplxProcess drawnProcess, final List<Question> list,
				final List<P> input, final List<P> allWrongAnswers,
				final String type) {
			if (allWrongAnswers.size() > 3) {
				for (final P cplxInputOutput : input) {
					final String qStringI = "Which is an " + type + " to the '"
							+ drawnProcess.getName() + "' process?";
					list.add(QuestionUtil.createQuestion(questionGroup,
							qStringI, cplxInputOutput, allWrongAnswers));
				}
			}
			if (input.size() > 3) {
				// you can create NOT questions
				for (final P cplxInputOutput : allWrongAnswers) {
					final String qStringI = "Which is an " + type + " to the '"
							+ drawnProcess.getName() + "' process?";
					list.add(QuestionUtil.createQuestion(questionGroup,
							qStringI, cplxInputOutput, input));
				}
			}
		}

		@Override
		public String toString() {
			return "Guess Input/Output in CplxProcess Group "
					+ processGroup.getName();
		}

	}

	public static class GuessProcessNotInThisProcessGroup implements
			QuestionFactory<CplxProcess> {

		private final CplxProcessGroup processGroup;
		private final List<CplxProcess> allProcessesNotInThisProcessGroup;
		private final DefaultQuestionGroup questionGroup;

		public GuessProcessNotInThisProcessGroup(
				final CplxProcessGroup processGroup,
				final List<CplxProcessGroup> allProcessGroups) {
			this.processGroup = processGroup;
			allProcessesNotInThisProcessGroup = produceAllProcessNotInThisProcessGroup(
					processGroup, allProcessGroups);
			questionGroup = new DefaultQuestionGroup(
					"Which Process is NOT in the " + processGroup.getName()
							+ "?");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.neuenberger.pmp.processes.generator.QuestionFactory#
		 * createQuestionForProcess(generated.CplxNamed)
		 */
		@Override
		public void createQuestionForProcess(final CplxProcess drawnProcess,
				final List<Question> list) {
			final CplxProcess processNotHere = RandomDrawer
					.drawRandomSingle(allProcessesNotInThisProcessGroup);
			final String qString = "Which Process is NOT in the "
					+ processGroup.getName() + "?";
			list.add(QuestionUtil.createQuestion(questionGroup, qString,
					processNotHere, processGroup.getProcess()));
		}

		@Override
		public String toString() {
			return "Guess CplxProcess not in CplxProcess Group "
					+ processGroup.getName();
		}

	}
}
