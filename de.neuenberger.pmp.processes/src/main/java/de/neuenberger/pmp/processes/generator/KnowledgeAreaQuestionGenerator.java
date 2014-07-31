/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxKnowledgeArea;
import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class KnowledgeAreaQuestionGenerator implements QuestionDrawer {
	CplxKnowledgeArea	knowledgeArea;

	public KnowledgeAreaQuestionGenerator(final CplxKnowledgeArea knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	/**
	 * @return the knowledgeArea
	 */
	public CplxKnowledgeArea getKnowledgeArea() {
		return knowledgeArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neuenberger.pmp.processes.generator.QuestionDrawer#generateQuestion
	 * ()
	 */
	@Override
	public Question drawQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

}
