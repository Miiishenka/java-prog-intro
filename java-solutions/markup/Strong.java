package markup;

import java.util.List;

public class Strong extends AbstractElement implements ParagraphElement {
    public Strong(List<ParagraphElement> list) {
        super(list);
    }

    public String getTeg() {
        return "strong";
    }

    public String getMark() {
        return "__";
    }
}
