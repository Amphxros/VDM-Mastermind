package mastermind.engine;

public interface ISound {
    /**
     * Plays the sound.
     */
    void play();

    /**
     * Stops playing the sound.
     */
    void stop();

    /**
     * Sets the sound in a loop.
     */
    void setLoop();

    /**
     *
     * @return
     */
    float getVolume();

    void setVolume(float volume);
}
