import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

/***
 * η κλάση αυτή είναι επέκταση της Jframe σε αυτήν δημιουργούνται τα συστατικά του παραθύρου και τα TextFields
 * που δέχονται τιμές που εισάγει ο χρήστης για την θέση που θέλει να τοποθετήσει στοιχείο και για το στοιχείο
 */

public class SudokuGraphics extends JFrame {

    private static final int[][] BOARD = {
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
    };

    private Sudokulogic sudoku = new Sudokulogic(9,BOARD);
    private DisplaySudoku sudokuBoard = new DisplaySudoku(sudoku);

    private JTextField rowfield = new JTextField(2);
    private JTextField colfield = new JTextField(2);
    private JTextField numfield= new JTextField(2);

    /***
     * κατασκευαστής
     * Δημιουργούνται 2 JPanel για την ομαδοποίηση των συστατικών του παραθύρου(το panel για ετικέτες,κουμπια
     * και το inside για τα συστατικά του: τιτλο,τοποθεσία,περίγραμμα)
     *
     */
    public SudokuGraphics() {
        JButton move = new JButton("Move");
        JButton help = new JButton("Help");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Row "));
        panel.add(rowfield);
        panel.add(new JLabel("Col "));
        panel.add(colfield);
        panel.add(new JLabel("Num:"));
        panel.add(numfield);
        panel.add(move);
        panel.add(help);
        move.addActionListener(new moveListener());
        help.addActionListener(new helpListener());
        JPanel inside = new JPanel();
        inside.setLayout(new BorderLayout());

        inside.add(panel, BorderLayout.NORTH);
        inside.add(sudokuBoard, BorderLayout.CENTER);
        setContentPane(inside);
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    /***
     * η κλάση αυτή αναπαριστά την κίνηση του πάικτη
     */
    class moveListener implements ActionListener {
        /***
         *η μέθοδος αυτή ειναι override της actionPerformed
         *Μετατρέπει τα row και col που δίνει ο χρήστης σε δείκτες πίνακα
         * αν δεν υπάρχει άλλο στοιχείο στην θέση και αν αυτό το στοιχείο μπορεί να τοποθετηθεί σε αυτή τοποθετείται
         * αλλιώς εμφανίζεται παράθυρο με μήνυμα λάθους
         */
        public void actionPerformed(ActionEvent e) {
            //Μετατρέπω τα row και col σε δείκτες πίνακα
            int row = Integer.parseInt(rowfield.getText()) - 1;
            int col = Integer.parseInt(colfield.getText()) - 1;
            int num = Integer.parseInt(numfield.getText());
            if (sudoku.isAcceptable(row, col, num) && sudoku.isEmpty(row, col)) {
                sudoku.setElement(row, col, num);
                sudokuBoard.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Μη έγκυρη κίνηση");
            }
        }
    }

    class helpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = Integer.parseInt(rowfield.getText()) - 1;
            int col = Integer.parseInt(colfield.getText()) - 1;
            String message=sudoku.helpdis(row,col);
            JOptionPane.showMessageDialog(null,message);
        }

    }
    public static void main(String[] args) {
        new SudokuGraphics().setVisible(true);
    }
}
