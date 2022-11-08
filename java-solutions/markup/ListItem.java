package markup;

import java.util.List;

public class ListItem extends AbstractElement implements Elements {
    public ListItem(List<ListItemElement> list) {
        super(list);
    }

    public String getTeg() {
        return "li";
    }

    public String getMark() {
        return "";
    }
}
