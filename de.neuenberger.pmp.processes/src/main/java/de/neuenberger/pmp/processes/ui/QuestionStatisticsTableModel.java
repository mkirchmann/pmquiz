/**
 * 
 */
package de.neuenberger.pmp.processes.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import de.neuenberger.pmp.processes.model.DefaultQuestionGroup;
import de.neuenberger.pmp.processes.model.QuestionStatisticEntry;
import de.neuenberger.pmp.processes.model.QuestionStatistics;

/**
 * @author Michael Kirchmann
 * 
 */
public class QuestionStatisticsTableModel extends AbstractTableModel implements
		PropertyChangeListener {

	private final QuestionStatistics statistics;

	public QuestionStatisticsTableModel(final QuestionStatistics statistics) {
		this.statistics = statistics;
		statistics.addPropertyChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return getEntries().size()+1;
	}

	private List<QuestionStatisticEntry<DefaultQuestionGroup>> getEntries() {
		return statistics.getStatisticEntries();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(final int row, final int column) {
		
		List<QuestionStatisticEntry<DefaultQuestionGroup>> entries = getEntries();
		final QuestionStatisticEntry entry;
		if (row==entries.size()) {
			// this is the overall row
			entry = statistics.getOverall();
		} else {
			entry = entries.get(row);
		}
		String result;
		if (column == 0) {
			result = entry.getGrouping().getName();
		} else if (column == 1) {
			result = "" + entry.getCountCorrect();
		} else if (column ==2){
			result = "" + entry.getCountIncorrect();
		} else if (column==3) {
			result = "" + NumberFormat.getPercentInstance().format(entry.getPercentage());
		} else {
			result ="";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent arg0) {
		fireTableChanged(new TableModelEvent(this));

	}

}
