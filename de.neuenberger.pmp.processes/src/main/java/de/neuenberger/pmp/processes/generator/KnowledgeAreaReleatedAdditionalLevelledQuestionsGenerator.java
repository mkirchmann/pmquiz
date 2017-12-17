package de.neuenberger.pmp.processes.generator;

import generated.CplxDefinition;
import generated.CplxDefinitionWithLevels;
import generated.CplxGroup;
import generated.CplxKnowledgeArea;
import generated.CplxLevelledDefinition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.neuenberger.pmp.processes.generator.QuestionUtil.LabelProvider;
import de.neuenberger.pmp.processes.model.DefaultQuestionGroup;
import de.neuenberger.pmp.processes.model.LevelledQuestion;
import de.neuenberger.pmp.processes.model.Question;

public class KnowledgeAreaReleatedAdditionalLevelledQuestionsGenerator extends LevelledQuestionContainer {
    private final CplxKnowledgeArea knowledgeArea;
    private final DefaultQuestionGroup questionGroup;

    public KnowledgeAreaReleatedAdditionalLevelledQuestionsGenerator(final CplxKnowledgeArea knowledgeArea) {
        super("Definitions for " + knowledgeArea.getName());
        this.knowledgeArea = knowledgeArea;
        questionGroup = new DefaultQuestionGroup(getName());
    }

    @Override
    protected List<LevelledQuestion> createQuestions() {
        final List<LevelledQuestion> questions = new LinkedList<>();
        if (knowledgeArea.getAddition() != null) {
            final List<CplxGroup> groups = knowledgeArea.getAddition().getGroup();
            for (final CplxGroup cplxGroup : groups) {
                questions.addAll(createQuestionForProcess(cplxGroup));
            }
        }
        return questions;
    }

    public List<LevelledQuestion> createQuestionForProcess(final CplxGroup drawnProcess) {
        final List<CplxDefinitionWithLevels> definition = drawnProcess.getLevelledDefinition();
        final DefinitionDefinitionLabelProvider provider = new DefinitionDefinitionLabelProvider();

        final List<LevelledQuestion> result = new LinkedList<>();
        for (final CplxDefinitionWithLevels cplxDefinitionWithLevels : definition) {
            List<CplxLevelledDefinition> levelledDefinitions = cplxDefinitionWithLevels.getLevelledDefinition();
            LevelledQuestion lq1 = new LevelledQuestion();
            LevelledQuestion lq2 = new LevelledQuestion();
            String solution1 = null;
            String solution2 = null;
            for (CplxLevelledDefinition levelledDefinition : levelledDefinitions) {
                Integer level = levelledDefinition.getLevel();
                if (level==null || level==1) {
                    solution1 = levelledDefinition.getName()+" : "+levelledDefinition.getDescription();
                    solution2 = levelledDefinition.getDescription()+" : "+levelledDefinition.getName();
                    break;
                }
            }
            for (CplxLevelledDefinition levelledDefinition : levelledDefinitions) {
                Question question1 = QuestionUtil.createQuestion(questionGroup, "Looking at " + drawnProcess.getName()
                        + ", what is defined by '" + levelledDefinition.getDescription() + "'?", levelledDefinition, filter(definition, levelledDefinition.getLevel()), solution1);
                Question question2 = QuestionUtil.createQuestion(questionGroup, "Looking at " + drawnProcess.getName()
                        + ", what is defining '" + levelledDefinition.getName() + "'?", levelledDefinition, filter(definition, levelledDefinition.getLevel()), solution2, provider);
                lq1.setQuestionForLevel(question1, levelledDefinition.getLevel());
                lq2.setQuestionForLevel(question2, levelledDefinition.getLevel());
            }
            result.add(lq1);
            result.add(lq2);
        }

        return result;
    }

    private List<CplxDefinition> filter(List<CplxDefinitionWithLevels> definitionList, Integer level) {
        List<CplxDefinition> result = new ArrayList<CplxDefinition>(definitionList.size());
        for (CplxDefinitionWithLevels container : definitionList) {
            List<CplxLevelledDefinition> levelledDefinition = container.getLevelledDefinition();
            for (CplxLevelledDefinition ldef : levelledDefinition) {
                if (level!=null && level.equals(ldef.getLevel())) {
                    result.add(ldef);
                }
            }
        }
        return result;
    }

    public static class DefinitionDefinitionLabelProvider implements LabelProvider<CplxDefinition> {

        @Override
        public String getLabel(final CplxDefinition e) {
            return e.getDescription();
        }

    }
}
