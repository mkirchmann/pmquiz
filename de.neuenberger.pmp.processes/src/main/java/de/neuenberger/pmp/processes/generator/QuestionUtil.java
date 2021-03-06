/**
 *
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxDefinition;
import generated.CplxLevelledDefinition;
import generated.CplxNamed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.model.DefaultQuestionGroup;
import de.neuenberger.pmp.processes.model.Question;

/**
 * Class for ...
 * 
 * @author Michael Kirchmann
 */
public class QuestionUtil {
	/**
	 * @return
	 */
	public static <E> List<E> create4Answers(final List<E> allAnswers,
			final E correctAnswer) {
		final LinkedList<E> allAnswersExceptCorrect = new LinkedList<>();
		allAnswersExceptCorrect.addAll(allAnswers);
		allAnswersExceptCorrect.remove(correctAnswer);

		List<E> drawRandom = RandomDrawer
				.drawRandom(allAnswersExceptCorrect, 3);
		drawRandom.add(correctAnswer);
		drawRandom = RandomDrawer.randomizeList(drawRandom);
		return drawRandom;
	}

	public static <E> List<String> convertAnswersToString(
			final List<E> options, LabelProvider<E> labelProvider) {
		final List<String> allOptionsAsString = new ArrayList<>(options.size());
		for (final E cplxProcessGroup : options) {
			final String result = labelProvider.getLabel(cplxProcessGroup);
			allOptionsAsString.add(result);
		}
		return allOptionsAsString;
	}
	
	public static <E extends CplxNamed> List<String> convertAnswersToString(
			final List<E> options) {
		return convertAnswersToString(options, CplxNamedLabelProvider.<E>getInstance());
	}

	public static <E extends CplxNamed> Question createQuestion(
            final DefaultQuestionGroup group, final String qString,
            final E correctAnswer, final List<E> possibleAnswers) {
        return createQuestion(group, qString, correctAnswer, null, possibleAnswers, CplxNamedLabelProvider.<E>getInstance());
    }
	
	public static <E extends CplxNamed> Question createQuestion(
			final DefaultQuestionGroup group, final String qString,
			final E correctAnswer, final List<E> possibleAnswers, String solution) {
		return createQuestion(group, qString, correctAnswer, solution, possibleAnswers, CplxNamedLabelProvider.<E>getInstance());
	}
	
	/**
	 * 
	 * @param group
	 * @param qString
	 * @param correctAnswer
	 * @param possibleAnswers
	 * @param labelProvider
	 * @return
	 */
	public static <E> Question createQuestion(
            final DefaultQuestionGroup group, final String qString,
            final E correctAnswer, final List<E> possibleAnswers, LabelProvider<E> labelProvider) {
	    return createQuestion(group, qString, correctAnswer, null, possibleAnswers, labelProvider);
	}
	
	/**
	 * 
	 * @param group
	 * @param qString
	 * @param correctAnswer
	 * @param solution
	 * @param possibleAnswers
	 * @param labelProvider
	 * @return
	 */
	public static <E> Question createQuestion(
			final DefaultQuestionGroup group, final String qString,
			final E correctAnswer, String solution, final List<E> possibleAnswers, LabelProvider<E> labelProvider) {
		final List<E> drawRandom = QuestionUtil.create4Answers(possibleAnswers,
				correctAnswer);
		final List<String> allOptionsAsString = QuestionUtil.convertAnswersToString(drawRandom, labelProvider);
		final String correctAnswerString = labelProvider.getLabel(correctAnswer);
		List<String> solutionOptions = calculateSolutionOptions(solution, allOptionsAsString, correctAnswerString);
		final Question question = new Question(group, qString,
				allOptionsAsString, correctAnswerString, solutionOptions);
		return question;
	}

    private static List<String> calculateSolutionOptions(String solution, final List<String> allOptionsAsString,
            final String correctAnswerString) {
        List<String> solutionsOptions;
		if (solution!=null) {
            solutionsOptions = new ArrayList<String>(allOptionsAsString);
    		int indexOf = solutionsOptions.indexOf(correctAnswerString);
    		solutionsOptions.set(indexOf, solution);
		} else {
		    solutionsOptions = Collections.emptyList();
		}
		return solutionsOptions;
    }
	
	public static <E> Question createQuestion(DefaultQuestionGroup questionGroup, String qString,
            E correctAnswer, List<E> possibleAnswers, String solution,
            LabelProvider<E> labelProvider) {
        return createQuestion(questionGroup, qString, correctAnswer, solution, possibleAnswers, labelProvider);
    }
	
	public interface LabelProvider<E> {
		String getLabel(E e);
	}
	
	public static class CplxNamedLabelProvider<E extends CplxNamed> implements LabelProvider<E>{
		private static final CplxNamedLabelProvider instance = new CplxNamedLabelProvider();
		private CplxNamedLabelProvider() {
			
		}
		@Override
		public String getLabel(CplxNamed e) {
			return e.getName();
		}
		
		public static <X extends CplxNamed> CplxNamedLabelProvider<X> getInstance() {
			return instance;
		}
	}

}
