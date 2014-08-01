package de.neuenberger.pmp.processes.generator;

import java.util.List;

import de.neuenberger.pmp.processes.model.Question;

public interface QuestionContainer {
	/**
	 * 
	 * @return Returns all Questions.
	 */
	public List<Question> getAllQuestions();
	
	public String getName();
}
