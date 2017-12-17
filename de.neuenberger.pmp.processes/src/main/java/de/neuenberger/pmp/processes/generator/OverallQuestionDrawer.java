/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxProcessGroups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann
 * 
 * Type of the question.
 */
public abstract class OverallQuestionDrawer<Q> implements QuestionDrawer<Q> {
	private final CplxProcessGroups processGroups;
	private final List<QuestionContainer<Q>> generators;

	private List<QuestionContainer<Q>> selectedContainers;
	private List<Q> selectedQuestions;

	public OverallQuestionDrawer(final CplxProcessGroups processGroups, List<QuestionContainer<Q>> theGenerators) {
		this.processGroups = processGroups;
		this.generators = new ArrayList<>(theGenerators);
		this.selectedContainers=theGenerators;
	}

	/**
	 * @return the generators
	 */
	public List<QuestionContainer<Q>> getGenerators() {
		return Collections.unmodifiableList(generators);
	}

	/**
	 * @return the selectedContainers
	 */
	public List<QuestionContainer<Q>> getSelectedContainers() {
		return selectedContainers;
	}

	/**
	 * @param selectedContainers
	 *            the selectedContainers to set
	 */
	public void setSelectedContainers(
			final List<QuestionContainer<Q>> selectedContainers) {
		this.selectedContainers = selectedContainers;
		selectedQuestions = null;
	}

	/**
	 * @return the selectedQuestions
	 */
	public List<Q> getSelectedQuestions() {
		if (selectedQuestions == null) {
			if (selectedContainers != null) {
				selectedQuestions = new LinkedList<>();
				for (final QuestionContainer<Q> container : selectedContainers) {
					selectedQuestions.addAll(container.getAllQuestions());
				}
			} else {
				selectedQuestions = Collections.emptyList();
			}
		}
		return selectedQuestions;
	}
	
	@Override
	public void answeredCorrect(Question question) {
	    // do nothing
	}
	
	@Override
    public void answeredWrong(Question question) {
        // do nothing
    }
}
