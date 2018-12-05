package model;

/**
 * This class represents a solution for a sudoku 
 * @author Aleksandra, Ben, Jefferson
 */
import java.util.Random;

public class SolutionCreator {

	private int[][] firstBoard;
	static final int SIZE = 9;

	public SolutionCreator() {
		firstBoard = new int[SIZE][SIZE];
		createBoard();
		shuffleBoard(firstBoard);
	}

	/**
	 * 
	 * @return the firstBoard; given is the original solved board
	 */
	public int[][] getSolution() {
		return firstBoard;
	}

	/**
	 * Shuffling the initial solvable board
	 * 
	 * @param an int 2D array to shuffle
	 */
	private void shuffleBoard(int[][] a) {
		shuffle_inGroup(a, 0);
		shuffle_inGroup(a, 1);
		shuffle_random(a, 0);
		shuffle_random(a, 1);
	}

	/**
	 * Shuffle columns or rows randomly based on the rule that 0,3, and 6 could be
	 * switched
	 * 
	 * @param a    an int 2D array to modify
	 * @param flag 0 or 1 to decide shuffle columns(1) or rows(1)
	 */
	private void shuffle_random(int[][] a, int flag) {
		Random random = new Random();
		int[] tmp = { 0, 3, 6 };
		int k1, k2;
		for (int i = 0; i < 2; i++) {
			k1 = tmp[random.nextInt(tmp.length)];
			do {
				k2 = tmp[random.nextInt(tmp.length)];
			} while (k1 == k2);
			if (flag == 0)
				changeRow_random(a, k1, k2);
			else if (flag == 1)
				changeCol_random(a, k1, k2);

		}
	}

	/**
	 * Swaps two given columns
	 * 
	 * @param a  an int 2D array to modify
	 * @param k1 one column
	 * @param k2 another column
	 */
	private void changeCol_random(int[][] a, int k1, int k2) {
		int temp;
		for (int n = 1; n <= 3; n++) {
			for (int i = 0; i < 9; i++) {
				temp = a[i][k1];
				a[i][k1] = a[i][k2];
				a[i][k2] = temp;
			}
			k1++;
			k2++;
		}
	}

	/**
	 * Swaps two given rows
	 * 
	 * @param a  2D int array to be shuffled
	 * @param k1 one row
	 * @param k2 another row
	 */
	private void changeRow_random(int[][] a, int k1, int k2) {
		int temp;
		for (int n = 1; n <= 3; n++) {
			for (int i = 0; i < 9; i++) {
				temp = a[k1][i];
				a[k1][i] = a[k2][i];
				a[k2][i] = temp;
			}
			k1++;
			k2++;
		}
	}

	/**
	 * Shuffles rows or columns within a group of 3: 1-3, 4-6, or 7-9
	 * 
	 * @param tmp  2D int array to be shuffled
	 * @param flag 0 or 1 to decide shuffle columns(1) or rows(1)
	 */
	private void shuffle_inGroup(int[][] tmp, int flag) {
		int k1, k2, min = 0, max = 2;
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			k1 = random.nextInt(max - min + 1) + min;
			do {
				k2 = random.nextInt(max - min + 1) + min;
			} while (k1 == k2);
			min += 3;
			max += 3;
			if (flag == 1)
				changeRow_inGroup(tmp, k1, k2);
			else if (flag == 0)
				changeCol_inGroup(tmp, k1, k2);
		}
	}

	/**
	 * 
	 * Shuffles rows within a group 1-3, 4-6, or 7-9
	 * 
	 * @param a  an int 2D array to be shuffled
	 * @param r1 one row
	 * @param r2 another row
	 */
	private void changeRow_inGroup(int[][] a, int r1, int r2) {
		int[] tmp = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
			tmp[i] = a[r1][i];
		for (int i = 0; i < SIZE; i++) {
			a[r1][i] = a[r2][i];
			a[r2][i] = tmp[i];
		}
	}

	/**
	 * Shuffles Columns within a group 1-3, 4-6, or 7-9
	 * 
	 * @param a  an int 2D array to be shuffled
	 * @param c1 one column
	 * @param c2 another column
	 */
	private void changeCol_inGroup(int[][] a, int c1, int c2) {
		int[] tmp = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
			tmp[i] = a[i][c1];
		for (int i = 0; i < SIZE; i++) {
			a[i][c1] = a[i][c2];
			a[i][c2] = tmp[i];
		}
	}

	/**
	 * Creates a firstboard - initial solvable sudoku
	 */
	private void createBoard() {
		int k = 1, n = 1;
		for (int r = 0; r < SIZE; r++) {
			k = n;
			for (int c = 0; c < SIZE; c++) {
				if (k <= SIZE) {
					firstBoard[r][c] = k;
					k++;
				} else {
					k = 1;
					firstBoard[r][c] = k;
					k++;
				}
			}
			n = k + 3;
			if (k == 10)
				n = 4;
			if (n > 9)
				n = (n % 9) + 1;
		}
	}

}
