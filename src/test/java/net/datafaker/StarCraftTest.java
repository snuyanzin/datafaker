package net.datafaker;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class StarCraftTest extends AbstractFakerTest {
    private static final Pattern NO_LEADING_TRAILING_WHITESPACE_REGEX = Pattern.compile("^(?! )[-A-Za-z\\d' ]*(?<! )$");

    @Test
    void testUnit() {
        String unit = faker.starCraft().unit();
        assertThat(unit)
            .isNotEmpty()
            .matches(NO_LEADING_TRAILING_WHITESPACE_REGEX);
    }

    @RepeatedTest(1000)
    void testUnitOneThousand() {
        String unit = faker.starCraft().unit();
        // System.out.println(unit);
        assertThat(unit)
            .isNotEmpty()
            .matches(NO_LEADING_TRAILING_WHITESPACE_REGEX);
    }

    @Test
    void testBuilding() {
        String building = faker.starCraft().building();
        assertThat(building)
            .isNotEmpty()
            .matches(NO_LEADING_TRAILING_WHITESPACE_REGEX);
    }

    @Test
    void testCharacter() {
        String character = faker.starCraft().character();
        assertThat(character)
            .isNotEmpty()
            .matches(NO_LEADING_TRAILING_WHITESPACE_REGEX);
    }

    @Test
    void testPlanet() {
        String planet = faker.starCraft().planet();
        assertThat(planet)
            .isNotEmpty()
            .matches(NO_LEADING_TRAILING_WHITESPACE_REGEX);
    }

    @RepeatedTest(1000)
    void testPlanetOneThousand() {
        String planet = faker.starCraft().planet();
        assertThat(planet)
            .isNotEmpty()
            .matches(NO_LEADING_TRAILING_WHITESPACE_REGEX);
    }

}
