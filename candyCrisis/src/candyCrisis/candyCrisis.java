package candyCrisis;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class candyCrisis {

	//Initialize the board
	char board[][] = new char[3][5];
	Scanner k = new Scanner(System.in);
	long startTime = System.currentTimeMillis();
	
	//Reading and writing to file
	PrintWriter pw;
	FileInputStream fis;
	BufferedReader b;
	
	//Gets X and Y value for user input
	int fromR;
	int fromC;
	int toR;
	int toC;
	
	//Counts the total steps to completion. A sum total
	private int counter = 0;

	//Get file ready to write too
	public candyCrisis() {
		try {
			File file = new File("myfile.txt");

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
		}

		catch (IOException e) {
			System.out.println("Exception occurred:");
			e.printStackTrace();
		}

	}

	// Pick a level
	// Read the text file and puts the string into the board
	public String levelChoice() {

		String fileName = null;

		System.out.println("Enter 1 for custom text file");
		System.out.println("Enter 2 for preset levels");
		int choice = k.nextInt();
		if (choice == 1) {

			System.out.println("Enter filename with .txt at end");
			fileName = k.next();
		}

		if (choice == 2) {

			System.out.println("Pick from the following choices:");
			System.out.println(" -> 1 for Easy Level");
			System.out.println(" -> 2 for Medium Level");
			System.out.println(" -> 3 for Hard Level");
			System.out.println(" -> 4 for CandyExpert Level");

			int level = k.nextInt();

			switch (level) {
			case 1:
				fileName = "lvl1.txt";
				break;

			case 2:
				fileName = "lvl2.txt";
				break;

			case 3:
				fileName = "lvl3.txt";
				break;

			case 4:
				fileName = "lvl4.txt";
				break;

			}

		}

		String line = null;
		try {
			b = new BufferedReader(new FileReader(fileName));
			line = b.readLine();
		
			
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		
		System.out.println("-------------------------------");
		//System.out.println(line);
		return line;

	}

	public boolean loadToBoard(String line) {
		
		String tempLine = line;
		
		int i = 0;
		if (line != null) {
			tempLine = line.replaceAll("\\s+", "");
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 5; col++) {

					char current = tempLine.charAt(i);

					board[row][col] = current;
					i++;
					if (board[row][col] == 'e') {
						board[row][col] = ' ';

					}

				}

			}
			return true;
		} else
			return false;
	}

	// Save the sequence to a text file. Does not rewrite the file
	public int saveSeq(int fromR, int fromC) {

		int a = Integer.parseInt(fromR + "" + fromC);

		// Probably an easier way to do this but I just thought of this so far
		if (a == 00) {
			pw.print("A");
		}
		if (a == 01) {
			pw.print("B");
		}
		if (a == 02) {
			pw.print("C");
		}
		if (a == 03) {
			pw.print("D");
		}
		if (a == 04) {
			pw.print("E");
		}
		if (a == 10) {
			pw.print("F");
		}
		if (a == 11) {
			pw.print("G");
		}
		if (a == 12) {
			pw.print("H");
		}
		if (a == 13) {
			pw.print("I");
		}
		if (a == 14) {
			pw.print("J");
		}
		if (a == 20) {
			pw.print("K");
		}
		if (a == 21) {
			pw.print("L");
		}

		if (a == 22) {
			pw.print("M");
		}
		if (a == 23) {
			pw.print("N");
		}
		if (a == 24) {
			pw.print("O");
		}

		return 0;
	}

	// Print board
	public void printBoard() {

		// 00 01 02 03 04
		// 10 11 12 13 14
		// 20 21 22 23 24

		// A B C D E
		// F G H I J
		// K L M N O
		System.out.println("--------------------");
		System.out.println("  0 1 2 3 4");
		for (int i = 0; i < 3; i++) {

			if (i == 0) {
				System.out.print("0" + " ");
			}
			if (i == 1) {
				System.out.print("1" + " ");
			}
			if (i == 2) {
				System.out.print("2" + " ");
			}
			for (int j = 0; j < 5; j++) {

				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("--------------------");

	}
	
	public void userInput(){
		
		boolean move = false;
		do {
			System.out.println("Enter number:");
			while (!k.hasNextInt()) {
				String input = k.next();
				System.out.println(input + " not a number");
				System.out.println("Enter number:");

			}
			fromR = k.nextInt();
			fromC = k.nextInt();
			toR = k.nextInt();
			toC = k.nextInt();
			move = true;
		} while (!move);
	}

	// Method to move along the board
	// Move as you were moving on array
	// Want to move to 00 from 01 need to type in 0 enter 1 enter 0 enter 0
	public void makeMove() throws IOException {
		
		userInput();
		boolean canMove = false;

		while (!canMove) {

			// wont move if bad move
			if (board[toR][toC] != ' ') {
				System.out.println("Enter valid move: ");
				userInput();
				
			}
			// detects diagonal
			else if (toR == fromR + 1 && toC == fromC + 1 || toR == fromR - 1 && toC == fromC - 1
					|| toR == fromR - 1 && toC == fromC + 1 || toR == fromR + 1 && toC == fromC - 1) {

				System.out.println("Cannot move diagonal");
				System.out.println("Enter valid move: ");
				userInput();
			
			// detects if moving from across the board (ie 04 to 00 not allowed)
			}else if( Math.abs(toR-fromR) > 1 || Math.abs(toC-fromC) > 1  ){
				System.out.println("Woah there! ");
				System.out.println("Can't move across the board");
				userInput();
			}
			
			
			
			else {
				canMove = true;
			}
		}

		char current = board[fromR][fromC];
		board[toR][toC] = current;
		board[fromR][fromC] = ' ';

		saveSeq(fromR, fromC);
		printBoard();
		counter++;

		gameOver();
	}

	public boolean gameOver() throws IOException {

		if (board[0][0] == board[2][0] && board[0][1] == board[2][1] && board[0][2] == board[2][2]
				&& board[0][3] == board[2][3] && board[0][4] == board[2][4]) {
			System.out.println("Done a puzzle!");
			final long endTime = System.currentTimeMillis();
			pw.println();
			pw.println("Time: " + (endTime - startTime));
			pw.println("Number of moves: " + counter);
			//counter = 0;

			if (loadToBoard(b.readLine())) {

				System.out.println("Next puzzle!");
				
				printBoard();
				makeMove();

				// restart loop with next line
			}

			else {
				System.out.println("Game Completed");
				pw.close();
				System.exit(0);
			}

		}

		return false;
	}

}
