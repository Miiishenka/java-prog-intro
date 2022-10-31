package markup;

import java.util.ArrayList;
import java.util.List;

public class Strikeout implements ParagraphElement {
    private List<ParagraphElement> list = new ArrayList<>();
    public Strikeout(List<ParagraphElement> list) {
        this.list = list;
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        s.append('~');
        for (ParagraphElement element : this.list) {
            element.toMarkdown(s);
        }
        s.append('~');
    }
    @Override
    public void toHtml(StringBuilder s) {
        s.append("<s>");
        for (ParagraphElement element : this.list) {
            element.toHtml(s);
        }
        s.append("</s>");
    }
}
