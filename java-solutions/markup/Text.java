package markup;

public class Text implements ParagraphElement {
    private String word;
    public Text(String s) {
        this.word = s;
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(this.word);
    }
    @Override
    public void toHtml(StringBuilder s) {
        s.append(this.word);
    }
}
