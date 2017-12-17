package de.neuenberger.pmp.processes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelledQuestion {
    private final Map<Integer, Question> mapLevelToQuestion = new HashMap<>();
    
    public Question getQuestionForLevel(int level) {
        return mapLevelToQuestion.get(level);
    }

    public void setQuestionForLevel(Question question, Integer level) {
        mapLevelToQuestion.put(level, question);
    }
    
    public List<Question> getQuestions() {
        return new ArrayList<>(mapLevelToQuestion.values());
    }
}
