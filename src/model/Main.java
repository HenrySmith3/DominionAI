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
		//GameState state = new GameState(2,2, "Mine Village Festival Labratory Smithy Workshop WoodCutter Cellar CouncilRoom Market");
		GameState state = new GameState(2,2, "Feast Village Festival Labratory Smithy Workshop WoodCutter Cellar CouncilRoom Market");
		//second one is only cards that currently work
		state.play();
	}
}