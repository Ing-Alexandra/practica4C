import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas {
    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;
    private Image canvasImage;
    private String tableroTexto = ""; // Inicializa el tablero con "[   ]"

    public Canvas(String title, int width, int height) {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = Color.white;
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xClic = e.getX();
                int yClic = e.getY();
                int tableroX = 100;
                int tableroY = 100;
                int cuadroWidth = 40;

                int filaClic = (yClic - tableroY) / cuadroWidth;
                int columnaClic = (xClic - tableroX) / cuadroWidth;

                if (filaClic == 0 && columnaClic >= 0 && columnaClic < 10) {
                    char letra = (char) ('A' + columnaClic);
                    actualizarTablero(letra);
                }
            }
        });

        Dimension size = canvas.getSize();
        canvasImage = canvas.createImage(size.width, size.height);
        graphic = (Graphics2D) canvasImage.getGraphics();
        graphic.setColor(backgroundColor);
        graphic.fillRect(0, 0, size.width, size.height);
    }

    public void setVisible(boolean visible) {
        if (graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D) canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(true);
    }

    public boolean isVisible() {
        return frame.isVisible();
    }

    public void draw(Shape shape) {
        graphic.draw(shape);
        repaint();
    }

    public void fill(Shape shape) {
        graphic.fill(shape);
        repaint();
    }

    public void fillRectangle(int xPos, int yPos, int width, int height) {
        fill(new Rectangle(xPos, yPos, width, height));
    }

    public void erase() {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
        repaint();
    }

    public void setLetraEnTablero(char letra) {
        tableroTexto = "[" + letra + tableroTexto.substring(2);
        repaint();
    }

    public void eraseRectangle(int xPos, int yPos, int width, int height) {
        erase(new Rectangle(xPos, yPos, width, height));
    }

    public void erase(Shape shape) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.fill(shape); // erase by filling background color
        graphic.setColor(original);
        repaint();
    }

    public void eraseOutline(Shape shape) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.draw(shape); // erase by drawing background color
        graphic.setColor(original);
        repaint();
    }

    public boolean drawImage(Image image, int x, int y) {
        boolean result = graphic.drawImage(image, x, y, null);
        repaint();
        return result;
    }

    public void drawString(String text, int x, int y) {
        graphic.drawString(text, x, y);
        repaint();
    }

    public void eraseString(String text, int x, int y) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.drawString(text, x, y);
        graphic.setColor(original);
        repaint();
    }

    public void drawLine(int x1,int y1, int x2, int y2) {
        graphic.drawLine(x1, y1, x2, y2);
        repaint();
    }

    public void setForegroundColor(Color newColor) {
        graphic.setColor(newColor);
    }

    public Color getForegroundColor() {
        return graphic.getColor();
    }

    public void setBackgroundColor(Color newColor) {
        backgroundColor = newColor;
        graphic.setBackground(newColor);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setFont(Font newFont) {
        graphic.setFont(newFont);
    }

    public Font getFont() {
        return graphic.getFont();
    }

    public void setSize(int width, int height) {
        canvas.setPreferredSize(new Dimension(width, height));
        Image oldImage = canvasImage;
        canvasImage = canvas.createImage(width, height);
        graphic = (Graphics2D) canvasImage.getGraphics();
        graphic.setColor(backgroundColor);
        graphic.fillRect(0, 0, width, height);
        graphic.drawImage(oldImage, 0, 0, null);
        frame.pack();
    }

    public Dimension getSize() {
        return canvas.getSize();
    }

    public void actualizarTablero(char letra) {
        tableroTexto = "[" + letra + tableroTexto.substring(2);
        repaint();
    }

    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // ignoring exception at the moment
        }
    }

    public void addMouseListener(MouseAdapter listener) {
        canvas.addMouseListener(listener);
    }

    public void repaint() {
        canvas.repaint();
    }

    private class CanvasPane extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(canvasImage, 0, 0, null);
            g.setColor(Color.BLACK);
            g.drawString(tableroTexto, 100, 100);
        }
    }
}



