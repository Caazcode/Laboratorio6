/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio6_x0;

/**
 *
 * @author adrianaguilar
 */
public class Jugador {
    private String nombre;
    private String username;
    private String password;
    private int puntos;

    public Jugador(String nombre, String username, String password, int puntos) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void incrementarPuntos() {
        this.puntos++;
    }
}
