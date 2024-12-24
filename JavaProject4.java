
import java.util.Random;
import java.util.Scanner;

public class JavaProject4 {
	public static void main(String[] args) {
		
	    
	    Scanner console = new Scanner(System.in);
	    boolean running = true;
	    while (running) {
	        System.out.println("Choose a game: 1 (Magic 8-Ball), 2 (Connect 4)");
	        int choice = console.nextInt();
	        if (choice == 1) {
	        	Random random = new Random();
	    	    String[] responses = {
	    	        "Yes", "No", "Maybe", "Ask again later"
	    	    };
	    	    System.out.println("Ask a yes or no question:");
	    	    console.nextLine();
	    	    console.nextLine(); // Wait for input
	    	    String response = responses[random.nextInt(responses.length)];
	    	    System.out.println("MAGIC 8-BALL SAYS: " + response);
	    	    System.out.println(" ");
	        } else if (choice == 2) {
	            connect4(console);
	        }
	        else
	        {
	        	System.out.println("Please choose 1 or 2");
	        }
	    }
	}
	
	public static void connect4(Scanner console) {
		char[][] board = new char[6][7];
		final int rows = 6;
	    final int columns = 7;
      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
              board[i][j] = '.'; // shows the board as empty
          }
      }
      char currentPlayer = 'R'; // R for Red, Y for Yellow
      boolean gameWon = false;
      while (!gameWon) {
          printBoard(board);
          System.out.println("Current Player is: " + currentPlayer); 
          System.out.println("");
          int column = getColumnInput(board);
          dropPiece(board, column, currentPlayer);
          gameWon = checkWin(board, currentPlayer);
          if (gameWon) {
              printBoard(board);
              System.out.println("Player " + currentPlayer + " wins!");
              System.out.println("");
          } else {
              currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R'; // Switches player by using a ternary operator (condition) ? if true : if false;
          }
      }
  }
	
  public static void printBoard(char[][] board) {
      for (char[] row : board) {
          for (char cell : row) {
              System.out.print(cell + " "); // adds spacing to the board
          }
          System.out.println("");
      }
      char currentPlayer = 'R';
      System.out.println("");
      System.out.println("0 1 2 3 4 5 6"); // Column numbers
      System.out.println("");
     
  }
  public static int getColumnInput(char[][] board) {
      Scanner scanner = new Scanner(System.in);
      int column;
      while (true) {
          System.out.print("Choose a column (0-6): ");
          System.out.println("");
          column = scanner.nextInt();
          if (column >= 0 && column < 7 && board[0][column] == '.') {
              return column; // Valid column
          }
          System.out.println("Invalid column. Try again.");
      }
  }
  public static void dropPiece(char[][] board, int column, char player) {
      for (int i = 5; i >= 0; i--) {
          if (board[i][column] == '.') {
              board[i][column] = player;
              break;
          }
      }
  }
  public static boolean checkWin(char[][] board, char player) {
      return checkHorizontalWin(board, player) || checkVerticalWin(board, player) || checkDiagonalWin(board, player);
  }
  private static boolean checkHorizontalWin(char[][] board, char player) {
      for (int r = 0; r < 6; r++) {
          for (int c = 0; c < 4; c++) {
              if (board[r][c] == player && board[r][c + 1] == player && board[r][c + 2] == player && board[r][c + 3] == player) {
                  return true;
              }
          }
      }
      return false;
  }
  public static boolean checkVerticalWin(char[][] board, char player) {
      for (int c = 0; c < 7; c++) {
          for (int r = 0; r < 3; r++) {
              if (board[r][c] == player && board[r + 1][c] == player && board[r + 2][c] == player && board[r + 3][c] == player) {
                  return true;
              }
          }
      }
      return false;
  }
  public static boolean checkDiagonalWin(char[][] board, char player) {
      // Check for diagonal wins (bottom-left to top-right)
      for (int r = 0; r < 3; r++) {
          for (int c = 0; c < 4; c++) {
              if (board[r][c] == player && board[r + 1][c + 1] == player && board[r + 2][c + 2] == player && board[r + 3][c + 3] == player) {
                  return true;
              }
          }
      }
      // Check for diagonal wins (top-left to bottom-right)
      for (int r = 3; r < 6; r++) {
          for (int c = 0; c < 4; c++) {
              if (board[r][c] == player && board[r - 1][c + 1] == player && board[r - 2][c + 2] == player && board[r - 3][c + 3] == player) {
                  return true;
              }
          }
      }
      return false;
  }
 }
 
