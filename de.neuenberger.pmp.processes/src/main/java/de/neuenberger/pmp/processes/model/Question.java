/**
 * 
 */
package de.neuenberger.pmp.processes.model;

import java.util.ArrayList;
import java.util.Collections;
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
	private final List<String> solutionOptions;

	public Question(final DefaultQuestionGroup group, final String question,
            final List<String> options, final String correctAnswers) {
	    this(group,question,options,correctAnswers, Collections.emptyList());
	}
	
	public Question(final DefaultQuestionGroup group, final String question,
			final List<String> options, final String correctAnswers, List<String> theSolutionOptions) {
		super();
		this.group = group;
		this.question = question;
		this.options = options;
		this.correctAnswer = correctAnswers;
		this.solutionOptions = new ArrayList<>(theSolutionOptions);

		if (options.size() != 4) {
			throw new IllegalArgumentException("Question size wrong: "
					+ options.size());
		}
		if (solutionOptions.size()!=0 && solutionOptions.size()!=4) {
		    throw new IllegalArgumentException("Solution size wrong: "
                    + solutionOptions.size());
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

    public List<String> getSolutionOptions() {
        return solutionOptions;
    }
}
