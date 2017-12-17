package de.neuenberger.pmp.processes.generator;

import java.util.List;

/**
 * Container for drawing the questions.
 * 
 * @author Michael Kirchmann
 *
 * @param <Q> Type of the question
 */
public interface QuestionContainer<Q> {
	/**
	 * 
	 * @return Returns all Questions.
	 */
	public List<Q> getAllQuestions();
	
	public String getName();
}
