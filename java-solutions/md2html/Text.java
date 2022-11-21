package md2html;

import java.util.ArrayDeque;
import java.util.Map;

public class Text {
    String markup;
    StringBuilder sb;
    ArrayDeque<String> tags;
    Map<String, String> singleTags = Map.of(
            "*", "em",
            "_", "em",
            "`", "code"
    );
    Map<String, String> doubleTags = Map.of(
            "**", "strong",
            "__", "strong",
            "--", "s"
    );
    Map<String, String> specialSymbols = Map.of(
            "<", "&lt;",
            ">", "&gt;",
            "&", "&amp;"
    );

    public Text(String markup) {
        this.markup = markup;
        sb = new StringBuilder();
        tags = new ArrayDeque<>();
    }
    String toHtml() {
        String tag = "";
        for (int i = 0; i < markup.length(); i++) {
            if (markup.charAt(i) == '\\') {
                if (i + 1 < markup.length()) {
                    String symbol = markup.substring(i + 1, i + 2);
                    sb.append(specialSymbols.getOrDefault(symbol, symbol));
                    i++;
                    continue;
                }
            }
            if (markup.charAt(i) == '!') {
                StringBuilder name = new StringBuilder();
                StringBuilder link = new StringBuilder();
                int k = i + 1;
                if (k < markup.length() && markup.charAt(k) == '[') {
                    k++;
                    while (k < markup.length() && markup.charAt(k) != ']') {
                        name.append(markup.charAt(k));
                        k++;
                    }
                    if (k+1 < markup.length() && markup.substring(k, k+2).equals("](")) {
                        k += 2;
                        while (k < markup.length() && markup.charAt(k) != ')') {
                            link.append(markup.charAt(k));
                            k++;
                        }
                    }
                }
                if (!name.isEmpty() && !link.isEmpty() && k < markup.length() && markup.charAt(k) == ')') {
                    sb.append("<img alt='").append(name).append("' src='").append(link).append("'>");
                    i = k;
                    continue;
                }
            }
            if (i + 1 < markup.length() && doubleTags.get(markup.substring(i, i + 2)) != null) {
                tag = doubleTags.get(markup.substring(i, i + 2));
                i += 1;
            } else if (singleTags.get(markup.substring(i, i + 1)) != null
                && (i > 0 && !Character.isWhitespace(markup.charAt(i - 1))
                || (i + 1 < markup.length() && !Character.isWhitespace(markup.charAt(i + 1))))
            ) {
                tag = singleTags.get(markup.substring(i, i + 1));
            }
            if (!tag.isEmpty()) {
                if (tag.equals(tags.peek())) {
                    sb.append("</").append(tag).append(">");
                    tags.pop();
                } else {
                    sb.append("<").append(tag).append(">");
                    tags.push(tag);
                }
                tag = "";
                continue;
            }
            if (specialSymbols.get(markup.substring(i, i + 1)) != null) {
                sb.append(specialSymbols.get(markup.substring(i, i + 1)));
                continue;
            }
            sb.append(markup.charAt(i));
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
