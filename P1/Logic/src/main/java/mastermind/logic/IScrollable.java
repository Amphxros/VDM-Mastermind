package mastermind.logic;

public interface IScrollable {
    void onScroll(float inputX, float inputY);

    void setScrollUP(boolean sUP);
    void setScrollDOWN(boolean sDOWN);
}
