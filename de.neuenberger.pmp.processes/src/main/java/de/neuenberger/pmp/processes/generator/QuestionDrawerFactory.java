package de.neuenberger.pmp.processes.generator;

import generated.CplxKnowledgeArea;
import generated.CplxProcess;
import generated.CplxProcessGroup;
import generated.CplxProcessGroups;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.neuenberger.pmp.processes.MainWindow;
import de.neuenberger.pmp.processes.model.LevelledQuestion;
import de.neuenberger.pmp.processes.model.Question;

public interface QuestionDrawerFactory {
    QuestionDrawer createQuestionDrawer(CplxProcessGroups groups);
    
    public static class PmpQuestionDrawer implements QuestionDrawerFactory {

        @Override
        public QuestionDrawer<Question> createQuestionDrawer(CplxProcessGroups groups) {
            final List<CplxProcessGroup> processGroup = groups.getProcessGroup();
            List<QuestionContainer<Question>> generators = new ArrayList<>();
            for (final CplxProcessGroup cplxProcessGroup : processGroup) {
                final List<CplxProcess> allProcesses = cplxProcessGroup
                        .getProcess();

                final QuestionFactory<CplxProcess> factory3 = new ProcessRelatedQuestionGenerator.GuessProcessGroupQuestionFactory(
                        cplxProcessGroup, processGroup);
                final QuestionFactory<CplxProcess> factory4 = new ProcessRelatedQuestionGenerator.GuessProcessOfProcessGroup(
                        cplxProcessGroup, processGroup);

                final QuestionFactory<CplxProcess> factory5 = new ProcessRelatedQuestionGenerator.GuessProcessNotInThisProcessGroup(
                        cplxProcessGroup, processGroup);
                final QuestionFactory<CplxProcess> factory6 = new ProcessRelatedQuestionGenerator.GuessInputOutputOfProcess(
                        cplxProcessGroup, processGroup);

                if (Boolean.TRUE.equals(cplxProcessGroup.isSequential())) {
                    final QuestionFactory<CplxProcess> factory1 = new ProcessRelatedQuestionGenerator.GuessNextProcessQuestionFactory(
                            allProcesses);
                    final QuestionFactory<CplxProcess> factory2 = new ProcessRelatedQuestionGenerator.GuessPreviousProcessQuestionFactory(
                            allProcesses);

                    generators.add(new ProcessRelatedQuestionGenerator(
                            cplxProcessGroup, factory1));
                    generators.add(new ProcessRelatedQuestionGenerator(
                            cplxProcessGroup, factory2));
                }

                generators.add(new ProcessRelatedQuestionGenerator(
                        cplxProcessGroup, factory3));
                generators.add(new ProcessRelatedQuestionGenerator(
                        cplxProcessGroup, factory4));
                generators.add(new ProcessRelatedQuestionGenerator(
                        cplxProcessGroup, factory5));
                generators.add(new ProcessRelatedQuestionGenerator(
                        cplxProcessGroup, factory6));
            }

            final List<CplxKnowledgeArea> knowledgeAreas = groups.getKnowledgeArea();
            for (final CplxKnowledgeArea cplxKnowledgeArea : knowledgeAreas) {
                final QuestionFactory<CplxProcess> questionFactory = new KnowledgeAreaRelatedQuestionGenerator.GuessKnowledgeArea(
                        cplxKnowledgeArea, knowledgeAreas);
                generators.add(new KnowledgeAreaRelatedQuestionGenerator(
                        cplxKnowledgeArea, questionFactory));
                generators
                        .add(new KnowledgeAreaReleatedAdditionalQuestionsGenerator(
                                cplxKnowledgeArea));
            }
            
            return new OverallQuestionDrawer<Question>(groups, generators) {

                /*
                 * (non-Javadoc)
                 * 
                 * @see
                 * de.neuenberger.pmp.processes.generator.QuestionDrawer#generateQuestion ()
                 */
                @Override
                public Question drawQuestion() {
                    return RandomDrawer.drawRandomSingle(getSelectedQuestions());
                }
                
            };
        }
        
    }
    
    public static class HskQuestionDrawer implements QuestionDrawerFactory {

        @Override
        public QuestionDrawer<Question> createQuestionDrawer(CplxProcessGroups groups) {
            List<QuestionContainer<Question>> generators = new ArrayList<>();
            
            final List<CplxKnowledgeArea> knowledgeAreas = groups.getKnowledgeArea();
            for (final CplxKnowledgeArea cplxKnowledgeArea : knowledgeAreas) {
                generators
                        .add(new KnowledgeAreaReleatedAdditionalQuestionsGenerator(
                                cplxKnowledgeArea));
            }
            
            return new OverallQuestionDrawer<Question>(groups, generators){
                List<Question> formerQuestions = new ArrayList<>();
                @Override
                public Question drawQuestion() {
                    final List<Question> questionList;
                    if (formerQuestions.isEmpty()) {
                        questionList = getSelectedQuestions();
                    } else {
                        questionList = RandomDrawer.drawRandomSingle(Arrays.asList(getSelectedQuestions(), formerQuestions));
                    }
                    Question drawnQuestion = RandomDrawer.drawRandomSingle(questionList);
                    if (!formerQuestions.contains(drawnQuestion)) {
                        formerQuestions.add(drawnQuestion);
                    }
                    return drawnQuestion;
                }
                
            };
        }
        
    }
    
    public static class HskQuestionDrawerLevelled implements QuestionDrawerFactory {

        @Override
        public QuestionDrawer<LevelledQuestion> createQuestionDrawer(CplxProcessGroups groups) {
            try {
                MainWindow.enrichWithHSK();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            List<QuestionContainer<LevelledQuestion>> generators = new ArrayList<>();
            
            final List<CplxKnowledgeArea> knowledgeAreas = groups.getKnowledgeArea();
            for (final CplxKnowledgeArea cplxKnowledgeArea : knowledgeAreas) {
                generators
                        .add(new KnowledgeAreaReleatedAdditionalLevelledQuestionsGenerator(
                                cplxKnowledgeArea));
            }
            
            return new OverallQuestionDrawer<LevelledQuestion>(groups, generators){
                Map<Question, LevelledQuestion> questionMapping = new HashMap<Question, LevelledQuestion>();
                RepeatingMemory<LevelledQuestion> wrongAnsweredQuestions = new RepeatingMemory<>();
                RepeatingMemory<LevelledQuestion> correctAnsweredQuestions = new RepeatingMemory<>();
                Set<LevelledQuestion> alreadyDrawn = new HashSet<>();
                LevelledQuestion previousQuestion;
                @Override
                public Question drawQuestion() {
                    
                    LevelledQuestion drawRandomSingle;
                    int count = 0;
                    while (true) {
                        List<List<LevelledQuestion>> questionQuestionList = new ArrayList<>();
                        if (!wrongAnsweredQuestions.isEmpty()) {
                            List<LevelledQuestion> contents = wrongAnsweredQuestions.getContents();
                            questionQuestionList.add(contents);
                            questionQuestionList.add(contents);
                        }
                        if (!correctAnsweredQuestions.isEmpty()) {
                            questionQuestionList.add(correctAnsweredQuestions.getContents());
                        }
                        List<LevelledQuestion> remainingQuestions = new ArrayList<>(getSelectedQuestions());
                        remainingQuestions.removeAll(alreadyDrawn);
                        if (!remainingQuestions.isEmpty()) {
                            questionQuestionList.add(remainingQuestions);
                        }
                        if (Math.random()>0.95) {
                            questionQuestionList.add(new ArrayList<LevelledQuestion>(alreadyDrawn));
                        }
                        List<LevelledQuestion> questionList= RandomDrawer.drawRandomSingle(questionQuestionList);
                        drawRandomSingle = RandomDrawer.drawRandomSingle(questionList);
                        if (drawRandomSingle!=previousQuestion || count>5) {
                            break;
                        } else {
                            count++;
                        }
                    }
                    previousQuestion = drawRandomSingle;
                    alreadyDrawn.add(drawRandomSingle);
                    Question result = RandomDrawer.drawRandomSingle(drawRandomSingle.getQuestions());
                    questionMapping.put(result, drawRandomSingle);
                    return result;
                }
                
                @Override
                public void answeredCorrect(Question question) {
                    LevelledQuestion levelledQuestion = questionMapping.get(question);
                    if (alreadyDrawn.contains(levelledQuestion) && !correctAnsweredQuestions.contains(levelledQuestion) && !wrongAnsweredQuestions.contains(levelledQuestion)) {
                        return;
                    }
                    if (levelledQuestion!=null && wrongAnsweredQuestions.contains(levelledQuestion)) {
                        boolean contained = wrongAnsweredQuestions.changeScore(levelledQuestion, -1);
                        if (!contained) {
                            correctAnsweredQuestions.changeScore(levelledQuestion, -1);
                        }
                    } else {
                        correctAnsweredQuestions.changeScore(levelledQuestion, -1);
                    }
                }
                
                @Override
                public void answeredWrong(Question question) {
                    LevelledQuestion levelledQuestion = questionMapping.get(question);
                    if (levelledQuestion!=null) {
                        if (correctAnsweredQuestions.contains(levelledQuestion)) {
                            correctAnsweredQuestions.remove(levelledQuestion);
                        }
                        wrongAnsweredQuestions.changeScore(levelledQuestion, 1);
                    }
                }
            };
        }
        
    }
    
}
