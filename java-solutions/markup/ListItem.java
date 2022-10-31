package markup;

import java.util.ArrayList;
import java.util.List;

public class ListItem {
    private List<ListItemElement> list = new ArrayList<>();
    public ListItem(List<ListItemElement> list) {
        this.list = list;
    }
    void toHtml(StringBuilder s) {
        s.append("<li>");
        for (ListItemElement element : this.list) {
            element.toHtml(s);
        }
        s.append("</li>");
    }
}
