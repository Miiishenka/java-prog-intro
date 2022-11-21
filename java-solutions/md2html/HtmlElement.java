package md2html;

public abstract class HtmlElement {

    public String markup;
    public int headLevel;

    public HtmlElement(String markup, int headLevel) {
        this.markup = markup;
        this.headLevel = headLevel;
    }

    public HtmlElement(String markup) {
        this.markup = markup;
    }

    abstract String getTag();

    String toHtml() {
        Text text = new Text(markup);
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(getTag()).append(">").append(text.toHtml()).append("</").append(getTag()).append(">");
        return sb.toString();
    }
}
