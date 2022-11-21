package markup;

public class Text implements ParagraphElement {
    private final String word;

    public Text(String s) {
        this.word = s;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(word);
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append(word);
    }
}
