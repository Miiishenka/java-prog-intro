package markup;

import java.util.ArrayList;
import java.util.List;

public class UnorderedList implements ListItemElement {
    private List<ListItem> list = new ArrayList<>();
    public UnorderedList(List<ListItem> list) {
        this.list = list;
    }
    public void toMarkdown(StringBuilder s) {
        throw new UnsupportedOperationException();
    }
    public void toHtml(StringBuilder s) {
        s.append("<ul>");
        for (ListItem element : this.list) {
            element.toHtml(s);
        }
        s.append("</ul>");
    }
}
