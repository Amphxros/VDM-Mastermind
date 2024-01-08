package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;
import mastermind.logic.scene.GameScene;


/**
 * Clase que representa una casilla coloreable que implementa la interfaz DaltonicListener.
 * Rellena la casilla con un valor asociado cuando es seleccionada.
 */
public class ColouringCell extends Button implements DaltonicListener{

    int value;
    boolean daltonic_mode=false;
    IImage image;
    /**
     * Constructor de la clase ColouringCell.
     *
     * @param scene La escena a la que pertenece la casilla.
     * @param value El valor asociado a la casilla.
     */
    public ColouringCell(IScene scene, int value) {
        super(scene);
        this.value=value;
    }

    /**
     /**
     * Constructor de la clase ColouringCell.
     *
     * @param scene La escena a la que pertenece la casilla.
     * @param value El valor asociado a la casilla.
     * @param image Imagen a renderizar
     */
    public ColouringCell(IScene scene, int value, IImage image) {
        super(scene);
        this.value=value;
        this.image=image;
    }

    /**
     * Maneja el evento de toque en la casilla, restableciendo su estado y reproduciendo un sonido si está configurado.
     *
     * @param event {@link TouchEvent} a manejar.
     * @return true para indicar que el evento fue manejado.
     */
    @Override
    public boolean onTouchDown(TouchEvent event) {
        GameScene scene= (GameScene) getScene();
        if(scene!=null)
            scene.onColouringCellSelected(color,value,image);
        return true;
    }
    /**
     * Método de renderizado que dibuja la casilla rellenable, su contorno y la información daltónica si está activada.
     *
     * @param graphics instancia {@link IGraphics} del motor que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(color);
        if(this.image==null) { //circles
            graphics.fillCircle(getX() + getWidth() / 2, getY() + getHeight() / 2, getWidth() / 2);
        }
        else{ //images
            graphics.drawImage(this.image, getX(),getY(),getWidth(),getHeight());
        }
        drawDaltonicInfo(graphics);
        super.render(graphics);
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
     * @param graphics instancia {@link IGraphics} del motor que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        if(daltonic_mode){
            graphics.setColor(Color.BLACK);
            graphics.drawText(String.valueOf(value + 1),getX() + getWidth()/2, getY() + (getHeight()/2)+5);
        }
    }
}
