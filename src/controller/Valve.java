package controller;


import view.Message;

public interface Valve {

	enum ValveResponse {
		MISS, EXECUTED, FINISH;
	}

	public ValveResponse execute(Message message);
}
