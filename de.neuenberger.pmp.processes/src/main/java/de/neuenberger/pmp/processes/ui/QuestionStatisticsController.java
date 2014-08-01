/**
 * 
 */
package de.neuenberger.pmp.processes.ui;

import de.neuenberger.pmp.processes.model.QuestionStatistics;

/**
 * @author Michael Kirchmann
 * 
 */
public class QuestionStatisticsController implements
		Controller<QuestionStatisticsComposite, QuestionStatistics> {

	private final QuestionStatisticsComposite questionStatisticsComposite;
	private final QuestionStatistics questionStatistics;

	/**
	 * 
	 * @param questionStatisticsComposite
	 * @param questionStatistics
	 */
	public QuestionStatisticsController(
			final QuestionStatisticsComposite questionStatisticsComposite,
			final QuestionStatistics questionStatistics) {
		this(questionStatisticsComposite, questionStatistics,
				new QuestionStatisticsTableModel(questionStatistics));

	}

	/**
	 * 
	 * @param questionStatisticsComposite
	 * @param questionStatistics
	 * @param tableModel
	 */
	QuestionStatisticsController(
			final QuestionStatisticsComposite questionStatisticsComposite,
			final QuestionStatistics questionStatistics,
			final QuestionStatisticsTableModel tableModel) {
		this.questionStatisticsComposite = questionStatisticsComposite;
		this.questionStatistics = questionStatistics;

		questionStatisticsComposite.getTable().setModel(tableModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.neuenberger.pmp.processes.ui.Controller#getComponent()
	 */
	@Override
	public QuestionStatisticsComposite getComponent() {
		return questionStatisticsComposite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.neuenberger.pmp.processes.ui.Controller#getModel()
	 */
	@Override
	public QuestionStatistics getModel() {
		return questionStatistics;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Statistics";
	}

}
