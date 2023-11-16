package vdm.mastermind.engine.interfaces;

/**
 * Basic definition of game to the engine
 */
public interface ILogic {
    void init();
    void render(IGraphics graphics);
    void update(float time);
    void handleInput(IInput input);
}
