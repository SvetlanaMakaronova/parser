import java.util.Map;

public class Main {

    public static void main(String... args) {
       var writer = new TextWriter();
       var url = args[0];

        if (writer.isValidURL(url)) {
            writer.addTextInFile(url);

            Map<String, Integer> woldCounts = new Counter().parseTextInMap();

            var words = new TextReader().addWordsRatingInDb(woldCounts);
            new RatingOutput().output(words);
        }


    }
}
