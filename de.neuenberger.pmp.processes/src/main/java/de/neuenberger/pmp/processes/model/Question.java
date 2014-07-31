/**
 * 
 */
package de.neuenberger.pmp.processes.model;

import java.util.List;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class Question {
	private final List<String> options;
	private final String correctAnswer;
	private final String question;

	public Question(final String question, final List<String> options,
			final String correctAnswers) {
		super();
		this.question = question;
		this.options = options;
		this.correctAnswer = correctAnswers;

		if (options.size() != 4) {
			throw new IllegalArgumentException("Question size wrong: "
					+ options.size());
		}
		if (!options.contains(correctAnswers)) {
			throw new IllegalArgumentException("Answer ('" + correctAnswers
					+ "') not contained!");
		}
	}

	/**
	 * @return the options
	 */
	public List<String> getOptions() {
		return options;
	}

	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
}
