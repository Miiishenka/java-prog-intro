package markup;

import java.util.ArrayList;
import java.util.List;

public class OrderedList extends AbstractElement implements ListItemElement {
    public OrderedList(List<ListItem> list) {
        super(list);
    }
    public String getTeg() {
        return "ol";
    }
    public String getMark() {
        return "";
    }

}
