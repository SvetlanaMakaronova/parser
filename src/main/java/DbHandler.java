import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class DbHandler {

    private static final String dbPath = Config.dbPath;
    private static DbHandler instance = null;

    private final static String ALL_WORDS_RESULT = "SELECT word, count FROM Words";
    private final static String INSERT_WORD = "INSERT INTO Words(`word`, `count`) VALUES(?, ?)";
    private final static String DELETE_WORD = "DELETE FROM Words WHERE word = ?";
    private final static String CLEAR_TABLE = "DELETE from Words";

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private final Connection connection;

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(dbPath);
    }

    public List<Words> getListOfAllWords() {

        try (Statement statement = this.connection.createStatement()) {
            List<Words> words = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(ALL_WORDS_RESULT);
            while (resultSet.next()) {
                words.add(new Words(
                        resultSet.getString("word"),
                        resultSet.getInt("count")));
            }
            return words;

        } catch (SQLException e) {
            Logger.LOGGER.log(Level.WARNING, "SQLException: " + e);
            return Collections.emptyList();
        }
    }

    public void addWord(Words words) {
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT_WORD)) {
            statement.setObject(1, words.word);
            statement.setObject(2, words.count);
            statement.execute();
        } catch (SQLException e) {
            Logger.LOGGER.log(Level.WARNING, "SQLException: " + e);
        }
    }

    public void deleteWord(String word) {
        try (PreparedStatement statement = this.connection.prepareStatement(DELETE_WORD)) {
            statement.setObject(1, word);
            statement.execute();
        } catch (SQLException e) {
            Logger.LOGGER.log(Level.WARNING, "SQLException: " + e);
        }
    }

    public void clearWords() {
        try (PreparedStatement statement = this.connection.prepareStatement(CLEAR_TABLE)) {
            statement.execute();
        } catch (SQLException e) {
            Logger.LOGGER.log(Level.WARNING, "SQLException: " + e);
        }
    }

}
