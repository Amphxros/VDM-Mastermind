package mastermind.logic.scene;

import java.util.ArrayList;

import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.engine.IJsonObject;
import mastermind.engine.ISound;
import mastermind.logic.AnimalID;
import mastermind.logic.Container;
import mastermind.logic.Image;
import mastermind.logic.PaletteID;
import mastermind.logic.Text;
import mastermind.logic.button.GoTo.GoToMenuScene;
import mastermind.logic.button.PaletteButton;
import mastermind.logic.button.SetAnimalButton;
import mastermind.logic.button.ShopButtons;

public class ShopScene extends Scene {
    ArrayList<Container> containers;

    Text t;

    int id=0;
    //ISound sonidoDinero;
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
    public String getID() {
        return "Shop";
    }
    @Override
    public void init() {
        IFont font = getEngine().getGraphics().newFont("fonts/handwriting.ttf",10,false);
        IFont tittle = getEngine().getGraphics().newFont("fonts/handwriting.ttf",30,false);
        IImage back= getEngine().getGraphics().newImage("images/back_button.png");
        IImage coin= getEngine().getGraphics().newImage("images/coin.png");
        ISound sound= getEngine().getAudio().createSound("sounds/01");

        IJsonObject prices= getEngine().getFileManager().readJSON("Json/shop-prices.json");
        IJsonObject images= getEngine().getFileManager().readJSON("Json/shop-images.json");

        ISound moneySound = getEngine().getAudio().createSound("sonido/dinero.mp3");

        t= new Text(this,String.valueOf(getLogicData().getCoins()),tittle);
       t.setPosition(-10,50);
        addGameObject(new Container(this)
                .setPosition(300,0)
                .setColor(getLogicData().getButtons())

                .addChild(t)
                .addChild(new Image(this,coin)
                        .setPosition(50,10)
                        .setSize(50,50)
                )



        );

        addGameObject(new ShopButtons(this,-1)
                .setPosition(40,70)
                .setSize(50,50)
                .setColor(getLogicData().getButtons())


        );
        addGameObject(new ShopButtons(this,1)
                .setPosition(300,70)
                .setSize(50,50)
                .setColor(getLogicData().getButtons())


        );

        addGameObject(new GoToMenuScene(this)
                .setPosition(10,10)
                .setSize(50,50)
                .setColor(getLogicData().getButtons())

                .addChild(new Image(this, back)
                        .setPosition(5,5)
                        .setSize(40,40))
        );

        /**
         *animals
         */
        containers.get(0).addChild(new Text(this,"Skins",tittle)
                .setPosition(200,100)
                .setColor(getLogicData().getTittle())

        );
        for(int i = 0; i<AnimalID.Num_Animals.ordinal(); i++){
            IImage image= getEngine().getGraphics().newImage("images/buttons/buttons-"+i+".png");

            containers.get(0).addChild(
                    (new SetAnimalButton(this,AnimalID.values()[i],prices.getIntKey("Skin "+ i), !getLogicData().isAnimalUnlocked(i), moneySound)
                    .setSize(300,60)
                    .setPosition(50,  140 +70*(i))
                    .setColor(getLogicData().getButtons())
                    .addChild(new Image(this,image)
                            .setPosition(20,10)
                            .setSize(40,40)
                    )
                    .addChild(new Text(this,AnimalID.values()[i].name()
                            ,font)
                            .setPosition(100,30)
                            .setColor(getLogicData().getFont())
                    )
                    .addChild(new Image(this,coin)
                            .setPosition(200,10)
                            .setSize(30,30)
                    )
                    .addChild(new Text(this, String.valueOf(prices.getIntKey("Skin "+ i)),font)
                            .setPosition(250,30)
                            .setColor(getLogicData().getFont())
                    )
                )
            );
        }



        containers.get(1).addChild(new Text(this,"Palettes",tittle)
                .setPosition(200,100)
                .setColor(getLogicData().getTittle())


        );
        for(int i=0;i<6;i++){
            IImage image= getEngine().getGraphics().newImage("images/palettes/palettes-"+i+".png");

            containers.get(1).addChild(
                    new PaletteButton(this,PaletteID.values()[i] ,moneySound,prices.getIntKey("Palette "+ i), !getLogicData().isPaletteUnlock(i))
                            .setSize(300,60)
                            .setPosition(50,  140 +70*(i))
                            .setColor(getLogicData().getButtons())

                            .addChild(new Image(this,image)
                                    .setPosition(20,10)
                                    .setSize(40,40)
                            )
                            .addChild(new Text(this,PaletteID.values()[i].name(),font)
                                    .setPosition(130,30)
                                    .setColor(getLogicData().getFont())
                            )
                            .addChild(new Image(this,coin)
                                    .setPosition(200,10)
                                    .setSize(30,30)
                            )
                            .addChild(new Text(this, String.valueOf(prices.getIntKey("Palette "+ i)),font)
                                    .setPosition(250,30)
                                    .setColor(getLogicData().getFont())
                            )

            );
        }

        onClick(0);
        super.init();
    }

    public void onClick(int id){
        this.id=(this.id + id)%2;
        if (this.id<0) this.id += 2;
        for(Container c: containers)
            c.hide();

        containers.get(this.id).show();
    }

    @Override
    public void update(double delta) {
        t.setText(String.valueOf(getLogicData().getCoins()));
        super.update(delta);
    }


}
