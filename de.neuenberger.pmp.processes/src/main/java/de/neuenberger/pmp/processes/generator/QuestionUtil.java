/**
 *
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxNamed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.model.Question;
import de.neuenberger.pmp.processes.model.QuestionGroup;

/**
 * Class for ...
 * 
 * @author Michael Kirchmann, PRODYNA AG
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

	public static <E extends CplxNamed> List<String> convertAnswersToString(
			final List<E> options) {
		final List<String> allOptionsAsString = new ArrayList<>(options.size());
		for (final CplxNamed cplxProcessGroup : options) {
			final String result = cplxProcessGroup.getName();
			allOptionsAsString.add(result);
		}
		return allOptionsAsString;
	}

	/**
	 * @param qString
	 * @param correctAnswer
	 * @param possibleAnswers
	 * @return
	 */
	public static <E extends CplxNamed> Question createQuestion(
			final QuestionGroup group, final String qString,
			final E correctAnswer, final List<E> possibleAnswers) {
		final List<E> drawRandom = QuestionUtil.create4Answers(possibleAnswers,
				correctAnswer);
		final List<String> allOptionsAsString = QuestionUtil
				.convertAnswersToString(drawRandom);
		final String correctAnswerString = correctAnswer.getName();
		final Question question = new Question(group, qString,
				allOptionsAsString, correctAnswerString);
		return question;
	}
}
