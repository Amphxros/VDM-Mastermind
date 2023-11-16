package vdm.mastermind.logic.gameobjects;

import java.util.Vector;

import vdm.mastermind.engine.classes.Color;
import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IEngine;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IScene;
import vdm.mastermind.logic.Vector2D;

public abstract class GameObject {
    private final Vector<GameObject> children = new Vector<>();
    private final IScene scene;
    private Vector2D position = new Vector2D(0, 0);
    private Vector2D size = new Vector2D(0, 0);
    private boolean enabled = true;
    protected Color strokeColor = new Color(0,0,0);
    private GameObject parent = null;

    public GameObject(IScene scene) {
        this.scene = scene;
    }

    public Vector2D getPosition() {
        return position;
    }

    public GameObject setPosition(Vector2D position) {
        this.position = position;
        return this;
    }

    public GameObject setPosition(int x, int y) {
        setPosition(new Vector2D(x, y));
        return this;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Vector2D getSize() {
        return size;
    }

    public GameObject setSize(Vector2D size) {
        this.size = size;
        return this;
    }

    public GameObject setSize(int width, int height) {
        return setSize(new Vector2D(width, height));
    }

    public int getWidth() {
        return getSize().getX();
    }

    public int getHeight() {
        return getSize().getY();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public GameObject getParent() {
        return parent;
    }

    public Vector<GameObject> getChildren() {
        return children;
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


    /**
     * Adds a child to the GameObject.
     *
     * @param gameObject The object to add as a child.
     * @return The updated {@link GameObject} instance.
     * @throws RuntimeException When the given object already has a parent.
     */
    public GameObject addChild(GameObject gameObject) {
        if (gameObject.parent != null) {
            throw new RuntimeException("The given GameObject already has a parent");
        }

        gameObject.parent = this;
        children.add(gameObject);
        return this;
    }

    /**
     * An event method that's called once on initialization.
     */
    public void init() {
        for (GameObject child : getChildren()) {
            child.init();
        }
    }

    public void render(IGraphics graphics) {
        for (GameObject child : getChildren()) {
            if (child.isEnabled()) {
                child.render(graphics);
            }
        }
    }

    /**
     * An event method that's called on each frame.
     *
     * @param delta The number of seconds since the last frame.
     */
    public void update(double delta) {
        for (GameObject child : getChildren()) {
            if (child.isEnabled()) {
                child.update(delta);
            }
        }
    }

    /**
     * An event method that's called when the device receives an event.
     *
     * @param event The received event from the device.
     * @return Whether or not the input has been processed.
     */
    public boolean handleInput(TouchEvent event) {
        for (GameObject child : getChildren()) {
            if (child.isEnabled() && child.handleInput(event)) {
                return true;
            }
        }

        return false;
    }

}