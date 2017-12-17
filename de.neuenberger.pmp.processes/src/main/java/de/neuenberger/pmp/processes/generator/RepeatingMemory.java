package de.neuenberger.pmp.processes.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntPredicate;

import de.neuenberger.pmp.processes.model.LevelledQuestion;

/**
 * A List that can store points for elements. There is a maximum score that can not be exceeded, and a minimum score at which the item would be removed.
 * @author Michael Kirchmann
 *
 * @param <E> Type of elements stored.
 */
public class RepeatingMemory<E> {
    private final Map<E,Integer> memory = new HashMap<>();
    private final int minScore;
    private final int maxScore;
    
    /**
     * Constructor.
     */
    public RepeatingMemory() {
        this(0,3);
    }
    
    /**
     * 
     * @param theMinScore
     * @param theMaxScore
     */
    public RepeatingMemory(int theMinScore, int theMaxScore) {
        minScore = theMinScore;
        maxScore = theMaxScore;
    }

    /**
     * See {@link Map#containsKey(Object)}.
     */
    public boolean contains(E e) {
        return memory.containsKey(e);
    }
    
    /**
     * Puts an element with a delta score.
     * @param e Element e.
     * @param delta the score to be added to the current score.
     * @return Returns if the element is contained after this operation (could be it has been removed).
     */
    public boolean changeScore(E e, int delta) {
        Optional<Integer> containedValue = Optional.ofNullable(memory.get(e));
        Integer currentScore = containedValue.map(x->Math.max(minScore, Math.min(maxScore, x+delta))).orElse(maxScore);
        if (containedValue.isPresent() && currentScore<=minScore) {
            memory.remove(e);
            return false;
        } else {
            memory.put(e, currentScore);
        }
        return true;
    }
    
    public List<E> getContents() {
        return new ArrayList<>(memory.keySet());
    }

    /**
     * See {@link Map#isEmpty()}.
     */
    public boolean isEmpty() {
        return memory.isEmpty();
    }

    /**
     * See {@link Map#remove(Object)}.
     */
    public void remove(E e) {
        memory.remove(e);
    }
}
