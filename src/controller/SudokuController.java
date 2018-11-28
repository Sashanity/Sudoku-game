package controller;

import java.awt.Color;
import java.awt.Component;

/**

 * Controls and respond to user actions (pressed buttons such as newgame, solutions etc)
 * event handling
 * @author Aleksandra, Ben, Jefferson
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Game;
import view.ButtonPad;
import view.Cell;

import view.SudokuBoard;

public class SudokuController {

	private SudokuBoard sudokuBoard;
	private Game game;

	/**
	 * 
	 * @param sudokuBoard  the current board
	 * @param game         is the numerical board situation
	 * @param buttonPanel
	 * @param sudokuBoard2
	 */
	public SudokuController(SudokuBoard sudokuBoard, Game game, ButtonPad buttonPanel) {
		this.sudokuBoard = sudokuBoard;
		this.game = game;

	}

	public void update() {

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++)
				sudokuBoard.getCells()[y][x].addMouseListener(new Handler(game));

		}

	}

}

class Handler implements MouseListener {
	private Game game;

	public Handler(Game game) {
		this.game = game;
		// System.out.println("MouseListener attached");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		JLabel aLabel = (JLabel) e.getSource();
		Component component = aLabel.getComponentAt(e.getPoint());
		if (component instanceof Cell) {
			Cell aCell = (Cell) component;
			int r = aCell.getCellX();
			int c = aCell.getCellY();
			if (game.getValue(r, c) == 0 || aCell.getForeground().equals(Color.BLUE)) {
				System.out.println("Cell " + r + " " + c + " can be modified");

				System.out.println(game.getUserInput());

				game.setValue(game.getUserInput(), c, r);// sets values to the game array
				aCell.setValue(game.getUserInput(), true);
			} else {

				System.out.println("Cell " + r + " " + c + " cannot be modified");
				System.out.println("Value: " + game.getValue(r, c));
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}