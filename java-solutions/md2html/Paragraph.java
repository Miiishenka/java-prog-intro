package md2html;

public class Paragraph extends HtmlElement {
    public Paragraph(String markup) {
        super(markup);
    }
    @Override
    String getTag() {
        return "p";
    }
}
