package mastermind.engine;

public interface ISound {
    /**
     * Reproduce el sonido
     */
    void play();

    /**
     * Para el sonido
     */
    void stop();

    /**
     * Pone el sonido en bucle
     */
    void setLoop();

    /**
     * Cambia el volumen del sonido
     * @param volume Volumen al que se pone
     */
    void setVolume(float volume);
}
