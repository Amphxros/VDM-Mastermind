package mastermind.logic;

/**
 * Enumeración que representa los posibles estados de una celda en un contexto específico.
 */
public enum CellState {
    Empty, // Estado que indica que la celda está vacía.
    Filled, // Estado que indica que la celda está llenada.
    Correct, // Estado que indica que la celda está correctamente ubicada.
    Misplaced, // Estado que indica que la celda está ubicada, pero en una posición incorrecta.
    Wrong, // Estado que indica que la celda está equivocada o incorrecta.
    Resolve // Estado que indica que ya se ha dado una solucion a esta celda y asi no poder borrarla.
}
