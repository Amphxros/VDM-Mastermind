package mastermind.logic;

import mastermind.engine.Color;
import mastermind.engine.IGraphics;
import mastermind.engine.IImage;
import mastermind.engine.IJsonObject;
import mastermind.engine.IScene;

public class ColouringTable extends GameObject implements DaltonicListener{
    boolean daltonic;

    Color[] colors;
    IImage[]images;
    int numCells;
    public ColouringTable(IScene scene, int numCells, Color[] colors) {
        super(scene);
        this.numCells=numCells;

        IJsonObject jsonObject= getEngine().getFileManager().readJSON("Json/cells.json");
        PlayerData playerData= (PlayerData) scene.getLogicData();
        if(playerData.getCurrentAnimalID()==AnimalID.None){
            this.colors=colors.clone();
        }
        else {
            this.colors=null;
            this.images=new IImage[9];
            AnimalID animalID= playerData.getCurrentAnimalID();
            SkinID skinID= playerData.getCurrentSkin();
            for (int i = 0; i < 9; i++) {

                String s= jsonObject.getStringKey(animalID.name()) +jsonObject.getStringKey(skinID.name())+(i+1)+".png";
                System.out.println(s);
                images[i] = getEngine().getGraphics().newImage(s);
            }
        }
    }


    @Override
    public void init() {
        if(colors!=null){
            for(int i=0;i<this.numCells;i++){
                this.addChild(new ColouringCell(getScene(),i)
                    .setSize(30,30)
                    .setColor(colors[i])
                    .setPosition(20 + 40*(i),20)
                );
            }
        }
        else{
            for(int i=0;i<this.numCells;i++){
                this.addChild(new ColouringCell(getScene(),i,images[i])
                        .setSize(30,30)
                        .setPosition(20 + 40*(i),20)
                );
            }
        }
        super.init();
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(color);
        graphics.fillRoundRectangle(getX(),getY(),getWidth(),getHeight(),50);

        super.render(graphics);
    }

    @Override
    public void setDaltonicMode(boolean mode) {
        daltonic=mode;
        for(GameObject d: getChildren()){
            if((DaltonicListener)d!=null)
                ((DaltonicListener) d).setDaltonicMode(mode);
        }

    }

    @Override
    public void drawDaltonicInfo(IGraphics graphics) {

    }
}
