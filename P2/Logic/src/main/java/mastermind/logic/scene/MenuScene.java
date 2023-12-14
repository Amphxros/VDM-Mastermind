package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.HorizontalAlignment;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.GoToChooseLevel;
import mastermind.logic.button.GoToShopScene;
import mastermind.logic.button.ShowRewardedAdButton;

public class MenuScene extends Scene {
    public MenuScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",40,false);
        IFont fonty = getEngine().getGraphics().newFont("fonts/KIN668.ttf",25,false);

        int maxWidth = getEngine().getGraphics().getWidth();
        int center = maxWidth / 2;

        int buttonW = (int) (maxWidth * 0.8);
        int buttonX = center - buttonW / 2;
        // Title
        addGameObject(new Text(this, "Mastermind", font)
                .setPosition(center, 150)
                .setStrokeColor(getLogicData().getTittle())

        );
        Text t= new Text(this, "Partida Rapida",fonty);
        t.setPosition(center-50,25);
        t.setAlignment(HorizontalAlignment.CENTRE);
        t.setStrokeColor(getLogicData().getFont());


        addGameObject(new GoToChooseLevel(this)
                .setPosition(center/3,250)
                .setSize(300,50)
                .setStrokeColor(getLogicData().getButtons())

                .addChild(t)
        );

        addGameObject(new GoToShopScene(this)
                .setPosition(center/3,500)
                .setSize(300,50)
                .setStrokeColor(getLogicData().getButtons())

                .addChild(new Text(this,"Personalizar",fonty)
                        .setPosition(center-50,25)
                        .setStrokeColor(getLogicData().getFont())
                )


        );

    }
}
