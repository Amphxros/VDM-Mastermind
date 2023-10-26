package vdm.mastermind.logic;

public class Hint extends GameObject{

    private HintState state = HintState.NOTSOLUTION;

    public final void isWrong(){state = HintState.NOTSOLUTION;}
    public final void isDisplaced(){state = HintState.DISPLACED;}
    public final void isCorrect(){state = HintState.CORRECT;}

}