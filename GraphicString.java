import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.FontMetrics;

//basicamente es pura paqueteria que exporta como cuadros de texto para poner texto jsjsjs salu2
public class GraphicString {
    private String text;
    private int x;
    private int y;
    private Font font;
    private Color color;

    public GraphicString(String text, int x, int y, Font font, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = font;
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(text, x, y);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle getBounds(Graphics2D g2) {
        FontMetrics metrics = g2.getFontMetrics(font);
        int width = metrics.stringWidth(text);
        int height = metrics.getHeight();
        return new Rectangle(x, y - height, width, height);
    }
}
