package ec.edu.epn.fis.uil4midp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Utility class to read properties files
 */
class Properties extends Hashtable {

    public void load(String filename) throws IOException {
        InputStream stream = getClass().getResourceAsStream(filename);
        InputStreamReader reader = new InputStreamReader(stream);

        StringBuffer fileContent = new StringBuffer();
        char[] buffer = new char[1024];

        int pos = reader.read(buffer, 0, 1024);
        while (pos != -1) {
            fileContent.append(buffer, 0, pos);
            pos = reader.read(buffer, 0, 1024);
        }

        String[] lines = TextManager.split(fileContent.toString(), '\n');
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith("#")) {
                continue;
            }

            String[] property = TextManager.split(lines[i], '=');
            if (property.length == 1) {
                put(property[0], "");
            }
            if (property.length == 2) {
                put(property[0], property[1]);
            }
        }
    }
}
