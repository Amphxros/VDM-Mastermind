package mastermind.logic;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IJsonObject;
import mastermind.engine.ILogicData;
import mastermind.engine.Input;

public final class PlayerData implements ILogicData, Serializable {
    int coins;
    transient Color background;
    transient Color font;
    transient Color tittle;
    transient Color buttons;

    boolean[] unlockedAnimals;
    AnimalID currentAnimalID;
    SkinID currentSkin;

    IEngine engine;
    int lastLevel;
    int lastWorld;

    public PlayerData(IEngine engine){
        this.engine=engine;

        this.background= Color.WHITE;
        this.buttons=new Color(100,100,100);
        this.font=Color.BLACK;
        this.tittle= new Color(30,30,50);
        this.currentAnimalID=AnimalID.None;
        this.currentSkin=SkinID.basic;
        this.unlockedAnimals= new boolean[AnimalID.Num_Animals.ordinal()];
        for(int i=0;i<AnimalID.Num_Animals.ordinal();i++)
            this.unlockedAnimals[i]=i==0; //unlock only the default

        this.coins=9999;
        this.lastLevel=1;
        this.lastWorld=1;


    }
    public static PlayerData load(IEngine engine){
        PlayerData playerData;
        InputStream stream = null;
        try {
             stream = engine.getFileManager().openInputFile("save");
        }
        catch (Exception e){
            return new PlayerData(engine);

        }
        playerData=null;

        try {
            ObjectInputStream objectInputStream= new ObjectInputStream(stream); 
            playerData= (PlayerData) objectInputStream.readObject();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return playerData;
    }

    @Override
    public void loadData(String route) {

    }

    @Override
    public void saveData() throws IOException {

    }

    public int getCoins(){
        return this.coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }



    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }


    public Color getButtons() {
        return buttons;
    }

    public void setButtons(Color buttons) {
        this.buttons = buttons;
    }

    public Color getFont() {
        return font;
    }

    public void setFont(Color font) {
        this.font = font;
    }

    public Color getTittle() {
        return tittle;
    }

    public void setTittle(Color tittle) {
        this.tittle = tittle;
    }
    public AnimalID getCurrentAnimalID(){
        return this.currentAnimalID;
    }
    public void setCurrentAnimalID(AnimalID currentAnimalID) {
         this.currentAnimalID = currentAnimalID;
    }

    public boolean isAnimalUnlocked(int id){
        return this.unlockedAnimals[id];
    }

    public void unlockedAnimal(int id){
        this.unlockedAnimals[id]=true;
    }

    public void onLevelCompleted(){
        lastLevel++;
        int numLevels= engine.getFileManager().getFileListDirectory("levels/world" + lastWorld).length;
        if(lastLevel>numLevels){
            lastLevel=1;
            lastWorld++;
        }

        //saveData()
    }

    public SkinID getCurrentSkin() {
        return currentSkin;
    }

    public void setCurrentSkin(SkinID currentSkin) {
        this.currentSkin = currentSkin;
    }
}
