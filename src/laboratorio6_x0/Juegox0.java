/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio6_x0;

/**
 *
 * @author adrianaguilar
 */
import javax.swing.*;

public class Juegox0 {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private boolean isXTurn = true; // Turno inicial de X
    private char[][] tablero;
    private Jugador jugadorX;
    private Jugador jugadorO;

    public Juegox0(Jugador[] jugadores) {
        this.tablero = new char[3][3];
    }

    public void iniciarJuego(String username1, String username2, Jugador[] jugadores) {
        jugadorX = buscarJugador(username1, jugadores);
        jugadorO = buscarJugador(username2, jugadores);

        if (jugadorX == null || jugadorO == null) {
            JOptionPane.showMessageDialog(null, "Ambos jugadores deben estar registrados.");
            return;
        }

        inicializarTablero();
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        frame = new JFrame("X-0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(null);

       
        statusLabel = new JLabel("Turno de " + jugadorX.getUsername() + " (X)", SwingConstants.CENTER);
        statusLabel.setBounds(10, 10, 260, 30);
        frame.add(statusLabel);

        
        buttons = new JButton[3][3];
        int size = 80;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setBounds(10 + j * size, 50 + i * size, size, size);
                buttons[i][j].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
                final int fila = i;
                final int columna = j;
                buttons[i][j].addActionListener(e -> handleButtonClick((JButton) e.getSource(), fila, columna));
                frame.add(buttons[i][j]);
            }
        }

        // Botón para salir
        JButton exitButton = new JButton("Salir");
        exitButton.setBounds(100, 310, 80, 30);
        exitButton.addActionListener(e -> {
            frame.dispose();
            return;
        });
        frame.add(exitButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleButtonClick(JButton button, int fila, int columna) {
        if (!button.getText().isEmpty()) return; 

        char fichaActual = isXTurn ? 'X' : 'O';
        button.setText(String.valueOf(fichaActual));
        tablero[fila][columna] = fichaActual;

        if (hayGanador(fichaActual)) {
            String ganador = isXTurn ? jugadorX.getUsername() : jugadorO.getUsername();
            statusLabel.setText("¡Ganó " + ganador + "!");
            if (isXTurn) jugadorX.incrementarPuntos();
            else jugadorO.incrementarPuntos();
            disableButtons();
        } else if (isTableroLleno()) {
            statusLabel.setText("¡Empate!");
        } else {
            isXTurn = !isXTurn; // Cambiar turno
            statusLabel.setText("Turno de " + (isXTurn ? jugadorX.getUsername() : jugadorO.getUsername()) + " (" + (isXTurn ? "X" : "O") + ")");
        }
    }

    private boolean hayGanador(char ficha) {
        for (int i = 0; i < 3; i++) {
            // Revisar filas y columnas
            if (tablero[i][0] == ficha && tablero[i][1] == ficha && tablero[i][2] == ficha) return true;
            if (tablero[0][i] == ficha && tablero[1][i] == ficha && tablero[2][i] == ficha) return true;
        }
        // Revisar diagonales
        return (tablero[0][0] == ficha && tablero[1][1] == ficha && tablero[2][2] == ficha) ||
               (tablero[0][2] == ficha && tablero[1][1] == ficha && tablero[2][0] == ficha);
    }

    private boolean isTableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '\0') return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '\0';
            }
        }
    }

    private Jugador buscarJugador(String username, Jugador[] jugadores) {
        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.getUsername().equals(username)) {
                return jugador;
            }
        }
        return null;
    }

    void iniciarJuego(String jugador1, String jugador2) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

