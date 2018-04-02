package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.fail;

public class AppControllerTest {
    private AppController appControllerInstance;

    @Before
    public void setUp() throws Exception {
        appControllerInstance = new AppController();
    }

    @Test
    public void validateCreateNewTestWithNoQuestionsInRepo() {
        try {
            appControllerInstance.createNewTest();
        } catch (NotAbleToCreateTestException ex) {
            Assert.assertEquals(ex.getMessage(), "Nu exista suficiente intrebari pentru crearea unui test!(5)");
        }
    }

    @Test
    public void validateCreateNewTestWithInsufficientDomains() {
        try {
            Intrebare intrebare1 = new Intrebare("Ce faci1?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare2 = new Intrebare("Ce faci2?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare3 = new Intrebare("Ce faci3?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare4 = new Intrebare("Ce faci4?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare5 = new Intrebare("Ce faci5?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");

            appControllerInstance.addNewIntrebare(intrebare1);
            appControllerInstance.addNewIntrebare(intrebare2);
            appControllerInstance.addNewIntrebare(intrebare3);
            appControllerInstance.addNewIntrebare(intrebare4);
            appControllerInstance.addNewIntrebare(intrebare5);

            try {
                appControllerInstance.createNewTest();
                fail("Validarea existentei suficientor domenii a esuat!");
            } catch (NotAbleToCreateTestException ex) {
                Assert.assertEquals(ex.getMessage(), "Nu exista suficiente domenii pentru crearea unui test!(5)");
            }
        } catch (InputValidationFailedException | DuplicateIntrebareException ignored) {
            fail("Validarea existentei suficientor domenii a esuat in setup!");
        }
    }

    @Test
    public void validateCreateNewTestSuccessfully() {
        try {
            Intrebare intrebare1 = new Intrebare("Ce faci1?", "1) Bine", "2) Ok", "3) Nasol", "1", "IT");
            Intrebare intrebare2 = new Intrebare("Ce faci2?", "1) Bine", "2) Ok", "3) Nasol", "1", "Geografie");
            Intrebare intrebare3 = new Intrebare("Ce faci3?", "1) Bine", "2) Ok", "3) Nasol", "1", "Biologie");
            Intrebare intrebare4 = new Intrebare("Ce faci4?", "1) Bine", "2) Ok", "3) Nasol", "1", "Istorie");
            Intrebare intrebare5 = new Intrebare("Ce faci5?", "1) Bine", "2) Ok", "3) Nasol", "1", "Matematica");

            appControllerInstance.addNewIntrebare(intrebare1);
            appControllerInstance.addNewIntrebare(intrebare2);
            appControllerInstance.addNewIntrebare(intrebare3);
            appControllerInstance.addNewIntrebare(intrebare4);
            appControllerInstance.addNewIntrebare(intrebare5);

            appControllerInstance.createNewTest();
        } catch (InputValidationFailedException | DuplicateIntrebareException | NotAbleToCreateTestException ignored) {
            fail("Validarea crearii cu succes a unui test a esuat!");
        }
    }
}
