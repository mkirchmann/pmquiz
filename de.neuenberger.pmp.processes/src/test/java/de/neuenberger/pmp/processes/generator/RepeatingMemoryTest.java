package de.neuenberger.pmp.processes.generator;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RepeatingMemoryTest {
    
    @Test
    public void testPut() {
        String aString = "a";
        String bString = "b";
        
        RepeatingMemory<String> repeatingMemory = new RepeatingMemory<>(0,3);
        Assertions.assertThat(repeatingMemory.isEmpty()).isTrue();
        Assertions.assertThat(repeatingMemory.contains(aString)).isFalse();
        Assertions.assertThat(repeatingMemory.contains(bString)).isFalse();
        repeatingMemory.changeScore(aString, 1);
        repeatingMemory.changeScore(bString, 1);
        Assertions.assertThat(repeatingMemory.isEmpty()).isFalse();
        
        Assertions.assertThat(repeatingMemory.contains(aString)).isTrue();
        Assertions.assertThat(repeatingMemory.contains(bString)).isTrue();
        Assertions.assertThat(repeatingMemory.getContents()).containsOnly(aString, bString);
        
        boolean changeScore1 = repeatingMemory.changeScore(aString, -1);
        Assertions.assertThat(changeScore1).isTrue();
        Assertions.assertThat(repeatingMemory.contains(aString)).isTrue();
        
        boolean changeScore2 = repeatingMemory.changeScore(aString, -1);
        Assertions.assertThat(changeScore2).isTrue();
        Assertions.assertThat(repeatingMemory.contains(aString)).isTrue();
        
        boolean changeScore3 = repeatingMemory.changeScore(aString, -1); // minimum reached.
        Assertions.assertThat(changeScore3).isFalse();
        Assertions.assertThat(repeatingMemory.contains(aString)).isFalse();
        Assertions.assertThat(repeatingMemory.getContents()).containsOnly(bString);
        repeatingMemory.remove(bString);
        Assertions.assertThat(repeatingMemory.isEmpty()).isTrue();
        Assertions.assertThat(repeatingMemory.getContents()).isEmpty();
    }
}
