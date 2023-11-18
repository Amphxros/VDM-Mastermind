package mastermind.logic;

import mastermind.engine.IScene;

public class Table extends GameObject{
    int numElems;
    public Table(IScene scene,int numElems) {
        super(scene);
        this.numElems=numElems;
    }

    @Override
    public void init() {
        super.init();
    }
}
