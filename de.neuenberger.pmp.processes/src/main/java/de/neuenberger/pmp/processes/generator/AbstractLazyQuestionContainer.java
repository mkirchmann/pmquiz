/**
 *
 */
package de.neuenberger.pmp.processes.generator;

import java.util.List;
import java.util.Random;

import de.neuenberger.pmp.processes.model.Question;

/**
 * Class for ...
 * 
 * @author Michael Kirchmann, PRODYNA AG
 */
public abstract class AbstractLazyQuestionContainer implements QuestionContainer {
	private List<Question>	allQuestions;

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

}
