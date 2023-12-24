package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.logic.Container;
import mastermind.logic.GameObject;
import mastermind.logic.Image;
import mastermind.logic.Text;
import mastermind.logic.button.GoToGameScene;
import mastermind.logic.button.GoToMenuScene;

public class ChooseLevelScene extends Scene {
    public ChooseLevelScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/KIN668.ttf",20,false);
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");


        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;

        // Title
        addGameObject(new Text(this,"¿en que dificultad quieres jugar?",font)
                .setPosition(center +30, 40)
                .setStrokeColor(getLogicData().getFont())
        );

        addGameObject(new GoToMenuScene(this)
                .setPosition(20,20)
                .setSize(50,50)
                .setStrokeColor(new Color(200,200,200,50))

                .addChild(new Image(this, back)
                                .setSize(50,50)
                )


        );

        addGameObject(new Container(this)
                .setPosition(100, 100)

                .setStrokeColor(new Color(150,150,150))

                .addChild(createGameButton(0,0,center,center/4,getLogicData().getButtons(),"facil",font,4,6,4,false))
                .addChild(createGameButton(0,center/3,center,center/4,getLogicData().getButtons(),"Medio",font,6,6,5,false))
                .addChild(createGameButton(0,2*center/3,center,center/4,getLogicData().getButtons(),"Dificil",font,6,6,6,true))
                .addChild(createGameButton(0,center,center,center/4,getLogicData().getButtons(),"Imposible",font,6,10,6,true))
        );
    }

    @Override
    public String getID() {
        return null;
    }

    private GameObject createGameButton(int x, int y, int w, int h,
                                        Color color, String text, IFont font,
                                        int numColors, int numIntentos, int tamPassword,boolean repeating ){
        return new GoToGameScene(this,numColors,numIntentos,tamPassword,repeating,true,false,false)
                .setPosition(x,y)
                .setSize(w,h)
                .setStrokeColor(color)
                .addChild(new Text(this,text,font)
                        .setPosition(w,3*h - h/3)
                        .setStrokeColor(getLogicData().getFont())

                );
    }
}
