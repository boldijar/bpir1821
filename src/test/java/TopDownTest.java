package tests;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.util.InputValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.fail;

public class TopDownTest {
    private AppController appController;

    @Before
    public void setUp() {
        appController = new AppController();
    }

    @Test
    public void unitTestA() {
        String enunt = "Is Paris the capital of Romania?";
        String varianta1 = "1) No way";
        String varianta2 = "2) Not sure";
        String varianta3 = "3) Yes";
        String variantaCorecta = "1";
        String domeniu = "Geografie";

        try {
            InputValidation.validateEnunt(enunt);
            InputValidation.validateVarianta1(varianta1);
            InputValidation.validateVarianta2(varianta2);
            InputValidation.validateVarianta3(varianta3);
            InputValidation.validateVariantaCorecta(variantaCorecta);
            InputValidation.validateDomeniu(domeniu);

            Intrebare intrebare = new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
            appController.addNewIntrebare(intrebare);

            try {
                appController.addNewIntrebare(new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu));
            } catch (DuplicateIntrebareException ignored) {
                Assert.assertTrue(true);
            }
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            fail("Testarea unitara pentru modulul A a esuat!");
        }
    }

    @Test
    public void integrationB() {
        try {
            appController.addNewIntrebare(new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT"));
            appController.addNewIntrebare(new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Geografie"));
            appController.addNewIntrebare(new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Biologie"));
            appController.addNewIntrebare(new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Istorie"));
            appController.addNewIntrebare(new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Matematica"));

            appController.createNewTest();
        } catch (InputValidationFailedException | DuplicateIntrebareException | NotAbleToCreateTestException ignored) {
            fail("Integrarea modulul B a esuat!");
        }
    }

    @Test
    public void integrationC() {
        try {
            appController.addNewIntrebare(new Intrebare("Ce faci1?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT"));
            appController.addNewIntrebare(new Intrebare("Ce faci2?", "1) Bine", "2) Ok", "3) Nasol", "1", "Geografie"));
            appController.addNewIntrebare(new Intrebare("Ce faci3?", "1) Bine", "2) Ok", "3) Nasol", "1", "Biologie"));
            appController.addNewIntrebare(new Intrebare("Ce faci4?", "1) Bine", "2) Ok", "3) Nasol", "1", "Istorie"));
            appController.addNewIntrebare(new Intrebare("Ce faci5?", "1) Bine", "2) Ok", "3) Nasol", "1", "Matematica"));

            appController.createNewTest();
            Statistica statistica = appController.getStatistica();
            Map<String, Integer> intrebariDomenii = statistica.getIntrebariDomenii();
            Assert.assertEquals(intrebariDomenii.get("IT").toString(), "1");
            Assert.assertEquals(intrebariDomenii.get("Geografie").toString(), "1");
            Assert.assertEquals(intrebariDomenii.get("Biologie").toString(), "1");
            Assert.assertEquals(intrebariDomenii.get("Istorie").toString(), "1");
            Assert.assertEquals(intrebariDomenii.get("Matematica").toString(), "1");
        } catch (DuplicateIntrebareException| InputValidationFailedException | NotAbleToCreateTestException | NotAbleToCreateStatisticsException e) {
            fail("Testarea de integrare a esuat!");
        }
    }
}
