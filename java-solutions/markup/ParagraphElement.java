package markup;

public interface ParagraphElement extends Elements {
    void toMarkdown(StringBuilder s);
    void toHtml(StringBuilder s);
}
