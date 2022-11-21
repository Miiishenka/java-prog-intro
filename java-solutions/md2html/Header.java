package md2html;

public class Header extends HtmlElement {
    public Header(String markup, int headLevel) {
        super(markup, headLevel);
    }
    @Override
    String getTag() {
        return "h" + headLevel;
    }
}
