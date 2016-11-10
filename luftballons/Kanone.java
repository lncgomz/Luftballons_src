package luftballons;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author LeoncioGomez
 */
public class Kanone extends JLabel implements Runnable {

    private int x;
    private int y;
    private int geschwindigkeit;
    private boolean enPausa, detenido;
    final ImageIcon PFEIL = new ImageIcon(getClass().getResource("/bilder/pfeil.png"));

    /**
     * Método constructor Cañón vacío con especificación de su posición
     *
     * @param x Coordenada x
     * @param y Coordenada y
     */
    public Kanone(int x, int y, int g) {
        super();
        setSize(12, 70);
        this.x = x;
        this.y = y;
        geschwindigkeit = g;
        setX(x);
        setY(y);
        setIcon(PFEIL);
        enPausa = false;
        detenido = false;
    }

    @Override
    public void run() {
        while (!isDetenido()) {
            try {
                if (!enPausa) {
                    fortschritt();
                }
                Thread.sleep(geschwindigkeit);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Controla el avance del proyectil hacia arriba
     */
    public void fortschritt() {
        y = y - 10; //Ascenso
        setY(y);
        if (y < -70){ //Se sale de la vista de la pantalla
            setDetenido(true);
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
     * @return the detenido
     */
    public boolean isDetenido() {
        return detenido;
    }

    /**
     * @param detenido the detenido to set
     */
    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }
}
