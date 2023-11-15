import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Teclado {
    private Tablero tablero;
    private int filaActual = 0;
    private int columnaActual = 0;
    private boolean enterPresionado = false;
    private String palabraSeleccionada;
    private Canvas canvas;
    private int letrasEnFilaActual = 0;

    public Teclado(Tablero tablero, String palabraSeleccionada, Canvas canvas) {
        this.tablero = tablero;
        this.palabraSeleccionada = palabraSeleccionada;
        this.canvas = canvas;
    }

    public void dibujar() {
        int tecladoWidth = 490;
        int tecladoHeight = 190;
        int xTeclado = (canvas.getSize().width - tecladoWidth) / 2;
        int yTeclado = canvas.getSize().height / 2 + 100;
        int anchoNormal = 40;
        int anchoEspecial = 80;

        for (int fila = 0; fila < 4; fila++) {
            int xCuadro = (fila == 0 ? xTeclado + 15 : (fila == 1 ? xTeclado + 42 : (fila == 2 ? xTeclado + 91 : xTeclado + 140)));
            int xTablero = (fila == 0 ? xTeclado + 15 : (fila == 1 ? xTeclado + 42 : (fila == 2 ? xTeclado + 91 : xTeclado + 140)));

            int numCuadrados = (fila == 0 ? 10 : (fila == 1 ? 9 : (fila == 2 ? 7 : 2)));

            for (int i = 0; i < numCuadrados; i++) {
                final int filaClic = fila;
                final int columnaClic = i;
                final int xCuadroActual = xCuadro;
                final int yCuadroActual = yTeclado + 10 + (fila * 50);
                final int indice = i;

                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int xClic = e.getX();
                        int yClic = e.getY();

                        if (xClic >= xCuadroActual && xClic < xCuadroActual + (filaClic == 3 && (indice == 0 || indice == 1) ? anchoEspecial : anchoNormal) &&
                                yClic >= yCuadroActual && yClic < yCuadroActual + 40) {
                            char letra = obtenerLetra(filaClic, indice);
                            keyPressed(canvas, xCuadroActual, yCuadroActual, letra, filaClic, columnaClic);
                        }
                    }
                });

                canvas.setForegroundColor(Color.black);
                canvas.fillRectangle(xCuadroActual, yCuadroActual, (fila == 3 && (i == 0 || i == 1) ? anchoEspecial : anchoNormal), 40);

                if (fila == 0) {
                    String letras = "QWERTYUIOP";
                    if (i < letras.length()) {
                        char letra = letras.charAt(i);
                        canvas.setForegroundColor(Color.white);
                        canvas.fillRectangle(xCuadro + 2, yTeclado + 12 + (fila * 50), (fila == 3 && (i == 0 || i == 1) ? anchoEspecial - 4 : anchoNormal - 4), 36);
                        canvas.setForegroundColor(Color.black);
                        canvas.drawString(String.valueOf(letra), xCuadro + (fila == 3 && (i == 0 || i == 1) ? 20 : 15), yTeclado + 35 + (fila * 50));
                    }
                } else if (fila == 1) {
                    String letras = "ASDFGHJKL";
                    if (i < letras.length()) {
                        char letra = letras.charAt(i);
                        canvas.setForegroundColor(Color.white);
                        canvas.fillRectangle(xCuadro + 2, yTeclado + 12 + (fila * 50), (fila == 3 && (i == 0 || i == 1) ? anchoEspecial - 4 : anchoNormal - 4), 36);
                        canvas.setForegroundColor(Color.black);
                        canvas.drawString(String.valueOf(letra), xCuadro + (fila == 3 && (i == 0 || i == 1) ? 20 : 15), yTeclado + 35 + (fila * 50));
                    }
                } else if (fila == 2) {
                    String letras = "ZXCVBNM";
                    if (i < letras.length()) {
                        char letra = letras.charAt(i);
                        canvas.setForegroundColor(Color.white);
                        canvas.fillRectangle(xCuadro + 2, yTeclado + 12 + (fila * 50), (fila == 3 && (i == 0 || i == 1) ? anchoEspecial - 4 : anchoNormal - 4), 36);
                        canvas.setForegroundColor(Color.black);
                        canvas.drawString(String.valueOf(letra), xCuadro + (fila == 3 && (i == 0 || i == 1) ? 20 : 15), yTeclado + 35 + (fila * 50));
                    }
                } else if (fila == 3) {
                    if (i == 0) {
                        canvas.setForegroundColor(Color.white);
                        canvas.fillRectangle(xCuadro + 2, yTeclado + 12 + (fila * 50), anchoEspecial - 4, 36);
                        canvas.setForegroundColor(Color.black);
                        canvas.drawString("Delete", xCuadro + 8, yTeclado + 35 + (fila * 50));
                    } else if (i == 1) {
                        canvas.setForegroundColor(Color.white);
                        canvas.fillRectangle(xCuadro + 2, yTeclado + 12 + (fila * 50), anchoEspecial - 4, 36);
                        canvas.setForegroundColor(Color.black);
                        canvas.drawString("Enter", xCuadro + 12, yTeclado + 35 + (fila * 50));
                    }
                }

                xCuadro += (fila == 3 && (i == 0 || i == 1) ? anchoEspecial : anchoNormal);
            }
        }
    }

    private char obtenerLetra(int fila, int columna) {
        if (fila == 0) {
            String letras = "QWERTYUIOP";
            if (columna >= 0 && columna < letras.length()) {
                return letras.charAt(columna);
            }
        } else if (fila == 1) {
            String letras = "ASDFGHJKL";
            if (columna >= 0 && columna < letras.length()) {
                return letras.charAt(columna);
            }
        } else if (fila == 2) {
            String letras = "ZXCVBNM";
            if (columna >= 0 && columna < letras.length()) {
                return letras.charAt(columna);
            }
        } else if (fila == 3) {
            if (columna == 0) {
                return 'd'; // Letra 'D' para "Delete"
            } else if (columna == 1) {
                return 'e'; // Letra 'E' para "Enter"
            }
        }
        return ' ';
    }

    private void keyPressed(Canvas canvas, int xCuadroActual, int yCuadroActual, char letra, int fila, int columna) {
        if (tablero.estaLleno()) {
            mostrarGameOver(canvas, "¡Game Over! Palabra: " + palabraSeleccionada);
            return;
        }

        if (letra == 'd') {
            tablero.borrarUltimaLetra();
            retrocederPosicion();
        } else if (letra == 'e') {
            if (tablero.filaLlena(filaActual) && filaActual < 6) {
                avanzarFila();
            }
        } else {
            if (!tablero.filaLlena(filaActual)) {
                tablero.colocarLetraEnTablero(letra, filaActual, columnaActual);
                avanzarPosicion();
            }
        }

        

        tablero.dibujar(canvas);
        canvas.repaint();
    }

    private void mostrarGameOver(Canvas canvas, String mensaje) {
        canvas.setForegroundColor(Color.red);
        canvas.drawString(mensaje, 300, 400); // Ajusta la posición del mensaje
        canvas.repaint();
    }

    private void avanzarPosicion() {
        columnaActual++;
        if (columnaActual >= 5) {
            columnaActual = 0;
        }

        if (filaActual >= 6) {
            filaActual = 0;
        }
    }

    private void retrocederPosicion() {
        if (filaActual == 0 && columnaActual == 0) {
            return;
        }

        columnaActual--;
        if (columnaActual < 0) {
            columnaActual = 4;
            filaActual--;
        }

        if (filaActual < 0) {
            filaActual = 5;
        }
    }

    private void avanzarFila() {
        String palabraEnTablero = tablero.obtenerPalabraEnTablero();
        String feedback = proporcionarFeedback(palabraSeleccionada, palabraEnTablero);
        System.out.println("Feedback: " + feedback);

        filaActual++;
        if (filaActual >= 6) {
            if (tablero.palabraCompleta(palabraSeleccionada)) {
                System.out.println("¡Felicidades, adivinaste la palabra!");
            } else {
                System.out.println("¡Sigue intentando!");
            }
            filaActual = 0;
        }
    }

    private String proporcionarFeedback(String palabraSeleccionada, String palabraEnTablero) {
    StringBuilder feedback = new StringBuilder();
        
            for (int i = 0; i < palabraSeleccionada.length(); i++) {
        char letraSeleccionada = palabraSeleccionada.charAt(i);
        char letraEnTablero = (i < palabraEnTablero.length()) ? palabraEnTablero.charAt(i) : ' ';
    
        if (letraEnTablero == letraSeleccionada) {
            feedback.append("?"); // Letra en la posición correcta
        } else if (palabraEnTablero.contains(String.valueOf(letraSeleccionada))) {
            feedback.append("?"); // Letra en la palabra pero en la posición incorrecta
        } else {
            feedback.append("?"); // Letra no encontrada en el tablero
        }
    }
        
    return feedback.toString();
    }

}




