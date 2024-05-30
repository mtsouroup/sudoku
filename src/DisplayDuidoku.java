import javax.swing.*;
import java.awt.*;

/***
 * η κλάση αυτή είναι ίδια με την DisplaySudoku με διαφορετικά μεγέθη συστατικών και άλλο τίτλο
 */
public class DisplayDuidoku extends JComponent {
    private static final int CELL_PIXELS=90;
    private static final int TEXT_OFFSET=35;
    private static final int SIZE=4;
    private static final int BOARD_PIXELS=CELL_PIXELS*SIZE;
    private Sudokulogic duidoku;

    public DisplayDuidoku(Sudokulogic duidoku) {
        setPreferredSize(new Dimension(BOARD_PIXELS + 2,  BOARD_PIXELS+ 2));
        setBackground(Color.BLACK);
        this.duidoku = duidoku;
    }

    private int boardpixels = CELL_PIXELS * SIZE;

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
            int yDisplays = (i+1) * CELL_PIXELS - TEXT_OFFSET;
            for (int j = 0; j < SIZE; j++) {
                if (duidoku.getElement(i, j) != 0) {
                    int xDisplays = j * CELL_PIXELS + TEXT_OFFSET;
                    graph.drawString("" + duidoku.getElement(i, j), xDisplays, yDisplays);
                }
            }
        }
    }
}
