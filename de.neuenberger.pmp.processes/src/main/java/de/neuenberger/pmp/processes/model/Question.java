/**
 * 
 */
package de.neuenberger.pmp.processes.model;

import generated.CplxProcess;

import java.util.List;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class Question {
	private final List<CplxProcess> options;
	private final CplxProcess correctAnswer;
	private final String question;

	public Question(final String question, final List<CplxProcess> options,
			final CplxProcess correctAnswers) {
		super();
		this.question = question;
		this.options = options;
		this.correctAnswer = correctAnswers;
	}

	/**
	 * @return the options
	 */
	public List<CplxProcess> getOptions() {
		return options;
	}

	/**
	 * @return the correctAnswer
	 */
	public CplxProcess getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
}
