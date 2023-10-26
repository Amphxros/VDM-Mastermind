package vdm.mastermind.logic;

import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.Cell;
public class Table extends GameObject{

    private final int tries;
    private final Cell[] cells;

    public Table(IScene scene) {
        super(scene);
    }


}
