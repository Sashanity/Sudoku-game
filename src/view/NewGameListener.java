/**package view;

import java.awt.event.ActionEvent;
import controller.*;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import model.Main;
import view.Sudoku;
import controller.ButtonController;

public class NewGameListener implements ActionListener {

	public void actionPerformed(ActionEvent event) {
		try {
			queue.put(new NewGameMessage());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

}
*/
