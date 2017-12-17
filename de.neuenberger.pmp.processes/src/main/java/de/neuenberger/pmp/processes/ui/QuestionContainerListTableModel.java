package de.neuenberger.pmp.processes.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.neuenberger.pmp.processes.generator.QuestionContainer;

/**
 * 
 * @author Michael Kirchmann
 *
 * @param <Q> Type of the question.
 */
public class QuestionContainerListTableModel<Q> extends AbstractTableModel {
	private final List<QuestionContainer<Q>> questionContainerList;
	
	QuestionContainerListTableModel(List<QuestionContainer<Q>> questionContainerList) {
		this.questionContainerList = questionContainerList;
	}
	
	@Override
	public int getRowCount() {
		return questionContainerList.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return questionContainerList.get(rowIndex).getName();
	}

}
