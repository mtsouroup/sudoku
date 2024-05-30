import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * η κλάση αυτή είναι ίδια με την SudokuGraphics με διαφορά ότι δέχεται διαφορετικό πίνακα και δημιουργεί το
 * αντικείμενο duidoku τύπου Sudokoulogic
 *
 */

public class DuidokuGraphics extends JFrame {
    private static final int[][] BOARD = {
            {0, 0, 0, 0 },
            {0, 0, 0, 0 },
            {0, 0, 0, 0 },
            {0, 0, 0, 0 }
    };
    private Sudokulogic duidoku = new Sudokulogic(4, BOARD);
    private DisplayDuidoku duidokuBoard = new DisplayDuidoku(duidoku);

    private JTextField rowfield = new JTextField(2);
    private JTextField colfield = new JTextField(2);
    private JTextField numfield = new JTextField(2);

    public DuidokuGraphics() {
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
        inside.add(duidokuBoard, BorderLayout.CENTER);
        setContentPane(inside);
        setTitle("Duidoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    /***
     * η κλάση αναπαριστά μια κίνηση του παίκτη.
     * μετά από κάθε κίνηση του παίκτη ακολουθεί μια κίνηση του υπολογιστεί η οποιά γίνετια με την μέθοδο computersMove();
     */
    class moveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Μετατρέπω τα row και col σε δείκτες πίνακα
            int row = Integer.parseInt(rowfield.getText()) - 1;
            int col = Integer.parseInt(colfield.getText()) - 1;
            int num = Integer.parseInt(numfield.getText());
            if (duidoku.isAcceptable(row, col, num) && duidoku.isEmpty(row, col)) {
                duidoku.setElement(row, col, num);
                duidokuBoard.repaint();
                duidoku.computersMove();//τυχαία κίνηση υπολογιστή
            } else {
                JOptionPane.showMessageDialog(null, "Μη έγκυρη κίνηση");
            }
        }
    }

    class helpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = Integer.parseInt(rowfield.getText()) - 1;
            int col = Integer.parseInt(colfield.getText()) - 1;
            String message=duidoku.helpdis(row,col);
            JOptionPane.showMessageDialog(null,message);
        }

    }
}
