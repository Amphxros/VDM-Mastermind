package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IFont;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IJsonObject;
import mastermind.engine.IScene;
import mastermind.engine.TouchEvent;
import mastermind.logic.button.Button;
import mastermind.logic.scene.GameScene;

public class Table extends Button implements DaltonicListener, ScrollEventListener{
    int numElems;
    boolean scrolling;
    int[] solution;
    Cell [] cells;
    IFont font;
    HintObject hintObject;
    boolean showHints;
    public Table(IScene scene, int numElems, IFont font, boolean showHints) {
        super(scene);
        this.numElems=numElems;
        this.solution= new int[this.numElems];
        this.font=font;
        this.cells= new Cell[this.numElems];
        this.showHints=showHints;
        this.scrolling = false;
    }

    @Override
    public void init() {
        PlayerData p= (PlayerData) getEngine().getLogic().getLogicData();
        IJsonObject jsonObject= getEngine().getFileManager().readJSON("Json/cells.json");
        if(p.getCurrentAnimalID()==AnimalID.None){
            for(int i=0;i<this.numElems;i++){
                this.cells[i]=(new Cell(getScene(),font));
                this.cells[i].setSize(30,30)
                    .setStrokeColor(new Color(150,150,150))

                    .setPosition(20 + 40*(i),10);
                this.addChild(cells[i]);
            }
        }
        else {
            AnimalID animalID= p.getCurrentAnimalID();
            for(int i=0;i<this.numElems;i++){
                IImage image= getEngine().getGraphics().newImage(jsonObject.getStringKey(animalID.name()) +"default"+".png" );
                this.cells[i]=(new Cell(getScene(),font,image));
                this.cells[i].setSize(30,30)
                        .setStrokeColor(new Color(150,150,150))

                        .setPosition(20 + 40*(i),10);
                this.addChild(cells[i]);
            }
        }
        if(showHints) {
            hintObject = new HintObject(getScene(), this.numElems);
            hintObject.setPosition(getWidth() - 90, 2);
            hintObject.setSize(70, 40);
            hintObject.setStrokeColor(new Color(50, 50, 50));

            this.addChild(hintObject);
        }
        super.init();
    }
    /**
     *
     * @return true if the table is completed
     */
    public boolean isComplete(){
       int i=0;
       while(i< cells.length && cells[i].getState()!=CellState.Empty) {
           i++;
       }

       for(int j=0;j<this.cells.length;j++){
            this.solution[j]=cells[j].getValue();
       }
       return i==cells.length;
    }

    public void fillCell(Color c, int value,IImage image){
        int i=0;
        while(i< cells.length && cells[i].getState()!=CellState.Empty) {
            i++;
        }
        cells[i].fillCell(c,value,image);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(strokeColor);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(), getHeight(),30);
        super.render(graphics);
    }


    @Override
    public void setDaltonicMode(boolean mode) {
        for(Cell c: cells)
            c.setDaltonicMode(mode);

    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {
        for(Cell c: cells)
            c.drawDaltonicInfo(graphics);
    }

    @Override
    public void onScroll(int deltaY){
        // Ajustar la posición Y del objeto en función del desplazamiento
        int posicionY = getY();

        posicionY += deltaY;

        // Puedes agregar lógica adicional aquí, como limitar la posición Y dentro de ciertos límites.

        // Por ejemplo, para evitar que el objeto se salga de la pantalla hacia arriba:
        if (posicionY < 0) {
            posicionY = 0;
        }

        this.setPosition(getX(), posicionY);
    }

    public boolean correctHints(int[] solution){
        return hintObject.showHints(solution, this.solution);
    }


    @Override
    public boolean onScroll(TouchEvent event){
        scrolling=true;
        GameScene scene= (GameScene) getScene();
        if(scene!=null){
            scene.onScroll(1);

        }
        return scrolling;
    }
    @Override
    public boolean onTouchUp(TouchEvent event){
        scrolling = false;
        return true;
    }
}
