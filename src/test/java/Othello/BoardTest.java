package Othello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Othello.Board;
import Othello.Color;
import Othello.Direction;
import Othello.Disk;

public class BoardTest {
	Board board;
	Disk[][] auxBoard;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		auxBoard = board.getGameBoard();
	}

	@Test
	public void BoardConstructorTest() {
		
		for(int i=0; i<auxBoard.length; i++) {
			for(int j=0; j<auxBoard.length; j++) {
				if((i==3 && j==3) || (i==4 && j==4)) {
					assertEquals(Color.White, auxBoard[i][j].getColor());
				}
				else if ((i==3 && j==4) || (i==4 && j==3)) {
					assertEquals(Color.Black, auxBoard[i][j].getColor());
					}
				else {
					assertEquals(null, auxBoard[i][j]);
					}
			}
		}
	}
	
	@Test
	public void isFullTest() {
		//Condition Coverage, Decision Coverage
		
		board.setTotalWhites(25);
		board.setTotalBlacks(32);
		boolean res_25W_32B = board.isFull();
		assertFalse(res_25W_32B);
		
		board.setTotalWhites(32);
		board.setTotalBlacks(32);
		boolean res_32W_32B = board.isFull();
		assertTrue(res_32W_32B);
	}
	
	@Test
	public void updateScoreTest() {
		//Score at initial state 
		board.updateScore();
		assertEquals(2, board.getTotalBlacks());
		assertEquals(2, board.getTotalWhites());
		
		//Score after fail placing disk attempt  
		board.placeDisk(1, 1, Color.Black);
		board.updateScore();
		assertEquals(2, board.getTotalBlacks());
		assertEquals(2, board.getTotalWhites());
		
		//Score after correct placing disk attempt  
		board.placeDisk(2, 3, Color.Black);
		board.updateScore();
	    assertEquals(4, board.getTotalBlacks());
		assertEquals(1, board.getTotalWhites());
	}
	
	@Test
	public void outOfLimitsTest() {
		//Black box test
		//position out of board next to corners
		assertTrue(board.proxyoutOfLimits(0, -1));
		assertTrue(board.proxyoutOfLimits(-1, -1));
		assertTrue(board.proxyoutOfLimits(-1, 7));
		assertTrue(board.proxyoutOfLimits(0, 8));
		assertTrue(board.proxyoutOfLimits(-1, 8));
		assertTrue(board.proxyoutOfLimits(8, 0));
		assertTrue(board.proxyoutOfLimits(7, -1));
		assertTrue(board.proxyoutOfLimits(8, -1));
		assertTrue(board.proxyoutOfLimits(7, 8));
		assertTrue(board.proxyoutOfLimits(8, 7));
		assertTrue(board.proxyoutOfLimits(8, 8));	
		
		//position in corners
		assertFalse(board.proxyoutOfLimits(0, 0));
		assertFalse(board.proxyoutOfLimits(0, 7));
		assertFalse(board.proxyoutOfLimits(7, 0));
		assertFalse(board.proxyoutOfLimits(7, 7));
		
		//position out of board next to laterals
		assertTrue(board.proxyoutOfLimits(-3, 0));
		assertTrue(board.proxyoutOfLimits(0, -3));
		assertTrue(board.proxyoutOfLimits(12, 0));
		assertTrue(board.proxyoutOfLimits(0, 9));
		assertTrue(board.proxyoutOfLimits(7, -3));
		assertTrue(board.proxyoutOfLimits(-3, 7));
		assertTrue(board.proxyoutOfLimits(7, 11));
		assertTrue(board.proxyoutOfLimits(9, 7));
		
		//position in laterals
		assertFalse(board.proxyoutOfLimits(0, 3));
		assertFalse(board.proxyoutOfLimits(3, 0));
		assertFalse(board.proxyoutOfLimits(7, 3));
		assertFalse(board.proxyoutOfLimits(3, 7));
		
		//position out of board with row or column in central cells
		assertTrue(board.proxyoutOfLimits(10, -3));
		assertTrue(board.proxyoutOfLimits(-5, 7));
		assertTrue(board.proxyoutOfLimits(-0, 11));
		assertTrue(board.proxyoutOfLimits(-4, 9));
		assertTrue(board.proxyoutOfLimits(7, -3));
		assertTrue(board.proxyoutOfLimits(10, -3));
		assertTrue(board.proxyoutOfLimits(9, 7));
		
		//position with all coordinates out of board
		assertTrue(board.proxyoutOfLimits(-2, -4));
		assertTrue(board.proxyoutOfLimits(9, 10));
		
		
	}
	
	@Test
	public void nextToDiskTest() {
		//Only let the disk be placed if there is an opponent next to it
				
				//positions where you should let black disks
				assertTrue(board.proxynextToDisk(2, 3));
				assertTrue(board.proxynextToDisk(3, 2));
				assertTrue(board.proxynextToDisk(4, 5));
				assertTrue(board.proxynextToDisk(5, 4));
				
				//corners can also be placed
				assertTrue(board.proxynextToDisk(2, 2));
				assertTrue(board.proxynextToDisk(5, 5));
				
				//frontier positions
				assertFalse(board.proxynextToDisk(0, 0));
				assertFalse(board.proxynextToDisk(0, 1));
				assertFalse(board.proxynextToDisk(0, 2));
				assertFalse(board.proxynextToDisk(0, 3));
				assertFalse(board.proxynextToDisk(0, 4));
				assertFalse(board.proxynextToDisk(0, 5));
				assertFalse(board.proxynextToDisk(0, 6));
				assertFalse(board.proxynextToDisk(0, 7));
				
				//
				assertFalse(board.proxynextToDisk(1, 0));
				assertFalse(board.proxynextToDisk(2, 0));
				assertFalse(board.proxynextToDisk(3, 0));
				assertFalse(board.proxynextToDisk(4, 0));
				assertFalse(board.proxynextToDisk(5, 0));
				assertFalse(board.proxynextToDisk(6, 0));
				assertFalse(board.proxynextToDisk(7, 0));
				
				//
				assertFalse(board.proxynextToDisk(0, 7));
				assertFalse(board.proxynextToDisk(1, 7));
				assertFalse(board.proxynextToDisk(2, 7));
				assertFalse(board.proxynextToDisk(3, 7));
				assertFalse(board.proxynextToDisk(4, 7));
				assertFalse(board.proxynextToDisk(5, 7));
				assertFalse(board.proxynextToDisk(6, 7));
				assertFalse(board.proxynextToDisk(7, 7));
				
				//
				assertFalse(board.proxynextToDisk(7, 0));
				assertFalse(board.proxynextToDisk(7, 1));
				assertFalse(board.proxynextToDisk(7, 2));
				assertFalse(board.proxynextToDisk(7, 3));
				assertFalse(board.proxynextToDisk(7, 4));
				assertFalse(board.proxynextToDisk(7, 5));
				assertFalse(board.proxynextToDisk(7, 6));
				assertFalse(board.proxynextToDisk(7, 7));

	
	}
	
	@Test
	public void positionsToSameColorTest() {
		
		assertEquals(-1, board.proxypositionsToSameColor(2, 3, Color.White, Direction.down, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 3, Color.White, Direction.down_left_diagonal, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 3, Color.White, Direction.left, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 3, Color.White, Direction.right, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 3, Color.White, Direction.down_right_diagonal, false));
		
		assertEquals(-1, board.proxypositionsToSameColor(2, 2, Color.White, Direction.down, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 2, Color.White, Direction.down_left_diagonal, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 2, Color.White, Direction.left, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 2, Color.White, Direction.right, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 2, Color.White, Direction.down_right_diagonal, false));
		
		assertEquals(-1, board.proxypositionsToSameColor(2, 5, Color.White, Direction.down, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 5, Color.White, Direction.down_left_diagonal, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 5, Color.White, Direction.left, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 5, Color.White, Direction.right, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 5, Color.White, Direction.down_right_diagonal, false));
		
		assertEquals(-1, board.proxypositionsToSameColor(2, 1, Color.White, Direction.down, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 1, Color.White, Direction.down_left_diagonal, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 1, Color.White, Direction.left, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 1, Color.White, Direction.right, false));
		assertEquals(-1, board.proxypositionsToSameColor(2, 1, Color.White, Direction.down_right_diagonal, false));
		
		assertEquals(-1, board.proxypositionsToSameColor(4, 2, Color.White, Direction.down, false));
		assertEquals(-1, board.proxypositionsToSameColor(4, 2, Color.White, Direction.down_left_diagonal, false));
		assertEquals(-1, board.proxypositionsToSameColor(4, 2, Color.White, Direction.left, false));
		assertEquals(-1, board.proxypositionsToSameColor(4, 2, Color.White, Direction.right, false));
		assertEquals(-1, board.proxypositionsToSameColor(4, 2, Color.White, Direction.down_right_diagonal, false));
		
		assertEquals(-1, board.proxypositionsToSameColor(5, 2, Color.Black, Direction.up_right_diagonal, false));
	}
	
	@Test
	public void checkPlaceDiskTest(){
		
		////positions where we can place a white disk but we can't place a black disk
		assertFalse(board.proxycheckPlaceDisk(4, 2, Color.Black, true));
		assertFalse(board.proxycheckPlaceDisk(4, 2, Color.Black, false));
		assertFalse(board.proxycheckPlaceDisk(5, 3, Color.Black, false));
		assertFalse(board.proxycheckPlaceDisk(2, 4, Color.Black, false));
		assertFalse(board.proxycheckPlaceDisk(3, 5, Color.Black, false));
		
		//positions next to white disk but on lines it doesn't have another disk of the same color to meet the condition 
		assertFalse(board.proxycheckPlaceDisk(5, 2, Color.Black, false));
		assertFalse(board.proxycheckPlaceDisk(2, 5, Color.Black, false));
		
		//position where we can put a black disk
		assertTrue(board.proxycheckPlaceDisk(2, 3, Color.Black, false));
		assertTrue(board.proxycheckPlaceDisk(3, 2, Color.Black, false));
		assertTrue(board.proxycheckPlaceDisk(4, 5, Color.Black, false));
		assertTrue(board.proxycheckPlaceDisk(5, 4, Color.Black, false));
		
		//positions where we can place a black disk but we can't place a white disk
		assertFalse(board.proxycheckPlaceDisk(2, 3, Color.White, true));
		assertFalse(board.proxycheckPlaceDisk(2, 3, Color.White, false));
		assertFalse(board.proxycheckPlaceDisk(3, 2, Color.White, false));
	    assertFalse(board.proxycheckPlaceDisk(4, 5, Color.White, false));
	    assertFalse(board.proxycheckPlaceDisk(5, 4, Color.White, false));
				
		//positions next to black disk but on lines it doesn't have another disk of the same color to meet the condition 
		assertFalse(board.proxycheckPlaceDisk(2, 2, Color.White, false));
		assertFalse(board.proxycheckPlaceDisk(5, 5, Color.White, false));
		
		//position where we can put a white disk
		assertTrue(board.proxycheckPlaceDisk(4, 2, Color.White, false));
		assertTrue(board.proxycheckPlaceDisk(5, 3, Color.White, false));
		assertTrue(board.proxycheckPlaceDisk(2, 4, Color.White, false));
		assertTrue(board.proxycheckPlaceDisk(3, 5, Color.White, false));
		assertTrue(board.proxycheckPlaceDisk(3, 5, Color.White, true));
		
	}
	
	@Test
	public void placeDiskTest(){
		
		//we add a black disk in a position where we can't.
		assertFalse(board.placeDisk(3, 3, Color.Black));
		assertFalse(board.placeDisk(2, 2, Color.Black));
		
		//We add a Black disk in a position where we can and check the changes that are made on the board.
		assertTrue(board.placeDisk(2, 3, Color.Black));
		assertNotEquals(Color.White, auxBoard[3][3].getColor());
		assertEquals(Color.Black, auxBoard[2][3].getColor());
		assertEquals(Color.Black, auxBoard[3][3].getColor());
		
		//we add a white disk in a position where we can't.
		assertFalse(board.placeDisk(3, 3, Color.White));
		assertFalse(board.placeDisk(2, 3, Color.White));
		
		//We add a Black disk in a position where we can and check the changes that are made on the board.
		assertTrue(board.placeDisk(2, 2, Color.White));
		assertNotEquals(Color.Black, auxBoard[3][3].getColor());
		assertEquals(Color.White, auxBoard[2][2].getColor());
		assertEquals(Color.White, auxBoard[3][3].getColor());
	}
	
	@Test
	public void outOfLimits_white_box_test() {
		
		//Decision and Condition Coverage
		
	    assertTrue(board.proxyoutOfLimits(-2, -2)); //!((0 <= -2 && 0 <= -2) && (-2 < 8 && -2 < 8)) -> !(false and true) -> true
	    assertTrue(board.proxyoutOfLimits(3, -2)); //!((0 <= 3 && 0 <= -2) && (3 < 8 && -2 < 8)) -> !(false and true) -> true
	    assertTrue(board.proxyoutOfLimits(-2, 4)); //!((0 <= -2 && 0 <= 4) && (-2 < 8 && 4 < 8)) -> !(false and true) ->true
	    assertFalse(board.proxyoutOfLimits(1, 1)); //!((0 <= 1 && 0 <= 1) && (1 < 8 && 1 < 8)) -> !(true and true) -> false
	    assertTrue(board.proxyoutOfLimits(8, 8)); //!((0 <=  8 && 0 <= 8) && (8 < 8 && 8 < 8))-> !(true and false) -> true
	    assertTrue(board.proxyoutOfLimits(6, 9)); //!((0 <= 6 && 0 <= 9) && (6 < 8 && 9 < 8)) -> !(true and false) -> true
	    assertTrue(board.proxyoutOfLimits(8, 7)); //!((0 <= 8 && 0 <= 7) && (8 < 8 && 7 < 8)) -> !(true and false) -> true
	      
	}
	
	@Test
	public void countCellstoDiskTest() {
		//Loop Testing: simple loop
		
		int[] skipFor = new int[0];
		board.countCellstoDisk(skipFor);
		
		int[] onceFor = {2};
		board.countCellstoDisk(onceFor);
		
		int[] mTimesFor = {2, 0, 4, 3, 0, 5, 2};
		board.countCellstoDisk(mTimesFor);
		
		int[] maxTimesFor = {2, 0, 4, 3, 0, 5, 2, 3};
		board.countCellstoDisk(maxTimesFor);	
	}
	
	@Test
	public void checkDirectionTest() {
		//Path Coverage
		board.checkDirection(2, 4, Direction.up);
		board.checkDirection(1, 3, Direction.down);
		board.checkDirection(5, 6, Direction.left);
		board.checkDirection(3, 2, Direction.right);
		board.checkDirection(7, 1, Direction.down_left_diagonal);
		board.checkDirection(6, 3, Direction.down_right_diagonal);
		board.checkDirection(4, 4, Direction.up_left_diagonal);
		board.checkDirection(6, 7, Direction.up_right_diagonal);
		
	}
		
}
		
