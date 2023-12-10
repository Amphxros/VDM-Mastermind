package mastermind.engine;

public interface IJSON {

    String getInfoJSON();
    int getIntKey(String s);
    String getStringKey(String s);

    Boolean getBoleanKey(String s);
}
