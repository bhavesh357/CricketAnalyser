package cricketanalyser;

import cricketanalyser.model.PlayerDAO;

import java.util.Map;

public class PlayerAdapterFactory {
    public static Map<String, PlayerDAO> getData(PlayerAdapter.PLAYER_TYPE type, String csvFilePath) {
        if(type.equals(PlayerAdapter.PLAYER_TYPE.BATSMAN)){
            return new BatsmanPlayerAdapter().loadData(type,csvFilePath);
        }else{
            return new BowlerPlayerAdapter().loadData(type,csvFilePath);
        }
    }

}
