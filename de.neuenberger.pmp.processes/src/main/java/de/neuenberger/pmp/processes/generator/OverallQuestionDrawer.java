/**
 * 
 */
package de.neuenberger.pmp.processes.generator;

import generated.CplxKnowledgeArea;
import generated.CplxProcess;
import generated.CplxProcessGroup;
import generated.CplxProcessGroup.Process;
import generated.CplxProcessGroups;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.neuenberger.pmp.processes.model.Question;

/**
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class OverallQuestionDrawer implements QuestionDrawer {
	private final CplxProcessGroups	processGroups;
	List<QuestionContainer>			generators	= new ArrayList<>();

	public OverallQuestionDrawer(final CplxProcessGroups processGroups) {
		this.processGroups = processGroups;

		final List<CplxProcessGroup> processGroup = processGroups.getProcessGroup();
		for (final CplxProcessGroup cplxProcessGroup : processGroup) {
			List<Process> allProcesses = cplxProcessGroup.getProcess();
			QuestionFactory<Process> factory1 = new ProcessRelatedQuestionGenerator.GuessNextProcessQuestionFactory(allProcesses);
			QuestionFactory<Process> factory2 = new ProcessRelatedQuestionGenerator.GuessPreviousProcessQuestionFactory(
					allProcesses);
			QuestionFactory<Process> factory3 = new ProcessRelatedQuestionGenerator.GuessProcessGroupQuestionFactory(
					cplxProcessGroup, processGroup);
			QuestionFactory<Process> factory4 = new ProcessRelatedQuestionGenerator.GuessProcessOfProcessGroup(cplxProcessGroup,
					processGroup);
			
			QuestionFactory<Process> factory5 = new ProcessRelatedQuestionGenerator.GuessProcessNotInThisProcessGroup(cplxProcessGroup,
					processGroup);

			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory1));
			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory2));
			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory3));
			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory4));
			generators.add(new ProcessRelatedQuestionGenerator(cplxProcessGroup, factory5));
		}
		
		List<CplxKnowledgeArea> knowledgeAreas = processGroups.getKnowledgeArea();
		for (CplxKnowledgeArea cplxKnowledgeArea : knowledgeAreas) {
			QuestionFactory<CplxProcess> questionFactory = new KnowledgeAreaRelatedQuestionGenerator.GuessKnowledgeArea(cplxKnowledgeArea, knowledgeAreas);
			generators.add(new KnowledgeAreaRelatedQuestionGenerator(cplxKnowledgeArea, questionFactory ));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.neuenberger.pmp.processes.generator.QuestionDrawer#generateQuestion
	 * ()
	 */
	@Override
	public Question drawQuestion() {
		Random random = new Random();
		final int genIndex = random.nextInt(generators.size());
		List<Question> allQuestions = generators.get(genIndex).getAllQuestions();
		return allQuestions.get(random.nextInt(allQuestions.size()));
	}

}
