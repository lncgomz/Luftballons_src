package luftballons;

import jaco.mp3.player.MP3Player;
import java.io.File;

/**
 *
 * @author LeoncioGomez
 */
public class PlayerThread extends Thread {

    private MP3Player mp3_player;
    private String path;
    private boolean detenido, enPausa;

    public PlayerThread(String path) {
        this.path = path;
        detenido = false;
        enPausa = false;
    }

    @Override
    public void run() {
        while (!isDetenido()) {
            try {
                if (!isEnPausa()) {
                    setMp3_player(new MP3Player(new File(getPath())));
                    getMp3_player().play();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * @return the mp3_player
     */
    public MP3Player getMp3_player() {
        return mp3_player;
    }

    /**
     * @param mp3_player the mp3_player to set
     */
    public void setMp3_player(MP3Player mp3_player) {
        this.mp3_player = mp3_player;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
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

    /**
     * @return the enPausa
     */
    public boolean isEnPausa() {
        return enPausa;
    }

    /**
     * @param enPausa the enPausa to set
     */
    public void setEnPausa(boolean enPausa) {
        this.enPausa = enPausa;
    }

}
