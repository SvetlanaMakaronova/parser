import java.util.List;

public class RatingOutput {

    public void output(List<Words> words){
        words.forEach(word -> System.out.println(word.toString()));
    }
}
