package vdm.mastermind.engine.interfaces;

import java.util.ArrayList;

import vdm.mastermind.engine.classes.TouchEvent;

public interface IInput {
    ArrayList<TouchEvent> getTouchEvents();

}
