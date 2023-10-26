package vdm.mastermind.logic;

import java.util.ArrayList;

import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IInput;
import vdm.mastermind.engine.interfaces.IScene;

public abstract class GameObject {
    protected final ArrayList<GameObject> children= new ArrayList<>();
    protected final GameObject parent=null;
    private final IScene scene;
    public GameObject(IScene scene){
        this.scene=scene;
    }

    public abstract void render(IGraphics graphics);
    public abstract void update(double t);
    public abstract boolean handleInput(IInput input);

}
