package kcg.knightmove;

/**
 * this class helps to validate user input
 * make sure the input by the user is a
 * valid integer
 *
 * @author J. Chen
 * */
public class KnightValidator {

	/**
	 * check user input, make sure it is valid positive integer
	 * @param numberOfMoves entered by user
	 * @return true if user input is valid
	 * */
	public boolean validate(String numberOfMoves) {
		int n = 0;

		try {
			n = Integer.valueOf(numberOfMoves);
		} catch (NumberFormatException e) {
			System.out.println("input entered: " + numberOfMoves + " is not Integer \n");
			return false;
		}

		if (n <= 0) {
			System.out.println("number entered: " + n + " is not positive \n");
			return false;
		}
		return true;
	}
}