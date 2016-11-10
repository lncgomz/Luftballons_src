package luftballons;

import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author LeoncioGomez
 */
public class Brett extends JFrame {   

    public Brett(int h, int w) {
        super("Luftballons");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/bilder/logo.png")));
        setSize(h, w);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container behaelter = getContentPane();
        behaelter.setLayout(new BorderLayout());
        ErstePaneel erstPaneel = new ErstePaneel(behaelter);
        behaelter.add(erstPaneel);
                
    } 
}
        
        
