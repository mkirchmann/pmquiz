package de.neuenberger.pmp.processes.generator;

import generated.CplxDefinition;
import generated.CplxGroup;
import generated.CplxKnowledgeArea;

import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.generator.QuestionUtil.LabelProvider;
import de.neuenberger.pmp.processes.model.DefaultQuestionGroup;
import de.neuenberger.pmp.processes.model.Question;

public class KnowledgeAreaReleatedAdditionalQuestionsGenerator extends
		AbstractLazyQuestionContainer {
	private final CplxKnowledgeArea knowledgeArea;
	private final DefaultQuestionGroup questionGroup;

	public KnowledgeAreaReleatedAdditionalQuestionsGenerator(
			final CplxKnowledgeArea knowledgeArea) {
		super("Definitions for " + knowledgeArea.getName());
		this.knowledgeArea = knowledgeArea;
		questionGroup = new DefaultQuestionGroup(getName());
	}

	@Override
	protected List<Question> createQuestions() {
		final List<Question> questions = new LinkedList<>();
		if (knowledgeArea.getAddition() != null) {
			final List<CplxGroup> groups = knowledgeArea.getAddition()
					.getGroup();
			for (final CplxGroup cplxGroup : groups) {
				questions.addAll(createQuestionForProcess(cplxGroup));
			}
		}
		return questions;
	}

	public List<Question> createQuestionForProcess(final CplxGroup drawnProcess) {
		final List<CplxDefinition> definition = drawnProcess.getDefinition();
		final DefinitionDefinitionLabelProvider provider = new DefinitionDefinitionLabelProvider();

		final List<Question> result = new LinkedList<>();
		for (final CplxDefinition cplxDefinition : definition) {
			if (!Boolean.TRUE.equals(cplxDefinition.isIgnoreAsQuestion())) {
				result.add(QuestionUtil.createQuestion(
						questionGroup,
						"Looking at " + drawnProcess.getName()
								+ ", what is defined by '"
								+ cplxDefinition.getDescription() + "'?",
						cplxDefinition, definition));
				result.add(QuestionUtil.createQuestion(
						questionGroup,
						"Looking at " + drawnProcess.getName()
								+ ", what is defining '"
								+ cplxDefinition.getName() + "'?",
						cplxDefinition, definition, provider));
			}
		}

		return result;
	}

	public static class DefinitionDefinitionLabelProvider implements
			LabelProvider<CplxDefinition> {

		@Override
		public String getLabel(final CplxDefinition e) {
			return e.getDescription();
		}

	}
}
