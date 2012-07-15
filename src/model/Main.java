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
		GameState state = new GameState(2,0);
		state.play();
	}

}
