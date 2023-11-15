import java.awt.*;

public class Tablero {
    private char[][] tablero = new char[6][5];
    private int filaActual = 0;
    private int letrasEnFilaActual = 0;
    private int tableroY = 140;
    private int tableroX = 325;
    private String palabraCompleta = null;

    public void dibujar(Canvas canvas) {
        int filas = 6;
        int columnas = 5;
        int anchoCelda = 40;
        int altoCelda = 40;

        int tableroWidth = columnas * anchoCelda;
        int tableroHeight = filas * altoCelda;

        int yInicio = tableroY;

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                int x = tableroX + columna * anchoCelda;
                int y = yInicio + fila * altoCelda;

                canvas.setForegroundColor(Color.gray);
                canvas.fillRectangle(x, y, anchoCelda, altoCelda);

                canvas.setForegroundColor(Color.white);
                canvas.fillRectangle(x + 2, y + 2, anchoCelda - 4, altoCelda - 4);

                char letra = tablero[fila][columna];

                if (letra != 0) {
                    String cuadroTexto = "[" + letra + "]";
                    canvas.setForegroundColor(Color.black);
                    int xTexto = x + (anchoCelda - 2 * 3) / 2;
                    int yTexto = y + (altoCelda - 2 * 3) / 2;
                    canvas.drawString(cuadroTexto, xTexto, yTexto);
                }
            }
        }
    }

    public void colocarLetraEnTablero(char letra, int fila, int columna) {
        if (fila >= 0 && fila < 6 && columna >= 0 && columna < 5) {
            if (letrasEnFilaActual < 5) {
                tablero[fila][columna] = letra;
                filaActual = fila;
                letrasEnFilaActual++;

                if (letrasEnFilaActual == 5) {
                    avanzarFila();
                }
            }
        }
    }

    public void borrarUltimaLetra() {
        for (int columna = 4; columna >= 0; columna--) {
            if (tablero[filaActual][columna] != 0) {
                tablero[filaActual][columna] = 0;
                letrasEnFilaActual--;
                return;
            }
        }
    }

    public boolean filaLlena(int fila) {
        for (int columna = 0; columna < 5; columna++) {
            if (tablero[fila][columna] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean estaLleno() {
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                if (tablero[fila][columna] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean palabraCompleta(String palabra) {
        String palabraEnTablero = obtenerPalabraEnTablero();
        return palabra.equals(palabraEnTablero);
    }

    public String obtenerPalabraEnTablero() {
        StringBuilder palabra = new StringBuilder();
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                char letra = tablero[fila][columna];
                if (letra != 0) {
                    palabra.append(letra);
                }
            }
        }
        return palabra.toString();
    }

    private void avanzarFila() {
        filaActual++;
        letrasEnFilaActual = 0;
        if (filaActual >= 6) {
            filaActual = 0;
        }
    }
}

