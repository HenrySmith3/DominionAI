package model;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
		GameState state = new GameState(0,2, "Mine Village Festival Labratory Smithy Workshop WoodCutter Cellar CouncilRoom Market");
		state.play();
	}
}