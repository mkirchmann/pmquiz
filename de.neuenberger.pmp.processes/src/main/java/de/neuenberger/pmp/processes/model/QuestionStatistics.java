/**
 * 
 */
package de.neuenberger.pmp.processes.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class QuestionStatistics {
	Map<Question, QuestionStatisticEntry> mapQuestionToCorrectCount = new LinkedHashMap<Question, QuestionStatisticEntry>();
	private List<QuestionStatisticEntry> cached;

	private final PropertyChangeSupport support = new PropertyChangeSupport(
			this);

	public void count(final Question question, final boolean answeredCorrect) {
		cached = null;
		QuestionStatisticEntry statisticEntry = mapQuestionToCorrectCount
				.get(question);
		if (statisticEntry == null) {
			statisticEntry = new QuestionStatisticEntry(question);
			mapQuestionToCorrectCount.put(question, statisticEntry);
		}

		statisticEntry.countup(answeredCorrect);

		support.firePropertyChange(new PropertyChangeEvent(this,
				"mapQuestionToCorrectCount", null, true));
	}

	public List<QuestionStatisticEntry> getStatisticEntries() {
		if (cached == null) {
			final Collection<QuestionStatisticEntry> values = mapQuestionToCorrectCount
					.values();
			final LinkedList<QuestionStatisticEntry> linkedList = new LinkedList<>(
					values);

			Collections.sort(linkedList);
			cached = Collections.unmodifiableList(linkedList);
		}
		return cached;
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(
			final PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

}
