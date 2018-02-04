package candyCrisis;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class candyCrisis {

	char board[][] = new char[3][5];
	Scanner k = new Scanner(System.in);

	//Pick a level
	public void levelChoice() {
		
		String fileName = null;
		
		System.out.println("Enter 1 for custom text file");
		System.out.println("Enter 2 for preset levels");
		int choice = k.nextInt();
		if(choice == 1){
			
		System.out.println("Enter filename with .txt at end");
		fileName = k.next();
		}
		
		if(choice == 2){
		
		System.out.println("Pick from the following choices:");
		System.out.println(" -> 1 for Easy Level");
		System.out.println(" -> 2 for Medium Level");
		System.out.println(" -> 3 for Hard Level");
		System.out.println(" -> 4 for CandyExpert Level");
		
		int level = k.nextInt();
		
		switch(level){
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
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			char current;

			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 5; col++) {

					current = (char) fis.read();
				
					board[row][col] = current;
					if (board[row][col] == 'e') {
						board[row][col] = ' ';

					}

				}

			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------");

	}

	// Save the sequence to a text file. Does not rewrite the file
	public int saveSeq(int fromR, int fromC) {

		try {
			File file = new File("myfile.txt");

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			int a = Integer.parseInt(fromR + "" + fromC);

			// Probably an easier way to do this but I just thought of this so
			// far
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

			// pw.print(fromR+ " " + fromC);
			pw.close();

		} catch (IOException e) {
			System.out.println("Exception occurred:");
			e.printStackTrace();
		}
		return 0;
	}
	
	//Print board
	public void printBoard() {

		// 00 01 02 03 04
		// 10 11 12 13 14
		// 20 21 22 23 24

		// A B C D E
		// F G H I J
		// K L M N O
		//System.out.println("--------------------");
		System.out.println("  0 1 2 3 4");
		for (int i = 0; i < 3; i++) {
			
			if( i == 0){
				System.out.print("0" + " ");
			}
			if( i == 1){
				System.out.print("1" + " ");
			}
			if( i == 2){
				System.out.print("2" + " ");
			}
			for (int j = 0; j < 5; j++) {

				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		
		
		System.out.println("--------------------");

	}
	
	//Method to move along the board
	//Move as you were moving on array
	//Want to move to 00 from 01 need to type in 0 enter 1 enter 0 enter 0
	public void makeMove() throws IOException {
		
		int fromR = k.nextInt();
		int fromC = k.nextInt();

		int toR = k.nextInt();
		int toC = k.nextInt();

		boolean canMove = false;

		// NEED TO FIX, can move diagonally
		while (!canMove) {
			if (board[toR][toC] != ' ') {
				System.out.println("Enter valid move: ");
				toR = k.nextInt();
				toC = k.nextInt();
			} else
				canMove = true;
		}

		char current = board[fromR][fromC];
		board[toR][toC] = current;
		board[fromR][fromC] = ' ';

		saveSeq(fromR, fromC);
		printBoard();
		/*
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {

				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		*/
		gameOver(1);
	}

	public boolean gameOver(int cond) {

		// condition when top row matches bot row
		if (cond == 1) {
			if(board[0][0] == board[2][0] && board[0][1] == board[2][1] && board[0][2] == board[2][2] && board[0][3] == board[2][3] && board[0][4] == board[2][1] ) {
				System.out.println("Game Completed");
				//insert time here
				System.exit(0);
				
			}
		}
		return false;
	}

}