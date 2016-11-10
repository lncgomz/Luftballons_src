package luftballons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.*;
import java.awt.Color;

/**
 *
 * @author LeoncioGomez
 */
public class Ballon extends JLabel implements Runnable {

    private int x;
    private int y;
    private String wort;
    private int genus;
    private int richtung;
    final ImageIcon GELB = new ImageIcon(getClass().getResource("/bilder/gelb.png"));
    final ImageIcon BLAU = new ImageIcon(getClass().getResource("/bilder/blau.png"));
    final ImageIcon ROT = new ImageIcon(getClass().getResource("/bilder/rot.png"));
    final ImageIcon GRUEN = new ImageIcon(getClass().getResource("/bilder/gruen.png"));
    final ImageIcon VIOLET = new ImageIcon(getClass().getResource("/bilder/violett.png"));
    ImageIcon bilder[] = {BLAU, ROT, GELB, GRUEN, VIOLET};
    private boolean enPausa, detenido;

    /**
     * Método constructor Globo vacío con especificación de su posición
     *
     * @param x Coordenada x
     * @param y Coordenada y
     */
    public Ballon(int x, int y) {
        super();
        setSize(120, 120);
        setX(x);
        setY(y);
        wort = "";
        genus = 0;
        richtung = zufaelling(1, 2);
        enPausa = false;
        detenido = false;
    }

    /**
     * Método para establecer imagen del balón a partir del género/categoría
     *
     * @param genus Categoría de la palabra
     */
    public void bildEinsetzen(int genus) {
        setIcon(bilder[genus]);
        repaint();
        validate();
    }

    /**
     * Método para generar número aleatorio
     *
     * @param a Límite inferior número aleatorio
     * @param b Límite superior número aleatorio
     * @return Número aleatorio entre a y b
     */
    public static int zufaelling(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }

    @Override
    public void run() {
        while (!detenido) {
            try {
                if (!enPausa) {
                    bewegung();
                }
                Thread.sleep(250);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Método para el control del movimiento del globo, en función de su dirección (Richtung)
     */
    public void bewegung() {
        switch (richtung) {
            case 1:
                x = x - 10;
                setX(x);
                if (x < 0) {
                    richtung = 2;
                }
                break;
            case 2:
                x = x + 10;
                setX(x);
                if (x > 400) {
                    richtung = 1;
                }
            default:
        }
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
        setLocation(this.x, y);
        repaint();
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
        setLocation(x, this.y);
        repaint();
    }

    /**
     * @return the wort
     */
    public String getWort() {
        return wort;
    }

    /**
     * @param wort the wort to set
     */
    public void setWort(String wort) {
        this.wort = wort;
        setText(wort);
        setHorizontalTextPosition(CENTER);
        repaint();
    }

    /**
     * @return the genus
     */
    public int getGenus() {
        return genus;
    }

    /**
     * @param genus the genus to set
     */
    public void setGenus(int genus) {
        this.genus = genus;
        bildEinsetzen(genus);
    }

    /**
     * @return the richtung
     */
    public int getRichtung() {
        return richtung;
    }

    /**
     * @param richtung the richtung to set
     */
    public void setRichtung(int richtung) {
        this.richtung = richtung;
    }
}
