package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IJsonObject;
import mastermind.engine.IScene;
import mastermind.logic.scene.Scene;

/** Clase Table que representa una tabla de juego en un nivel (scene).
 * Extiende la clase GameObject e implementa la interfaz DaltonicListener.
 */
public class Table extends GameObject implements DaltonicListener, ScrollEventListener {
    int numElems; // Número de elementos en la tabla.
    int[] solution; // Solución de la tabla.
    Cell [] cells; // Arreglo de celdas que forman la tabla.
    IFont font; // Fuente utilizada en las celdas.
    HintObject hintObject; // Objeto de pista para mostrar pistas en caso necesario.
    boolean showHints; // Booleano que indica si se deben mostrar las pistas

    public Table(IScene scene, int numElems, IFont font, boolean showHints) {
        super(scene);
        this.numElems = numElems;
        this.solution = new int[this.numElems];
        this.font = font;
        this.cells = new Cell[this.numElems];
        this.showHints = showHints;

        Scene scene_= (Scene) getScene();
        scene_.addScrollListener(this);
    }

    @Override
    public void init() {
        PlayerData p = (PlayerData) getEngine().getLogic().getLogicData();
        IJsonObject jsonObject = getEngine().getFileManager().readJSON("Json/cells.json");
        if (p.getCurrentAnimalID() == AnimalID.None) {
            for (int i = 0; i < this.numElems; i++) {
                this.cells[i] = (new Cell(getScene(), font));
                this.cells[i].setSize(30, 30)
                        .setColor(new Color(150, 150, 150))

                        .setPosition(20 + 40 * (i), 10);
                this.addChild(cells[i]);
            }
        } else {
            AnimalID animalID = p.getCurrentAnimalID();
            for (int i = 0; i < this.numElems; i++) {
                IImage image = getEngine().getGraphics().newImage(jsonObject.getStringKey(animalID.name()) + "default" + ".png");
                this.cells[i] = (new Cell(getScene(), font, image));
                this.cells[i].setSize(30, 30)
                        .setColor(new Color(150, 150, 150))

                        .setPosition(20 + 40 * (i), 10);
                this.addChild(cells[i]);
            }
        }
        if (showHints) {
            hintObject = new HintObject(getScene(), this.numElems);
            hintObject.setPosition(getWidth() - 90, 2);
            hintObject.setSize(70, 40);
            hintObject.setColor(new Color(50, 50, 50));

            this.addChild(hintObject);
        }
        super.init();
    }

    /**
     * @return true if the table is completed
     */
    public boolean isComplete() {
        int i = 0;
        while (i < cells.length && cells[i].getState() != CellState.Empty) {
            i++;
        }

        for (int j = 0; j < this.cells.length; j++) {
            this.solution[j] = cells[j].getValue();
        }
        return i == cells.length;
    }

    public void fillCell(Color c, int value, IImage image) {
        int i = 0;
        while (i < cells.length && cells[i].getState() != CellState.Empty) {
            i++;
        }
        cells[i].fillCell(c, value, image);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(color);
        graphics.fillRoundRectangle(getX(), getY(), getWidth(), getHeight(), 30);
        super.render(graphics);
    }


    @Override
    public void setDaltonicMode(boolean mode) {
        for (Cell c : cells)
            c.setDaltonicMode(mode);

    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        for (Cell c : cells)
            c.drawDaltonicInfo(graphics);
    }


    public boolean correctHints(int[] solution) {
        return hintObject.showHints(solution, this.solution);
    }

    public boolean getDone() {
        return showHints;
    }

    @Override
    public void onScroll(int deltaY) {
        if(showHints){
            setPosition(getX(), getY() + deltaY);
            move(new Vector2D(0, deltaY));
        }
    }
}