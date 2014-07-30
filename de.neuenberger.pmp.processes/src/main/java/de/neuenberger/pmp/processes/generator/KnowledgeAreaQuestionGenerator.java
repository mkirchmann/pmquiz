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
public class KnowledgeAreaQuestionGenerator implements QuestionGenerator {
	CplxKnowledgeArea knowledgeArea;

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
	 * de.neuenberger.pmp.processes.generator.QuestionGenerator#generateQuestion
	 * ()
	 */
	@Override
	public Question generateQuestion() {
		// TODO Auto-generated method stub
		return null;
	}
}
