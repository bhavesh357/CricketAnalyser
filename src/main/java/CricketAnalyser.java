import model.PlayerDAO;

import java.util.Map;

public class CricketAnalyser {
    private PlayerAdapter.PLAYER_TYPE type;
    private Map<String, PlayerDAO> playerMap;

    public int loadBatsmanData(String csvFilePath) {
        type = PlayerAdapter.PLAYER_TYPE.BATSMAN;
        playerMap=PlayerAdapterFactory.getData(type,csvFilePath);
        return playerMap.size();
    }
}
