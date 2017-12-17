/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import java.util.List;

import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann
 * 
 * @param <Q> Type of the questions in the container.
 */
public interface QuestionDrawer<Q> {
	public Question drawQuestion();

    public List<QuestionContainer<Q>> getGenerators();

    public void setSelectedContainers(List<QuestionContainer<Q>> selectedContainers);

    public void answeredCorrect(Question question);

    public void answeredWrong(Question question);

}
