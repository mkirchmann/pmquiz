/**
 * 
 */
package de.neuenberger.pmp.processes.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
/**
 * @author Michael Kirchmann
 * 
 */
public class QuestionComposite extends JPanel {
	private final JTextArea question;
	private final JButton nextButton;

	private final List<JButton> optionButtons = new ArrayList<>();
	private final List<JTextArea> optionLabels = new ArrayList<>();
	private final JPanel panelLeft;
	private final JPanel panelRight;
	private Font bigFont;

	public QuestionComposite() {
		
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		panelLeft = new JPanel();
		panelRight = new JPanel();
		GridLayout layoutLeft = new GridLayout(6, 1);
		GridLayout layoutRight = new GridLayout(6, 1);
		panelLeft.setLayout(layoutLeft);
		panelRight.setLayout(layoutRight);
		this.add(panelLeft, BorderLayout.WEST);
		this.add(panelRight, BorderLayout.CENTER);
		
		panelLeft.add(new JLabel("X"));
		question = createTextLabel();
		
		panelRight.add(question);
		createButtonAndLabel("A");
		createButtonAndLabel("B");
		createButtonAndLabel("C");
		createButtonAndLabel("D");

		panelLeft.add(new JLabel(""));
		nextButton = new JButton("Next");
		panelRight.add(nextButton);
	}

	/**
	 * @param string
	 */
	private void createButtonAndLabel(final String string) {
		final JButton button = new JButton(string);
		final JTextArea optionLabel = createTextLabel();

		panelLeft.add(button);
		panelRight.add(optionLabel);
		optionLabels.add(optionLabel);
		optionButtons.add(button);
	}

	private JTextArea createTextLabel() {
		final JTextArea optionLabel = new JTextArea("");
		Font font = getFontFor(optionLabel);
		optionLabel.setFont(font);
		optionLabel.setBorder(new LineBorder(Color.GRAY));
		optionLabel.setLineWrap(true);
		optionLabel.setEditable(false);
		optionLabel.setWrapStyleWord(true);
		return optionLabel;
	}

	private Font getFontFor(JTextArea optionLabel) {
		if (bigFont==null) {
			Font font2 = optionLabel.getFont();
			bigFont = new Font(font2.getFontName(),font2.getStyle(), font2.getSize()*2);
		}
		return bigFont;
	}

	/**
	 * @return the question
	 */
	public JTextArea getQuestion() {
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
	public List<JTextArea> getOptionLabels() {
		return Collections.unmodifiableList(optionLabels);
	}

	public void setEnabledNextButton(boolean b) {
		for (int i = 0; i < 4; i++) {
			getOptionButtons().get(i).setEnabled(!b);
		}
		getNextButton().setEnabled(b);
	}
}
