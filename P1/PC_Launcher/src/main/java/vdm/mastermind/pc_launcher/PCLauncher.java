package vdm.mastermind.pc_launcher;

import javax.swing.JFrame;

import vdm.mastermind.logic.Logic;
import vdm.mastermind.pc_engine.PCEngine;

public class PCLauncher {
    public static void main(String[] args){

        JFrame frame= new JFrame("Mastermind");


        System.out.println("Launched");
        PCEngine engine= new PCEngine(frame);
        engine.setLogic(new Logic(engine));
        engine.run();

    }
}