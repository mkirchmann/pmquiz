package de.neuenberger.pmp.processes.ui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.neuenberger.pmp.processes.generator.OverallQuestionDrawer;
import de.neuenberger.pmp.processes.generator.QuestionContainer;
import de.neuenberger.pmp.processes.generator.QuestionDrawer;

public class SelectQuestionContainerController<Q> implements
		Controller<SelectQuestionContainerComposite, QuestionDrawer<Q>> {
	private final SelectQuestionContainerComposite composite;
	private final QuestionDrawer<Q> questionDrawer;

	public SelectQuestionContainerController(
			final SelectQuestionContainerComposite composite,
			final QuestionDrawer<Q> questionDrawer) {
		this(composite, questionDrawer, new QuestionContainerListTableModel(
				questionDrawer.getGenerators()));
	}

	SelectQuestionContainerController(
			final SelectQuestionContainerComposite composite,
			final QuestionDrawer<Q> questionDrawer,
			final QuestionContainerListTableModel tableModel) {
		this.composite = composite;
		this.questionDrawer = questionDrawer;

		final ListSelectionListener x = new QuestionDrawerSelector();
		composite.getTable().getSelectionModel().addListSelectionListener(x);
		composite.getTable().setModel(tableModel);
	}

	public class QuestionDrawerSelector implements ListSelectionListener {

		@Override
		public void valueChanged(final ListSelectionEvent e) {
			final List<QuestionContainer<Q>> selectedContainers = new LinkedList<>();
			final int[] selectedRows = composite.getTable().getSelectedRows();
			for (final int i : selectedRows) {
				selectedContainers.add(questionDrawer.getGenerators().get(i));
			}
			questionDrawer.setSelectedContainers(selectedContainers);
		}

	}

	@Override
	public SelectQuestionContainerComposite getComponent() {
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.neuenberger.pmp.processes.ui.Controller#getModel()
	 */
	@Override
	public QuestionDrawer getModel() {
		return questionDrawer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Select Question Container";
	}
}
