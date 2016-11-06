package kcg.knightmove;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static kcg.knightmove.KnightMoveMain.QUIT;
import static kcg.knightmove.KnightMoveMain.EXIT;

/**
 * this class contains some handy helper functions to interact
 * with the user
 *
 * @author J. Chen
 * */
public class KnightUserHelper {


	/**
	 * get user input from terminal
	 *
	 * @return inputLine, a String representation of user input
	 *
	 * */
	public String getUserInput(){
		String inputLine = null;
		try{
			BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));
			inputLine = is.readLine();
			if(inputLine.length() == 0) return null;
		}catch(IOException e){
			System.out.println("IOException: "+e);
		}
		return inputLine;
	}

	/**
	 * display some basic welcome msg
	 * */
	public void displayHelpMsg() {
		System.out.println("");
		System.out.println("Type " + QUIT + " or " + EXIT +" to exit the program");
		System.out.println("Type 'help' to display help msg");
		System.out.println("Enter number: ");
		System.out.println("Press [enter] to start program with your number");

	}


}