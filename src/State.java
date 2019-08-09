import java.util.*;
public class State {
	 int N; // Size of grid. Default 8
	 int grid[][];
     int heuristic;

	  // new state random size =8
	  public State(){
	    this.N = 8;
	    grid = new int[N][N];
	    generateRandomGrid();
	    this.heuristic = checkHeuristic();
	  }
	  public State(int n){
	    this.N = n;
	    grid = new int[N][N];

	    generateRandomGrid();
	    this.heuristic = checkHeuristic();
	  }

	  public State(int[][] g){
	    if(g.length == g[0].length){
	      this.grid = g;
	      N = g.length;
	      this.heuristic = checkHeuristic();
	    }
	    else{
	      System.out.println("Error. N*N grid was not entered.");
	      generateRandomGrid();
	      this.heuristic = checkHeuristic();
	    }
	  }
 // GETTERS 

	  public int[][] getGrid(){
	    return grid;
	  }

	  public int getHeuristic(){
	    return heuristic;
	  }
	  //Generates a random grid set up of one queen per column
	  private void generateRandomGrid(){
	        for(int i = 0; i < N; i++){
	          for(int j = 0; j < N; j++){
	            grid[i][j] = 0;
	          }
	        }

	   // Place 8 queens=1 in random row per column
	        for(int i = 0; i < N; i++){
	          int rand = (int)(Math.random()*N);
	          grid[rand][i] = 1;
	        }
	  }

	  public boolean isGoal(){
	    if(heuristic == 0) return true;
	    else return false;
	  }
	   //startRow, startCol=starting position endRow, endCol=ending or goal position
	  
	  public void moveQueen(int startRow, int startCol, int endRow, int endCol){
	    if(grid[startRow][startCol] == 1){
	      grid[startRow][startCol] = 0; 
	      grid[endRow][endCol] = 1; 
	    }

	    heuristic = checkHeuristic();
	  }

	  // heuristic

	  //return heuristic of this state
	  private int checkHeuristic(){
	    int conflicts = 0;
	    for(int i = 0; i < N; i++){
	      for(int j = 0; j < N; j++){
	        if(grid[i][j] == 1){
	          conflicts += checkHorizontal(i,j);
	          conflicts += checkVertical(i,j);
	          conflicts += checkDiagonal(i,j);
	        }
	      }
	    }

	    return conflicts/2;
	  }

	  private int checkVertical(int row, int column){
	    int conflicts = 0;
	    for(int i = 0; i < N; i++){
	      if(i == row) continue;
	      if(grid[i][column] == 1) conflicts++;
	    }

	    return conflicts;
	  }

	  private int checkHorizontal(int row, int column){
	    int conflicts = 0;

	    for(int i = 0; i < N; i++){
	      if(i == column) continue;
	      if(grid[row][i] == 1) conflicts++;
	    }

	    return conflicts;
	  }

	  private int checkDiagonal(int row, int column){
	    int conflicts = 0;

	    for(int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--){
	      if(grid[i][j] == 1) conflicts++;
	    }

	    for(int i = row+1, j = column+1; i < N && j < N; i++, j++){
	      if(grid[i][j] == 1) conflicts++;
	    }

	    for(int i = row+1, j = column-1; i < N && j >= 0; i++, j--){
	      if(grid[i][j] == 1) conflicts++;
	    }

	    for(int i = row-1, j = column+1; i >= 0 && j < N; i--, j++){
	      if(grid[i][j] == 1) conflicts++;
	    }

	    return conflicts;
	  }


	  public String toString(){
	    String str = "";

	    for(int i = 0; i < N; i++){
	      for(int j = 0; j < N; j++){
	        str += grid[i][j] + " ";
	      }
	      str += "\n";
	    }

	    return str.substring(0, str.length() - 2);
	  }

	  public HashMap getAllNeighbors (){
	    HashMap neighbors = new HashMap();

	    for(int row = 0; row < N; row++){
	      for(int col = 0; col < N; col++){

	        // Find each queen
	        if(grid[row][col] == 1){
	          for(int i = 0; i < N; i++){
	            if(i == row) continue; 

	            State s = new State(copyArray(grid, N));
	            s.moveQueen(row, col, i, col); 
	            neighbors.put(s, s.getHeuristic()); 

	          }
	        }

	      }
	    }

	    return neighbors;
	  }

	  private int[][] copyArray(int[][] g, int size){

	    int[][] a = new int[size][];
	    for (int i = 0; i < size; i++) {
	      a[i] = Arrays.copyOf(g[i], g[i].length);
	    }

	    return a;

	  }
}
