package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Container;
import mastermind.logic.GameObject;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.GoToGameScene;

public class ChooseLevelScene extends Scene {
    public ChooseLevelScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",20,false);

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        // Title
        addGameObject(new Text(this,"¿en que dificultad quieres jugar?",font)
                .setPosition(center, 20));

        addGameObject(new Container(this)
                .setPosition(100, 100)
                .setSize(center, center)
                .setStrokeColor(new Color(150,150,150,0))

                .addChild(createGameButton(0,0,center,center/4,new Color(200,200,120),"facil",font,4,6,4,false))
                .addChild(createGameButton(0,center/3,center,center/4,new Color(249,231,132),"Medio",font,6,6,5,false))
                .addChild(createGameButton(0,2*center/3,center,center/4,new Color(229,145,101),"Dificil",font,6,6,6,true))
                .addChild(createGameButton(0,center,center,center/4,new Color(208,83,83),"Imposible",font,6,10,6,true))
        );
    }

    private GameObject createGameButton(int x, int y, int w, int h,
                                        Color color, String text, IFont font,
                                        int numColors, int numIntentos, int tamPassword,boolean repeating ){
        return new GoToGameScene(this,numColors,numIntentos,tamPassword,repeating)
                .setPosition(x,y)
                .setSize(w,h)
                .setStrokeColor(color)
                .addChild(new Text(this,text,font)
                        .setPosition(w,3*h - h/3)
                        .setStrokeColor(Color.BLACK)

                );
    }
}
