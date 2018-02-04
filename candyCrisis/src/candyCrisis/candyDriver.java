package candyCrisis;

import java.util.*;
import java.io.*;

public class candyDriver {

	public static void main(String[] args) {
		System.out.println("Candy Crisis v1.0");
		System.out.println("-----------------");
		candyCrisis n = new candyCrisis();
	
		n.levelChoice();
		n.printBoard();
		
		while(!n.gameOver(0)){
			try {
				n.makeMove();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	

}
