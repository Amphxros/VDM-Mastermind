package mastermind.logic.scene;

import java.util.ArrayList;

import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.engine.IJsonObject;
import mastermind.logic.Container;
import mastermind.logic.GameObject;
import mastermind.logic.Image;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.WorldObject;
import mastermind.logic.button.ContainerButtons;
import mastermind.logic.button.GoToGameScene;
import mastermind.logic.button.GoToMenuScene;

public class ExploreWorldsScene extends Scene {

    private WorldObject mWorldsObject [];

    public ExploreWorldsScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);
        IJsonObject levels= getEngine().getFileManager().readJSON("Json/levels/levels.json");
        ArrayList<Container> containers= new ArrayList<>();

        addGameObject(new ContainerButtons(this,-1)
                .setPosition(40,70)
                .setSize(50,50)
                .setStrokeColor(getLogicData().getButtons())


        );
        addGameObject(new ContainerButtons(this,1)
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

        for(int i=0;i<levels.getIntKey("numWorlds");i++){
           String s= "world" + ""+ String.valueOf(i+1);
           String route= levels.getStringKey(s);
           System.out.println(s + "  " + route);

            IJsonObject object= getEngine().getFileManager().readJSON(route);
            System.out.println(object==null);


            containers.add((Container) createContainer(route,font));
            addGameObject(containers.get(0));
        }


    }

    private GameObject createContainer(String string,IFont font){
        Container container= new Container(this);
        container.setPosition(30,150);
        container.setSize(350,400);

        return container;



    }
}
