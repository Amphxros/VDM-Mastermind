package vdm.mastermind.logic;

import java.util.ArrayList;

import vdm.mastermind.engine.interfaces.IScene;

public abstract class GameObject {
    protected final ArrayList<GameObject> children= new ArrayList<>();
    protected final GameObject parent=null;
    private final IScene scene;
    public GameObject(IScene scene){
        this.scene=scene;
    }
}
