package markup;

import java.util.ArrayList;
import java.util.List;

public class UnorderedList extends AbstractElement implements ListItemElement {
    public UnorderedList(List<ListItem> list) {
        super(list);
    }
    public String getTeg() {
        return "ul";
    }
    public String getMark() {
        return "";
    }
}
