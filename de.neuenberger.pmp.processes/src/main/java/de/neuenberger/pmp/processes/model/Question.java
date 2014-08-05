/**
 * 
 */
package de.neuenberger.pmp.processes.model;

import java.util.List;

/**
 * @author Michael Kirchmann
 * 
 */
public class Question implements Named {
	private final List<String> options;
	private final String correctAnswer;
	private final String question;
	private final DefaultQuestionGroup group;

	public Question(final DefaultQuestionGroup group, final String question,
			final List<String> options, final String correctAnswers) {
		super();
		this.group = group;
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

	/**
	 * @return the group
	 */
	public DefaultQuestionGroup getGroup() {
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.neuenberger.pmp.processes.model.Named#getName()
	 */
	@Override
	public String getName() {
		return question;
	}
}
