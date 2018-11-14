package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonPad extends JPanel{
	
	private JButton solutionButton, newGameButton, exitButton; // buttons used in the game.
	private JCheckBox helpButton;
	private ButtonGroup keypad;
	private JToggleButton[] keypadNumbers;
	
	public ButtonPad() {
		super(new BorderLayout());
		
		JPanel aPanel = new JPanel();
		aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.Y_AXIS));
		add(aPanel, BorderLayout.NORTH);
		
		JPanel panelGameOptions = new JPanel();
		panelGameOptions.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelGameOptions.setBorder(BorderFactory.createTitledBorder("panelGameOptions")); 
		aPanel.add(panelGameOptions);
		
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(100,30));
		
		solutionButton = new JButton("Solution");
		solutionButton.setPreferredSize(new Dimension(100,30));
		
		exitButton = new JButton(" Exit"); 
		exitButton.setPreferredSize(new Dimension(100,30));
		
		panelGameOptions.add(newGameButton);
		panelGameOptions.add(solutionButton);
		panelGameOptions.add(exitButton);
		
		
		helpButton = new JCheckBox("Help", false);
		helpButton.setBackground(Color.green);
		helpButton.setPreferredSize(new Dimension(50,30));
		
		JPanel panelNumbers = new JPanel();
		panelNumbers.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelNumbers.setBorder(BorderFactory.createTitledBorder("panelNumbers")); 
		aPanel.add(panelNumbers);
		
		
	
		panelNumbers.add(helpButton); // maybe put help in diff position
		keypad = new ButtonGroup();
		keypadNumbers = new JToggleButton[9];
		
		for (int i=0;i<9;i++) {
			keypadNumbers[i] = new JToggleButton(""+(i+1));
			keypadNumbers[i].setPreferredSize(new Dimension(50,50));
			keypad.add(keypadNumbers[i]);
			panelNumbers.add(keypadNumbers[i]);
		}
		
		
	}

}
