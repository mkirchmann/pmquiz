/**
 * 
 */
package de.neuenberger.pmp.processes;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class QuestionComposite extends JPanel {
	private final JLabel question;
	private final JButton buttonA;
	private final JButton buttonB;
	private final JButton buttonC;
	private final JButton buttonD;
	private final JLabel optionA;
	private final JLabel optionB;
	private final JLabel optionC;
	private final JLabel optionD;
	private final JButton nextButton;

	QuestionComposite() {
		this.setLayout(new GridLayout(2, 6));
		this.add(new JLabel("X"));
		question = new JLabel();

		buttonA = new JButton("A");
		optionA = new JLabel("");

		buttonB = new JButton("B");
		optionB = new JLabel("");

		buttonC = new JButton("C");
		optionC = new JLabel("");

		buttonD = new JButton("D");
		optionD = new JLabel("");

		new JLabel("");
		nextButton = new JButton("Next");
	}

	/**
	 * @return the question
	 */
	public JLabel getQuestion() {
		return question;
	}

	/**
	 * @return the buttonA
	 */
	public JButton getButtonA() {
		return buttonA;
	}

	/**
	 * @return the buttonB
	 */
	public JButton getButtonB() {
		return buttonB;
	}

	/**
	 * @return the buttonC
	 */
	public JButton getButtonC() {
		return buttonC;
	}

	/**
	 * @return the buttonD
	 */
	public JButton getButtonD() {
		return buttonD;
	}

	/**
	 * @return the optionA
	 */
	public JLabel getOptionA() {
		return optionA;
	}

	/**
	 * @return the optionB
	 */
	public JLabel getOptionB() {
		return optionB;
	}

	/**
	 * @return the optionC
	 */
	public JLabel getOptionC() {
		return optionC;
	}

	/**
	 * @return the optionD
	 */
	public JLabel getOptionD() {
		return optionD;
	}

	/**
	 * @return the nextButton
	 */
	public JButton getNextButton() {
		return nextButton;
	}
}
