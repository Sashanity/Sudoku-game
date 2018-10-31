package model;

import java.util.Random;

public class SolutionCreator {

	public int[][] firstBoard;
	static final int SIZE = 9;

	public SolutionCreator() {
		firstBoard = new int[SIZE][SIZE];
		createBoard();

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(firstBoard[i][j]);
			}
			System.out.println("");
		}

		shuffleBoard(firstBoard);
		/*
		 * Random random = new Random(); int x = random.nextInt(10);
		 */
		// for (int i = 0; i < x; i++) // x times shuffle the board
		// shuffleBoard0(firstBoard); // shuffle columns
		// shuffleBoard0(firstBoard, 1); // shuffle rows

	}

	private void shuffleBoard(int[][] a) {
		shuffle_inGroup(a, 0);
		shuffle_inGroup(a, 1);

		shuffle_random(a, 0);
		shuffle_random(a, 1);

	}

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

	private void changeCol_random(int[][] a, int k1, int k2) {
		// TODO Auto-generated method stub
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

	private void changeRow_random(int[][] a, int k1, int k2) {
		// TODO Auto-generated method stub
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

	private void changeRow_inGroup(int[][] a, int r1, int r2) {

		int[] tmp = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
			tmp[i] = a[r1][i];

		for (int i = 0; i < SIZE; i++) {
			a[r1][i] = a[r2][i];
			a[r2][i] = tmp[i];
		}

		/*
		 * int tmp; for(int j=0;j<9;j++) { tmp=a[r1][j]; a[r1][j]=a[r2][j];
		 * a[r2][j]=tmp; }
		 */
	}

	private void changeCol_inGroup(int[][] a, int c1, int c2) {
		int[] tmp = new int[SIZE];

		for (int i = 0; i < SIZE; i++)
			tmp[i] = a[i][c1];

		for (int i = 0; i < SIZE; i++) {
			a[i][c1] = a[i][c2];
			a[i][c2] = tmp[i];
		}
		/*
		 * int temp; for(int j=0;j<9;j++) { temp=a[j][k1]; a[j][k1]=a[j][k2];
		 * a[j][k2]=temp; }
		 */
	}

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
