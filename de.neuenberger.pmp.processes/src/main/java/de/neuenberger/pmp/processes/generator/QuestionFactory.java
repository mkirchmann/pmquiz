/**
 *
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxNamed;
import de.neuenberger.pmp.processes.model.Question;

public interface QuestionFactory<E extends CplxNamed> {
	public Question createQuestionForProcess(final E drawnProcess);
}