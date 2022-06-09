package net.datafaker;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

class FamousLastWordsTest extends AbstractFakerTest {

    @RepeatedTest(100)
    @Execution(ExecutionMode.CONCURRENT)
    void testLastWords() {
        assertThat(faker.famousLastWords().lastWords()).matches("^[A-Za-z- .,'!?-…]+$");
    }
}
