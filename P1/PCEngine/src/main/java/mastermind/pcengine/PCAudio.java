package mastermind.pcengine;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import mastermind.engine.IAudio;
import mastermind.engine.ISound;

public class PCAudio implements IAudio {

    public PCAudio(){

    }
    /**
     *
     * @param path // Crea un objeto PCSound a partir de un archivo de sonido
     * @return An {@link ISound} instance.
     */
    @Override
    public PCSound createSound(String path) {
        // Declaración de un objeto Clip para almacenar el sonido
        Clip sound;

        try {
            // Crear un objeto File representando el archivo de audio en la ruta especificada
            File audioFile = new File("Assets/" + path);

            // Obtener un flujo de entrada de audio desde el archivo
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Obtener un objeto Clip del sistema de audio
            sound = AudioSystem.getClip();

            // Abrir el Clip con el flujo de entrada de audio
            sound.open(audioStream);

        } catch (Exception exception) {

            // Manejar excepciones imprimiendo la traza y devolver null en caso de error
            exception.printStackTrace();
            return null;
        }

        return new PCSound(sound, 0.03f);
    }

    /**
     * Comienza a sonar un sonido.
     *
     * @param s El {@link ISound} de playSound.
     */
    @Override
    public void playSound(ISound s) {
        s.play();
    }

    /**
     * Para de sonar el sonido.
     *
     * @param s El {@link ISound} para parar el sonido.
     */
    @Override
    public void stopSound(ISound s) {
        s.stop();
    }


}
