package view;

/**
 * Instance of the class is an object that includes 2 panels with
 * Main game buttons and Keypad buttons.
 *  
 * @author Aleksandra, Ben, Jefferson
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import messages.ExitGameMessage;
import messages.HelpMessage;
import messages.Message;
import messages.NewGameMessage;
import messages.SolutionMessage;
import messages.SubmitGameMessage;
import model.Game;

public class ButtonPad extends JPanel {
	private JButton solutionButton, newGameButton, exitButton, submitButton;
	private JCheckBox helpButton;
	private ButtonGroup keypad;
	private JToggleButton[] keypadNumbers;
	public BlockingQueue<Message> queue;

	/**
	 * Creates button pad
	 * @param queue takes messages that will result in updates in the game
	 * 
	 */
	public ButtonPad(BlockingQueue<Message> queue) {
		this.queue = queue;
		
		//containerPanel is used to contain other panels 
		//containing button pad panel and option panel
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		add(containerPanel, BorderLayout.NORTH);

		//Adding button panel to container panel
		containerPanel.add(createButtonPanel());
		
		//Adding key pad panel (with key pad buttons numbers 1-9) to container panel
		containerPanel.add(createKeyPanel());
	}
	
	/**
	 * Creates button panel consisting of different buttons such as new game, solution, etc.
	 * @return button panel 
	 */
	public JPanel createButtonPanel() {
		
		//Game options panel containing game buttons
		JPanel panelGameOptions = new JPanel();
		panelGameOptions.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Creates game buttons
		createNewGameButton();
		createSolutionButton();
		createExitButton();
		createSubmitButton();
		createHelpBox();
		
		//Adds game buttons to game panel
		panelGameOptions.add(newGameButton);
		panelGameOptions.add(solutionButton);
		panelGameOptions.add(submitButton);
		panelGameOptions.add(exitButton);
		panelGameOptions.add(helpButton);
		
		return panelGameOptions;
	}
	
	public JPanel createKeyPanel() {
		//Key pad panel consisting of numbers 1-9, used for user to change cells
		JPanel panelNumbers = new JPanel(new GridLayout(3, 3));
		panelNumbers.setPreferredSize(new Dimension(200, 400));
		
		keypad = new ButtonGroup();
		keypadNumbers = new JToggleButton[9];
		//Adds toggle buttons (1-9) to the key pad panel
		for (int i = 0; i < 9; i++) {
			keypadNumbers[i] = new JToggleButton("" + (i + 1));
			keypadNumbers[i].setPreferredSize(new Dimension(50, 50));
			keypad.add(keypadNumbers[i]);
			panelNumbers.add(keypadNumbers[i]);
		}
		
		return panelNumbers;
	}
	
	/**
	 * Action listeners that will track actions performed and update model
	 * 
	 * @param game the current game being played 
	 * @param game the current game being played 
	 */
	public void addActionlisteners(Game game) {
		for (int i = 0; i < 9; i++)
			this.getKeypadNumbers()[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game.setUserInput(Integer.parseInt(e.getActionCommand()));
				}
			});
	}

	/**
	 * Creates new game button, used to determine when a new game should be generated.
	 * Sends a message to queue, to update board if button is clicked.
	 * 
	 * @return new game button that has action listener
	 */
	public JButton createNewGameButton() {
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(100, 30));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					queue.put(new NewGameMessage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		return newGameButton;
	}
	
	/**
	 * Creates solution button, used to generate solution to board.
	 * Sends a message to queue, to update board if button is clicked.
	 * 
	 * @return solution button that has action listener
	 */
	public JButton createSolutionButton() {
		solutionButton = new JButton("Solution");
		solutionButton.setPreferredSize(new Dimension(100, 30));
		solutionButton.setToolTipText("See the solution!");
		solutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new SolutionMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
		});
		return solutionButton;
	}
	
	/**
	 * Creates submit button, used to determine when a user submits their current work.
	 * Sends a message to queue, to update board if button is clicked.
	 * 
	 * @return submit button that has action listener
	 */
	public JButton createSubmitButton() {
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(100, 30));
		submitButton.setToolTipText("Check if your input is correct!");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new SubmitGameMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
		});
		return submitButton;
	}
	
	/**
	 * Creates exit button, used to determine when a user wishes to close application.
	 * Sends message to queue, to close window after button is clicked.
	 * 
	 * @return exit button that has action listener
	 */
	public JButton createExitButton() {
		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(100, 30));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new ExitGameMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}

		});
		return exitButton;
	}
	
	/**
	 * Creates help check box, used to determine when a user wishes to view their mistakes.
	 * Sends message to queue when box is checked, to display help for user.
	 * 
	 * @return help check box that has action listener
	 */
	public JCheckBox createHelpBox() {
		helpButton = new JCheckBox("Help", false);
		helpButton.setBackground(Color.green);
		helpButton.setPreferredSize(new Dimension(75, 30));
		helpButton.setToolTipText("Toggle on to see which cells are correct/incorrect");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					queue.put(new HelpMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
		});
		return helpButton;
	}

	/**
	 * Gets keypad numbers
	 * 
	 * @return keypad numbers
	 */
	public JToggleButton[] getKeypadNumbers() {
		return keypadNumbers;
	}
	
}
