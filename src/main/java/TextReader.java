import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class TextReader {
    DbHandler dbHandler;

    public List<Words> addWordsRatingInDb(Map<String, Integer> woldCount) {
        List<Words> words = null;
        try {
            assert woldCount != null;
            dbHandler = DbHandler.getInstance();
            dbHandler.clearWords();
            woldCount.forEach((key, value) -> dbHandler.addWord(new Words(key, value)));
            words = dbHandler.getListOfAllWords();

        } catch (SQLException e) {
            Logger.LOGGER.log(Level.WARNING, "Instance not received : " + e);
        }
        return words;
    }
}
