/**
 *
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxNamed;

import java.util.List;

import de.neuenberger.pmp.processes.model.Question;

public interface QuestionFactory<E extends CplxNamed> {
	public void createQuestionForProcess(final E drawnProcess,
			List<Question> list);
}