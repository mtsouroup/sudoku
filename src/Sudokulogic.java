import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/***
 * η κλάση αναπαριστά την λογική του Sudoku που χρησιμοποιείται και για την λογική του Duidoku όταν παίζει ο παίκτης
 */

public class Sudokulogic {
    private int[][] board;
    private int size;
    private int m; //η διάσταση του τετραγώνου

    /***
     *κατασκευαστής
     * @param size το μέγεθος των διαστάσεων του πίνακα 4 για duidoku και 9 για sudoku
     * @param board ο αρχικός πίνακας sudoku,duidoku
     */

    public Sudokulogic(int size, int[][] board) {
        this.size = size;
        m = (int) Math.sqrt(size);
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }


    /***
     * Επιστρέφει true αν υπάρχει ο αριθμός στην γραμμή
     * @param row γραμμή
     * @param number αριθμός
     *
     */

    public boolean isInRow(int row, int number) {
        for (int j = 0; j < size; j++) {
            if (board[row][j] == number)
                return true;
        }
        return false;
    }
    /***
     * Επιστρέφει true αν υπάρχει ο αριθμός στην στήλη
     * @param col αριθμός γραμμής
     * @param number αριθμός
     *
     */

    public boolean isInCol(int col, int number) {
        for (int i = 0; i < size; i++) {
            if (board[i][col] == number)
                return true;
        }
        return false;
    }

    /***
     *
     * @param row γραμμή
     * @param col στήλη
     * @param number αριθμός
     * Επιστρέφει αν υπάρχει το νούμερο στο τετράγωνο οπου βρίσκονται η γραμμή και η στήλη
     */

    public boolean isInBox(int row, int col, int number) {
        int r = row - row % m;
        int c = col - col % m;
        for (int i = r; i < r + m; i++) {
            for (int j = c; j < c + m; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     *
     * @param row γραμμή
     * @param col στήλη
     * @param number  νούμερο
     * Επιστρέφει true αν το νούμερο μπορεί τοποθετηθεί στην συγκεκριμένη γραμμή και στήλη
     *                (δηλαδή όταν δεν υπάρχει στο τετράγωνο,την γραμμή και την στήλη)
     */

    public boolean isAcceptable(int row, int col, int number) {
        return !(isInRow(row, number) || isInCol(col, number) || isInBox(row, col, number));
    }

    /***
     * επιστρέφει  στοιχείο του πίνακα που βρίσκεται στην δοσμένης γραμμή και στήλη
     */

    public int getElement(int row, int col) {
        return board[row][col];
    }
    public void setElement(int row, int col, int number) {
        board[row][col] = number;
    }

    /***
     *
     *επιστρέφει true αν το στοιχείο της γραμμής στήλης είναι 0 δηλαδή δεν έχει κάποιο νούμερο
     */
    public boolean isEmpty(int row,int col){
        if (board[row][col]==0){
            return true;
        }
        return false;
    }

    /***
     *
     * Δημιουργεί μια συμβολοσειρά που αποτελεί τα νούμερα της βοήθειας
     * μετατρέπει τα επιτρεπτά νούμερα σε συμβολοσειρά και τα προσθέτει στην αρχική συμβολοσειρά
     */
    public String helpdis(int row, int col) {
        String help="help: ";
        for (int i = 1; i <= size; i++) {
            if (isAcceptable(row, col, i)) {
                String si=Integer.toString(i);
                help=help +"   " + si;
            }
        }
        return help;
    }

    public int getSize(){
        return size;
    }

    /***
     *Επιστρέφει ένα Arraylist ακεραίων που περιέχει όλα τα στοιχεία που μπορούν να τοποθετηθούν στην συγκεκριμένη θέση που προσδιορίζεται από τις παραμέτρους
     * για καθένα από τα νούμερα αν αυτό μπορεί τα τοποθετηθεί σε αυτήν την θέση προστιθεται στο arraylist
     */
    public ArrayList<Integer> help(int row, int col) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            if (isAcceptable(row, col, i)) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    /***
     * επιλέγουμε τυχαία μια γραμμή και μια στήλη και με την μέθοδο helpnumbers βρίσκουμε τους αριθμούς που μπορούν να τοποθετηθούν σε αυτήν.
     * η διαδικασία επαναλαμβάνεται όσο η θέση δεν είναι κενή ή δεν μπορεί να τοποθετηθεί κανένα στοιχείο σε αυτή
     * Από τους αριθμούς του Arraylist επιλέγουμε έναν τυχαία
     */
    public void computersMove(){
        Random random = new Random();
        int rrow = random.nextInt(4) ;
        int rcol = random.nextInt(4) ;
        ArrayList<Integer> helpnumbersk = new ArrayList<>();
        helpnumbersk = help(rrow, rcol);
         while( getElement(rrow,rcol)!= 0 || helpnumbersk.isEmpty()) {
            rrow = random.nextInt(4) ;
            rcol = random.nextInt(4);
            helpnumbersk = help(rrow, rcol);
        }
         int number =helpnumbersk.get(random.nextInt(helpnumbersk.size()));
         setElement(rrow,rcol,number);
     }
}
