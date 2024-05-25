package mastermind.engine;

public interface ILogic {

    /**
     * Inicio
     */
    void init();

    /**
     * Actualizacion de movimiento
     * @param t tiempo
     */
    void update(double t);

    /**
     * Renderizado
     * @param graphics Sistema grafico a usar
     */
    void render(IGraphics graphics);

    /**
     * Manejo de eventos de pantalla
     * @param input Sistema de input a usar
     */
    void handleEvents(IInput input);

    /**
     * @return El motor que se esta usando
     */
    IEngine getEngine();
}
