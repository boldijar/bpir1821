package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.controller.AppController;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.model.Test;

public class StartApp {

	private static final String file = "intrebari.txt";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		AppController appController = new AppController();
		
		boolean activ = true;
		String optiune = null;
		
		while(activ){
			
			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");
			
			optiune = console.readLine();
			
			switch(optiune){
			case "1" :
				System.out.println("Enunt: ");
				String enunt = console.readLine();
				System.out.println("Varianta 1: ");
				String varianta1 = console.readLine();
				System.out.println("Varianta 2: ");
				String varianta2 = console.readLine();
				System.out.println("Varianta 3: ");
				String varianta3 = console.readLine();
				System.out.println("Varianta corecta: ");
				String variantaCorecta = console.readLine();
				System.out.println("Domeniu: ");
				String domeniu = console.readLine();

				try {
					appController.addNewIntrebare(new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu));
				} catch (DuplicateIntrebareException e) {
					System.out.println("Exista deja intrebarea data!");
				} catch (InputValidationFailedException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "2" :
				try {
					Test test = appController.createNewTest();
					System.out.println("Am creat cu succes testul! Intrebarile sunt:\n");
					for (Intrebare intrebare : test.getIntrebari()) {
						System.out.println(intrebare.getEnunt());
					}
				} catch (NotAbleToCreateTestException e) {
					System.out.println("Nu am putut testul: " + e.getMessage());
				}
				break;
			case "3" :
				try {
					appController.loadIntrebariFromFile(file);
				} catch (Exception e) {
					System.out.println("Nu am putut citi din fisierul input!");
					break;
				}
				Statistica statistica;
				try {
					statistica = appController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
					System.out.println("Nu am putut crea statistica: " + e.getMessage());
				}
				
				break;
			case "4" :
				activ = false;
				break;
			default:
				break;
			}
		}
		
	}

}
