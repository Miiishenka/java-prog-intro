package markup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement implements Elements {
    protected List<? extends Elements> list;

    public AbstractElement(List<? extends Elements> list) {
        this.list = list;
    }

    public void toMarkdown(StringBuilder s) {
        s.append(getMark());
        for (Elements element : list) {
            element.toMarkdown(s);
        }
        s.append(getMark());
    }

    @Override
    public void toHtml(StringBuilder s) {
        if (getTeg().isEmpty()) {
            for (Elements element : list) {
                element.toHtml(s);
            }
        } else {
            s.append('<').append(getTeg()).append('>');
            for (Elements element : list) {
                element.toHtml(s);
            }
            s.append("</").append(getTeg()).append('>');
        }
    }

    protected abstract String getMark();

    protected abstract String getTeg();
}
