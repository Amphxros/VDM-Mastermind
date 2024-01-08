package mastermind.logic;

public class Vector2D {
    private final int x;
    private final int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return La coordenada en el eje X.
     */
    public int getX() {
        return x;
    }

    /**
     * @return La coordenada en el eje Y.
     */
    public int getY() {
        return y;
    }

}
