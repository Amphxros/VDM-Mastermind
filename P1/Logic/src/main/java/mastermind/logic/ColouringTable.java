package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Clase que representa una mesa de colores que implementa la interfaz DaltonicListener.
 */
public final class ColouringTable extends GameObject implements DaltonicListener{
    boolean daltonic; // Flag que indica si se encuentra en modo daltónico.
    Color[] colors; // Arreglo de colores para las celdas de la mesa.
    int numCells; // Número de celdas en la mesa.

    /**
     * Constructor de la clase ColouringTable.
     *
     * @param scene     La escena a la que pertenece la mesa de colores.
     * @param numCells  Número de celdas en la mesa.
     * @param colors    Arreglo de colores para las celdas.
     */
    public ColouringTable(IScene scene, int numCells, Color[] colors) {
        super(scene);
        this.numCells=numCells;
        this.colors=colors.clone();
    }

    /**
     * Inicializa la mesa de colores creando las celdas y configurándolas.
     */
    @Override
    public void init() {
        int initialpos= getWidth()/numCells;
        for(int i=0;i<this.numCells;i++){
            this.addChild(new ColouringCell(getScene(),i)
                    .setSize(40,40)
                    .setStrokeColor(colors[i])
                    .setPosition(initialpos + 50*(i),20)
            );
        }
        super.init();
    }

    /**
     * Método de renderizado que dibuja la mesa de colores y sus celdas.
     *
     * @param graphics Objeto que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),50);

        super.render(graphics);
    }

    /**
     * Establece el modo daltónico y propaga el cambio a las celdas hijas.
     *
     * @param mode Valor del modo daltónico.
     */
    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic=mode;
        for(GameObject d: getChildren()){
            if((DaltonicListener)d!=null)
                ((DaltonicListener) d).setDaltonicMode(mode);
        }

    }

    /**
     * Método no implementado ya que la información daltónica no es necesaria para esta clase.
     *
     * @param graphics Objeto que proporciona capacidades gráficas para el dibujo.
     */
    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        // No es necesario implementar en esta clase.
    }
}
