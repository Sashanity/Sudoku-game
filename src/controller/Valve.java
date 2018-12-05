package controller;

import view.Message;

/*
 * Used to process messages
 */
public interface Valve {
	/*
	 * MISS is returned if the message received is not for this valve EXECUTED is
	 * returned if the message received matched the valve and its code was executed
	 * FINISH is returned when the game is finished by exiting the game
	 */
	enum ValveResponse {
		MISS, EXECUTED, FINISH;
	}

	/*
	 * @param message the message received from queue
	 */
	public ValveResponse execute(Message message);
}
