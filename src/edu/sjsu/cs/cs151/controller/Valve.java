package edu.sjsu.cs.cs151.controller;

import edu.sjsu.cs.cs151.view.messages.Message;

/**
 * Used to process messages
 * @author Aleksandra, Ben, Jefferson
 */
public interface Valve {
	/**
	 * MISS is returned if the message received is not for this valve EXECUTED is
	 * returned if the message received matched the valve and its code was executed
	 * FINISH is returned when the game is finished by exiting the game
	 */
	enum ValveResponse {
		MISS, EXECUTED, FINISH;
	}

	/**
	 * 
	 * @param message message the message received from queue
	 * @return 
	 */
	public ValveResponse execute(Message message);
}
