package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;
import mastermind.engine.ISound;

/**
 * Clase Table que representa una tabla de juego en un nivel (scene).
 * Extiende la clase GameObject e implementa la interfaz DaltonicListener.
 */
public class Table extends GameObject implements DaltonicListener{
    int numElems; // Número de elementos en la tabla.
    int[] solution; // Solución de la tabla.
    Cell [] cells; // Arreglo de celdas que forman la tabla.
    IFont font; // Fuente utilizada en las celdas.
    HintObject hintObject; // Objeto de pista para mostrar pistas en caso necesario.
    boolean showHints; // Booleano que indica si se deben mostrar las pistas.
    ISound sound; // Objeto de sonido asociado a la tabla.
    int marginCell; //Margen de las cells en cada table. Se configura en la constructora. 
    boolean scrollUp; boolean scrollDown;

    /**
     * Constructor de la clase Table.
     *
     * @param scene      El escenario al que pertenece la tabla.
     * @param numElems   Número de elementos en la tabla.
     * @param font       La fuente utilizada en las celdas de la tabla.
     * @param showHints  Indica si se deben mostrar las pistas.
     * @param sound      Objeto de sonido asociado a la tabla.
     */
    public Table(IScene scene, int numElems, IFont font, boolean showHints, ISound sound) {
        super(scene);
        this.numElems=numElems;
        this.solution= new int[this.numElems];
        this.font=font;
        this.cells= new Cell[this.numElems];
        this.showHints=showHints;
        this.sound=sound;
        this.marginCell = 10;
    }

    /**
     * Inicializa la tabla, creando y posicionando las celdas.
     */
    @Override
    public void init() {
        int initialPos=getWidth()/numElems;
        for(int i=0;i<this.numElems;i++){
            this.cells[i]=(new Cell(getScene(),font,sound));
            this.cells[i].setSize(2*getHeight()/3,2*getHeight()/3)
                    .setStrokeColor(new Color(150,150,150))
                    //.setPosition(initialPos/3 + (3*getHeight()/4)*(i),10);
                    .setPosition(((getWidth()-90 - marginCell*2)/numElems)*i + marginCell,10);
            this.addChild(cells[i]);
        }
        if(showHints) {
            hintObject = new HintObject(getScene(), this.numElems);
            hintObject.setPosition(getWidth()-90, 5);
            hintObject.setSize(90, getHeight() -10);
            hintObject.setStrokeColor(new Color(50, 50, 50));

            this.addChild(hintObject);
        }

        scrollUp = true; scrollDown = true;

        super.init();
    }

    /**
     * Verifica si la tabla está completa.
     *
     * @return true si la tabla está completa.
     */
    public boolean isComplete(){
       int i=0;
       while(i< cells.length && cells[i].getState()!=CellState.Empty) {
           i++;
       }

       for(int j=0;j<this.cells.length;j++){
            this.solution[j]=cells[j].getValue();
       }
       return i==cells.length;
    }

    /**
     * Rellena una celda con un color y un valor dados.
     *
     * @param c     Color con el que se rellenará la celda.
     * @param value Valor que se asignará a la celda.
     */
    public void fillCell(Color c, int value){
        int i=0;
        while(i< cells.length && cells[i].getState()!=CellState.Empty) {
            i++;
        }
        cells[i].fillCell(c,value);
    }

    /**
     * Método de renderizado que dibuja el contorno redondeado de la tabla.
     *
     * @param graphics El contexto gráfico utilizado para el renderizado.
     */
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(),getY(),getWidth(), getHeight(),30);
        super.render(graphics);
    }

    /**
     * Establece el modo daltónico de la tabla y aplica el mismo modo a todas las celdas.
     *
     * @param mode true para activar el modo daltónico, false para desactivarlo.
     */
    @Override
    public void setDaltonicMode(boolean mode) {
        for(Cell c: cells)
            c.setDaltonicMode(mode);
    }

    /**
     * Dibuja la información daltónica de cada celda en la tabla.
     *
     * @param graphics El contexto gráfico utilizado para el renderizado.
     */
    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        for(Cell c: cells)
            c.drawDaltonicInfo(graphics);
    }

    /**
     * Verifica si las pistas proporcionadas coinciden con la solución de la tabla.
     *
     * @param solution Arreglo que contiene las pistas proporcionadas.
     * @return true si las pistas son correctas, false en caso contrario.
     */
    public boolean correctHints(int[] solution){
        for(int i = 0; i < cells.length; ++i){
            cells[i].setCellState(CellState.Resolve);
        }
        return hintObject.showHints(solution, this.solution);
    }

}
