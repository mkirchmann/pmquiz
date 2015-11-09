package de.neuenberger.pmp.processes.ui.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.neuenberger.pmp.processes.ui.QuestionController;

public class QuestionSelectedWithKeyListener implements KeyListener {

	private QuestionController questionController;

	public QuestionSelectedWithKeyListener(QuestionController questionController) {
		this.questionController = questionController;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		Integer answer;
		
		char keyChar = arg0.getKeyChar();
		switch (keyChar) {
			case '1':
			case 'a':
			case 'A':
				answer=0;
				break;
			case '2':
			case 'b':
			case 'B':
				answer=1;
				break;
			case '3':
			case 'c':
			case 'C':
				answer=2;
				break;
			case '4':
			case 'd':
			case 'D':
				answer=3;
				break;
			default:
				answer=null;
		}
		if (keyChar==' ' && !questionController.isQuestionOpen()) {
			questionController.drawNextQuestion();
		} else if (answer!=null) {
			questionController.selectedAnswer(answer);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
