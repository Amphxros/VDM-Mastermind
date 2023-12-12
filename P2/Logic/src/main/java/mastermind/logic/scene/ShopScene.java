package mastermind.logic.scene;

import java.util.ArrayList;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.logic.AnimalID;
import mastermind.logic.Container;
import mastermind.logic.Image;
import mastermind.logic.Scene;
import mastermind.logic.Text;
import mastermind.logic.button.GoToMenuScene;
import mastermind.logic.button.SetAnimalButton;
import mastermind.logic.button.ShopButtons;

public class ShopScene extends Scene {
    ArrayList<Container> containers;
    int id=0;
    public ShopScene(IEngine engine) {
        super(engine);
        this.containers= new ArrayList<>(3);
        for(int i=0;i<3;i++){
            Container c= new Container(this);
            addGameObject(c);
            this.containers.add(c);
        }
    }

    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",10,false);
        IFont tittle = getEngine().getGraphics().newFont("fonts/handwriting.ttf",30,false);
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");
        IImage coin= getEngine().getGraphics().newImage("images/coin.png");

        addGameObject(new ShopButtons(this,-1)
                .setPosition(40,70)
                .setSize(50,50)
                .setStrokeColor(new Color(230,230,230))


        );
            addGameObject(new ShopButtons(this,1)
                .setPosition(300,70)
                .setSize(50,50)
                .setStrokeColor(new Color(230,230,230))


        );

        addGameObject(new GoToMenuScene(this)
                .setPosition(10,10)
                .setSize(50,50)
                .setStrokeColor(new Color(200,200,200))

                .addChild(new Image(this, back)
                        .setPosition(5,5)
                        .setSize(40,40))
        );

        /**
         *animals
         */
        containers.get(0).addChild(new Text(this,"Skins",tittle)
                .setPosition(200,100)

        );
        for(int i = 0; i<AnimalID.Num_Animals.ordinal(); i++){
            IImage image= getEngine().getGraphics().newImage("images/buttons/buttons-"+i+".png");

            containers.get(0).addChild(
                    (new SetAnimalButton(this,AnimalID.values()[i])
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
                )
            );
        }

        containers.get(1).addChild(new Text(this,"Backgrounds",tittle)
                .setPosition(200,100)


        );
        for(int i=0;i<4;i++){
            containers.get(1).addChild(
              new Container(this)
                      .setPosition(30, 250 + 100*i)
                      .setSize(300,70)
                      .setStrokeColor(new Color(200,200,200))
                      .addChild(new Text(this,"Fondo " + i,font)
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

        onClick(0);
        super.init();
    }

    public void onClick(int id){
        this.id=(this.id + id)%3;
        if (this.id<0) this.id += 3;
        for(Container c: containers)
            c.hide();

        containers.get(this.id).show();
    }
}
