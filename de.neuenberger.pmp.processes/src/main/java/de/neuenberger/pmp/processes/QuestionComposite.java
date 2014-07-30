/**
 * 
 */
package de.neuenberger.pmp.processes;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class QuestionComposite extends JPanel {
	private final JLabel question;
	private final JButton nextButton;

	List<JButton> optionButtons = new ArrayList<>();
	List<JLabel> optionLabels = new ArrayList<>();

	QuestionComposite() {
		this.setLayout(new GridLayout(6, 2));
		this.add(new JLabel("X"));
		question = new JLabel();
		this.add(question);
		createButtonAndLabel("A");
		createButtonAndLabel("B");
		createButtonAndLabel("C");
		createButtonAndLabel("D");

		this.add(new JLabel(""));
		nextButton = new JButton("Next");
		this.add(nextButton);
	}

	/**
	 * @param string
	 */
	private void createButtonAndLabel(final String string) {
		final JButton button = new JButton(string);
		final JLabel optionLabel = new JLabel("");

		this.add(button);
		this.add(optionLabel);
		optionLabels.add(optionLabel);
		optionButtons.add(button);
	}

	/**
	 * @return the question
	 */
	public JLabel getQuestion() {
		return question;
	}

	/**
	 * @return the nextButton
	 */
	public JButton getNextButton() {
		return nextButton;
	}

	/**
	 * @return the buttons
	 */
	public List<JButton> getOptionButtons() {
		return Collections.unmodifiableList(optionButtons);
	}

	/**
	 * @return the labels
	 */
	public List<JLabel> getOptionLabels() {
		return Collections.unmodifiableList(optionLabels);
	}
}
