/**
 *
 */
package de.neuenberger.pmp.processes.generator;

import java.util.List;

import de.neuenberger.pmp.processes.model.Question;

/**
 * Class for ...
 * 
 * @author Michael Kirchmann
 */
public abstract class AbstractLazyQuestionContainer implements QuestionContainer {
	private List<Question>	allQuestions;

	private final String name;
	public AbstractLazyQuestionContainer(String name) {
		this.name = name;
	}
	
	public List<Question> getAllQuestions() {
		if (allQuestions == null) {
			allQuestions = createQuestions();
		}
		return allQuestions;
	}

	/**
	 * @return Returns all created questions.
	 */
	abstract protected List<Question> createQuestions();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
