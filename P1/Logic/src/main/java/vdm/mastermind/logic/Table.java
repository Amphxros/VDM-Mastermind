package vdm.mastermind.logic;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.buttons.GameObject;

public class Table extends GameObject {

    private final int tries;
    private final Cell[] cells;

    public Table(IScene scene, int tries, int combinations, int elems) {
        super(scene);
        this.cells= new Cell[elems];
        this.tries=tries;

    }

    @Override
    public boolean handleInput(TouchEvent event) {
        return false;
    }
}
