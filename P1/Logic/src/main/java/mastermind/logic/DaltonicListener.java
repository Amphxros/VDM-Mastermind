package mastermind.logic;

import mastermind.engine.IGraphics;

/**
 * Interfaz que define un escuchador para el modo daltónico.
 */
public interface DaltonicListener {

    /**
     * Establece el modo daltónico.
     *
     * @param mode Valor del modo daltónico.
     */
    void setDaltonicMode(boolean mode);

    /**
     * Dibuja información adicional para ser accesible en el contexto daltónico.
     *
     * @param graphics Objeto que proporciona capacidades gráficas para el dibujo.
     */
    void drawDaltonicInfo(IGraphics graphics);
}
