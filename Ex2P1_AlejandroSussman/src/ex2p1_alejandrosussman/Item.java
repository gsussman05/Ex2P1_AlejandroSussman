/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2p1_alejandrosussman;

/**
 *
 * @author Gabriel
 */
public class Item {
    
    String nombre;
    int Hp_points;
    int Mp_points;

    public Item(String nombre, int Hp_points, int Mp_points) {
        this.nombre = nombre;
        this.Hp_points = Hp_points;
        this.Mp_points = Mp_points;
    }

    public Item() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp_points() {
        return Hp_points;
    }

    public void setHp_points(int Hp_points) {
        this.Hp_points = Hp_points;
    }

    public int getMp_points() {
        return Mp_points;
    }

    public void setMp_points(int Mp_points) {
        this.Mp_points = Mp_points;
    }

    @Override
    public String toString() {
        return "Item{" + "nombre=" + nombre + ", Hp_points=" + Hp_points + ", Mp_points=" + Mp_points + '}';
    }
    
}


