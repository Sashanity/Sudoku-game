package controller;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import messages.Message;
import messages.RightClickMessage;
import messages.UserInputMessage;
import model.Game;
import view.Cell;

/**
 * Sets userinput values on the viewable board. Also makes sure to modify them
 * in the model.
 * @author Aleksandra, Ben, Jefferson
 */
public class Handler extends MouseAdapter {
	private Game game;
	private BlockingQueue<Message> queue;

	/**
	 * 
	 * @param game that Handler is modifying cells for
	 */
	public Handler( BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		JLabel aLabel = (JLabel) e.getSource();
		Component component = aLabel.getComponentAt(e.getPoint());
		if (component instanceof Cell) {
			Cell aCell = (Cell) component;
			try {
				queue.put(new UserInputMessage(aCell.getCellX() , aCell.getCellY(), aCell));
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
			/*if (game.getValue(r, c) == 0 || aCell.getForeground().equals(Color.BLUE)) {
				game.setValue(game.getUserInput(), c, r);
				aCell.setValue(game.getUserInput(), true);
			}
			*/
		}
		if(SwingUtilities.isRightMouseButton(e)) {
			if (component instanceof Cell) {
				Cell aCell = (Cell) component;
				// Create message for Right-click
				try {
					queue.put(new RightClickMessage(aCell.getCellX(), aCell.getCellY(), aCell));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
