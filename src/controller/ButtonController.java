package controller;
/**

 * Controller class for the Button class
 * event handler
 * @author Aleksandra, Ben, Jefferson
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;
import view.ButtonPad;

public class ButtonController {
	private ButtonPad buttonPad;
	private Game game;
	public ButtonController(ButtonPad buttonPad, Game game) {
		this.buttonPad = buttonPad;
		this.game = game;
	}
	
	public void update() {
		buttonPad.getNewGameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.newGame();
				System.out.println("NEW GAME");
				/*System.out.println("Created game:");
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {

						System.out.print(game.getGame()[i][j]);
					}
					System.out.println("");
				}*/
			}
		});
		
		buttonPad.getExitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("EXIT");
				System.exit(0);
			}
		});
		buttonPad.getSolutionButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Show Solution");
			}
		});
		buttonPad.getHelpButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Help toggled");
			}
		});
		buttonPad.getSubmitButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Input Submitted");
			}
		});
		
	}

	

}
