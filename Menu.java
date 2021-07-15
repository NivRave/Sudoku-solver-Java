public class Menu {
	private static String[] options= {"1. Print original sudoku","2. Solve sudoku","3. Print solved sudoku","4. Exit"};
	
	public static void printMenu() {
		System.out.println("Welcome to sudoku solver! choose an action:");
		for(String s :options){
			System.out.println(s);
		}
		System.out.println("You selection: ");
	}
}
