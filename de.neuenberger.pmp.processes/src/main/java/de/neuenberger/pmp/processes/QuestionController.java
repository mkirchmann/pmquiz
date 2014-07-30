/**
 * 
 */
package de.neuenberger.pmp.processes;

import generated.CplxProcess;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import de.neuenberger.pmp.processes.generator.QuestionGenerator;
import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class QuestionController {
	Question question;
	QuestionGenerator generator;
	private final QuestionComposite questionComposite;

	public QuestionController(final Question question,
			final QuestionGenerator generator,
			final QuestionComposite questionComposite) {
		this.question = question;
		this.generator = generator;
		this.questionComposite = questionComposite;

		final List<JButton> optionButtons = questionComposite
				.getOptionButtons();

		for (int i = 0; i < 4; i++) {
			optionButtons.get(i).addActionListener(
					new QuestionSelectedListener(this, i));
		}
		questionComposite.getNextButton().addActionListener(
				new NextQuestionActionListener(this));

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
		final List<CplxProcess> options = question.getOptions();
		for (int i = 0; i < 4; i++) {
			final String value = options.get(i).getName();
			questionComposite.getOptionLabels().get(i).setText(value);
		}
		questionComposite.getQuestion().setText(question.getQuestion());
	}

	public void selectedAnswer(final int idx) {
		if (question != null) {
			final CplxProcess answer = question.getOptions().get(idx);
			Integer idxCorrect;
			Integer idxWrong;
			if (answer == question.getCorrectAnswer()) {
				// correct
				idxWrong = null;
				idxCorrect = idx;
			} else {
				// incorrect
				idxCorrect = question.getOptions().indexOf(
						question.getCorrectAnswer());
				idxWrong = idx;
			}
			markAnswer(idxCorrect, idxWrong);
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
			questionComposite.getOptionButtons().get(idxCorrect)
					.setBackground(Color.RED);
		}
	}

	/**
	 * 
	 */
	public void drawNextQuestion() {
		setQuestion(generator.generateQuestion());
	}

	/**
	 * @return
	 */
	public Component getQuestionComposite() {
		return questionComposite;
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

}
