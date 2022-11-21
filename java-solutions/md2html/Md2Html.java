package md2html;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main (String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]), StandardCharsets.UTF_8));
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(args[1]), StandardCharsets.UTF_8));
                try {
                    String line;
                    StringBuilder markup = new StringBuilder();
                    int headLevel = 0;
                    boolean checked = false;
                    do {
                        line = reader.readLine();
                        if (line == null || line.isEmpty()) {
                            if (headLevel > 0 && markup.length() > headLevel) {
                                Header header = new Header(markup.substring(headLevel + 1), headLevel);
                                writer.write(header.toHtml());
                                writer.newLine();
                            } else if (headLevel == 0 && !markup.isEmpty()) {
                                Paragraph paragraph = new Paragraph(markup.toString());
                                writer.write(paragraph.toHtml());
                                writer.newLine();
                            }
                            headLevel = 0;
                            markup.setLength(0);
                            checked = false;
                        } else {
                            markup.append(line).append('\n');
                            if (!checked) {
                                while (line.charAt(headLevel) == '#') {
                                    headLevel++;
                                }
                                if (!Character.isWhitespace(line.charAt(headLevel))) {
                                    headLevel = 0;
                                }
                                checked = true;
                            }
                        }
                    } while (line != null);
                } finally {
                    writer.close();
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        }
    }
}
