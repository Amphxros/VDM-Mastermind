package mastermind.logic;

import java.util.Arrays;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
/**
 * Clase HintObject que representa un objeto de pistas en un escenario (scene) de juego.
 * Extiende la clase GameObject y se utiliza para mostrar pistas relacionadas con la solución del juego.
 */
public class HintObject extends GameObject{

    private HintElem[] hintElems;
    int numCells;
    public HintObject(IScene scene,int numCells) {
        super(scene);
        this.numCells=numCells;
        this.hintElems= new HintElem[this.numCells];
    }

    @Override
    public void init() {
        for(int i=0;i<this.numCells;i++){
            hintElems[i]= new HintElem(getScene());

            if(i>=this.numCells/2){
                hintElems[i].setPosition(10+(i-this.numCells/2 )* (getWidth() / numCells), getHeight()/2 +10);
                hintElems[i].setSize((getWidth() / numCells) - 5, (getWidth() / numCells) - 10);
                hintElems[i].setColor(new Color(200, 200, 200));

            }
            else {
                hintElems[i].setPosition(15+ i * (getWidth() / numCells), 10);
                hintElems[i].setSize((getWidth() / numCells) - 5, (getWidth() / numCells) - 10);
                hintElems[i].setColor(new Color(200, 200, 200));

            }
            addChild(hintElems[i]);
        }
        super.init();
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(color);
        graphics.drawRoundRectangle(getX(),getY(),getWidth(),getHeight(),30);

        super.render(graphics);
    }

    public boolean showHints(int[] solution, int[] tableSolution){
        int correctElems = 0;
        int wrongPosition = 0;
        boolean [] visto = new boolean[solution.length];
        Arrays.fill(visto,false);
        for(int i = 0; i < solution.length; i++){
            if(tableSolution[i]==solution[i]){
                visto[i] = true;
                correctElems++;
            }else{

                boolean encontrado = false;
                int j = 0;
                while (!encontrado && j < visto.length){
                    if(visto[i]){
                        encontrado = true;
                    }else if(tableSolution[j] == solution[i] && tableSolution[j] != solution[j]){
                        visto[i] = true;
                        wrongPosition++;
                        encontrado = true;
                    }
                    j++;
                }
            }
        }

        /*if (correctElems == this.numCells) return true;

        else { //buscar los que estan en una mala posicion
            for(int i = 0; i < tableSolution.length; i++){
                boolean encontrado = false;
                int j = 0;
                while (!encontrado && j < visto.length){
                    if(visto[i]){
                        encontrado = true;
                    }else if(tableSolution[j] == solution[i]){
                        visto[i] = true;
                        wrongPosition++;
                        encontrado = true;
                    }
                    j++;
                }
            }
        }*/


        int n = 0;
        for (int i = 0; i < correctElems; i++) {
            this.hintElems[n].setCellState(CellState.Correct);
            n++;
        }

        for (int i = 0; i < wrongPosition; i++) {
            this.hintElems[n].setCellState(CellState.Misplaced);
            n++;
        }

        /*for(int i=0;i<this.numCells;i++){
            if(tableSolution[i]==solution[i]){
                this.hintElems[i].setCellState(CellState.Correct);
                correctElems++;
            }
            else if(this.hintElems[i].getCellState()==CellState.Empty){
                for(int j=0;j<this.numCells;j++){
                    if(tableSolution[i]==solution[j] && i!=j){
                        this.hintElems[i].setCellState(CellState.Misplaced);
                    }
                }
            }


        }

        for(int i=0;i<this.numCells;i++){
            if(this.hintElems[i].getCellState()==CellState.Empty){
                this.hintElems[i].setCellState(CellState.Wrong);
            }
        }*/

        return correctElems == this.numCells;
    }
}
