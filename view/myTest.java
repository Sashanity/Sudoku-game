package view;

import model.SolutionCreator;

public class myTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SolutionCreator solution = new SolutionCreator();
		System.out.println("");
		 for(int i=0;i<9;i++)
		   {
		      for(int j=0;j<9;j++)
		      {
		    	  
		         System.out.print(solution.firstBoard[i][j]);
		      }
		      System.out.println("");
		   }

	}

}

