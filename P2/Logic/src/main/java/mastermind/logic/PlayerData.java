package mastermind.logic;

import java.util.ArrayList;
import java.util.HashMap;

import mastermind.engine.IEngine;

public class PlayerData {
    AnimalID currentAnimalID;
    HashMap<AnimalID, Boolean> unlockedAnimals;

    IEngine engine;
    public PlayerData(IEngine engine){
        this.engine=engine;
        currentAnimalID =AnimalID.None;
    }

    public void saveData(){

    }

    public void loadData(){

    }

    public void buyAnimal(AnimalID animalID){

    }

    public void setAnimal(AnimalID animalID){

    }
}
