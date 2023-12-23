package mastermind.logic;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.IJsonObject;
import mastermind.engine.ILogicData;

public class PlayerData implements ILogicData {
    int coins;
    Color background;
    Color font;
    Color tittle;
    Color buttons;

    String currentScene;
    boolean[] unlockedAnimals;
    AnimalID currentAnimalID;

    IEngine engine;


    public PlayerData(IEngine engine){
        this.engine=engine;
        loadData("Json/save.json");
    }

    @Override
    public void loadData(String route) {
        IJsonObject jsonObject= this.engine.getFileManager().readJSON(route);
        if(jsonObject!=null){
            IJsonObject checksum= this.engine.getFileManager().readJSON("checksum.json");
            this.coins= jsonObject.getIntKey("coins");
            int animal= jsonObject.getIntKey("currentAnimal");
            this.currentAnimalID= AnimalID.values()[animal];
            this.unlockedAnimals= new boolean[AnimalID.Num_Animals.ordinal()];

            for(int i=0;i<AnimalID.Num_Animals.ordinal();i++){
                this.unlockedAnimals[i]=jsonObject.getBooleanKey("animal "+i);
            }

            }
        else{
            this.background= Color.WHITE;
            this.buttons=new Color(100,100,100);
            this.font=Color.BLACK;
            this.tittle= new Color(30,30,50);
            this.currentAnimalID=AnimalID.None;
            this.unlockedAnimals= new boolean[AnimalID.Num_Animals.ordinal()];
            for(int i=0;i<AnimalID.Num_Animals.ordinal();i++)
                this.unlockedAnimals[i]=i==0; //unlock only the default

            this.coins=9999;

            }
    }


    @Override
    public void saveData() throws IOException {
        JSONObject jsonObject= new JSONObject();
        String s="";
        try {
            jsonObject.clear();
            s+="{";
            jsonObject.put("currentAnimal", currentAnimalID.ordinal());
            s+= jsonObject.toString();
            s+="},\n";

            for(int i=0;i<AnimalID.Num_Animals.ordinal();i++){
                s+="{";
                jsonObject.clear();
                jsonObject.put("animal "+i, this.unlockedAnimals[i]);
                s+="},\n";
            }
            jsonObject.clear();
            s+="{";
            jsonObject.put("coins", String.valueOf(this.coins));
            s+= jsonObject.toString();
            s+="}\n";

            s+="}\n";

        }
        catch (Exception e){
            e.printStackTrace();
        }
        String content= s;
        System.out.println(content);
        OutputStream save= engine.getFileManager().openOutputFile("Json/save.json");
        save.flush();
        save.write(content.getBytes());
        save.close();
        //TODO need NDK, checksum and that shit
        OutputStream checksum= engine.getFileManager().openOutputFile("Json/checksum.json");
        JSONObject check=new JSONObject();
        String encriptedContent="";
        check.put("checksum",encriptedContent );
        checksum.close();
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

    public boolean isAnimalUnlocked(int id){
        return this.unlockedAnimals[id];
    }

    public void unlockedAnimal(int id){
        this.unlockedAnimals[id]=true;
    }
}
