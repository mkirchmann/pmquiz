/**
 * 
 */
package de.neuenberger.pmp.processes.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author Michael Kirchmann
 * 
 */
public class QuestionStatisticsComposite extends JPanel {

	private final JTable table;

	public QuestionStatisticsComposite() {
		table = new JTable();
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(table), BorderLayout.CENTER);
	}

	/**
	 * @return the table
	 * 
	 */
	public JTable getTable() {
		return table;
	}

}
