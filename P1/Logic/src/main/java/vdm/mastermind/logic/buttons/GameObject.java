package vdm.mastermind.logic.buttons;

import java.util.Vector;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.Vector2D;

public abstract class GameObject {
    private final Vector<GameObject> children = new Vector<>();
    protected final GameObject parent=null;
    private final IScene scene;
    protected Color strokeColor = null;
    private boolean enabled = true;
    Vector2D position;
    int width;
    int height;


    public GameObject(IScene scene){
        this.scene=scene;
        this.position= new Vector2D(0,0);
        this.setSize(0,0);
        this.enabled=true;

    }

    public void render(IGraphics graphics){
        if (strokeColor != null) {
            graphics.setColor(strokeColor);
            graphics.drawRectangle(getX(), getY(), getWidth(), getHeight());
        }

        for (GameObject child : getChildren()) {
            if (child.isEnabled()) {
                child.render(graphics);
            }
        }
    }
    public void update(double t){
        for (GameObject child : getChildren()) {
            if (child.isEnabled()) {
                child.update(t);
            }
        }
    }
    public boolean handleInput(TouchEvent event) {
        for (GameObject child : children) {
            if (child.isEnabled() && child.handleInput(event)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public GameObject getParent() {
        assert(parent!=null);
        return parent;
    }

    public Vector<GameObject> getChildren() {
        return children;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public void setY(int y) {
        position.setY(y);
    }

    public void setPosition(int x,int y){
        setX(x);
        setY(y);
    }

    public Vector2D getSize() {
        return new Vector2D(width,height);
    }
    public GameObject setSize(Vector2D size) {
        this.width=size.getX();
        this.height= size.getY();
        return this;
    }

    public GameObject setSize(int width, int height) {
        return setSize(new Vector2D(width, height));
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public GameObject setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public IScene getScene() {
        return scene;
    }

    public IEngine getEngine() {
        return scene.getEngine();
    }


}
