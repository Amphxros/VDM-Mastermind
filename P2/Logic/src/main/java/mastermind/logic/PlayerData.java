package mastermind.logic;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.ILogicData;

public final class PlayerData implements ILogicData, Serializable {
    int coins;

    boolean[] unlockedAnimals;
    boolean[] paletteItemsUnlocked;
    AnimalID currentAnimalID;
    SkinID currentSkin;
    PaletteID currentPalette;

    IEngine engine;
    int lastLevel;
    int lastWorld;

    PaletteItem[] paletteItems;

    public PlayerData(IEngine engine){
        this.engine=engine;
        this.currentAnimalID=AnimalID.None;
        this.currentSkin=SkinID.basic;
        this.currentPalette= PaletteID.Default;
        this.unlockedAnimals= new boolean[AnimalID.Num_Animals.ordinal()];
        for(int i=0;i<AnimalID.Num_Animals.ordinal();i++)
            this.unlockedAnimals[i]=i==0; //unlock only the default

        this.paletteItemsUnlocked= new boolean[PaletteID.NumPalettes.ordinal()];
        for(int i=0;i<this.paletteItemsUnlocked.length;i++){
            this.paletteItemsUnlocked[i]=i==0;
        }

        loadPalettes();
        this.coins=9999;
        this.lastLevel=1;
        this.lastWorld=1;


    }
    public static PlayerData load(IEngine engine){
        PlayerData playerData;
        InputStream stream = null;
        try {
             stream = engine.getFileManager().openInputFile("save");
             System.out.println(stream!=null);
        }
        catch (Exception e){
            return new PlayerData(engine);

        }
        playerData=null;

        try {
            ObjectInputStream objectInputStream= new ObjectInputStream(stream);

            playerData= (PlayerData) objectInputStream.readObject();
            playerData.loadPalettes();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return playerData;
    }

    @Override
    public void loadData(String route) {

    }


    public void loadPalettes(){
        paletteItems= new PaletteItem[PaletteID.NumPalettes.ordinal()];
        paletteItems[0]= new PaletteItem(Color.WHITE, new Color(200,200,200), Color.BLACK,Color.BLACK);
        paletteItems[1]= new PaletteItem(new Color(99,155,255), new Color(172,50,50), new Color(253,202,60),Color.BLACK);
        paletteItems[2]= new PaletteItem(Color.WHITE, new Color(95,205,255), Color.BLACK,new Color(217,87,99));
        paletteItems[3]= new PaletteItem(new Color(84,154,171), new Color(241,128,45), Color.WHITE,new Color(18,55,64));
        paletteItems[4]= new PaletteItem(new Color(245,202,195),new Color(246,189,90),new Color(132,165,157),new Color(208,148,137));
        paletteItems[5]= new PaletteItem(new Color(231,109,88), new Color(38,162,174), new Color(174,58,67),new Color(32,125,140));
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
        return paletteItems[currentPalette.ordinal()].colors[0];
    }


    public Color getButtons() {
        return paletteItems[currentPalette.ordinal()].colors[1];
    }

    public Color getFont() {
        return paletteItems[currentPalette.ordinal()].colors[2];
    }

    public Color getTittle() {
        return paletteItems[currentPalette.ordinal()].colors[3];
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

    public int getLastLevel() {
        return lastLevel;
    }

    public int getLastWorld() {
        return lastWorld;
    }

    public PaletteID getCurrentPalette() {
        return currentPalette;
    }

    public void setCurrentPalette(PaletteID currentPalette) {
        this.currentPalette = currentPalette;
    }

    public void unlockPalette(int id){
        paletteItemsUnlocked[id]=true;
    }

    public boolean isPaletteUnlock(int id){
        return paletteItemsUnlocked[id];
    }
}
