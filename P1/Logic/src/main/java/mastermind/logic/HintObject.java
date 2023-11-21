package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

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
                hintElems[i].setStrokeColor(new Color(200, 200, 200));

            }
            else {
                hintElems[i].setPosition(15+ i * (getWidth() / numCells), 10);
                hintElems[i].setSize((getWidth() / numCells) - 5, (getWidth() / numCells) - 10);
                hintElems[i].setStrokeColor(new Color(200, 200, 200));

            }
            addChild(hintElems[i]);
        }
        super.init();
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.drawRoundRectangle(getX(),getY(),getWidth(),getHeight(),30);

        super.render(graphics);
    }

    public void showHints(int[] solution, int[] tableSolution){

    }
}
