package ec.edu.epn.fis.uil4midp.components.controls;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ImageBox extends UserControl {

    private Image image;
    private boolean imageSynced;
    private int maxWidth = -1;

    //<editor-fold desc="Constructors">
    /**
     * Initializes internal fields
     */
    private ImageBox() {
        imageSynced = false;
    }

    /**
     * Creates a new ImageBox instance with the specified image.
     * If the image passed is null, this constructor builds 24 px x 24 px
     * white image. Once the ImageBox instance is initialized, it is not possible
     * to change the image.
     * @param caption Image to show in the ImageBox.
     */
    public ImageBox(Image image) {
        this();
        this.image = image != null ? image : Image.createImage(24, 24);
    }

    /**
     * Creates a new ImageBox instance with the image referenced by its file name.
     * If the file specified does not exists, this constructor builds 24 px x 24 px
     * white image. Once the ImageBox instance is initialized, it is not possible
     * to change the image.
     * @param fileName Path and name of the file containing the image. The image 
     * file can be located on a package and the path must be associated with the
     * root of the package. Eg.: The file image.png is located on the <code>ec.edu.epn.fis.uil4midp.resources</code>
     * package. The file name passed must be <code>/ec/edu/epn/fis/uil4midp/resources/image.png</code>
     */
    public ImageBox(String fileName) {
        this();

        if (fileName != null && fileName.length() > 0) {
            try {
                this.image = Image.createImage(fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
                this.image = Image.createImage(24, 24);
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters">
    /**
     * Gets the actual width of the image component.
     * @return Actual width of the internal image.
     */
    public int getActualImageWidth() {
        return image != null ? image.getWidth() : 0;
    }

    /**
     * Sets the maximum width for the image. This value is used only as reference.
     * @param maxWidth Maximum width for the image.
     */
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }
    //</editor-fold>

    //<editor-fold desc="Abstract Methods Implementations">
    /**
     * Handles the key events.
     * Label can not handle any key press event.
     * @param action Canvas' key action number.
     * @param keyCode Pressed key code. This code may be device-specific.
     * @return This method always returns False.
     */
    public boolean keyPressed(int action, int keyCode) {
        return false;
    }

    /**
     * Determines if the ImageBox can be focused.
     * @return This method always return False, due to ImageBox can not be focused.
     */
    public boolean isFocusable() {
        return false;
    }

    /**
     * Paints the ImageBox.
     * @param g Graphics object on which paint.
     */
    public void paint(Graphics g) {
        prepareComponent();

        // Draw picture;
        int[] pos = {x + padding, y + padding};
        g.drawImage(image, pos[0], pos[1], Graphics.LEFT | Graphics.TOP);
    }

    /**
     * Prepares the layout of the ImageBox.
     */
    public void prepareComponent() {
        if (!imageSynced || !layoutSynced) {
            int maxImageWidth = maxWidth - 2 * padding;

            width = image.getWidth();

            if (image.getWidth() > maxImageWidth) {
                width = maxWidth;
                image = resizeImage(image, maxImageWidth);
            }

            height = image.getHeight() + 2 * padding;

            imageSynced = true;
            layoutSynced = true;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Utility Methods">
    /**
     * Resizes the passed image using the specified width as reference.
     * @param image Image to resize.
     * @param newWidth Target width of the resized image.
     * @return Image resized 
     */
    private Image resizeImage(Image image, int newWidth) {
        int newHeight = newWidth * image.getHeight() / image.getWidth();
        return resizeImage(image, newWidth, newHeight);
    }

    /**
     * Resizes the passed image using the specified height and width
     * @param image Image to resize.
     * @param newWidth Target width of the resized image.
     * @param newHeight Target height of the resized image.
     * @return Image resized.
     */
    private Image resizeImage(Image image, int newWidth, int newHeight) {
        //http://www.coderanch.com/t/228899/JME/Mobile/we-resize-image-me

        // Create array with the size of the original image
        int sourceImageARGB[] = new int[image.getWidth() * image.getHeight()];

        // Get the RGB array of image into "rgb"
        image.getRGB(sourceImageARGB, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

        // Resize the RGB array
        int outputImageARGB[] = resizeArray(sourceImageARGB, image.getWidth(), image.getHeight(), newWidth, newHeight);

        // Create an image with that RGB array
        Image output = Image.createRGBImage(outputImageARGB, newWidth, newHeight, true);
        return output;
    }

    /**
     * Resizes an array to an specified size.
     * @param data Array to be resized.
     * @param width
     * @param height
     * @param newWidth
     * @param newHeight
     * @return Resized array.
     */
    private int[] resizeArray(int[] data, int width, int height, int newWidth, int newHeight) {
        int out[] = new int[newWidth * newHeight];

        int i = 0;
        int tx = width / newWidth;
        int ty = height / newHeight;

        for (int y = 0; y < newHeight; y++) {
            int dy = y * ty;
            for (int x = 0; x < newWidth; x++) {
                int dx = x * tx;
                out[i++] = data[(width * dy) + dx];
            }
        }
        return out;
    }
    //</editor-fold>
}
