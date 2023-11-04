package vdm.mastermind.logic;

import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.gameobjects.GameObject;


public class TriedRow extends GameObject {

    private final int numTrie;
    private final Cell[] cells;
    private final Hint[] hintsC;

    public TriedRow(IScene scene, int currentTrie, int elems) {
        super(scene);
        this.cells= new Cell[elems];
        this.numTrie=currentTrie;
        this.hintsC= new Hint[elems];
    }
}
