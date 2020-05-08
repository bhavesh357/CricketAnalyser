import model.BatsmanCSV;
import model.BowlerCSV;
import model.PlayerDAO;

import java.util.Map;

public class BowlerPlayerAdapter extends PlayerAdapter{

    public Map<String, PlayerDAO> loadData(PlayerAdapter.PLAYER_TYPE type, String csvFilePath) {
        return super.loadData(BowlerCSV.class, type, csvFilePath);
    }
}
