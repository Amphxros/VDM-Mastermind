package mastermind.logic;

import java.util.Vector;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;

public class GameObject {
    private final Vector<GameObject> children = new Vector<>();
    private final IScene scene;
    private Vector2D position = new Vector2D(0, 0);
    private Vector2D size = new Vector2D(0, 0);
    private boolean enabled = true;
    protected Color color = Color.BLACK;
    private GameObject parent = null;

    public GameObject(IScene scene) {
        this.scene = scene;
    }

    /**
     * Mueve el objeto completo, incluido a sus hijos
     * Recomendale cuando quieres aplicar fisicas o simplemente scrollear
     * @param position
     * @return
     */
    public GameObject move(Vector2D position){
        for(GameObject g: getChildren()){
            g.setPosition(g.getX() + position.getX(), g.getY() + position.getY());
            g.move(position);
        }
        return this;
    }

    /**
     *
     * @return posicion
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Pone la posicion del objeto pero no afecta a los hijos
     * Recomendable para cuando quieres crear un objeto en el inicio
     * @param position
     * @return
     */
    public GameObject setPosition(Vector2D position) {
        this.position = position;
        return this;
    }

    /**
     * Pone la posicion del objeto pero no afecta a los hijos
     * Recomendable para cuando quieres crear un objeto en el inicio
     * @param x
     * @param y
     * @return
     */
    public GameObject setPosition(int x, int y) {
        setPosition(new Vector2D(x, y));

        return this;
    }


    /**
     *
     * @return posicion en horizontal
     */
    public int getX() {
        return position.getX();
    }

    /**
     *
     * @return posicion en vertical
     */
    public int getY() {
        return position.getY();
    }

    /**
     * @return Dimensiones del objeto (ancho, alto)
     */
    public Vector2D getSize() {
        return size;
    }

    /**
     * Cambia las dimensiones del objeto
     * @param size Vector (ancho, alto)
     * @return a si mismo
     */
    public GameObject setSize(Vector2D size) {
        this.size = size;
        return this;
    }

    /**
     * Cambia las dimensiones del objeto
     * @param width ancho
     * @param height alto
     * @return a si mismo
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
     * @return alto del objeto
     */
    public int getHeight() {
        return getSize().getY();
    }

    /**
     *
     * @return si esta activo el objeto
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
     * @return el padre del objeto
     */
    public GameObject getParent() {
        return parent;
    }

    /**
     *
     * @return el vector con los hijos de este objeto
     */
    public Vector<GameObject> getChildren() {
        return children;
    }

    /**
     * Cambia el color del que se dibuja el objeto
     * @param color color del objeto
     * @return
     */
    public GameObject setColor(Color color) {
        this.color = color;
        return this;
    }

    /**
     *
     * @return La escena en la que se ha creado el objeto
     */
    public IScene getScene() {
        return scene;
    }

    /**
     *
     * @return el motor en el que se esta corriendo el juego
     */
    public IEngine getEngine() {
        return scene.getEngine();
    }


    /**
     * Se incorpora un hijo a este objeto y
     * a ese hijo se le relocaliza respecto al padre
     *
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
     * Inicializacion de objetos
     */
    public void init() {
        for (GameObject child : getChildren()) {
            child.init();
        }
    }


    /**
     * Renderizado de objetos
     * @param graphics
     */
    public void render(IGraphics graphics) {
        if(!this.enabled) return;
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
