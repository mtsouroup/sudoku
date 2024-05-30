import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Η κλάση αυτή αναπαριστά ένα παράθυρο με μία ετικέτα που λέει στον χρήστη να διαλέξει ποιό παιχνίδι θέλει να παίξει
 * και 3 κουμπιά(ένα για κάθε παιχνίδι)
 */
public class Menu extends JFrame {
    private int choice;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    /***
     * κατασκευαστής
     * ορίζεται και η τοποθεσία το μέγεθος του παραθύρου,το κείμενο των κουμπιών και της ετικέτας
     */
    public Menu()

    {
        super("Επιλογή παιχνιδιού");
        //setTitle("Επίδειξη JButton");
        setSize(270, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        label1 = new JLabel("Διάλεξε παιχνίδι");
        button1 = new JButton();
        ListenerOfButtonA l1 = new ListenerOfButtonA();
        button1.addActionListener(l1);
        button1.setText("Sudoku");
        button2 = new JButton("Killer Sudoku");
        button2.addActionListener(new ListenerOfButtonB());
        button3 = new JButton("Duidoku");
        button3.addActionListener(new ListenerOfButtonC());

        FlowLayout aLayout = new FlowLayout();
        setLayout(aLayout);

        add(label1);
        add(button1);
        add(button2);
        add(button3);

        setVisible(true);

    }

    /***
     * κάνει ορατά τα γραφικά του Sudoku καλώντας την αντίστοιχη κλάση αφού πατήθηκε το αντίστοιχο κουμπί
     */
    private class ListenerOfButtonA implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            new SudokuGraphics().setVisible(true);
        }

    }

    private class ListenerOfButtonB implements ActionListener {

        public void actionPerformed(ActionEvent e) { choice=2;}

    }
    /***
     * κάνει ορατά τα γραφικά του Duidoku καλώντας την αντίστοιχη κλάση αφού πατήθηκε το Duidoku κουμπί
     */
    private class ListenerOfButtonC implements ActionListener {
       public void actionPerformed(ActionEvent e){

            new DuidokuGraphics().setVisible(true);
       }
    }
}
