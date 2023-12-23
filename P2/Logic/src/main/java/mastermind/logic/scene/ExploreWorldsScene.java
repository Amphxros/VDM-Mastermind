package mastermind.logic.scene;

import java.util.ArrayList;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.engine.IJsonObject;
import mastermind.logic.Container;
import mastermind.logic.GameObject;
import mastermind.logic.Image;
import mastermind.logic.PlayerData;
import mastermind.logic.Scene;
import mastermind.logic.SkinID;
import mastermind.logic.Text;
import mastermind.logic.button.GoToGameScene;
import mastermind.logic.button.GoToMenuScene;
import mastermind.logic.button.WorldButtons;

public class ExploreWorldsScene extends Scene {

    int numWorlds;
    int numCols;
    int id=0;
    ArrayList<Container> containers= new ArrayList<>();
    String rootLevels;
    String[] nameWorlds;
    ArrayList<String[]> listLevels;
    PlayerData playerData;
    public ExploreWorldsScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        playerData= getLogicData();
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);
        IJsonObject levels= getEngine().getFileManager().readJSON("Json/levels/levels.json");

        this.numCols = 3;

        this.rootLevels = "levels/";
        this.nameWorlds = getEngine().getFileManager().getFileListDirectory(rootLevels);
        this.numWorlds = nameWorlds.length;

        this.listLevels = new ArrayList<>();

        //recogemos todos los json en una matriz de Strings para su posterior lectura.
        for(int i = 0; i < numWorlds; i++){
            String[] auxString = getEngine().getFileManager().getFileListDirectory(rootLevels + nameWorlds[i]);
            this.listLevels.add(auxString);
        }

        addGameObject(new WorldButtons(this,-1)
                .setPosition(40,70)
                .setSize(50,50)
                .setStrokeColor(getLogicData().getButtons())


        );
        addGameObject(new WorldButtons(this,1)
                .setPosition(300,70)
                .setSize(50,50)
                .setStrokeColor(getLogicData().getButtons())


        );

        addGameObject(new GoToMenuScene(this)
                .setPosition(10,10)
                .setSize(50,50)
                .setStrokeColor(getLogicData().getButtons())

                .addChild(new Image(this, back)
                        .setPosition(5,5)
                        .setSize(40,40))
        );

        for(int i=0;i<this.numWorlds;i++){
           String s= "world" + ""+ String.valueOf(i+1);
           String route= nameWorlds[i] + "/";
           System.out.println(s + "  " + route);

           String[] auxWorld = this.listLevels.get(i);
           containers.add((Container) createContainer(i, nameWorlds[i],auxWorld,font));
           addGameObject(containers.get(i));

        }

        onClick(id);


    }


    private GameObject createContainer(int numWorld, String name, String[] arrayStringLevels,IFont font){
        Container container= new Container(this);
        String pathAux = "images/worlds/world" + (numWorld + 1) + ".png";
        IImage background= getEngine().getGraphics().newImage("images/worlds/world-" + (numWorld + 1) + ".png");


        container.setPosition(30,150);
        container.setSize(350,400);
        container.setStrokeColor(Color.RED);
        container.addChild(new Text(this,name,font)
                .setPosition(150,-50));
        container.addChild(new Image(this,background)
                .setPosition(0,0)
                .setSize(350,400));

        int fila = 0;
        int columna = 0;

        for(int i = 0; i < arrayStringLevels.length; ++i){
            IJsonObject worldFile= getEngine().getFileManager().readJSON(rootLevels + name + "/" + arrayStringLevels[i]);
            int colors= worldFile.getIntKey("numColors");
            int numAttempts= worldFile.getIntKey("numAttempts");
            boolean rep= worldFile.getBooleanKey("repeat");
            int codeSize= worldFile.getIntKey("codeSize");

            GoToGameScene game= new GoToGameScene(this,colors,numAttempts,codeSize,rep,true,true);
            game.setPosition(50 +(100*columna), 100*fila);
            game.setSize(80, 80);
            game.setStrokeColor(getLogicData().getButtons());
            game.addChild(new Text(this, String.valueOf(i),font)
                    .setPosition(70,200)
                    .setStrokeColor(getLogicData().getFont())
            );
            container.addChild(game);
            columna = columna + 1;
            if(columna >= numCols)
            {
                columna = 0;
                fila = fila + 1;
            }
        }

        return container;
    }

    public void onClick(int id){
        this.id=(this.id + id)%numWorlds;
        if (this.id<0) this.id += numWorlds;
        for(Container c: containers)
            c.hide();

        playerData.setCurrentSkin(SkinID.values()[this.id]);
        containers.get(this.id).show();
    }

}
