package mastermind.logic;

import java.util.Vector;

import mastermind.engine.Color;
import mastermind.engine.IAudio;
import mastermind.engine.IEngine;
import mastermind.engine.IGraphics;
import mastermind.logic.scene.IScene;
import mastermind.engine.TouchEvent;

/**
 * Representa un objeto en el juego con capacidades de posicionamiento, dimensiones y jerarquía.
 */
public class GameObject {
    private final Vector<GameObject> children = new Vector<>(); // Lista de hijos del GameObject.
    private final IScene scene; // Escena a la que pertenece el GameObject.
    private Vector2D position = new Vector2D(0, 0); // Posición del objeto en el espacio 2D.
    private Vector2D size = new Vector2D(0, 0); // Dimensiones del objeto.
    private boolean enabled = true; // Indica si el objeto está habilitado.

    protected Color strokeColor = Color.BLACK; // Color del trazo del objeto.
    private GameObject parent = null; // Referencia al padre del objeto.

    /**
     * Constructor que asigna la escena al objeto.
     *
     * @param scene La escena a la que pertenece el objeto.
     */
    public GameObject(IScene scene) {
        this.scene = scene;
    }

    /**
     * Obtiene la posición del GameObject.
     *
     * @return La posición del GameObject.
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Cambia la posición del GameObject sin afectar a sus hijos.
     *
     * @param position Nueva posición del GameObject.
     * @return El objeto actualizado.
     */
    public GameObject setPosition(Vector2D position) {
        this.position = position;
        return this;
    }

    /**
     * Cambia la posición del GameObject sin afectar a sus hijos.
     *
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     * @return El objeto actualizado.
     */
    public GameObject setPosition(int x, int y) {
        setPosition(new Vector2D(x, y));

        return this;
    }



    /**
     * Mueve el GameObject y todos sus hijos en el espacio.
     *
     * @param moveX Desplazamiento en el eje X.
     * @param moveY Desplazamiento en el eje Y.
     */
    public void move(int moveX, int moveY){
        setPosition(this.getX() + moveX, this.getY() + moveY);
        for(GameObject g: children)
            g.move(moveX, moveY);

    }

    /**
     * Obtiene la coordenada X de la posición del GameObject.
     *
     * @return Coordenada X del GameObject.
     */
    public int getX() {
        return position.getX();
    }

    /**
     * Obtiene la coordenada Y de la posición del GameObject.
     *
     * @return Coordenada Y del GameObject.
     */
    public int getY() {
        return position.getY();
    }

    /**
     * Obtiene las dimensiones del objeto.
     *
     * @return Las dimensiones del objeto en forma de un objeto Vector2D.
     */
    public Vector2D getSize() {
        return size;
    }

    /**
     * Cambia las dimensiones del objeto.
     *
     * @param size {@link Vector2D} (ancho, alto)
     * @return El objeto actualizado.
     */
    public GameObject setSize(Vector2D size) {
        this.size = size;
        return this;
    }

    /**
     * Cambia las dimensiones del objeto.
     *
     * @param width Ancho del objeto.
     * @param height Alto del objeto.
     * @return El objeto actualizado.
     */
    public GameObject setSize(int width, int height) {
        return setSize(new Vector2D(width, height));
    }

    /**
     * Obtiene el ancho del objeto.
     *
     * @return Ancho del objeto.
     */
    public int getWidth() {
        return getSize().getX();
    }

    /**
     * Obtiene el alto del objeto.
     *
     * @return Alto del objeto.
     */
    public int getHeight() {
        return getSize().getY();
    }

    /**
     * Verifica si el objeto está activo o no.
     *
     * @return true si el objeto está activo, false de lo contrario.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Activa o desactiva el objeto.
     *
     * @param enabled true para activar, false para desactivar.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Obtiene el padre del objeto.
     *
     * @return El objeto padre.
     */
    public GameObject getParent() {
        return parent;
    }

    /**
     * Obtiene un vector con los hijos del objeto.
     *
     * @return Vector con los hijos del objeto.
     */
    public Vector<GameObject> getChildren() {
        return children;
    }

    /**
     * Cambia el color del objeto.
     *
     * @param strokeColor Nuevo color del trazo.
     * @return El objeto actualizado.
     */
    public GameObject setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    /**
     * Obtiene la escena donde se creó el objeto.
     *
     * @return La escena asociada al objeto.
     */
    public IScene getScene() {
        return scene;
    }

    /**
     * Obtiene el motor donde se ejecuta el juego, útil para acceder a otros módulos como el sistema de audio.
     *
     * @return El motor del juego.
     */
    public IEngine getEngine() {
        return scene.getEngine();
    }


    /**
     * Agrega un hijo al GameObject.
     *
     * @param gameObject Objeto a agregar como hijo.
     * @return El objeto GameObject actualizado.
     * @throws RuntimeException Cuando el objeto dado ya tiene un padre.
     */
    public GameObject addChild(GameObject gameObject) {

        gameObject.parent = this;
        gameObject.setPosition(getX() + gameObject.getX(), getY() + gameObject.getY());
        children.add(gameObject);
        return this;
    }

    /**
     * Método de evento llamado una vez durante la inicialización.
     */
    public void init() {
        for (GameObject child : getChildren()) {
            child.init();
        }
    }

    /**
     * Método de evento llamado en cada cuadro.
     *
     * @param delta El número de segundos desde el último cuadro.
     */
    public void render(IGraphics graphics) {
        for (GameObject child : getChildren()) {
            if (child.isEnabled()) {
                child.render(graphics);
            }
        }
    }

    /**
     * Método de evento llamado en cada cuadro.
     *
     * @param delta El número de segundos desde el último cuadro.
     */
    public void update(double delta) {
        for (GameObject child : getChildren()) {
            if (child.isEnabled()) {
                child.update(delta);
            }
        }
    }

    /**
     * Método de evento llamado cuando el dispositivo recibe un evento.
     *
     * @param event El evento recibido desde el dispositivo.
     * @return Si la entrada ha sido procesada o no.
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
