package luftballons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author LeoncioGomez
 */
public class ZweitePaneel extends javax.swing.JPanel {

    final ImageIcon img = new ImageIcon(getClass().getResource("/bilder/brandenburg.jpg"));
    private Container behaelter;

    /**
     * Creates new form zweitePaneel
     *
     * @param behaelter
     */
    public ZweitePaneel(Container behaelter) {
        initComponents();
        this.behaelter = behaelter;
        setLayout(new BorderLayout());
        JLabel background_lbl = new JLabel();
        background_lbl.setIcon(img);
        add(background_lbl, BorderLayout.NORTH);
        JLabel texto_lbl = new JLabel("Seleccione una Categoría / Wählen Sie eine Kategorie");
        texto_lbl.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        texto_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texto_lbl.setBackground(Color.WHITE);
        texto_lbl.setOpaque(true);
        JButton adjektive_btn = new JButton("Adjetivos / Adjektive");
        JButton zeitpunkt_btn = new JButton("Tiempo / Zeitpunkt");
        JButton farben_btn = new JButton("Colores / Farben");
        JButton sport_btn = new JButton("Deportes / Sport");
        JButton familie_btn = new JButton("Familia / Familie");
        JButton obst_btn = new JButton("Frutas / Obst");
        JButton zahle_btn = new JButton("Números / Zahlen");
        JButton verben_btn = new JButton("Verbos / Verben");

        adjektive_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        zeitpunkt_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        farben_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        sport_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        familie_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        obst_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        zahle_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        verben_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));

        adjektive_btn.setBackground(Color.WHITE);
        zeitpunkt_btn.setBackground(Color.WHITE);
        farben_btn.setBackground(Color.WHITE);
        sport_btn.setBackground(Color.WHITE);
        familie_btn.setBackground(Color.WHITE);
        obst_btn.setBackground(Color.WHITE);
        zahle_btn.setBackground(Color.WHITE);
        verben_btn.setBackground(Color.WHITE);
        
        adjektive_btn.addActionListener(new Hoerer());
        zeitpunkt_btn.addActionListener(new Hoerer());
        farben_btn.addActionListener(new Hoerer());
        sport_btn.addActionListener(new Hoerer());
        familie_btn.addActionListener(new Hoerer());
        obst_btn.addActionListener(new Hoerer());
        zahle_btn.addActionListener(new Hoerer());
        verben_btn.addActionListener(new Hoerer());

        JPanel kategoriePaneel = new JPanel(new GridLayout(4, 2));
        kategoriePaneel.setOpaque(false);
        kategoriePaneel.add(adjektive_btn);
        kategoriePaneel.add(zeitpunkt_btn);
        kategoriePaneel.add(farben_btn);
        kategoriePaneel.add(sport_btn);
        kategoriePaneel.add(familie_btn);
        kategoriePaneel.add(obst_btn);
        kategoriePaneel.add(zahle_btn);
        kategoriePaneel.add(verben_btn);

        JPanel zentrumPaneel = new JPanel(new BorderLayout());
        zentrumPaneel.setOpaque(false);
        zentrumPaneel.add(texto_lbl, BorderLayout.NORTH);
        zentrumPaneel.add(kategoriePaneel, BorderLayout.CENTER);
        add(zentrumPaneel, BorderLayout.CENTER);
    }

    public class Hoerer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            behaelter.removeAll();
            JButton aux = (JButton) e.getSource();
            String txt = aux.getText();
            switch (txt) {
                case "Adjetivos / Adjektive":
                    behaelter.add(new HPaneel(behaelter, 0));
                    break;
                case "Tiempo / Zeitpunkt":
                    behaelter.add(new HPaneel(behaelter, 1));
                    break;
                case "Colores / Farben":
                    behaelter.add(new HPaneel(behaelter, 2));
                    break;
                case "Deportes / Sport":
                    behaelter.add(new HPaneel(behaelter, 3));
                    break;
                case "Familia / Familie":
                    behaelter.add(new HPaneel(behaelter, 4));
                    break;
                case "Frutas / Obst":
                    behaelter.add(new HPaneel(behaelter, 5));
                    break;
                case "Números / Zahlen":
                    behaelter.add(new HPaneel(behaelter, 6));
                    break;
                case "Verbos / Verben":
                    behaelter.add(new HPaneel(behaelter, 7));
                    break;
                default:
                    throw new AssertionError();
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
