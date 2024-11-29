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

public class Main {
    private static final int MAX_JUGADORES = 100;
    private static Jugador[] jugadores = new Jugador[MAX_JUGADORES];
    private static int totalJugadores = 0;

    public static void main(String[] args) {
        while (true) {
            String[] opciones = {"Iniciar Sesión", "Registro", "Jugar X-0", "Ranking", "Salir"};
            String opcion = (String) JOptionPane.showInputDialog(null, "MENÚ INICIO", "Menú", JOptionPane.QUESTION_MESSAGE,
                    null, opciones, opciones[0]);

            if (opcion == null || opcion.equals("Salir")) {
                JOptionPane.showMessageDialog(null, "Gracias por jugar. ¡Hasta pronto!");
                break;
            }

            switch (opcion) {
                case "Iniciar Sesión":
                    iniciarSesion();
                    break;

                case "Registro":
                    registrarJugador();
                    break;

                case "Jugar X-0":
                    iniciarJuego();
                    break;

                case "Ranking":
                    mostrarRanking();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void iniciarSesion() {
        String username = JOptionPane.showInputDialog("Ingrese su username:");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña:");
        if (validarCredenciales(username, password)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenido, " + username + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Intente de nuevo.");
        }
    }

    private static void registrarJugador() {
        if (totalJugadores >= MAX_JUGADORES) {
            JOptionPane.showMessageDialog(null, "No se pueden registrar más jugadores.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        String username = JOptionPane.showInputDialog("Ingrese su username:");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña (5 caracteres):");

        if (password.length() != 5) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres.");
            return;
        }

        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "El username ya está registrado.");
                return;
            }
        }

        jugadores[totalJugadores++] = new Jugador(nombre, username, password, 0);
        JOptionPane.showMessageDialog(null, "Registro exitoso. ¡Bienvenido, " + nombre + "!");
    }

    private static void iniciarJuego() {
        if (totalJugadores < 2) {
            JOptionPane.showMessageDialog(null, "Debe haber al menos dos jugadores registrados para iniciar un juego.");
            return;
        }

        String jugador1Username = JOptionPane.showInputDialog("Ingrese el username del jugador 1 (registrado):");
        String jugador2Username = JOptionPane.showInputDialog("Ingrese el username del jugador 2 (registrado):");

        Jugador jugador1 = buscarJugador(jugador1Username);
        Jugador jugador2 = buscarJugador(jugador2Username);

        if (jugador1 == null || jugador2 == null) {
            JOptionPane.showMessageDialog(null, "Ambos jugadores deben estar registrados.");
            return;
        }

        Juegox0 juego = new Juegox0(jugadores);
        juego.iniciarJuego(jugador1.getUsername(), jugador2.getUsername(), jugadores);
    }

    private static void mostrarRanking() {
        if (totalJugadores == 0) {
            JOptionPane.showMessageDialog(null, "No hay jugadores registrados aún.");
            return;
        }

        Jugador[] ranking = new Jugador[totalJugadores];
        System.arraycopy(jugadores, 0, ranking, 0, totalJugadores);

        // Ordenar los jugadores por puntos (burbuja)
        for (int i = 0; i < ranking.length - 1; i++) {
            for (int j = 0; j < ranking.length - i - 1; j++) {
                if (ranking[j].getPuntos() < ranking[j + 1].getPuntos()) {
                    Jugador temp = ranking[j];
                    ranking[j] = ranking[j + 1];
                    ranking[j + 1] = temp;
                }
            }
        }

        StringBuilder resultado = new StringBuilder("Ranking de jugadores:\n");
        for (Jugador jugador : ranking) {
            resultado.append(jugador.getUsername()).append(": ").append(jugador.getPuntos()).append(" puntos\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    private static boolean validarCredenciales(String username, String password) {
        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.getUsername().equals(username) && jugador.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static Jugador buscarJugador(String username) {
        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.getUsername().equals(username)) {
                return jugador;
            }
        }
        return null;
    }
}
