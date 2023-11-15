import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[] palabras = {"MICRO", "MESAS", "COBRA", "CASAS", "PLAYA"};
        Random random = new Random();
        String palabraSeleccionada = palabras[random.nextInt(palabras.length)];

        Canvas canvas = new Canvas("WORDLE", 900, 800);
        Tablero tablero = new Tablero();
        tablero.dibujar(canvas);
        Teclado teclado = new Teclado(tablero, palabraSeleccionada, canvas);
        teclado.dibujar();
        canvas.setVisible(true);
    }
}

