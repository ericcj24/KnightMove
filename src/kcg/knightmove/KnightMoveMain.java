package kcg.knightmove;

/**
 * main class for KnightMove program
 *
 * @author J. Chen
 * */
public class KnightMoveMain {

	public final static String QUIT = "quit";
	public final static String EXIT = "exit";
	public final static String HELP = "help";

	public static void main(String[] args) {

		boolean verbose = false;
		if (args.length > 0) {
			String verboseArg = args[0];
			String[] vs = verboseArg.split(":");
			if (vs.length > 1 && vs[0].trim().equalsIgnoreCase("verbose")) {
				verbose = Boolean.parseBoolean(vs[1].trim());
			}
		}

		System.out.println("welcome, init program ...");

		KnightMoveMain appMain = new KnightMoveMain();
		appMain.run(verbose);

	}

	private void run(boolean verbose) {

		KnightValidator validator = new KnightValidator();
		KnightUserHelper helper = new KnightUserHelper();

		helper.displayHelpMsg();

		while(true){

			// get each line of input
			String userInput = helper.getUserInput();

			// if user only press enter
			if(userInput == null){
				// doh!
			}
			else{
				// if user wants to quit program, break loop
				if (userInput.equalsIgnoreCase(QUIT) || userInput.equalsIgnoreCase(EXIT)) {
					System.out.println("Goodbye ~");
					break;
				} else if (userInput.equalsIgnoreCase(HELP)) {
					helper.displayHelpMsg();

				} else {
					// otherwise check for user's input
					boolean errorFlag = validator.validate(userInput);

					// if there is error
					if (!errorFlag) {
						// print the error
						System.out.println("invalid input please try again \n");
					}
					else{
						// validator already checked, so safe here
						int numberOfMoves = Integer.valueOf(userInput);

						KnightMoveApp app = new KnightMoveApp(numberOfMoves, verbose);

						app.start();
					}
				}
			}
		}

	}



}