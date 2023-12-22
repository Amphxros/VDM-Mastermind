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
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.ShopButtons;
import mastermind.logic.button.GoToGameScene;
import mastermind.logic.button.GoToMenuScene;
import mastermind.logic.button.WorldButtons;

public class ExploreWorldsScene extends Scene {

    int numWorlds;
    int id=0;
    ArrayList<Container> containers= new ArrayList<>();
    public ExploreWorldsScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);
        IJsonObject levels= getEngine().getFileManager().readJSON("Json/levels/levels.json");

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
        this.numWorlds=levels.getIntKey("numWorlds");
        for(int i=0;i<numWorlds;i++){
           String s= "world" + ""+ String.valueOf(i+1);
           String route= levels.getStringKey(s);
           System.out.println(s + "  " + route);

            IJsonObject object= getEngine().getFileManager().readJSON(route);
            containers.add((Container) createContainer(object,font));
            addGameObject(containers.get(i));
        }

        onClick(id);


    }


    private GameObject createContainer(IJsonObject object,IFont font){
        int rows= object.getIntKey("numRows");
        int cols= object.getIntKey("numCols");
        int numLevels= object.getIntKey("numLevels");
        String name= object.getStringKey("name");
        Container container= new Container(this);
        container.setPosition(30,150);
        container.setSize(350,400);
        container.setStrokeColor(Color.RED);
        container.addChild(new Text(this,name,font)
                .setPosition(150,-50)
        );



        assert (numLevels==rows*cols);

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){

                int value= (i*cols+j) + 1;


                GoToGameScene game= new GoToGameScene(this,1,1,1,false,false);
                        game.setPosition(50 +(100*j), 100*i);
                        game.setSize(80, 80);
                        game.setStrokeColor(getLogicData().getButtons());
                        game.addChild(new Text(this, String.valueOf(value),font)
                                .setPosition(70,200)
                                .setStrokeColor(getLogicData().getFont())
                        );

                container.addChild(game);
            }
        }

        return container;



    }

    public void onClick(int id){
        this.id=(this.id + id)%numWorlds;
        if (this.id<0) this.id += numWorlds;
        for(Container c: containers)
            c.hide();

        containers.get(this.id).show();
    }

}
