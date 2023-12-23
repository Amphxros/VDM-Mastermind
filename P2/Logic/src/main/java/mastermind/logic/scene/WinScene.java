package mastermind.logic.scene;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IFont;
import mastermind.engine.IImage;
import mastermind.engine.IJsonObject;
import mastermind.logic.AnimalID;
import mastermind.logic.PlayerData;
import mastermind.logic.Scene;
import mastermind.logic.SkinID;
import mastermind.logic.Table;
import mastermind.logic.Text;
import mastermind.logic.button.GoToChooseLevel;

public class WinScene extends Scene {
    Color[] colors;
    int[] solution;
    boolean hasWon;
    int coinsAmount;
    boolean fileLevel;
    public WinScene(IEngine engine, Color[] colors, int[] solution, boolean hasWon, int coinsAmount, boolean fileLevel) {
        super(engine);
        this.colors=colors.clone();
        this.solution=solution.clone();
        this.hasWon=hasWon;
        this.coinsAmount=coinsAmount;
        this.fileLevel=fileLevel;
    }


    @Override
    public void init() {
        IFont font= getEngine().getGraphics().newFont("fonts/KIN668.ttf",30,false);
        IJsonObject jsonObject= getEngine().getFileManager().readJSON("Json/cells.json");
        PlayerData playerData= (PlayerData) getLogicData();
        Table t= new Table(this,this.solution.length,font,false);
        t.setPosition(50,200);
        t.setSize(50 *this.solution.length,50);
        t.setStrokeColor(new Color(200,200,200));

        t.init();
        if(playerData.getCurrentAnimalID()== AnimalID.None) {
            for (int i = 0; i < this.solution.length; i++) {
                t.fillCell(colors[solution[i]], solution[i], null);
            }
        }
        else{
            AnimalID animalID= playerData.getCurrentAnimalID();
            SkinID skinID= playerData.getCurrentSkin();
            for (int i = 0; i < this.solution.length; i++) {
                String s= jsonObject.getStringKey(animalID.name()) +jsonObject.getStringKey(skinID.name())+(solution[i] +1)+".png";
                IImage image= getEngine().getGraphics().newImage(s);
                t.fillCell(colors[solution[i]], solution[i], image);
            }
        }
        Text text= new Text(this,"Game over",font);
        text.setPosition(200,110);
        text.setStrokeColor(new Color(20,20,20));
        if(hasWon){
            text.setText("You Won");
            if(fileLevel) {
                getLogicData().setCoins(getLogicData().getCoins() + this.coinsAmount);
                getLogicData().onLevelCompleted();
            }
        }

        addGameObject(text);

        text= new Text(this,"Solution:",font);
        text.setPosition(200,150);
        text.setStrokeColor(new Color(120,120,120));
        addGameObject(text);



        addGameObject(new GoToChooseLevel(this)
            .setPosition(30,450)
            .setStrokeColor(colors[0])
            .setSize(350,50)
                .addChild(new Text(this,"Go to other levels",font)
                        .setPosition(170,40)
                        .setStrokeColor(Color.BLACK)
                )

        );
        super.init();
        addGameObject(t);
    }
}
