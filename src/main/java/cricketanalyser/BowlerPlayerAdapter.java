package cricketanalyser;

import cricketanalyser.model.BowlerCSV;
import cricketanalyser.model.PlayerDAO;

import java.util.Map;

public class BowlerPlayerAdapter extends PlayerAdapter{

    public Map<String, PlayerDAO> loadData(PlayerAdapter.PLAYER_TYPE type, String csvFilePath) {
        return super.loadData(BowlerCSV.class, type, csvFilePath);
    }

}
