package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;
/**
 * Clase que representa una casilla rellenable en el juego, implementa la interfaz DaltonicListener.
 */
public class Cell extends Button implements DaltonicListener{
    int value=-1;
    CellState state;
    Color initialColor;
    boolean daltonic_mode;

    IFont font;

    IImage image;

    IImage initialImage;
    /**
     * Constructor de la clase Cell, en este caso se dibujara un circulo.
     *
     * @param scene La escena a la que pertenece la casilla.
     * @param font  Fuente utilizada para la información daltónica.
     */
    public Cell(IScene scene, IFont font) {
        super(scene);
        this.font=font;
        this.state=CellState.Empty;

    }
    /**
     * Constructor de la clase Cell.
     *
     * @param scene La escena a la que pertenece la casilla.
     * @param font  Fuente utilizada para la información daltónica.
     * @param image image por defecto de la casilla
     */
    public Cell(IScene scene, IFont font, IImage image) {
        super(scene);
        this.font=font;
        this.state=CellState.Empty;
        this.image=image;

    }
    /**
     * Inicializa la casilla estableciendo el color inicial.
     */
    @Override
    public void init() {
        initialColor= color;
        initialImage= image;
        super.init();
    }

    /**
     * Método de renderizado que dibuja la casilla rellenable, su contorno y la información daltónica si está activada.
     *
     * @param graphics instancia {@link IGraphics} del motor que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void render(IGraphics graphics) {
        if(image==null) {
            if (state == CellState.Empty) {
                graphics.setColor(initialColor);
            } else {
                graphics.setColor(color);
            }
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else{
            graphics.drawImage(image,getX(),getY(),getWidth(),getHeight());
        }

        drawDaltonicInfo(graphics);

        super.render(graphics);
    }

    /**
     * Maneja el evento de toque en la casilla, restableciendo su estado y reproduciendo un sonido si está configurado.
     *
     * @param event {@link TouchEvent} a manejar.
     * @return true para indicar que el evento fue manejado.
     */
    @Override
    public boolean onTouchDown(TouchEvent event) {
        state= CellState.Empty;
        image=initialImage;
        value=-1;
        return true;
    }

    /**
     * Rellena la casilla con un color y un valor asociado.
     *
     * @param c     Color con el que se llenará la casilla.
     * @param value Valor asociado a la casilla.
     * @param image Image a cambiar
     */
    public void fillCell(Color c, int value, IImage image){
        color =c;
        this.value=value;
        this.image=image;
        state=CellState.Filled;
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
        if(daltonic_mode && this.value!=-1){
            graphics.setColor(Color.BLACK);
            graphics.setFont(font);

            graphics.drawText(String.valueOf(value+1), getX() + getWidth()/2, getY() + (getHeight()/2)+5);
        }
    }
}
