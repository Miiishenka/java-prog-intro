package markup;

public interface ListItemElement extends Elements{
    void toMarkdown(StringBuilder s);
    void toHtml(StringBuilder s);
}
