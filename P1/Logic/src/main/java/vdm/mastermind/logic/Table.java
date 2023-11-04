package vdm.mastermind.logic;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.gameobjects.GameObject;
import vdm.mastermind.logic.TriedRow;

public class Table extends GameObject {

    private final TriedRow[] rows;
    private final int currentTrie;
    private final int numMaxTries;

    public Table(IScene scene, int tries, int combinations, int elems) {
        super(scene);
        this.currentTrie = 0;
        numMaxTries = tries;
        rows = new TriedRow[tries];
    }

    @Override
    public boolean handleInput(TouchEvent event) {
        return false;
    }
}
