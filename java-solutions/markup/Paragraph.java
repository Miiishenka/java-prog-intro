package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements ListItemElement {
    public Paragraph(List<ParagraphElement> list) {
        super(list);
    }

    public String getTeg() {
        return "";
    }

    public String getMark() {
        return "";
    }
}
