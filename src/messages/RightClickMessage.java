package messages;

import view.Cell;

public class RightClickMessage extends Message{
	private int x;
	private int y;
	private Cell cell;//the cell to be edited
	/*
	 * @param x the x coordinate of the cell
	 * @param y the y coordinate of the cell
	 * @param c the Cell to be edited
	 */
	public RightClickMessage(int y, int x, Cell c)
	{
		this.cell = c;
		this.x = x;
		this.y = y;
	}
	/*
	 * @return the x coordinate of the cell
	 */
	public int getX() {
		return x;
	}
	/*
	 * @return the y coordinate of the cell
	 */
	public int getY() {
		return y;
	}
	/*
	 * @return the cell to be edited
	 */
	public Cell getCell() {
		return cell;
	}
}
