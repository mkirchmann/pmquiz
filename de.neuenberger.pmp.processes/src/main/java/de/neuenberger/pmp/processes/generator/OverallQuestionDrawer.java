/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxKnowledgeArea;
import generated.CplxProcess;
import generated.CplxProcessGroup;
import generated.CplxProcessGroup.Process;
import generated.CplxProcessGroups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann
 * 
 */
public class OverallQuestionDrawer implements QuestionDrawer {
	private final CplxProcessGroups processGroups;
	private final List<QuestionContainer> generators = new ArrayList<>();

	private List<QuestionContainer> selectedContainers;
	private List<Question> selectedQuestions;

	public OverallQuestionDrawer(final CplxProcessGroups processGroups) {
		this.processGroups = processGroups;

		final List<CplxProcessGroup> processGroup = processGroups
				.getProcessGroup();
		for (final CplxProcessGroup cplxProcessGroup : processGroup) {
			final List<Process> allProcesses = cplxProcessGroup.getProcess();

			final QuestionFactory<Process> factory3 = new ProcessRelatedQuestionGenerator.GuessProcessGroupQuestionFactory(
					cplxProcessGroup, processGroup);
			final QuestionFactory<Process> factory4 = new ProcessRelatedQuestionGenerator.GuessProcessOfProcessGroup(
					cplxProcessGroup, processGroup);

			final QuestionFactory<Process> factory5 = new ProcessRelatedQuestionGenerator.GuessProcessNotInThisProcessGroup(
					cplxProcessGroup, processGroup);
			final QuestionFactory<Process> factory6 = new ProcessRelatedQuestionGenerator.GuessInputOutputOfProcess(
					cplxProcessGroup, processGroup);

			if (cplxProcessGroup.getName().contains("Plan")) {
				final QuestionFactory<Process> factory1 = new ProcessRelatedQuestionGenerator.GuessNextProcessQuestionFactory(
						allProcesses);
				final QuestionFactory<Process> factory2 = new ProcessRelatedQuestionGenerator.GuessPreviousProcessQuestionFactory(
						allProcesses);

				generators.add(new ProcessRelatedQuestionGenerator(
						cplxProcessGroup, factory1));
				generators.add(new ProcessRelatedQuestionGenerator(
						cplxProcessGroup, factory2));
			}

			generators.add(new ProcessRelatedQuestionGenerator(
					cplxProcessGroup, factory3));
			generators.add(new ProcessRelatedQuestionGenerator(
					cplxProcessGroup, factory4));
			generators.add(new ProcessRelatedQuestionGenerator(
					cplxProcessGroup, factory5));
			generators.add(new ProcessRelatedQuestionGenerator(
					cplxProcessGroup, factory6));

			selectedContainers = generators;
		}

		final List<CplxKnowledgeArea> knowledgeAreas = processGroups
				.getKnowledgeArea();
		for (final CplxKnowledgeArea cplxKnowledgeArea : knowledgeAreas) {
			final QuestionFactory<CplxProcess> questionFactory = new KnowledgeAreaRelatedQuestionGenerator.GuessKnowledgeArea(
					cplxKnowledgeArea, knowledgeAreas);
			generators.add(new KnowledgeAreaRelatedQuestionGenerator(
					cplxKnowledgeArea, questionFactory));
			generators
					.add(new KnowledgeAreaReleatedAdditionalQuestionsGenerator(
							cplxKnowledgeArea));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neuenberger.pmp.processes.generator.QuestionDrawer#generateQuestion ()
	 */
	@Override
	public Question drawQuestion() {
		return RandomDrawer.drawRandomSingle(getSelectedQuestions());
	}

	/**
	 * @return the generators
	 */
	public List<QuestionContainer> getGenerators() {
		return Collections.unmodifiableList(generators);
	}

	/**
	 * @return the selectedContainers
	 */
	public List<QuestionContainer> getSelectedContainers() {
		return selectedContainers;
	}

	/**
	 * @param selectedContainers
	 *            the selectedContainers to set
	 */
	public void setSelectedContainers(
			final List<QuestionContainer> selectedContainers) {
		this.selectedContainers = selectedContainers;
		selectedQuestions = null;
	}

	/**
	 * @return the selectedQuestions
	 */
	public List<Question> getSelectedQuestions() {
		if (selectedQuestions == null) {
			if (selectedContainers != null) {
				selectedQuestions = new LinkedList<>();
				for (final QuestionContainer container : selectedContainers) {
					selectedQuestions.addAll(container.getAllQuestions());
				}
			} else {
				selectedQuestions = Collections.emptyList();
			}
		}
		return selectedQuestions;
	}

}
