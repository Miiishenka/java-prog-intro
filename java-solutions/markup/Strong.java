package markup;

import java.util.ArrayList;
import java.util.List;

public class Strong implements ParagraphElement {
    private List<ParagraphElement> list = new ArrayList<>();
    public Strong(List<ParagraphElement> list) {
        this.list = list;
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        s.append("__");
        for (ParagraphElement element : this.list) {
            element.toMarkdown(s);
        }
        s.append("__");
    }
    @Override
    public void toHtml(StringBuilder s) {
        s.append("<strong>");
        for (ParagraphElement element : this.list) {
            element.toHtml(s);
        }
        s.append("</strong>");
    }
}
