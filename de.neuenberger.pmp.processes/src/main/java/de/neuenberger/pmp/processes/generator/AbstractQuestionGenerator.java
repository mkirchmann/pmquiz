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
public abstract class AbstractQuestionGenerator implements QuestionDrawer {
	private List<Question>	allQuestions;

	public List<Question> getAllQuestions() {
		if (allQuestions == null) {
			allQuestions = createQuestions();
		}
		return allQuestions;
	}

	/**
	 * @return
	 */
	abstract protected List<Question> createQuestions();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neuenberger.pmp.processes.generator.QuestionDrawer#generateQuestion
	 * ()
	 */
	@Override
	public Question drawQuestion() {
		List<Question> questions = getAllQuestions();
		return questions.get(new Random().nextInt(questions.size()));
	}
}
