package mastermind.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ByteArrayInputStream;


import mastermind.engine.Color;
import mastermind.engine.IEngine;
import mastermind.engine.ILogicData;
import mastermind.engine.IScene;
import mastermind.logic.scene.ChooseLevelScene;
import mastermind.logic.scene.ExploreWorldsScene;
import mastermind.logic.scene.GameScene;
import mastermind.logic.scene.ShopScene;
import mastermind.logic.scene.WinScene;

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
            this.paletteItemsUnlocked[i]=i==0; //unlock only the default
        }

        loadPalettes();
        this.coins=0;
        this.lastLevel=1;
        this.lastWorld=1;
    }

    public static PlayerData load(IEngine engine){
        PlayerData playerData;
        InputStream stream = null;
        InputStream SHA = null;
        // Checks existence of a SHA to check if the save was tampered
        try{
            SHA = engine.getFileManager().openInputFile("SHA");
            // Checks existence of save
            try {
                ObjectInputStream objectInputStream= new ObjectInputStream(SHA);
                String readSHA = (String) objectInputStream.readObject();
                objectInputStream.close();

                stream = engine.getFileManager().openInputFile("save");
                String save = engine.getFileManager().readFile("save");

                // Generates SHA of the save and checks against the readSHA
                if (readSHA == engine.getFileManager().generateSHA(save))
                    System.out.println(stream!=null);
            }
            catch (Exception e){    // Save not found exception
                return new PlayerData(engine);
            }
        }
            catch (Exception e){ // SHA not found exception
                return new PlayerData(engine);
        }
        playerData=new PlayerData(engine);

        try {
            ObjectInputStream objectInputStream= new ObjectInputStream(stream);

            int coins=-1;
            coins= (int) objectInputStream.readObject();
            if(coins!=-1)
                playerData.setCoins(coins);

            playerData.loadPalettes();
            for(int i=0;i< playerData.paletteItems.length;i++){
                playerData.paletteItemsUnlocked[i]=(boolean) objectInputStream.readObject();
            }

            for(int i=0;i< playerData.unlockedAnimals.length;i++){
               playerData.unlockedAnimals[i]=(boolean) objectInputStream.readObject();
            }

            playerData.currentPalette=(PaletteID) objectInputStream.readObject();
            playerData.currentAnimalID=(AnimalID) objectInputStream.readObject();

            playerData.lastWorld=(int)objectInputStream.readObject();
            playerData.lastLevel=(int) objectInputStream.readObject();

            playerData.loadGameState(objectInputStream);

            objectInputStream.close();
        
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
        OutputStream stream = engine.getFileManager().openOutputFile("save");
        if (stream == null)
            return;

        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(this.coins);
            for(int i=0;i< paletteItems.length;i++){
                objectStream.writeObject(paletteItemsUnlocked[i]);
            }

            for(int i=0;i< unlockedAnimals.length;i++){
                objectStream.writeObject(unlockedAnimals[i]);
            }

            objectStream.writeObject(currentPalette);
            objectStream.writeObject(currentAnimalID);

            objectStream.writeObject(lastWorld);
            objectStream.writeObject(lastLevel);

            objectStream.flush();
            objectStream.close();

            String save = engine.getFileManager().readFile("save");
            String SHA = engine.getFileManager().generateSHA(save);

            OutputStream streamSHA = engine.getFileManager().openOutputFile("SHA");
            ObjectOutputStream SHAStream = new ObjectOutputStream(streamSHA);
            SHAStream.writeObject(SHA);
            SHAStream.flush();
            SHAStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public void loadGameState(ObjectInputStream outputStream) throws IOException, ClassNotFoundException {
        String scene= (String) outputStream.readObject();
        if(scene!=null){
            Color[] colors;
            int colorsSize;

            int[] solution;
            int solutionSize;

            int tries;

            switch (scene){
                case "Choose":
                    engine.getLogic().setScene(new ChooseLevelScene(engine));
                    break;
                case "Shop":
                    engine.getLogic().setScene(new ShopScene(engine));

                    break;
                case "Explore":
                    engine.getLogic().setScene(new ExploreWorldsScene(engine));
                    break;
                case "Win":

                    break;


                case "Game":

                    break;
            }
        }
    }

    public void saveGameState(ObjectOutputStream objectOutputStream, IScene currentScene) throws IOException {
        String id= currentScene.getID();
        objectOutputStream.writeObject(id);
            switch (id){
                case "Win":
                    WinScene winScene= (WinScene) currentScene;
                    objectOutputStream.writeObject(winScene);
                    break;
                case "Choose":
                    ChooseLevelScene chooseScene= (ChooseLevelScene) currentScene;
                    break;
                case "Shop":
                    ShopScene shopScene= (ShopScene) currentScene;
                    break;
                case "Explore":
                    ExploreWorldsScene exploreWorldsScene= (ExploreWorldsScene) currentScene;
                    break;
                case "Game":
                    GameScene gameScene= (GameScene) currentScene;
                    break;
            }
    }




}
