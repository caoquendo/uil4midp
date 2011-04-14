package ec.edu.epn.fis.uil4midp.util;

import java.util.Vector;
import javax.microedition.lcdui.Font;

/**
 * Manages the creation of text lines
 * @author Carlos Andrés Oquendo
 */
public class TextManager {

    /**
     * Gets an array of text lines
     * Based on code from http://wiki.forum.nokia.com/index.php/Scrollable_Text_in_Java_ME
     * @param text Text to be processed
     * @param width Maximum width of a text line
     * @param font Font that will be used to measure the width of the text
     * @return String array containing the text converted in lines
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

    public static String[] split(String string, char separator) {
        if (string == null || string.length() == 0) {
            return new String[0];
        }

        Vector textPieces = new Vector();

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == separator) {
                textPieces.addElement(buffer.toString());
                buffer.setLength(0);
            } else {
                buffer.append(c);
            }
        }

        if (buffer.length() > 0) {
            textPieces.addElement(buffer.toString());
        }

        return toStringArray(textPieces);
    }

    public static String[] toStringArray(Vector strings) {
        String[] output = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            output[i] = strings.elementAt(i).toString();
        }
        return output;
    }
}
