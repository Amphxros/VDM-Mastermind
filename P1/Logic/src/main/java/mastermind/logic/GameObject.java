package mastermind.logic;

import java.util.Vector;

import mastermind.engine.Color;
import mastermind.engine.IAudio;
import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;

/**
 * Elemento basico del juego
 */
public class GameObject {
    private final Vector<GameObject> children = new Vector<>(); //hijos del GO
    private final IScene scene; // escena donde existe
    private Vector2D position = new Vector2D(0, 0); //posicion
    private Vector2D size = new Vector2D(0, 0); // dimensiones
    private boolean enabled = true; // si esta habilitado
    protected Color strokeColor = Color.BLACK; // color del GO
    private GameObject parent = null; // padre del objeto

    public GameObject(IScene scene) {
        this.scene = scene;
    }

    /**
     *
     * @return posicion del GO
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Cambia la posicion del GO solo, no de sus hijos
     *
     * @param position
     * @return
     */
    public GameObject setPosition(Vector2D position) {
        this.position = position;
        return this;
    }

    /**
     * Cambia la posicion del GO solo, no de sus hijos
     * @param x
     * @param y
     * @return
     */
    public GameObject setPosition(int x, int y) {
        setPosition(new Vector2D(x, y));
        return this;
    }

    /**
     * Mueve el GO completo, sus hijos incluidos
     * @param moveX
     * @param moveY
     */
    public void move(int moveX, int moveY){
        setPosition(getX() + moveX, getY() +moveY);
        for(GameObject g: children)
            g.setPosition(g.getX() + moveX, g.getY() + moveY);
    }

    /**
     *
     * @return la posicion X
     */
    public int getX() {
        return position.getX();
    }

    /**
     *
     * @return la posicion en Y
     */
    public int getY() {
        return position.getY();
    }

    /**
     *
     * @return Las dimensiones del objeto
     */
    public Vector2D getSize() {
        return size;
    }

    /**
     * Cambia las dimensiones del objeto
     * @param size {@link Vector2D} (ancho,alto)
     * @return
     */
    public GameObject setSize(Vector2D size) {
        this.size = size;
        return this;
    }

    /**
     *  Cambia las dimensiones del objeto
     * @param width
     * @param height
     * @return
     */
    public GameObject setSize(int width, int height) {
        return setSize(new Vector2D(width, height));
    }

    /**
     *
     * @return ancho del objeto
     */
    public int getWidth() {
        return getSize().getX();
    }

    /**
     *
     * @return alto del objeto
     */
    public int getHeight() {
        return getSize().getY();
    }

    /**
     *
     * @return Si esta activo o no
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Activa o desactiva el objeto
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @return padre del objeto
     */
    public GameObject getParent() {
        return parent;
    }

    /**
     *
     * @return Vector con los hijos del objeto
     */
    public Vector<GameObject> getChildren() {
        return children;
    }

    /**
     * Cambia el color del objeto
     * @param strokeColor
     * @return
     */
    public GameObject setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    /**
     *
     * @return La escena donde se le crea
     */
    public IScene getScene() {
        return scene;
    }

    /**
     *
     * @return El motor donde se corre el juego, util para acceder otros modulos como al sistema de audio
     */
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

        gameObject.parent = this;
        gameObject.setPosition(getX() + gameObject.getX(), getY() + gameObject.getY());
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
