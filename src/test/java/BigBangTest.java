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

public class BigBangTest {
    private AppController appController;

    @Before
    public void setUp() {
        appController = new AppController();
    }

    @Test
    public void unitTestA() {
        String enunt = "Is Paris the capital of Romania?";
        String varianta1 = "1) No";
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

            appController.addNewIntrebare(new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu));

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
    public void unitTestB() {
        try {
            appController.createNewTest();
            fail("Testarea unitara pentru modulul B a esuat!");
        } catch (NotAbleToCreateTestException ex) {
            Assert.assertEquals(ex.getMessage(), "Nu exista suficiente intrebari pentru crearea unui test!(5)");
        }

        try {
            Intrebare intrebareSameDomain1 = new Intrebare("Ce faci1?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebareSameDomain2 = new Intrebare("Ce faci2?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebareSameDomain3 = new Intrebare("Ce faci3?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebareSameDomain4 = new Intrebare("Ce faci4?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebareSameDomain5 = new Intrebare("Ce faci5?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");

            appController.addNewIntrebare(intrebareSameDomain1);
            appController.addNewIntrebare(intrebareSameDomain2);
            appController.addNewIntrebare(intrebareSameDomain3);
            appController.addNewIntrebare(intrebareSameDomain4);
            appController.addNewIntrebare(intrebareSameDomain5);

            try {
                appController.createNewTest();
                fail("Testarea unitara pentru modulul B a esuat!");
            } catch (NotAbleToCreateTestException ex) {
                Assert.assertEquals(ex.getMessage(), "Nu exista suficiente domenii pentru crearea unui test!(5)");
            }
        } catch (InputValidationFailedException | DuplicateIntrebareException ignored) {
            fail("Testarea unitara pentru modulul B a esuat!");
        }

        try {
            Intrebare intrebare1 = new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare2 = new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Geografie");
            Intrebare intrebare3 = new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Biologie");
            Intrebare intrebare4 = new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Istorie");
            Intrebare intrebare5 = new Intrebare("Ce faci?", "1) Bine", "2) Ok", "3) Nasol", "1", "Matematica");

            appController.addNewIntrebare(intrebare1);
            appController.addNewIntrebare(intrebare2);
            appController.addNewIntrebare(intrebare3);
            appController.addNewIntrebare(intrebare4);
            appController.addNewIntrebare(intrebare5);

            appController.createNewTest();
        } catch (InputValidationFailedException | DuplicateIntrebareException | NotAbleToCreateTestException ignored) {
            fail("Testarea unitara pentru modulul B a esuat!");
        }
    }

    @Test
    public void unitTestC() {
        try {
            appController.getStatistica();
            fail("Testarea unitara pentru modulul C a esuat!");
        } catch (NotAbleToCreateStatisticsException ignored) {
            Assert.assertTrue(true);
        }

        try {
            Intrebare intrebare1 = new Intrebare("Ce faci1?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare2 = new Intrebare("Ce faci2?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare3 = new Intrebare("Ce faci5?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");

            appController.addNewIntrebare(intrebare1);
            appController.addNewIntrebare(intrebare2);
            appController.addNewIntrebare(intrebare3);

            Statistica statistica = appController.getStatistica();
            Map<String, Integer> intrebariDomenii = statistica.getIntrebariDomenii();
            Assert.assertEquals(intrebariDomenii.get("IT").toString(), "3");
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateStatisticsException ignored) {
            fail("Testarea unitara pentru modulul C a esuat!");
        }
    }

    @Test
    public void integrationTesting() {
        try {
            Intrebare intrebare1 = new Intrebare("Ce faci1?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare2 = new Intrebare("Ce faci2?", "1) Bine", "2) Ok", "3) Nasol", "1", "Geografie");
            Intrebare intrebare3 = new Intrebare("Ce faci3?", "1) Bine", "2) Ok", "3) Nasol", "1", "Biologie");
            Intrebare intrebare4 = new Intrebare("Ce faci4?", "1) Bine", "2) Ok", "3) Nasol", "1", "Istorie");
            Intrebare intrebare5 = new Intrebare("Ce faci5?", "1) Bine", "2) Ok", "3) Nasol", "1", "Matematica");

            appController.addNewIntrebare(intrebare1);
            appController.addNewIntrebare(intrebare2);
            appController.addNewIntrebare(intrebare3);
            appController.addNewIntrebare(intrebare4);
            appController.addNewIntrebare(intrebare5);

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
