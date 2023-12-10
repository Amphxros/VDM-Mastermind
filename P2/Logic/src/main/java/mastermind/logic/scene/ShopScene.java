package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.logic.AnimalID;
import mastermind.logic.Image;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.GoToMenuScene;
import mastermind.logic.button.SetAnimalButton;

public class ShopScene extends Scene {
    public ShopScene(IEngine engine) {
        super(engine);
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",10,false);
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");
        IImage coin= getEngine().getGraphics().newImage("images/coin.png");
        addGameObject(new GoToMenuScene(this)
                .setPosition(10,10)
                .setSize(50,50)
                .setStrokeColor(new Color(200,200,200))

                .addChild(new Image(this, back)
                        .setPosition(5,5)
                        .setSize(40,40))
        );


        for(int i = 0; i<AnimalID.Num_Animals.ordinal(); i++){
            IImage image= getEngine().getGraphics().newImage("images/buttons/buttons-"+i+".png");

            addGameObject(new SetAnimalButton(this,AnimalID.values()[i])
                    .setSize(300,60)
                    .setPosition(50,  140 +70*(i))
                    .setStrokeColor(Color.BLACK)
                    .addChild(new Image(this,image)
                            .setPosition(20,10)
                            .setSize(40,40)
                    )
                    .addChild(new Text(this,"Animalito " + i,font)
                            .setPosition(100,30)
                    )
                    .addChild(new Image(this,coin)
                            .setPosition(200,10)
                            .setSize(30,30)
                    )
                    .addChild(new Text(this, String.valueOf(100*i),font)
                            .setPosition(250,30)
                    )
            );
        }

        super.init();
    }
}
