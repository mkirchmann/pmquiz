package de.neuenberger.pmp.processes.generator;

import java.util.ArrayList;
import java.util.List;

import sun.font.CreatedFontTracker;
import de.neuenberger.pmp.processes.model.LevelledQuestion;
import de.neuenberger.pmp.processes.model.Question;

public abstract class LevelledQuestionContainer implements QuestionContainer<LevelledQuestion> {

    private final String name;
    private List<LevelledQuestion> questions;
    
    public LevelledQuestionContainer(String theName) {
        name = theName;
    }
    
    @Override
    public List<LevelledQuestion> getAllQuestions() {
        if (questions==null) {
            questions = new ArrayList<LevelledQuestion>(createQuestions());
        }
        return questions;
    }

    @Override
    public String getName() {
        return name;
    }

    protected abstract List<LevelledQuestion> createQuestions();

}
