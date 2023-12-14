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


    public PlayerData(){
        this.background= Color.WHITE;
        this.buttons=new Color(100,100,100);
        this.font=Color.BLACK;
        this.tittle= new Color(30,30,50);
        this.coins=999;
        this.currentAnimalID=AnimalID.None;
        this.unlockedAnimals= new ArrayList<>();


    }

    @Override
    public void loadData(String route) {

    }

    @Override
    public void saveData() {

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
