package vdm.mastermind.pc_launcher;

import javax.swing.JFrame;

import vdm.mastermind.logic.Logic;
import vdm.mastermind.pc_engine.PCEngine;

public class PCLauncher {
    public static void main(String[] args){

        PCEngine engine= new PCEngine();
        Logic logic = new Logic(engine);
        engine.setLogic(logic);
        System.out.println("Launched " + logic!=null);
        engine.run();

    }
}