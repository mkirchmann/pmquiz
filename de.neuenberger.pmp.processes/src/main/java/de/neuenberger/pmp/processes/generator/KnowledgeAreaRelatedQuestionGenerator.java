/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import generated.CplxKnowledgeArea;
import generated.CplxProcess;
import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class KnowledgeAreaRelatedQuestionGenerator extends AbstractLazyQuestionContainer  {
	CplxKnowledgeArea	knowledgeArea;
	private QuestionFactory<CplxProcess> questionFactory;

	public KnowledgeAreaRelatedQuestionGenerator(final CplxKnowledgeArea knowledgeArea, QuestionFactory<CplxProcess> questionFactory) {
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
		List<CplxProcess> process = knowledgeArea.getProcess();
		List<Question> lAllQuestions = new ArrayList<>(process.size());
		for (CplxProcess cplxProcess : process) {
			Question question = questionFactory.createQuestionForProcess(cplxProcess);
			if (question!=null) {
				lAllQuestions.add(question);
			}
		}
		return lAllQuestions;
	}

	public static class GuessKnowledgeArea implements QuestionFactory<CplxProcess> {
		private CplxKnowledgeArea knowledgeArea;
		private List<CplxProcess> allNonCommonProcesses;
		public GuessKnowledgeArea(CplxKnowledgeArea	knowledgeArea, List<CplxKnowledgeArea> allKnowledgeAreas) {
			this.knowledgeArea = knowledgeArea;
			
			List<CplxKnowledgeArea> allExceptCurrentKnowledgeAreas = new LinkedList<>(allKnowledgeAreas);
			allExceptCurrentKnowledgeAreas.remove(knowledgeArea);
			
			allNonCommonProcesses = new LinkedList<>();
			for (CplxKnowledgeArea cplxKnowledgeArea : allExceptCurrentKnowledgeAreas) {
				allNonCommonProcesses.addAll(cplxKnowledgeArea.getProcess());
				allNonCommonProcesses.removeAll(knowledgeArea.getProcess()); // remove all processes common to this and other knowledge areas.
			}
		}
		@Override
		public Question createQuestionForProcess(CplxProcess drawnProcess) {
			String qString = "Which process belongs to the knowledgearea "+knowledgeArea.getName()+"?";
			return QuestionUtil.createQuestion(qString, drawnProcess, allNonCommonProcesses);
		}
		
		@Override
		public String toString() {
			return "Guess Knowledge Area for "+knowledgeArea.getName();
		}
		
	}
}
