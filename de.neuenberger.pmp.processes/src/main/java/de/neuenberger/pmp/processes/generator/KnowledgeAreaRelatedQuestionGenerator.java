/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxKnowledgeArea;
import generated.CplxProcess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.model.Question;
import de.neuenberger.pmp.processes.model.DefaultQuestionGroup;

/**
 * @author Michael Kirchmann
 * 
 */
public class KnowledgeAreaRelatedQuestionGenerator extends
		AbstractLazyQuestionContainer {
	CplxKnowledgeArea knowledgeArea;
	private final QuestionFactory<CplxProcess> questionFactory;

	public KnowledgeAreaRelatedQuestionGenerator(
			final CplxKnowledgeArea knowledgeArea,
			final QuestionFactory<CplxProcess> questionFactory) {
		super(questionFactory.toString());
		this.knowledgeArea = knowledgeArea;
		this.questionFactory = questionFactory;
	}

	/**
	 * @return the knowledgeArea
	 */
	public CplxKnowledgeArea getKnowledgeArea() {
		return knowledgeArea;
	}

	@Override
	protected List<Question> createQuestions() {
		final List<CplxProcess> process = knowledgeArea.getProcess();
		final List<Question> lAllQuestions = new ArrayList<>(process.size());
		for (final CplxProcess cplxProcess : process) {
			final Question question = questionFactory
					.createQuestionForProcess(cplxProcess);
			if (question != null) {
				lAllQuestions.add(question);
			}
		}
		return lAllQuestions;
	}

	public static class GuessKnowledgeArea implements
			QuestionFactory<CplxProcess> {
		private final CplxKnowledgeArea knowledgeArea;
		private final List<CplxProcess> allNonCommonProcesses;
		private final DefaultQuestionGroup questionGroup;

		public GuessKnowledgeArea(final CplxKnowledgeArea knowledgeArea,
				final List<CplxKnowledgeArea> allKnowledgeAreas) {
			this.knowledgeArea = knowledgeArea;
			questionGroup = new DefaultQuestionGroup(
					"Which process belongs to the knowledgearea "
							+ knowledgeArea.getName() + "?");
			final List<CplxKnowledgeArea> allExceptCurrentKnowledgeAreas = new LinkedList<>(
					allKnowledgeAreas);
			allExceptCurrentKnowledgeAreas.remove(knowledgeArea);

			allNonCommonProcesses = new LinkedList<>();
			for (final CplxKnowledgeArea cplxKnowledgeArea : allExceptCurrentKnowledgeAreas) {
				allNonCommonProcesses.addAll(cplxKnowledgeArea.getProcess());
				allNonCommonProcesses.removeAll(knowledgeArea.getProcess()); // remove
																				// all
																				// processes
																				// common
																				// to
																				// this
																				// and
																				// other
																				// knowledge
																				// areas.
			}
		}

		@Override
		public Question createQuestionForProcess(final CplxProcess drawnProcess) {
			final String qString = "Which process belongs to the knowledgearea "
					+ knowledgeArea.getName() + "?";
			return QuestionUtil.createQuestion(questionGroup, qString,
					drawnProcess, allNonCommonProcesses);
		}

		@Override
		public String toString() {
			return "Guess Knowledge Area for " + knowledgeArea.getName();
		}

	}
}
