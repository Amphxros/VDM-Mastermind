package mastermind.logic;

import java.util.ArrayList;
import java.util.HashMap;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.ILogicData;

public class PlayerData implements ILogicData {
    int coins;

    Color background;
    Color font;
    Color tittle;
    Color buttons;

    ArrayList<AnimalID> unlockedAnimals;
    AnimalID currentAnimalID;

    IEngine engine;


    public PlayerData(IEngine engine){
        this.engine=engine;
        this.background= Color.WHITE;
        this.buttons=new Color(100,100,100);
        this.font=Color.BLACK;
        this.tittle= new Color(30,30,50);
        this.coins=999; //TODO change this THIS IS ONLY FOR DEBUG THE SHOP
        this.currentAnimalID=AnimalID.None;
        this.unlockedAnimals= new ArrayList<>();


    }

    @Override
    public void loadData(String route) {
        //TODO need file manager :(
        //TODO load checksum
        //TODO compare with checksum
    }

    @Override
    public void saveData() {
        //TODO need file manager :(
        //TODO need NDK, checksum and that shit

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

    public void setCurrentAnimalID(AnimalID currentAnimalID) {
        this.currentAnimalID = currentAnimalID;
    }
}
