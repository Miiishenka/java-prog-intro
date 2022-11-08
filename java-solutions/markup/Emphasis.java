package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements ParagraphElement {
    public Emphasis(List<ParagraphElement> list) {
        super(list);
    }

    public String getTeg() {
        return "em";
    }

    public String getMark() {
        return "*";
    }
}
