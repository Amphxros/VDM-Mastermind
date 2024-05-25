package mastermind.engine;

public class TouchEvent {

    private int x = -1, y = -1; //coordenadas logicas
    private final int windowX, windowY; //coordenadas absolutas
    private final EventType type; //tipo de evento

    public TouchEvent(int windowX, int windowY, EventType event) {
        this.windowX = windowX;
        this.windowY = windowY;
        this.type = event;

    }


    /**
     *
     * @return La coordenada X (horizontal) del evento logico
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return La coordenada Y (vertical) del evento logico
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return Si las coordenadas estan en nuestro margen
     */
    public boolean isValid() {
        return x != -1 && y != -1;
    }

    /**
     * @return La coordenada X (horizontal) absoluta sin transformar en la logica
     */
    public int getWindowX() {
        return windowX;
    }

    /**
     * @return La coordenada Y (vertical) absoluta sin transformar en la logica
     */
    public int getWindowY() {
        return windowY;
    }

    /**
     * Cambia el evento para que sea acorde a la pantalla logica
     * @param graphics
     */
    protected void defineLogicCoordinates(IGraphics graphics) {
        this.x = graphics.getLogicPointX(getWindowX());
        this.y = graphics.getLogicPointY(getWindowY());
    }

    /**
     * @return El tipo de evento
     */
    public EventType getType() {
        return type;
    }
}
