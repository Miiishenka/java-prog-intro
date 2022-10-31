package markup;

public interface ParagraphElement {
    void toMarkdown(StringBuilder s);
    void toHtml(StringBuilder s);
}
