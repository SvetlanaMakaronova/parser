public class Words {

    public String word;

    public int count;

    public Words(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("%s - %s",
                this.word, this.count);
    }
}
