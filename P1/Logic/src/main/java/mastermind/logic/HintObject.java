package mastermind.logic;

import java.util.Arrays;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;

/**
 * Clase HintObject que representa un objeto de pistas en un escenario (scene) de juego.
 * Extiende la clase GameObject y se utiliza para mostrar pistas relacionadas con la solución del juego.
 */
public final class HintObject extends GameObject{

    private HintElem[] hintElems; // Arreglo de elementos de pista que componen el objeto HintObject.
    int numCells; // Número de celdas en la pista.

    int margin;

    /**
     * Constructor de la clase HintObject.
     *
     * @param scene     El escenario al que pertenece el objeto de pistas.
     * @param numCells  Número de celdas en la pista.
     */
    public HintObject(IScene scene,int numCells) {
        super(scene);
        this.numCells=numCells;
        this.hintElems= new HintElem[this.numCells];

        margin = 6;
    }

    /**
     * Inicializa el objeto HintObject creando y posicionando los elementos de pista.
     */
    @Override
    public void init() {
        boolean even= this.numCells%2==0; //TRUE = PAR; FALSE = IMPAR
        int mitadCell = this.numCells/2;
        int w = getWidth(),
            h = getHeight();
        int cellSizeW = (w - (2 * margin )) / mitadCell,
            cellSizeH = (h - (2* margin )) / 2,
            cellSize=-1;

        if(cellSizeW < cellSizeH) cellSize = cellSizeW;
        else cellSize = cellSizeH;

        //La colocacion de las pistas la diferenciamos entre PAR e IMPAR
        int posX= 0, posY = 0;

        if(even){// CASO PAR
            for(int i = 0; i < numCells; i++){

                if(i < mitadCell){
                    posY = margin/2;
                    posX = cellSizeW * i + margin/2 + cellSizeW/2;
                }else{
                    posY = h - cellSize - margin/2;
                    posX = cellSizeW * (i - mitadCell) + margin/2 + cellSizeW/2;
                }

                this.hintElems[i] =  new HintElem(getScene());
                this.hintElems[i].setPosition(posX, posY);
                this.hintElems[i].setSize(cellSize, cellSize);
                hintElems[i].setStrokeColor(Color.GRAY);
                addChild(hintElems[i]);
            }
        }else{ //CASO IMPAR
            for(int i = 0; i < numCells; i++){
                if(i <= mitadCell ){
                    posY = margin/2;
                    posX = ((w - 2*margin)/(mitadCell+1)) * i + cellSize/2 + margin;

                }else{
                    posY = h - cellSize - margin/2;
                    //EN ESTE CASO LOS MARGENES NO IMPORTAN
                    posX = (w/mitadCell)/2 * (i - mitadCell) + cellSize/2;
                }

                this.hintElems[i] =  new HintElem(getScene());
                this.hintElems[i].setPosition(posX, posY);
                this.hintElems[i].setSize(cellSize, cellSize);
                hintElems[i].setStrokeColor(Color.GRAY);
                addChild(hintElems[i]);
            }
        }

        super.init();
    }

    /**
     * Método de renderizado que dibuja el contorno redondeado del objeto HintObject.
     *
     * @param graphics El contexto gráfico utilizado para el renderizado.
     */
    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(),getY(),getWidth(),getHeight(),20);
        super.render(graphics);
    }

    /**
     * Muestra las pistas en función de la solución proporcionada y la solución de la tabla.
     *
     * @param solution       Arreglo que contiene la solución proporcionada.
     * @param tableSolution  Arreglo que contiene la solución de la tabla.
     * @return true si todas las pistas son correctas, false en caso contrario.
     */
    public boolean showHints(int[] solution, int[] tableSolution){
        int correctElems = 0;
        int wrongPosition = 0;
        boolean [] visto = new boolean[solution.length];
        Arrays.fill(visto,false);

        // Evalúa cada elemento en la solución y su posición en la tabla.
        for(int i = 0; i < solution.length; i++){
            if(tableSolution[i]==solution[i]){
                visto[i] = true;
                correctElems++;
            }
            else{
                boolean encontrado = false;
                int j = 0;

                // Busca un elemento en la tabla que coincida con la solución pero en una posición incorrecta.
                while (!encontrado && j < visto.length){
                    if(visto[i]){
                        encontrado = true;
                    }
                    else if(tableSolution[j] == solution[i] && tableSolution[j] != solution[j]){
                        visto[i] = true;
                        wrongPosition++;
                        encontrado = true;
                    }
                    j++;
                }
            }
        }

        int n = 0;
        // Establece el estado de los elementos de pista según las pistas proporcionadas.

        //Elementos correctos
        for (int i = 0; i < correctElems; i++) {
            this.hintElems[n].setCellState(CellState.Correct);
            n++;
        }
        //Elementos que aparececen pero colocado en un lugar incorrecto
        for (int i = 0; i < wrongPosition; i++) {
            this.hintElems[n].setCellState(CellState.Misplaced);
            n++;
        }
        //Elementos que estan mal y no aparecen
        while (n < hintElems.length){
            this.hintElems[n].setCellState(CellState.Wrong);
            n++;
        }

        // Devuelve true si todas las pistas son correctas.
        return correctElems == this.numCells;
    }
}
