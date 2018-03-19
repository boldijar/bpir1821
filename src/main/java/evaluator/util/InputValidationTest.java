package evaluator.util;

import evaluator.exception.InputValidationFailedException;
import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.fail;

public class InputValidationTest {
    @Test
    public void validateEnunt() {
        try {
            InputValidation.validateEnunt("Is Paris the capital of Romania?");
        } catch (InputValidationFailedException ignored) {
            fail("Validarea unui enunt corect a esuat!");
        }
    }

    @Test
    public void validateEnuntWithEmptyString() {
        try {
            InputValidation.validateEnunt("");
            fail("Validarea unui enunt incorect a esuat!");
        } catch (InputValidationFailedException ex) {
            Assert.assertEquals(ex.getMessage(), "Enuntul este vid!");
        }
    }

    @Test
    public void validateVarianta1() {
        try {
            InputValidation.validateVarianta1("1) No");
        } catch (InputValidationFailedException ignored) {
            fail("Validarea unei variante1 corecte a esuat!");
        }
    }

    @Test
    public void validateVarianta1WithEmptyString() {
        try {
            InputValidation.validateVarianta1("");
            fail("Validarea unei variante1 incorecte a esuat!");
        } catch (InputValidationFailedException ex) {
            Assert.assertEquals(ex.getMessage(), "Varianta1 este vida!");
        }
    }

    @Test
    public void validateVarianta2() {
        try {
            InputValidation.validateVarianta2("2) Not sure");
        } catch (InputValidationFailedException ignored) {
            fail("Validarea unei variante2 corecte a esuat!");
        }
    }

    @Test
    public void validateVarianta2WithEmptyString() {
        try {
            InputValidation.validateVarianta2("");
            fail("Validarea unei variante2 incorecte a esuat!");
        } catch (InputValidationFailedException ex) {
            Assert.assertEquals(ex.getMessage(), "Varianta2 este vida!");
        }
    }

    @Test
    public void validateVarianta3() {
        try {
            InputValidation.validateVarianta3("3) Yes");
        } catch (InputValidationFailedException ignored) {
            fail("Validarea unei variante3 corecte a esuat!");
        }
    }

    @Test
    public void validateVarianta3WithEmptyString() {
        try {
            InputValidation.validateVarianta3("");
            fail("Validarea unei variante3 incorecte a esuat!");
        } catch (InputValidationFailedException ex) {
            Assert.assertEquals(ex.getMessage(), "Varianta3 este vida!");
        }
    }

    @Test
    public void validateVariantaCorecta() {
        try {
            InputValidation.validateVariantaCorecta("1");
        } catch (InputValidationFailedException ignored) {
            fail("Validarea enuntului a esuat!");
        }
    }

    @Test
    public void validateVariantaCorectaWithInvalidData() {
        try {
            InputValidation.validateVariantaCorecta("0");
            fail("Validare variantei corecte a esuat!");
        } catch (InputValidationFailedException ex) {
            Assert.assertEquals(ex.getMessage(), "Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}");
        }
    }

    @Test
    public void validateDomeniu() {
        try {
            InputValidation.validateDomeniu("Geografie");
        } catch (InputValidationFailedException ignored) {
            fail("Validarea unui domeniu corect a esuat!");
        }
    }

    @Test
    public void validateDomeniuWithInvalidData() {
        try {
            InputValidation.validateDomeniu("Desene animate");
            fail("Validare domeniului a esuat!");
        } catch (InputValidationFailedException ex) {
            Assert.assertEquals(ex.getMessage(), "Domeniul nu apartine domeniilor valide!");
        }
    }
}
