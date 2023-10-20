package vdm.mastermind.logic;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.TouchEvent;
import vdm.mastermind.engine.interfaces.IGraphics;
import vdm.mastermind.engine.interfaces.IInput;
import vdm.mastermind.engine.interfaces.ILogic;

public class Logic implements ILogic {
    public Logic(){

    }

    @Override
    public void render(IGraphics graphics) {

    }

    @Override
    public void update(float time) {

    }

    @Override
    public void handleInput(IInput input) {
        ArrayList<TouchEvent> events= input.getTouchEvents();
    }
}