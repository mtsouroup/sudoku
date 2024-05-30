import javax.swing.*;
import java.awt.*;
import java.awt.Font;

/***
 * η κλάση αυτή δημιουργεί τα γραφικά του πίνακα (μέγεθος,χρώμα κελιών και συμβόλων και τις γραμμές ενδιάμεσα στα νούμερα)
 */
public class DisplaySudoku extends JComponent {
    private static final int CELL_PIXELS=60;
    private static final int TEXT_OFFSET=25;
    private static final int SIZE=9;
    private static final int BOARD_PIXELS=CELL_PIXELS*SIZE;
    private Sudokulogic sudoku;

    /***
     * κατασκευαστής
     * ορίζεται το μέγεθος και το χρώμα του background
     */
    public DisplaySudoku(Sudokulogic sudoku) {
        setPreferredSize(new Dimension(BOARD_PIXELS + 2,  BOARD_PIXELS+ 2));
        setBackground(Color.BLACK);
        this.sudoku = sudoku;
    }

    private int boardpixels = CELL_PIXELS * SIZE;

    /***
     * Στην μέθοδο αυτή σχεδιάζονται οι γραμμές ανάμεσα στα κελιά και τυπώνεται τα στοιχεία στις θέσεις του πίνακα
     * @param graph :το γράφημα το οποίο δημιουργείται
     */
    public void paintComponent(Graphics graph) {
        graph.fillRect(0, 0, getWidth(), getHeight());
        graph.setColor(Color.WHITE);
        Font font=new Font("TimesRoman", Font.PLAIN, 28);
        graph.setFont(font);
        for (int i = 0; i <= SIZE; i++) {
            graph.drawLine(i*CELL_PIXELS, 0, i*CELL_PIXELS, BOARD_PIXELS);
            graph.drawLine(0, i*CELL_PIXELS, BOARD_PIXELS, i*CELL_PIXELS);
        }
        for (int i = 0; i < SIZE; i++) {
            int yDisplacement = (i+1) * CELL_PIXELS - TEXT_OFFSET;
            for (int j = 0; j < SIZE; j++) {
                if (sudoku.getElement(i, j) != 0) {
                    int xDisplacement = j * CELL_PIXELS + TEXT_OFFSET;
                    graph.drawString("" + sudoku.getElement(i, j), xDisplacement, yDisplacement);
                }
            }
        }
    }
}

