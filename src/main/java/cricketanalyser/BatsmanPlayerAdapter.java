package cricketanalyser;

import cricketanalyser.model.BatsmanCSV;
import cricketanalyser.model.PlayerDAO;

import java.util.Map;

public class BatsmanPlayerAdapter extends PlayerAdapter{
    public Map<String, PlayerDAO> loadData(PlayerAdapter.PLAYER_TYPE type, String csvFilePath) {
        return super.loadData(BatsmanCSV.class,type,csvFilePath);
    }
}
