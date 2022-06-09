package net.datafaker;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberTest extends AbstractFakerTest {
    private static final Faker US_FAKER = new Faker(new Locale("en_US"));
    private static final Faker SE_FAKER = new Faker(new Locale("sv_SE"));
    private static final Faker CZ_FAKER = new Faker(new Locale("cs_CZ"));
    private static final Faker GB_FAKER = new Faker(new Locale("en_GB"));
    private static final Faker NO_FAKER = new Faker(new Locale("nb_NO"));
    private static final Faker NL_FAKER = new Faker(new Locale("nl_NL"));
    private static final Faker PH_FAKER = new Faker(new Locale("en_PH"));

    private final PhoneNumberUtil util = PhoneNumberUtil.getInstance();
    private final Phonenumber.PhoneNumber proto = new Phonenumber.PhoneNumber();

    @Test
    void testCellPhone_enUS() {
        String cellPhone = US_FAKER.phoneNumber().cellPhone();
        assertThat(cellPhone).matches("\\(?\\d+\\)?([- .]\\d+){1,3}");
    }


    @RepeatedTest(100)
    void testAllCellPhone_enUS() throws NumberParseException {
        String phoneNumber = US_FAKER.phoneNumber().phoneNumber();
        util.parse(phoneNumber, "US", proto);
        assertThat(util.isValidNumberForRegion(proto, "US")).as(phoneNumber).isTrue();
    }


    @RepeatedTest(100)
    void testAllCellPhone_svSE() throws NumberParseException {
        String phoneNumber = SE_FAKER.phoneNumber().phoneNumber();
        util.parse(phoneNumber, "SE", proto);
        assertThat(util.isValidNumberForRegion(proto, "SE")).as(phoneNumber).isTrue();
    }


    @RepeatedTest(100)
    void testAllCellPhone_csCZ() throws NumberParseException {
        String phoneNumber = CZ_FAKER.phoneNumber().phoneNumber();
        util.parse(phoneNumber, "CZ", proto);
        assertThat(util.isValidNumberForRegion(proto, "CZ")).as(phoneNumber).isTrue();
    }


    @Test
    void testAllCellPhone_enGB() throws NumberParseException {

        int errorCount = 0;
        for (int i = 0; i < 100; i++) {
            String phoneNumber = GB_FAKER.phoneNumber().phoneNumber();
            util.parse(phoneNumber, "GB", proto);
            if (!util.isValidNumberForRegion(proto, "GB")) {
                errorCount++;
            }
        }

        // Current score is ~420. Improvements are welcome.
        assertThat(errorCount).isLessThan(500);
    }


    @Test
    void testAllCellPhone_nbNO() throws NumberParseException {
        int errorCount = 0;

        for (int i = 0; i < 1000; i++) {
            String phoneNumber = NO_FAKER.phoneNumber().phoneNumber();
            util.parse(phoneNumber, "NO", proto);

            if (!util.isValidNumberForRegion(proto, "NO")) {
                errorCount++;
            }
        }

        // Not perfect yet, but should be good enough
        assertThat(errorCount).isLessThan(250);
    }


    @RepeatedTest(100)
    void testAllCellPhone_nl() throws NumberParseException {
        String phoneNumber = NL_FAKER.phoneNumber().phoneNumber();
        util.parse(phoneNumber, "NL", proto);
        assertThat(util.isValidNumberForRegion(proto, "NL")).as(phoneNumber).isTrue();
    }

    @Test
    void testPhone_esMx() {
        final Faker f = new Faker(new Locale("es_MX"));
        final Pattern cellPhonePattern = Pattern.compile("(044 )?\\(?\\d+\\)?([- .]\\d+){1,3}");
        final Pattern phoneNumberPattern = Pattern.compile("\\(?\\d+\\)?([- .]\\d+){1,3}");
        for (int i = 0; i < 100; i++) {
            assertThat(f.phoneNumber().cellPhone()).matches(cellPhonePattern);
            assertThat(f.phoneNumber().phoneNumber()).matches(phoneNumberPattern);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"en_CA", "ca"})
    void testPhone_CA(String language) {
        final Faker f = new Faker(new Locale(language));
        final String canadianAreaCode = "403|587|780|825|236|250|604|672|778|204|431|506|"
            + "709|782|902|226|249|289|343|365|416|437|519|548|613|647|705|807|905|367|"
            + "418|438|450|514|579|581|819|873|306|639|867";
        final Pattern caCodePattern = Pattern.compile(
            String.format("((1-)?(\\(?(%s)\\)?)|(%s))[- .]\\d{3}[- .]\\d{4}",
                canadianAreaCode, canadianAreaCode));
        for (int i = 0; i < 100; i++) {
            assertThat(f.phoneNumber().cellPhone()).matches(caCodePattern);
        }
    }

    @RepeatedTest(100)
    void testAllCellPhone_enPh() throws NumberParseException {
        String phoneNumber = PH_FAKER.phoneNumber().phoneNumber();
        util.parse(phoneNumber, "PH", proto);
        assertThat(util.isValidNumberForRegion(proto, "PH")).as(phoneNumber).isTrue();
    }

    @Test
    void testCellPhone() {
        assertThat(faker.phoneNumber().cellPhone()).matches("\\(?\\d+\\)?([- .]\\d+){1,3}");
    }

    @Test
    void testPhoneNumber() {
        assertThat(faker.phoneNumber().phoneNumber()).matches("\\(?\\d+\\)?([- .]x?\\d+){1,5}");
    }

    @Test
    void testExtension() {
        assertThat(faker.phoneNumber().extension()).matches("\\d{4}");
    }

    @Test
    void testSubscriberNumber() {
        assertThat(faker.phoneNumber().subscriberNumber()).matches("\\d{4}");
    }

    @Test
    void testSubscriberNumberWithLength() {
        assertThat(faker.phoneNumber().subscriberNumber(10)).matches("\\d{10}");
    }
}
