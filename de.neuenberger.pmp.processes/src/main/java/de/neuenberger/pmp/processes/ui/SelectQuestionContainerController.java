package de.neuenberger.pmp.processes.ui;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.neuenberger.pmp.processes.generator.OverallQuestionDrawer;
import de.neuenberger.pmp.processes.generator.QuestionContainer;

public class SelectQuestionContainerController {
	private final SelectQuestionContainerComposite composite;
	private OverallQuestionDrawer questionDrawer;
	
	public SelectQuestionContainerController(SelectQuestionContainerComposite composite, OverallQuestionDrawer questionDrawer) {
		this(composite, questionDrawer, new QuestionContainerListTableModel(questionDrawer.getGenerators()));
	}
	
	SelectQuestionContainerController(SelectQuestionContainerComposite composite, OverallQuestionDrawer questionDrawer, QuestionContainerListTableModel tableModel) {
		this.composite = composite;
		this.questionDrawer = questionDrawer;
		
		ListSelectionListener x = new QuestionDrawerSelector();
		composite.getTable().getSelectionModel().addListSelectionListener(x);
		composite.getTable().setModel(tableModel);
	}
	
	public class QuestionDrawerSelector implements  ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			List<QuestionContainer> selectedContainers = new LinkedList<>();
			int[] selectedRows = composite.getTable().getSelectedRows();
			for (int i : selectedRows) {
				selectedContainers.add(questionDrawer.getGenerators().get(i));
			}
			questionDrawer.setSelectedContainers(selectedContainers);
		}
		
	}

	public Component getComponent() {
		return composite;
	}
}
