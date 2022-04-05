public class Sudoku {
  
  private static final int Matrix_Size = 9;
  
  public static void main(String[] args) {
    
    int[][] Matrix = {
        {7, 0, 2, 0, 5, 0, 6, 0, 0},
        {0, 0, 0, 0, 0, 3, 0, 0, 0},
        {1, 0, 0, 0, 0, 9, 5, 0, 0},
        {8, 0, 0, 0, 0, 0, 0, 9, 0},
        {0, 4, 3, 0, 0, 0, 7, 5, 0},
        {0, 9, 0, 0, 0, 0, 0, 0, 8},
        {0, 0, 9, 7, 0, 0, 0, 0, 5},
        {0, 0, 0, 2, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
      };
    
    printMatrix(Matrix);
    
    if (solveMatrix(Matrix)) {System.out.println();
    System.out.println();
      System.out.println("Solved!");
    }
    else {
      System.out.println("Unsolvable Matrix :(");
    }
    
    printMatrix(Matrix);
    
  }
  
  
  private static void printMatrix(int[][] Matrix) {
	  
	  int box=(int)Math.sqrt(Matrix.length);
    for (int row = 0; row < Matrix_Size; row++) {
      if (row % box == 0 && row != 0) {
        System.out.println("___________");
      }
      for (int column = 0; column < Matrix_Size; column++) {
        if (column % box == 0 && column != 0) {
          System.out.print("*");
        }
        System.out.print(Matrix[row][column]);
      }
      System.out.println();
    }
  }


  private static boolean isInRow(int[][] Matrix, int number, int row) {
    for (int i = 0; i < Matrix_Size; i++) {
      if (Matrix[row][i] == number) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isInColumn(int[][] Matrix, int number, int column) {
    for (int i = 0; i < Matrix_Size; i++) {
      if (Matrix[i][column] == number) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isInBox(int[][] Matrix, int number, int row, int column) {
	  int box=(int)Math.sqrt(Matrix.length);
    int BoxRow = row - row % box;
    int BoxColumn = column - column % box;
    
    for (int i = BoxRow; i < BoxRow + box; i++) {
      for (int j = BoxColumn; j < BoxColumn + box; j++) {
        if (Matrix[i][j] == number) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static boolean isValid(int[][] Matrix, int number, int row, int column) {
    return !isInRow(Matrix, number, row) &&
        !isInColumn(Matrix, number, column) &&
        !isInBox(Matrix, number, row, column);
  }
  
  private static boolean solveMatrix(int[][] Matrix) {
    for (int row = 0; row < Matrix_Size; row++) {
      for (int column = 0; column < Matrix_Size; column++) {
        if (Matrix[row][column] == 0) {
          for (int number = 1; number <= Matrix_Size; number++) {
            if (isValid(Matrix, number, row, column)) {
              Matrix[row][column] = number;
              
              if (solveMatrix(Matrix)) {
                return true;
              }
              else {
                Matrix[row][column] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
  
  
  
}


