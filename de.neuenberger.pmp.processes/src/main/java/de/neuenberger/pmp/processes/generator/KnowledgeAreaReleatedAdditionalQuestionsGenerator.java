package de.neuenberger.pmp.processes.generator;

import generated.CplxDefinition;
import generated.CplxGroup;
import generated.CplxKnowledgeArea;

import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.generator.QuestionUtil.LabelProvider;
import de.neuenberger.pmp.processes.model.Question;
import de.neuenberger.pmp.processes.model.DefaultQuestionGroup;

public class KnowledgeAreaReleatedAdditionalQuestionsGenerator extends AbstractLazyQuestionContainer{
	private final CplxKnowledgeArea knowledgeArea;
	private DefaultQuestionGroup questionGroup;
	public KnowledgeAreaReleatedAdditionalQuestionsGenerator(CplxKnowledgeArea knowledgeArea) {
		super("Definitions for "+knowledgeArea.getName());
		this.knowledgeArea = knowledgeArea;
		questionGroup = new DefaultQuestionGroup(getName());
	}

	
	@Override
	protected List<Question> createQuestions() {
		List<Question> questions = new LinkedList<>();
		if (knowledgeArea.getAddition()!=null) {
			List<CplxGroup> groups = knowledgeArea.getAddition().getGroup();
			for (CplxGroup cplxGroup : groups) {
				questions.addAll(createQuestionForProcess(cplxGroup));
			}
		}
		return questions;
	}
	
	public List<Question> createQuestionForProcess(CplxGroup drawnProcess) {
		List<CplxDefinition> definition = drawnProcess.getDefinition();
		DefinitionDefinitionLabelProvider provider = new DefinitionDefinitionLabelProvider();
		
		List<Question> result = new LinkedList<>();
		for (CplxDefinition cplxDefinition : definition) {
			result.add(QuestionUtil.createQuestion(questionGroup, "Looking at "+drawnProcess.getName()+", what is defined by '"+cplxDefinition.getDescription()+"'?", cplxDefinition, definition));
			result.add(QuestionUtil.createQuestion(questionGroup, "Looking at "+drawnProcess.getName()+", what is defining '"+cplxDefinition.getName()+"'?", cplxDefinition, definition, provider));
		}
		
		return result;
	}
	
	public static class DefinitionDefinitionLabelProvider implements LabelProvider<CplxDefinition> {

		@Override
		public String getLabel(CplxDefinition e) {
			return e.getDescription();
		}
		
	}
}
