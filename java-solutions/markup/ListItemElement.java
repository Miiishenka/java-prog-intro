package markup;

public interface ListItemElement {
    void toMarkdown(StringBuilder s);

    void toHtml(StringBuilder s);
}
