package vdm.mastermind.engine.classes;

/**
 * Definition of color in various systems RGB
 */
public class Color {
    private final int r, b, g, a;

    /** @param
    *rgba An integer number encoded in RGBA
	 */
    public Color(int rgba) {
        this(rgba >> 24, rgba >> 16, rgba >> 8, rgba);
    }

    /**
     * @param rgba An integer number encoded in RGBA
     */
    public Color(long rgba) {
        this((int) rgba >> 24, (int) rgba >> 16, (int) rgba >> 8, (int) rgba);
    }

    /**
     * @param r The Red value (0-255)
     * @param g The Green value (0-255)
     * @param b The Blue value (0-255)
     */
    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(int r, int g, int b, int a) {
        this.r = r & 0xFF;
        this.g = g & 0xFF;
        this.b = b & 0xFF;
        this.a = a & 0xFF;

    }

    public static int rgbaToARGB(int color) {
        return ((color & 0xFF) << 24) | (color >> 8);
    }

    /**
     * @return Gets the Red component.
     */
    public int getRed() {
        return r;
    }

    /**
     * @return Gets the Green component.
     */
    public int getGreen() {
        return g;
    }

    /**
     * @return Gets the Blue component.
     */
    public int getBlue() {
        return b;
    }

    /**
     * @return Gets the Alpha component.
     */
    public int getAlpha() {
        return a;
    }

    /**
     * @return Gets an integer of the color encoded in RGBA.
     */
    public int getRGBA() {
        return (r << 24) | (g << 16) | (b << 8) | a;
    }

    /**
     * @return Gets an integer of the color encoded in ARGB.
     */
    public int getARGB() {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}