package mastermind.engine;

import java.io.IOException;

public interface ILogicData {
    void loadData(String route);
    void saveData() throws IOException;


}
