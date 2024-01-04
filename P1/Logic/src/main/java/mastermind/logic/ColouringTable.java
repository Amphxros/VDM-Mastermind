package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;

public class ColouringTable extends GameObject implements DaltonicListener{
    boolean daltonic;

    Color[] colors;
    int numCells;
    public ColouringTable(IScene scene, int numCells, Color[] colors) {
        super(scene);
        this.numCells=numCells;
        this.colors=colors.clone();
    }

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

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),50);

        super.render(graphics);
    }

    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic=mode;
        for(GameObject d: getChildren()){
            if((DaltonicListener)d!=null)
                ((DaltonicListener) d).setDaltonicMode(mode);
        }

    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        //not need
    }
}
