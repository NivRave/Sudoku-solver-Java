import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) {
		try {
			Puzzle p = Puzzle.getInstance(getSudoku());
			chooseAction(p);
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist!");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static ArrayList<ArrayList<Cell>> getSudoku() throws FileNotFoundException {
		File puzzle = new File("src\\Sudoku.txt");
		ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
		if(puzzle.exists()) {
			Scanner scanner = new Scanner(puzzle);
			for(int i=0;i<9;i++) {
				board.add(new ArrayList<Cell>());//initialize rows
				for(int j=0;j<9;j++) {
					if (scanner.hasNextInt()) board.get(i).add(new Cell(scanner.nextInt()));//initialize cells
					else System.exit(0);
				}
			}
			scanner.close();
		}
		return board;	
	}
	
	public static void chooseAction(Puzzle p) {
		Scanner scanner=new Scanner(System.in);
		int c;
		while(true) {
			Menu.printMenu();
			try {
				c=scanner.nextInt();
				switch(c) {
				case 1:{
					try {
						Puzzle.printOriginal(getSudoku());
					} catch (FileNotFoundException e) {
						System.out.println("File does not exist!");
						e.printStackTrace();
						System.exit(0);				}
					System.out.println();
					break;
				}
				case 2:{
					p.solvePuzzle();
					System.out.println("Sudoku solved!!!!\n");
					System.out.println();
					break;
				}
				case 3:{
					if(p.solvePuzzle()) {
						System.out.println("Sudoku solved!!!!\n");
						p.printPuzzle();
						System.out.println();
					break;
					}
					else {
						System.out.println("Sudoku unsolveable!!!!\n");
						System.exit(0);
					}
				}
				case 4:{
					System.out.println("The program will exit! thank you");
					scanner.close();
					System.exit(0);
				}
				default:{
					System.out.println("Choose 1-4");
					break;
				}
				}
				
			}catch (InputMismatchException e){
				System.out.println("Choose 1-4");
				chooseAction(p);
				scanner.close();
			}
		}
	}
}