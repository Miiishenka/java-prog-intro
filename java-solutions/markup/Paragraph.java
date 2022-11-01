package markup;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements ListItemElement {
    private List<ParagraphElement> list;

    public Paragraph(List<ParagraphElement> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder s) {
        for (ParagraphElement element : this.list) {
            element.toMarkdown(s);
        }
    }

    public void toHtml(StringBuilder s) {
        for (ParagraphElement element : this.list) {
            element.toHtml(s);
        }
    }
}
