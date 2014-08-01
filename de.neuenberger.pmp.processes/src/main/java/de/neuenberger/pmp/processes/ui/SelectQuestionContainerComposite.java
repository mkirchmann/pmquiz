package de.neuenberger.pmp.processes.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SelectQuestionContainerComposite extends JPanel {
	private final JTable table;

	public SelectQuestionContainerComposite() {
		this.setLayout(new BorderLayout());
		
		table = new JTable();
		DefaultListSelectionModel listSelectionModel = new DefaultListSelectionModel();
		listSelectionModel.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		table.setSelectionModel(listSelectionModel);
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}
}
