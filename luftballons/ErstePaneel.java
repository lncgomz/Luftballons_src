/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luftballons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author LeoncioGomez
 */
public class ErstePaneel extends javax.swing.JPanel {

    final ImageIcon img = new ImageIcon(getClass().getResource("/bilder/berlin.jpg"));
    private Container behaelter;
    private PlayerThread player_thread;
    ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     * Creates new form erstePannel
     *
     * @param behaelter
     */
    public ErstePaneel(Container behaelter) {
        initComponents();
        this.behaelter = behaelter;
        setLayout(new BorderLayout());
        JLabel background_lbl = new JLabel();
        background_lbl.setIcon(img);
        add(background_lbl, BorderLayout.CENTER);
        JButton anfang_btn = new JButton("INICIAR / ANFANGEN");
        anfang_btn.setBackground(Color.WHITE);
        anfang_btn.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
        anfang_btn.addActionListener(new Hoerer());
        add(anfang_btn, BorderLayout.SOUTH);
        player_thread = new PlayerThread("luftballons.mp3");
        threadPool.execute(player_thread);
    }

    /**
     * Método ActionListener del botón INICIAR/ANFANGEN
     */
    public class Hoerer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            player_thread.setDetenido(true);
            player_thread.setEnPausa(true);
            behaelter.removeAll();
            behaelter.add(new ZweitePaneel(behaelter));
            behaelter.validate();
            
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
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
