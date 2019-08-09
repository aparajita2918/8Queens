import java.util.*;
public class EightQueens {
	 public static void main(String[] args){
		  
		    int restarts = 0;
		    int stateChanges = 0;
		    final int SIZE = 10;

		    State currentState = new State(SIZE);
		    
		    while(!currentState.isGoal()) {
		      int h = currentState.getHeuristic();
		   // Display current state and heuristic
		      System.out.println("Current h: " + h);
		      System.out.println("Current State");
		      System.out.println(currentState.toString());

		   //state's neighbors
		      HashMap neighbors = currentState.getAllNeighbors();
		      Set set = neighbors.entrySet();
		      Iterator i = set.iterator();

		      int lowerNeighbors = 0;               
		      State lowestNeighbor = currentState;  

		      while(i.hasNext()){
		        Map.Entry me = (Map.Entry)i.next();
		        if((int)(me.getValue()) < h) lowerNeighbors++;
		        if(((State)me.getKey()).getHeuristic() < lowestNeighbor.getHeuristic())
		          lowestNeighbor = (State)me.getKey();
		      }

		      System.out.println("Neighbors found with lower h: " + lowerNeighbors);

		      		      if(lowerNeighbors == 0){
		        System.out.println("RESTART");
		        currentState = new State(SIZE);
		        restarts ++;
		      }
		       else {
		        System.out.println("Setting new current state\n");
		        currentState = lowestNeighbor;
		        stateChanges++;
		      }

		    } 

		    // Display output
		    System.out.println(currentState.toString());
		    System.out.println("Solution Found!");
		    System.out.println("State changes: " + stateChanges);
		    System. out.println("Restarts: " + restarts);

		  }
}
