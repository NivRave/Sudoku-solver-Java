import java.util.*;

public class Puzzle {
	
	private static Puzzle puzzle=null;
	private	ArrayList<ArrayList<Cell>> board;
	static int unsolvedCells=0;
	
	private Puzzle(ArrayList<ArrayList<Cell>> puzzle){
		initBoard(puzzle);
		initUnsolved();
	}
	
	public static Puzzle getInstance(ArrayList<ArrayList<Cell>> p){	//Singleton instance
        if (puzzle == null)
        	puzzle = new Puzzle(p);
  
        return puzzle;
    }
	
	public void initUnsolved() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(getCell(i,j).getValue()==0) unsolvedCells++;
			}
		}
	}
	
	private void initBoard(ArrayList<ArrayList<Cell>> puzzle) {
		this.board=puzzle;
	}
	
	private Cell getCell(int row, int column) {
		return board.get(row).get(column);
	}
	
	public void printPuzzle() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(getCell(i,j).getValue());
			}
			System.out.println();
		}
	}
	
	public boolean solvePuzzle() {
		 int tmp = findEmptyCell();
		 if(isSolved()) return true;
		 int row = tmp/10;
	     int column = tmp%10;
	     for (int value = 1; value <= 9; value++) {
	            if (checkCellValidity(row, column, value)) {
	            	getCell(row,column).setValue(value);	//force cell=value
	                if (solvePuzzle()) {
	                    return true;
	                }
	                else{
	                	getCell(row,column).setValue(0);	//current path failed
	                }
	            }
	        }
	     return false;
	}
	
	private int findEmptyCell() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(getCell(i,j).getValue()==0) return (i*10+j);	//returns 2 digits num, 10's is row and 1's is col
			}
		}
		unsolvedCells=0;
		return -1;	//will never happen
	}
	
	private boolean checkRow(int row, int value) {	//check if forced value exist in the same row
		for(int i=0;i<9;i++) {
			if(getCell(row,i).getValue()==value) return false;
		}
		return true;
	}
	
	private boolean checkColumn(int column, int value) {//check if forced value exist in the same col
		for(int i=0;i<9;i++) {
			if(getCell(i,column).getValue()==value) return false;
		}
		return true;
	}
	
	private boolean checkBox(int row, int column, int value) {//check if forced value exist in the same box
		int firstRow=row-row%3;
        int firstColumn=column-column%3;
        for (int i=firstRow;i<firstRow+3;i++){
            for (int j = firstColumn;j< firstColumn + 3; j++){
                if (getCell(i,j).getValue()==value){
                    return false;
                }
            }
        }
        return true;
	}
	
	private boolean checkCellValidity(int row, int column, int value) {
		return (checkRow(row, value) && checkColumn(column, value) && checkBox(row, column, value));
	}
	
	public boolean isSolved() {	//return true if all cells are solved
		return (unsolvedCells==0) ? true : false;
	}
	
	public static void printOriginal(ArrayList<ArrayList<Cell>> puzzle) {	//print original
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(puzzle.get(i).get(j).getValue());
			}
			System.out.println();
		}
	}
	public static int getUnsolved() {
		return unsolvedCells;
	}
	
}