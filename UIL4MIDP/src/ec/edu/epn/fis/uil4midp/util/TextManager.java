package ec.edu.epn.fis.uil4midp.util;

import java.util.Vector;
import javax.microedition.lcdui.Font;

public class TextManager {

    /**
     * Obtiene un arreglo de líneas.
     * Adaptado de: http://wiki.forum.nokia.com/index.php/Scrollable_Text_in_Java_ME
     * @param text
     * @param width
     * @param font
     * @return
     */
    public static String[] getLines(String text, int width, Font font) {
        char spaceChar = ' ';

        Vector lines = new Vector();
        StringBuffer currentLine = new StringBuffer();

        //indexes used to split text words
        int prevIndex = 0;
        int currIndex = text.indexOf(spaceChar);

        if (currIndex == -1) {
            currIndex = text.length();
        }

        int lineWidth = 0;
        int wordWidth = 0;
        int spaceWidth = font.stringWidth(" ");

        //current text token
        String currentWord = null;

        while (currIndex != -1) {
            //get the current token
            currentWord = text.substring(prevIndex, currIndex);

            //get the width of current token and update row width
            wordWidth = font.stringWidth(currentWord);
            lineWidth += wordWidth;

            //if row is not empty, add the whitespace width too
            if (currentLine.length() > 0) {
                lineWidth += spaceWidth;
            }

            //if new row width is bigger than max width, and previous row is not empty
            if (currentLine.length() > 0 && lineWidth > width) {

                //add current row text to rows ArrayList
                lines.addElement(currentLine.toString());

                //reinitialize current row with current token
                currentLine.setLength(0);
                currentLine.append(currentWord);

                //and update current row width
                lineWidth = wordWidth;
            } else {
                //if current row is not empty, add a whitespace
                if (currentLine.length() > 0) {
                    currentLine.append(spaceChar);
                }

                //and then add current token
                currentLine.append(currentWord);
            }

            //check if text is ended
            if (currIndex == text.length()) {
                break;
            }

            //update indexes
            prevIndex = currIndex + 1;

            currIndex = text.indexOf(spaceChar, prevIndex);

            if (currIndex == -1) {
                currIndex = text.length();
            }
        }

        //finally append current row, if not empty
        if (currentLine.length() > 0) {
            lines.addElement(currentLine.toString());
        }

        //Convert our rows vector to a String array
        String[] outLines = new String[lines.size()];
        lines.copyInto(outLines);

        return outLines;
    }
}
