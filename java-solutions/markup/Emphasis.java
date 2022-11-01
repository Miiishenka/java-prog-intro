package markup;

import java.util.List;

public class Emphasis implements ParagraphElement {
    private final List<ParagraphElement> list;

    public Emphasis(List<ParagraphElement> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append('*');
        for (ParagraphElement element : list) {
            element.toMarkdown(s);
        }
        s.append('*');
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<em>");
        for (ParagraphElement element : list) {
            element.toHtml(s);
        }
        s.append("</em>");
    }
}
