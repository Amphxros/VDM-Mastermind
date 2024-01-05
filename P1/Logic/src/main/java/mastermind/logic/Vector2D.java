package mastermind.logic;

/**
 * Clase Vector2D que representa un vector bidimensional inmutable con coordenadas x e y.
 * Esta clase final garantiza que no puede ser extendida ni modificada después de la creación.
 */
public final class Vector2D {
    private final int x; //coordenada X
    private final int y; // coordenada Y

    /**
     * Constructor de la clase Vector2D.
     *
     * @param x Coordenada X del vector.
     * @param y Coordenada Y del vector.
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene la coordenada X del vector.
     *
     * @return La coordenada X.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y del vector.
     *
     * @return La coordenada Y.
     */
    public int getY() {
        return y;
    }
}
