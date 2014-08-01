package de.neuenberger.pmp.processes.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.neuenberger.pmp.processes.generator.OverallQuestionDrawer;
import de.neuenberger.pmp.processes.generator.QuestionContainer;
import de.neuenberger.pmp.processes.generator.QuestionDrawer;

public class QuestionContainerListTableModel extends AbstractTableModel {
	private final List<QuestionContainer> questionContainerList;
	
	QuestionContainerListTableModel(List<QuestionContainer> questionContainerList) {
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
