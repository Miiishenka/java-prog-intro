package markup;

import java.util.ArrayList;
import java.util.List;

public class OrderedList implements ListItemElement {
    private List<ListItem> list;

    public OrderedList(List<ListItem> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder s) {
        throw new UnsupportedOperationException();
    }

    public void toHtml(StringBuilder s) {
        s.append("<ol>");
        for (ListItem element : this.list) {
            element.toHtml(s);
        }
        s.append("</ol>");
    }
}
