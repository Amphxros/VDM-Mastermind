package mastermind.engine;

public interface IJsonObject {
    String getInfoJSON();
    int getIntKey(String s);
    String getStringKey(String s);

    boolean getBooleanKey(String s);
}
