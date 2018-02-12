package candyCrisis;

import java.util.*;
import java.io.*;

public class candyDriver {

	public static void main(String[] args) throws IOException {
		System.out.println("Candy Crisis v1.0");
		System.out.println("-------------------------------");
		candyCrisis n = new candyCrisis();

		n.loadToBoard(n.levelChoice());
		n.printBoard();

		while (!n.gameOver()) {
			
				n.makeMove();
			

				
			}
		}

	}


