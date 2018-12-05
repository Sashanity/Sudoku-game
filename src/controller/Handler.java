package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import model.Game;
import view.Cell;

public class Handler extends MouseAdapter {
	private Game game;

	public Handler(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel aLabel = (JLabel) e.getSource();
		Component component = aLabel.getComponentAt(e.getPoint());
		if (component instanceof Cell) {
			Cell aCell = (Cell) component;
			int r = aCell.getCellX();
			int c = aCell.getCellY();
			if (game.getValue(r, c) == 0 || aCell.getForeground().equals(Color.BLUE)) {				
				game.setValue(game.getUserInput(), c, r);
				aCell.setValue(game.getUserInput(), true);
			} 
		}

	}

}
