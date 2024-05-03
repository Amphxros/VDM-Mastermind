package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;
import mastermind.engine.ISound;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;

/**
 * Clase que representa una casilla rellenable en el juego, implementa la interfaz DaltonicListener.
 */
public class Cell extends Button implements DaltonicListener{
    int value=-1; // Valor asociado a la casilla.
    CellState state; // Estado actual de la casilla.
    Color initialColor; // Color inicial de la casilla.
    boolean daltonic_mode; // Indica si la casilla está en modo daltónico.

    IFont font; // Fuente utilizada para dibujar información daltónica.
    ISound sound; // Sonido asociado a la casilla.

    /**
     * Constructor de la clase Cell.
     *
     * @param scene La escena a la que pertenece la casilla.
     * @param font  Fuente utilizada para la información daltónica.
     * @param sound Sonido asociado a la casilla.
     */
    public Cell(IScene scene, IFont font, ISound sound) {
        super(scene);
        this.font=font;
        this.state=CellState.Empty;
        this.sound=sound;
    }

    /**
     * Inicializa la casilla estableciendo el color inicial.
     */
    @Override
    public void init() {
        initialColor=strokeColor;
        super.init();
    }

    /**
     * Método de renderizado que dibuja la casilla rellenable, su contorno y la información daltónica si está activada.
     *
     * @param graphics Objeto que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void render(IGraphics graphics) {

        if(state ==CellState.Empty) {
            graphics.setColor(Color.GRAY);
        }
        else {
            graphics.setColor(strokeColor);
        }
        graphics.fillCircle(getX() + getWidth()/2,getY()+ getHeight()/2, getWidth()/2);
        drawDaltonicInfo(graphics);

        super.render(graphics);
    }

    /**
     * Maneja el evento de toque en la casilla, restableciendo su estado y reproduciendo un sonido si está configurado.
     *
     * @param event Evento de toque recibido.
     * @return true para indicar que el evento fue manejado.
     */
    @Override
    public boolean onTouchDown(TouchEvent event) {

        if(state != CellState.Resolve && state != CellState.Empty){
            state = CellState.Empty;
            this.value=-1;
            getEngine().getAudio().stopSound(sound);
            if(sound!=null)
                getEngine().getAudio().playSound(sound);
            return true;
        }
        return false;
    }

    /**
     * Rellena la casilla con un color y un valor asociado.
     *
     * @param c     Color con el que se llenará la casilla.
     * @param value Valor asociado a la casilla.
     */
    public void fillCell(Color c, int value){
        if(sound!=null) {
            getEngine().getAudio().stopSound(sound);
        }
        strokeColor=c;
        this.value=value;
        if(sound!=null) {
            getEngine().getAudio().playSound(sound);
        }
        this.state = CellState.Filled;
    }

    /**
     * Obtiene el estado actual de la casilla.
     *
     * @return El estado actual de la casilla.
     */
    public CellState getState(){
        return state;
    }

    /**
     * Obtiene el valor asociado a la casilla.
     *
     * @return El valor asociado a la casilla.
     */
    public int getValue(){
        return value;
    }

    /**
     * Establece el modo daltónico para la casilla.
     *
     * @param mode Valor del modo daltónico.
     */
    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic_mode=mode;
    }

    /**
     * Dibuja información daltónica en la casilla si el modo daltónico está activado y la casilla tiene un valor asociado.
     *
     * @param graphics Objeto que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        if(daltonic_mode && value!=-1){
            graphics.setColor(Color.BLACK);
            graphics.setFont(font);

            graphics.drawText(String.valueOf(value + 1), getX() + getWidth()/2, getY()+ 3*getHeight()/4);
        }
    }

    public void setCellState(CellState cs){
        state = cs;
    }
}
