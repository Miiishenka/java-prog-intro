package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements ParagraphElement {
    public Strikeout(List<ParagraphElement> list) {
        super(list);
    }

    public String getTeg() {
        return "s";
    }

    public String getMark() {
        return "~";
    }
}
