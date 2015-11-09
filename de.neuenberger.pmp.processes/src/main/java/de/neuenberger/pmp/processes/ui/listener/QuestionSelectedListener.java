package de.neuenberger.pmp.processes.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.neuenberger.pmp.processes.ui.QuestionController;

public class QuestionSelectedListener implements ActionListener {
	private final int index;
	private final QuestionController questionController;

	public QuestionSelectedListener(final QuestionController questionController,
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