package mastermind.pcengine;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import mastermind.engine.ISound;

/**
 * Clase que implementa la interfaz ISound para manejar la reproducción de sonidos en un entorno de PC.
 */
public class PCSound implements ISound {
    // Objeto Clip que representa el archivo de sonido a reproducir.
    Clip audioClip;
    // Variable que indica si el sonido debe reproducirse en bucle.
    boolean loop;



    /**
     * Constructor de la clase PCSound.
     *
     * @param audioClip El objeto {@Link Clip} que representa el archivo de sonido.
     */
    public PCSound(Clip audioClip, float volume){
        this.audioClip=audioClip;
        this.loop=false;
        this.setVolume(volume);
    }

    /**
     * Reproduce el sonido desde el principio.
     */
    @Override
    public void play() {
        audioClip.setFramePosition(0);
        audioClip.start();
    }

    /**
     * Detiene la reproducción del sonido.
     */
    @Override
    public void stop() {
        audioClip.stop();
    }

    /**
     * Establece o desactiva la reproducción en bucle del sonido.
     */
    @Override
    public void setLoop() {
        this.loop=!loop;
        if(loop){
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else{
            audioClip.loop(0);
        }
    }
    @Override
    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        if (volume >= 0f && volume <= 1f){
            gainControl.setValue(20f * (float) Math.log10(volume));
        }else if(volume < 0f){
            gainControl.setValue(20f * (float) Math.log10(0f));
        }else{
            gainControl.setValue(20f * (float) Math.log10(1f));
        }
    }
}
