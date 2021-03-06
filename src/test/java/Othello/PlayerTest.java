package Othello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Othello.Color;
import Othello.Player;

public class PlayerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getColorTest() {
		Player player1 = new Player(Color.Black);
		assertEquals(Color.Black, player1.getColor());
		
		Player player2 = new Player(Color.White);
		assertEquals(Color.White, player2.getColor());
	}
}
