import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Counter {

    final String REGEX = "\\s*(\\s|,| |!|\\?|\"|;|:|[|]|\\(|\\)|\n|\r|\t|\f|\\.|\\d|-|»|«|/|\\|@|\\*|`|$|%|^|&|\\+|'|\\{|\\})\\s*";

    Map<String, Integer> map = new HashMap<>();

    public Map<String, Integer> parseTextInMap() {
        try {
            var reader = new BufferedReader(new InputStreamReader(new FileInputStream(Config.outputFile)));
            String text = reader.readLine();
            String[] list = text.split(REGEX);
            for (String o : list) {
                if (map.containsKey(o)) {
                    map.put(o, map.get(o) + 1);
                } else {
                    map.put(o, 1);
                }
            }
        } catch (IOException e) {
            Logger.LOGGER.log(Level.WARNING, Config.outputFile + " not found: " + e);
        }


        return map;
    }

}
