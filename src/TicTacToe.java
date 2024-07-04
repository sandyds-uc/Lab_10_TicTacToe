import java.util.Scanner;

/**
 *
 * @author Daniel Sandy sandyds@mail.uc.edu
 */

public class TicTacToe
{

    private static final int ROW = 3; // declare & initialize global constant ROW
    private static final int COL = 3; // declare & initialize global constant COL
    private static String board[][] = new String[ROW][COL];// declare & initialize global variable board

    public static void main(String[] args)
    {
        final String X = "X";
        final String O = "O";
        Scanner in = new Scanner(System.in);
        boolean playAgain = false;
        boolean over = false;
        boolean okMove = false;
        String player1Name = "";
        String player2Name = "";
        String playerName = "";
        String player = "X";
        int count = 0;
        int rowMove = 0;
        int colMove = 0;
        int row = 0;
        int col = 0;



        System.out.println("\nWelcome to Daniel Sandy's TicTacToe!"); // output welcome message
        player1Name = SafeInput.getNonZeroLenString(in, "Enter name for Player 1"); // method to input player 1 name
        player2Name = SafeInput.getNonZeroLenString(in, "Enter name for Player 2"); // method to input player 2 name
        System.out.println("\nWelcome " + player1Name + " & " + player2Name + "!"); // output welcome message

        // clearBoard();
        do {
            clearBoard();

            while(!over) {
                display();

                if (player == "X") // name player A
                {
                    player = "O"; // player1 is player
                    playerName = player1Name;
                } else {
                    player = "X"; // player2 is player
                    playerName = player2Name;
                }

                do
                {
                    rowMove = SafeInput.getRangedInt(in, playerName + " enter the desired Row for your move", 1, 3);
                    colMove = SafeInput.getRangedInt(in, playerName + " enter the desired Column number", 1, 3);
                   // okMove = isValidMove();
                }while (!isValidMove());
                board[rowMove - 1][colMove - 1] = player;
                count++;
                if (count >= 5) // if enough moves for possibility of a win
                {
                    if (isWin())
                    {
                        over = true;
                        System.out.println(playerName + " Wins!");
                        display();
                    }

                }

            }
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again");
            if (player == "X") {
                player = "O";
            }
            else
            {
                player = "X";
            }
        }while (playAgain);


    } // return main()

    // methods to use in this program

    private static void clearBoard() // set each spot on the board to an empty space
    {
        for (int row = 0; row < ROW; row++) // for each row on the board
        {
            for (int col = 0; col < COL; col++) // go through each column on the board
            {
                board[row][col] = " "; // make cell empty space
            } // end column for loop
        } // end row for loop
    } // end method clearBoard

    private static void display()
    {
        System.out.println();
        for (int row = 0; row < ROW; row++) // for each row on the board
        {
           // System.out.print(row);
            for (int col = 0; col < COL; col++) // go through each column on the board
            {
                if (col < 2)
                {
                    System.out.print(board[row][col] + " | ");
                    // System.out.println(board[row][col]);
                }
                else {
                    System.out.println(board[row][col]);
                }
            } // end column for loop
            if (row < 2) {
                System.out.println("---------");
            }
            else
            {
                System.out.println();
            }
         //   System.out.println();
        } // end row for loop
    }

    private static boolean isValidMove()
    {
        boolean status = false;
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++)
            {
                if (board[row][col].equalsIgnoreCase(" "))
                {
                    status = true;
                }
            }
        }
        return status;
    }

    private static boolean isWin()
    {
        return false;
    }

    private static boolean isColWin()
    {
        return false;
    }

    private static boolean isRowWin()
    {
        return false;
    }

    private static boolean isDiagonalWin()
    {
        return false;
    }

    private static boolean isTie()
    {
        return false;
    }

} // end class TicTacToe