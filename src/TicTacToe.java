import java.util.Scanner; // import Scanner class

/**
 *
 * @author Daniel Sandy sandyds@mail.uc.edu
 */

public class TicTacToe // class TicTacToe
{

    private static final int ROW = 3; // declare & initialize global constant ROW
    private static final int COL = 3; // declare & initialize global constant COL
    private static String board[][] = new String[ROW][COL];// declare & initialize global variable board

    public static void main(String[] args) // main()
    {
        Scanner in = new Scanner(System.in); // store Scanner in in
        boolean playAgain = false; // variable to play again
        boolean over = false; // variable to end round
        String player1Name = ""; // name of player 1
        String player2Name = ""; // name of player 2
        String playerName = ""; // player whose turn it is
        String player = "O"; // symbol for each player
        int player1Wins = 0; // # of wins for player 1
        int player1Losses = 0; // # of losses for player 1
        int player1Ties = 0; // # of ties for player 1
        int player2Wins = 0; // # of wins for player 2
        int player2Losses = 0; // # of losses for player 2
        int player2Ties = 0; // # of ties for player 2
        int count = 0; // turn number in the round
        int rowMove = 0; // player row choice
        int colMove = 0; // player column choice

        System.out.println("\nWelcome to Daniel Sandy's TicTacToe!"); // output welcome message
        player1Name = SafeInput.getNonZeroLenString(in, "Enter name for Player 1"); // method to input player 1 name
        player2Name = SafeInput.getNonZeroLenString(in, "Enter name for Player 2"); // method to input player 2 name
        System.out.println("\nWelcome " + player1Name + " & " + player2Name + "!"); // output welcome message

        do { // start round
            clearBoard(board); // clear the board
            while(!over) { // while the round continues
                display(); // display the boar
                if (player.equals("O")) // name player A
                {
                    player = "X"; // player1 is X
                    playerName = player1Name; // player name is stored as player 1
                }
                else // naming player 2
                {
                    player = "O"; // player2 is O
                    playerName = player2Name; // player name is stored as player 2
                } // end if else
                boolean validMove = false; // assume it is not a valid move
                do // do to validate the space chosen on the board is open
                {
                    rowMove = SafeInput.getRangedInt(in, playerName + " enter the desired Row for your move", 1, 3) - 1; // input row choice & subtract 1 to reflect index
                    colMove = SafeInput.getRangedInt(in, playerName + " enter the desired Column for your move", 1, 3) - 1; // input column choice & subtract 1 to reflect index
                    validMove = isValidMove(board, rowMove, colMove); // check if it is an empty space
                    if (!validMove) // if it is not an empty spot on the board
                    {
                        System.out.println("\nTHE SPACE YOU SELECTED IS ALREADY TAKEN. REDO!"); // make player choose a different spot on the board
                    } // end if
                }while (!validMove); // continue as long as the move is invalid
                board[rowMove][colMove] = player; // once valid spot is chosen mark it on the board
                count++; // add one to the number of overall turns this round
                if (count >= 5) // if enough moves for possibility of a win
                {
                    if (isWin(player)) // if there is a winner
                    {
                        display(); // show the board
                        over = true; // end the round
                        System.out.println(playerName.toUpperCase() + " WINS!"); // output congratulatory message
                        if (playerName.equals(player1Name)) // if player 1 wins
                        {
                            player1Wins++; // add a win to their record
                            player2Losses++; // add a loss to player 2 record
                        }
                        else if (playerName.equals(player2Name)) // if player 2 wins
                        {
                            player2Wins++; // add a win to their record
                            player1Losses++; // add a loss to player 1 record
                        } // end if else
                    } // end if to check if there is a winner
                    else if (count >= 7 && isTie(player)) // if there is enough moves to call a tie
                    {
                        //if (isTie(player)) // check if it is a tie and if so
                        //{
                        display(); // show the board
                        over = true; // end the round
                        System.out.println("IT IS A TIE!"); // output that it is a tie
                        player1Ties++; // add a tie to player 1's record
                        player2Ties++; // add a tie to player 2's record
                        // } // end if
                    } // end if
                } // end if
            } // end the round
            System.out.println(); // clear a line for visual affects
            System.out.println(player1Name.toUpperCase() + "'s record is: " + player1Wins + " - " + player1Losses + " - " + player1Ties); // output player 1's record
            System.out.println(player2Name.toUpperCase() + "'s record is: " + player2Wins + " - " + player2Losses + " - " + player2Ties); // output player 2's record
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again"); // ask if they want to play again
            over = false; // reset over to false
            count = 0; // reset the turn count
        }while (playAgain); // continue the loop as long as they want to keep playing
        System.out.println("\nThank you for playing!"); // thank them for playing
    } // return main()

    // methods to use in this program

    private static void clearBoard(String[][] board) // set each spot on the board to an empty space
    {
        for (int row = 0; row < ROW; row++) // for each row on the board
        {
            for (int col = 0; col < COL; col++) // go through each column on the board
            {
                board[row][col] = " "; // make cell empty space
            } // end column for loop
        } // end row for loop
    } // end method clearBoard

    private static void display() // create board display
    {
        System.out.println(); // add a space for visual affect
        for (int row = 0; row < ROW; row++) // for each row on the board
        {
            for (int col = 0; col < COL; col++) // go through each column on the board
            {
                if (col < 2) // if it is before the third column
                {
                    System.out.print(board[row][col] + " | "); // add a pipe for visual affect
                }
                else
                {
                    System.out.println(board[row][col]); // otherwise do not add a pipe after the third column
                } // end if else
            } // end column for loop
            if (row < 2) { // if it is before the 3rd row
                System.out.println("---------"); // add dashes for visual affect
            }
            else // do not add dashes after the 3rd row
            {
                System.out.println(); // add a space after the third rod for visual affect
            } // end if else
        } // end row for loop
    } // end display method

    private static boolean isValidMove(String[][] board, int row, int col) // method to validate spot on board is open
    {
        if (board[row][col] != " ") // if spot does not contain a space
        {
            return false; // reject the user's move
        }
        else // if spot is open
        {
            return true; // allow the move
        } // end if else
    } // end isValidMove method

    private static boolean isWin(String player) // check if user won
    {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player); // check all three ways to win
    } // end isWin method

    private static boolean isColWin(String player) // check if user has three in one column
    {
        for (int col = 0; col < COL; col++) // loop through columns
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) // check if same person has 3 marks in column
            {
                return true; // they win if so
            } // end if
        } // end for loop
        return false; // if they did not have 3 in same column, no winner yet
    } // end isColWin method

    private static boolean isRowWin(String player) // check if player has three in one row
    {
        for (int row = 0; row < ROW; row++) // loop through rows
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) // if player has 3 in a row
            {
                return true; // they win
            } // end if
        } // end for loop
        return false; // the game goes on if not 3 in a row
    } // end isRowWin method

    private static boolean isDiagonalWin(String player) // method to check if there is a diagonal win
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)); // if there is a diagonal win return true
    } // end isDiagonalWin method

    private static boolean isTie(String player) // method to check if there is a tie
    {
        for (int row = 0; row < ROW; row++) // loop through rows
        {
            for (int col = 0; col < COL; col++) // loop through columns
            {
                if (board[row][col].equals(" ")) // if there are any empty spaces on the board
                {
                    board[row][col] = "X"; // put X in them to see if there could be a win
                    if (isWin("X")) // if there is a win for X
                    {
                        board[row][col] = " "; // take the X's out
                        return false; // continue the game
                    } // end if
                    board[row][col] = "O"; // put O in the empty spot to see if there could be a win
                    if (isWin("O")) // if there is a win for O
                    {
                        board[row][col] = " "; // take the O's out
                        return false; // continue the game
                    } // end if
                    board[row][col] = " "; // make sure empty spaces are still empty
                } // end if
            } // end for loop through columns
        } // end for loop through rows
        return true; // return true if there is no way to win
    } // end isTie method

} // end class TicTacToe