/**
 * 
 */
package de.neuenberger.pmp.processes.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import de.neuenberger.pmp.processes.generator.QuestionDrawer;
import de.neuenberger.pmp.processes.model.Question;
import de.neuenberger.pmp.processes.model.QuestionStatistics;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class QuestionController implements
		Controller<QuestionComposite, Question> {
	private Question question;
	private final QuestionDrawer generator;
	private final QuestionComposite questionComposite;
	private final QuestionStatistics questionStatistics;

	/**
	 * 
	 * @param generator
	 * @param questionStatistics
	 * @param questionComposite
	 */
	public QuestionController(final QuestionDrawer generator,
			final QuestionStatistics questionStatistics,
			final QuestionComposite questionComposite) {
		this.generator = generator;
		this.questionStatistics = questionStatistics;
		this.questionComposite = questionComposite;

		final List<JButton> optionButtons = questionComposite
				.getOptionButtons();

		for (int i = 0; i < 4; i++) {
			optionButtons.get(i).addActionListener(
					new QuestionSelectedListener(this, i));
		}
		questionComposite.getNextButton().addActionListener(
				new NextQuestionActionListener(this));
		questionComposite.setEnabledNextButton(true);
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(final Question question) {
		this.question = question;

		clearAnswers();
		final List<String> options = question.getOptions();
		for (int i = 0; i < 4; i++) {
			final String value = options.get(i);
			questionComposite.getOptionLabels().get(i).setText(value);
		}
		questionComposite.getQuestion().setText(question.getQuestion());
		questionComposite.setEnabledNextButton(false);
	}

	public void selectedAnswer(final int idx) {
		if (question != null) {
			final String answer = question.getOptions().get(idx);
			Integer idxCorrect;
			Integer idxWrong;
			boolean answeredCorrect;
			if (answer == question.getCorrectAnswer()) {
				// correct
				idxWrong = null;
				idxCorrect = idx;
				answeredCorrect = true;
			} else {
				// incorrect
				idxCorrect = question.getOptions().indexOf(
						question.getCorrectAnswer());
				idxWrong = idx;
				answeredCorrect = false;
			}
			markAnswer(idxCorrect, idxWrong);

			questionComposite.setEnabledNextButton(true);
			questionStatistics.count(question, answeredCorrect);
			question = null;

		}
	}

	public void clearAnswers() {
		final List<JButton> optionButtons = questionComposite
				.getOptionButtons();
		for (final JButton jButton : optionButtons) {
			jButton.setBackground(Color.LIGHT_GRAY);
		}
	}

	/**
	 * @param idxCorrect
	 * @param idxWrong
	 */
	private void markAnswer(final Integer idxCorrect, final Integer idxWrong) {
		clearAnswers();
		if (idxCorrect != null) {
			questionComposite.getOptionButtons().get(idxCorrect)
					.setBackground(Color.GREEN);
		}
		if (idxWrong != null) {
			questionComposite.getOptionButtons().get(idxWrong)
					.setBackground(Color.RED);
		}
	}

	/**
	 * 
	 */
	public void drawNextQuestion() {
		setQuestion(generator.drawQuestion());
	}

	public static class QuestionSelectedListener implements ActionListener {
		private final int index;
		private final QuestionController questionController;

		QuestionSelectedListener(final QuestionController questionController,
				final int index) {
			this.questionController = questionController;
			this.index = index;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			questionController.selectedAnswer(index);
		}

	}

	public static class NextQuestionActionListener implements ActionListener {

		private final QuestionController questionController;

		NextQuestionActionListener(final QuestionController questionController) {
			this.questionController = questionController;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			questionController.drawNextQuestion();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.neuenberger.pmp.processes.ui.Controller#getComponent()
	 */
	@Override
	public QuestionComposite getComponent() {
		return questionComposite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.neuenberger.pmp.processes.ui.Controller#getModel()
	 */
	@Override
	public Question getModel() {
		return question;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Quiz";
	}

}
