package net.datafaker;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Locale;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class IdNumberTest extends AbstractFakerTest {

    private static final Faker EN_ZA_FAKER = new Faker(new Locale("en_ZA"));
    private static final Faker SV_SE_FAKER = new Faker(new Locale("sv_SE"));
    private static final Pattern SSN_VALID = Pattern.compile("[0-8]\\d{2}-\\d{2}-\\d{4}");
    private static final Pattern VALID_SWEDISH_SSN = Pattern.compile("\\d{6}[-+]\\d{4}");
    private static final Pattern INVALID_SWEDISH_SSN = Pattern.compile("\\d{6}[-+]\\d{4}");
    private static final Pattern INVALID_EN_ZA_SSN = Pattern.compile("\\d{10}([01])8\\d");

    private static final Pattern PESEL_NUMBER = Pattern.compile("\\d{11}");

    @Test
    void testValid() {
        assertThat(faker.idNumber().valid()).matches("[0-8]\\d{2}-\\d{2}-\\d{4}");
    }

    @Test
    void testInvalid() {
        assertThat(faker.idNumber().invalid()).matches("\\d\\d{2}-\\d{2}-\\d{4}");
    }

    @RepeatedTest(100)
    void testSsnValid() {
        assertThat(faker.idNumber().ssnValid()).matches(SSN_VALID);
    }

    @RepeatedTest(100)
    void testValidSwedishSsn() {
        assertThat(SV_SE_FAKER.idNumber().validSvSeSsn()).matches(VALID_SWEDISH_SSN);
    }

    @RepeatedTest(100)
    void testInvalidSwedishSsn() {
        assertThat(SV_SE_FAKER.idNumber().invalidSvSeSsn()).matches(INVALID_SWEDISH_SSN);
    }

    @RepeatedTest(100)
    void testValidEnZaSsn() {
        assertThat(EN_ZA_FAKER.idNumber().validEnZaSsn()).matches("\\d{10}([01])8\\d");
    }

    @RepeatedTest(100)
    void testInvalidEnZaSsn() {
        assertThat(EN_ZA_FAKER.idNumber().inValidEnZaSsn()).matches(INVALID_EN_ZA_SSN);
    }

    @RepeatedTest(100)
    void testSingaporeanFin() {
        assertThat(faker.idNumber().singaporeanFin()).matches("G\\d{7}[A-Z]");
    }

    @RepeatedTest(100)
    void testSingaporeanFinBefore2000() {
        assertThat(faker.idNumber().singaporeanFinBefore2000()).matches("F\\d{7}[A-Z]");
    }

    @RepeatedTest(100)
    void testSingaporeanUin() {
        assertThat(faker.idNumber().singaporeanUin()).matches("T\\d{7}[A-Z]");
    }

    @RepeatedTest(100)
    void testSingaporeanUinBefore2000() {
        assertThat(faker.idNumber().singaporeanUinBefore2000()).matches("S\\d{7}[A-Z]");
    }

    @RepeatedTest(100)
    void testPeselNumber() {
        assertThat(faker.idNumber().peselNumber()).matches(PESEL_NUMBER);
    }
}
