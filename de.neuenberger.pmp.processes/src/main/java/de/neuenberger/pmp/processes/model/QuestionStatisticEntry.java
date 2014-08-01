/**
 * 
 */
package de.neuenberger.pmp.processes.model;

/**
 * @author Michael Kirchmann
 * 
 */
public class QuestionStatisticEntry<E extends Named> implements
		Comparable<QuestionStatisticEntry<E>> {

	private int countCorrect;
	private int countIncorrect;
	private final E grouping;

	QuestionStatisticEntry(final E question) {
		this.grouping = question;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final QuestionStatisticEntry o) {
		int result;
		if (o == null) {
			result = 1;
		} else {
			final Integer r = getScore();
			result = r.compareTo(o.getScore());
		}
		return result;
	}

	int getScore() {
		return countCorrect - countIncorrect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((grouping == null) ? 0 : grouping.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final QuestionStatisticEntry other = (QuestionStatisticEntry) obj;
		if (grouping == null) {
			if (other.grouping != null) {
				return false;
			}
		} else if (!grouping.equals(other.grouping)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the correct
	 */
	public int getCountCorrect() {
		return countCorrect;
	}

	/**
	 * @return the incorrect
	 */
	public int getCountIncorrect() {
		return countIncorrect;
	}

	public void countup(final boolean answeredCorrect) {
		if (answeredCorrect) {
			countCorrect++;
		} else {
			countIncorrect++;
		}
	}

	/**
	 * 
	 */
	public E getGrouping() {
		return grouping;
	}

}
