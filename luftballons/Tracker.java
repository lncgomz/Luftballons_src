package luftballons;

import java.awt.Container;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author LeoncioGomez
 */
public class Tracker implements Runnable {

    private Ballon[] ballons;
    private ArrayList<Wort> quelle, haupt, sekundar;
    private Kanone kanone;
    private boolean pfeilON = true;
    private JLabel bewertung;
    private JLabel unterschreibung;
    private Container behaelter;
    private boolean enPausa, detenido;
    private int richtigID;

    /**
     *Método constructor del objeto Tracker, se encarga de coordinar el comportamiento de los diferentes hilos activos en el panel
     * @param ballons Globos
     * @param bewertung Puntuación
     * @param unterschreibung Traducción en español
     * @param kategorie Categoría de la palabra
     * @param behaelter JFrame contenedor
     */
    public Tracker(Ballon[] ballons, JLabel bewertung, JLabel unterschreibung, int kategorie, Container behaelter) {
        this.ballons = ballons;
        enPausa = false;
        detenido = false;
        this.bewertung = bewertung;
        this.behaelter = behaelter;
        this.unterschreibung = unterschreibung;
        quelle = Daten(kategorie);
        haupt = new ArrayList();
        aktualizieren();
    }

    @Override
    public void run() {
        while (!detenido) {
            try {
                if (!enPausa) {
                    tracking();
                }
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Método de rastreo, coordina eventos de coincidencia entre globos y proyectiles
     */
    public void tracking() {

        if (((kanone.getY() >= 50) && (kanone.getY() <= 170)) && ((kanone.getX() >= getBallons()[0].getX()) && (kanone.getX() <= getBallons()[0].getX() + 120))) {
            zusammenstoss(0);
        }

        if (((kanone.getY() >= 50) && (kanone.getY() <= 170)) && ((kanone.getX() >= getBallons()[1].getX()) && (kanone.getX() <= getBallons()[1].getX() + 120))) {
            zusammenstoss(1);
        }

        if ((kanone.getY() > 180) && (kanone.getY() < 300) && ((kanone.getX() >= getBallons()[2].getX()) && (kanone.getX() <= getBallons()[2].getX() + 120))) {
            zusammenstoss(2);
        }

        if ((kanone.getY() > 180) && (kanone.getY() < 300) && ((kanone.getX() >= getBallons()[3].getX()) && (kanone.getX() <= getBallons()[3].getX() + 120))) {
            zusammenstoss(3);
        }

        if (kanone.getY() < -70) {
            pfeilON = true;
        }

    }
/**
 * Método de evento de choque entre proyectil y globo
 * @param ballonID El globo con el cual se dió la colisión
 */
    public void zusammenstoss(int ballonID) {
        kanone.setDetenido(true);
        kanone.setVisible(false);
        if (!pfeilON) {
            if (ballonID == richtigID) {
                bewertung.setText(String.valueOf(Integer.valueOf(bewertung.getText()) + 1));
                aktualizieren();
                pfeilON = true;
            } else {
                bewertung.setText(String.valueOf(Integer.valueOf(bewertung.getText()) - 1));
                aktualizieren();
                pfeilON = true;
            }
            bewertung.validate();
        }
    }

    /**
     * Método de actualización de palabras en los globos al inicio de la sección y luego de cada colisión, controla evento de fin de palabras en categoría
     */
    public void aktualizieren() { 
        if (haupt.size() < quelle.size()) {
            getHaupt();
            sekundar = getSekundar();
            woerterGeben();
        } else {
            JOptionPane.showMessageDialog(null, "RESULTADO: " + bewertung.getText() + "/" + quelle.size(), "FINALIZADO", JOptionPane.INFORMATION_MESSAGE);
            behaelter.removeAll();
            behaelter.add(new ZweitePaneel(behaelter));
            behaelter.validate();
        }

    }

    /**
     * Método estático para obtener un ArrayList con las búsquedas en BD de palabras correspondientes a una categoría determinada
     * @param kategorie Categoría de palabra
     * @return ArrayList de Palabras de la categoría kategorie.
     */
    public static ArrayList<Wort> Daten(int kategorie) {
        String deutsch, spanisch;
        int klasse;
        String query = "SELECT * FROM woerter WHERE kategorie = " + "'" + kategorie + "'";
        ArrayList<Wort> resp = new ArrayList();
        try {
            MySQL db = new MySQL();
            db.MySQLConnect();
            db.comando = db.conexion.createStatement();
            db.registro = db.comando.executeQuery(query);

            while (db.registro.next()) {
                deutsch = db.registro.getString("deutsch");
                spanisch = db.registro.getString("spanisch");
                klasse = db.registro.getInt("klasse");
                resp.add(new Wort(deutsch, spanisch, klasse, kategorie));
            }
            db.comando.close();
            db.registro.close();
            db.conexion.close();
        } catch (SQLException ex) {

        }
        return resp;
    }

    /**
     * Método para la obtención de palabra principal a ser buscada por el jugador, se buscarán todas las palabras de la categoría al menos una y sólo una vez.
     */
    public void getHaupt() {
        Wort aux = quelle.get(Ballon.zufaelling(0, quelle.size() - 1));
        if (haupt.isEmpty()) {
            haupt.add(aux);
        } else {
            boolean dasselbe = true;
            int cont = 0;
            while (dasselbe) {

                for (int i = 0; i < haupt.size(); i++) {
                    if (("" + aux.getDe()).equals(("" + haupt.get(i).getDe())) == true) {
                        cont++;
                    }
                }
                if (cont > 0) {
                    dasselbe = true;
                    cont = 0;
                    aux = quelle.get(Ballon.zufaelling(0, quelle.size() - 1));
                } else {
                    dasselbe = false;
                }
            }
            haupt.add(aux);
        }
    }

    /**
     * Método para la obtención de las palabras que irán acompañando a la principal
     * @return 
     */
    public ArrayList<Wort> getSekundar() {
        ArrayList<Wort> resp = new ArrayList();
        resp.add(haupt.get(haupt.size() - 1));
        int cont = 0;
        while (resp.size() < 4) {
            Wort aux = quelle.get(Ballon.zufaelling(0, quelle.size() - 1));
            for (int j = 0; j < resp.size(); j++) {
                if (("" + aux.getDe()).equals(("" + resp.get(j).getDe())) == true) {
                    cont++;
                }
            }
            if (cont == 0) {
                resp.add(aux);
            } else {
                cont = 0;
            }
        }
        return resp;
    }
/**
 * Método de "Da palabras" a los globos y asigna su color en función de la clase de la palabra, esto de forma aleatoria.
 */
    public void woerterGeben() {
        int rnd = Ballon.zufaelling(0, 3);
        unterschreibung.setText(sekundar.get(0).getEs());
        switch (rnd) {
            case 0:
                ballons[0].setWort(sekundar.get(0).getDe());
                ballons[0].setGenus(sekundar.get(0).getKlasse());
                ballons[1].setWort(sekundar.get(1).getDe());
                ballons[1].setGenus(sekundar.get(1).getKlasse());
                ballons[2].setWort(sekundar.get(2).getDe());
                ballons[2].setGenus(sekundar.get(2).getKlasse());
                ballons[3].setWort(sekundar.get(3).getDe());
                ballons[3].setGenus(sekundar.get(3).getKlasse());
                richtigID = 0;
                break;
            case 1:
                ballons[0].setWort(sekundar.get(1).getDe());
                ballons[0].setGenus(sekundar.get(1).getKlasse());
                ballons[1].setWort(sekundar.get(0).getDe());
                ballons[1].setGenus(sekundar.get(0).getKlasse());
                ballons[2].setWort(sekundar.get(2).getDe());
                ballons[2].setGenus(sekundar.get(2).getKlasse());
                ballons[3].setWort(sekundar.get(3).getDe());
                ballons[3].setGenus(sekundar.get(3).getKlasse());
                richtigID = 1;
                break;
            case 2:
                ballons[0].setWort(sekundar.get(1).getDe());
                ballons[0].setGenus(sekundar.get(1).getKlasse());
                ballons[1].setWort(sekundar.get(2).getDe());
                ballons[1].setGenus(sekundar.get(2).getKlasse());
                ballons[2].setWort(sekundar.get(0).getDe());
                ballons[2].setGenus(sekundar.get(0).getKlasse());
                ballons[3].setWort(sekundar.get(3).getDe());
                ballons[3].setGenus(sekundar.get(3).getKlasse());
                richtigID = 2;
                break;
            case 3:
                ballons[0].setWort(sekundar.get(1).getDe());
                ballons[0].setGenus(sekundar.get(1).getKlasse());
                ballons[1].setWort(sekundar.get(2).getDe());
                ballons[1].setGenus(sekundar.get(2).getKlasse());
                ballons[2].setWort(sekundar.get(3).getDe());
                ballons[2].setGenus(sekundar.get(3).getKlasse());
                ballons[3].setWort(sekundar.get(0).getDe());
                ballons[3].setGenus(sekundar.get(0).getKlasse());
                richtigID = 3;
                break;

            default:
                throw new AssertionError();
        }

    }

    /**
     * @return the ballons
     */
    public Ballon[] getBallons() {
        return ballons;
    }

    /**
     * @param ballons the ballons to set
     */
    public void setBallons(Ballon[] ballons) {
        this.ballons = ballons;
    }

    /**
     * @param kanoneON
     * @return the kanone
     */
    public Kanone getKanone() {
        return kanone;
    }

    /**
     * @param kanone the kanone to set
     * @param pfeilON
     */
    public void setKanone(Kanone kanone, boolean pfeilON) {
        this.kanone = kanone;
        this.pfeilON = pfeilON;

    }

    public boolean getPfeilON() {
        return pfeilON;
    }

}
