/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex2p1_alejandrosussman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Ex2P1_AlejandroSussman {

    static ArrayList<Personaje> pary = new ArrayList<>();
    static ArrayList<Personaje> backup = new ArrayList<>();
    static ArrayList<Item> bag = new ArrayList<>();
    static int vidaTotHless;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //fila 2 asiento 14
        System.out.println("BIENVENIDO AL CASTILLO DE OBLIVION");
        Equipos();
        Bag();
        int cuarto = 1;
        while (cuarto <= 20 && !pary.isEmpty()) {
            int randDoor = generarRandom(1, 5);

            switch (randDoor) {
                case 1:
                    System.out.println("Te has encontrado con Heartless en el cuarto " + cuarto + "!");
                    vidaTotHless = generarRandom(1, 3) * 75;
                    showPary();
                    break;
                case 2:
                    openChest("Pocion");
                    break;
                case 3:
                    openChest("Eter");
                    break;
                case 4:
                    openChest("Elixir");
                    break;
                case 5:
                    ayuda();
                    break;
            }

            cuarto++;
        }

        if (cuarto > 20) {
            System.out.println("Has escapado del castillo de Oblivion GANASTE!!!");
        } else {
            System.out.println("La Party ha sido derrotada Fin del juego.");
        }
    }

    private static void openChest(String item) {
        System.out.println("Te has encontrado un cofre que contiene una " + item + "!");
        bag.add(new Item(item, healthPoints(item), obtenerMpPoints(item)));
        mostrarMochila();
    }

    private static int healthPoints(String tipoItem) {
        switch (tipoItem.toLowerCase()) {
            case "pocion":
                return 50;
            case "eter":
                return 0;
            case "elixir":
                return 100;
            default:
                return 0;
        }
    }

    private static int obtenerMpPoints(String tipoItem) {
        switch (tipoItem.toLowerCase()) {
            case "pocion":
                return 0;
            case "eter":
                return 50;
            case "elixir":
                return 100;
            default:
                return 0;
        }
    }

    public static void ayuda() {
        System.out.println("Te has encontrado amigos que necesitan ayuda y les daras algunos items:");

        if (!bag.isEmpty()) {
            int cantidadItemsAmigos = generarRandom(1, 3);

            for (int i = 0; i < cantidadItemsAmigos && !bag.isEmpty(); i++) {
                int index = generarRandom(0, bag.size() - 1);
                Item itemAmigo = bag.get(index);
                bag.remove(index);

                System.out.println("Le has dado a tus amigos: " + itemAmigo.nombre);
                System.out.println("Quedan:");
                mostrarMochila();
                System.out.println("Deseas continuar? (s/n)");

                Scanner scanner = new Scanner(System.in);
                if (!scanner.nextLine().equalsIgnoreCase("s")) {
                    break;
                }
            }
        } else {
            System.out.println("No tienes items para darles a tus amigos.");
        }
    }

    public static void Equipos() {
        pary.add(new Personaje("Sora", 300, 300, 75, 15));
        pary.add(new Personaje("Donald", 150, 450, 45, 10));
        pary.add(new Personaje("Goofy", 450, 100, 150, 50));
        backup.add(new Personaje("Mickey", 100, 500, 150, 35));
        backup.add(new Personaje("Roxas", 300, 300, 15, 75));
        backup.add(new Personaje("Kairi", 200, 200, 50, 15));
    }

    public static void Bag() {
        bag.add(new Item("Pocion", 50, 0));
        bag.add(new Item("Eter", 0, 50));
        bag.add(new Item("Elixir", 100, 100));
    }

    public static void action(int opcion) {
        Scanner scanner = new Scanner(System.in);
        int indicePersonaje = opcion;

        if (opcion < 0 || opcion >= pary.size()) {
            System.out.println("Opcion invalida. Selecciona un personaje valido.");
            return;
        }

        Personaje personajeSeleccionado = pary.get(indicePersonaje);

        System.out.println("Menu:");
        System.out.println("1.- Atacar");
        System.out.println("2.- Usar Magia");
        System.out.println("3.- Usar Items");
        System.out.println("4.- Cambiar Personaje (Party)");

        int opcionAccion = scanner.nextInt();

        switch (opcionAccion) {
            case 1:
                int danio = generarRandom(1, 25);
                System.out.println(personajeSeleccionado.nombre + " ataco y causo " + danio + " de dano a los Heartless" + "\n" + personajeSeleccionado.nombre + " Recibio el golpe");
                menosVidaH(danio);
                if (vidaTotHless <= 0) {
                    System.out.println("Has derrotado a los Heartless!");
                }
                recibirDanioParty();
                if (pary.isEmpty()) {
                    System.out.println("Han derrotado la party! Fin del juego!!");
                }
                break;

            case 2:
                System.out.println("1.- Blizzard (50 MP - 50 DMG)");
                System.out.println("2.- Firaga (25 MP - 25 DMG)");
                System.out.println("3.- Gravity (75 MP - 100 DMG)");

                int tipoMagia = scanner.nextInt();

                switch (tipoMagia) {
                    case 1:
                        usarMagia(personajeSeleccionado, "Blizzard", 50, 50);
                        break;

                    case 2:
                        usarMagia(personajeSeleccionado, "Firaga", 25, 25);
                        break;

                    case 3:
                        usarMagia(personajeSeleccionado, "Gravity", 75, 100);
                        break;

                    default:
                        System.out.println("Opcion de magia invalida.");
                        break;
                }
                break;

            case 3:
                mostrarMochila();
                System.out.println("Elige un item:");
                int indiceItem = scanner.nextInt();

                if (indiceItem >= 0 && indiceItem < bag.size()) {
                    Item itemSeleccionado = bag.get(indiceItem);
                    usarItem(personajeSeleccionado, itemSeleccionado);

                    bag.remove(indiceItem);

                    System.out.println(personajeSeleccionado.nombre + " uso " + itemSeleccionado.nombre
                            + " y recupero " + itemSeleccionado.Hp_points + " HP y " + itemSeleccionado.Mp_points + " MP.");
                } else {
                    System.out.println("El item seleccionado es invalido");
                }
                break;

            case 4:
                mostrarPersonajesReserva();
                System.out.println("Elige a quien cambiar:");
                int change = scanner.nextInt();

                if (change >= 0 && change < backup.size()) {
                    reemplazarP(indicePersonaje, change);
                    System.out.println(personajeSeleccionado.nombre + " ha sido cambiado antes del ataque!");
                } else {
                    System.out.println("Opcion de cambio invalida.");
                }
                break;

            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }

    public static void usarMagia(Personaje personaje, String nombreMagia, int MPcost, int dmagic) {
        if (personaje.MP >= MPcost) {
            personaje.MP -= MPcost;
            System.out.println(personaje.nombre + " tiro " + nombreMagia + " y causo " + dmagic + " de dano a los Heartless.");
            menosVidaH(dmagic);
        } else {
            System.out.println("No tienes MP suficiente para lanzar " + nombreMagia + ".");
        }
    }

    public static void usarItem(Personaje personaje, Item item) {
        personaje.HP += item.Hp_points;
        personaje.MP += item.Mp_points;
    }

    public static void reemplazarP(int indicePersonaje, int indiceCambio) {
        Personaje personajeReserva = backup.get(indiceCambio);
        backup.remove(indiceCambio);
        backup.add(pary.get(indicePersonaje));
        pary.remove(indicePersonaje);
        pary.add(personajeReserva);
    }

    public static void menosVidaH(int danio) {
        vidaTotHless -= danio;
        System.out.println("La vida total de los Heartless es: " + vidaTotHless);
    }

    public static void recibirDanioParty() {
        for (int i = 0; i < pary.size(); i++) {
            Personaje personaje = pary.get(i);
            personaje.HP -= generarRandom(1, 10);
        }
        showPary();
    }

    public static void showPary() {
        System.out.println("Estado actual de la Party:");
        for (int i = 0; i < pary.size(); i++) {
            Personaje personaje = pary.get(i);
            System.out.println(i + "-" + personaje.nombre + "\n- HP: " + personaje.HP + "\n- MP: " + personaje.MP);
        }
    }

    public static void mostrarPersonajesReserva() {
        System.out.println("Personajes en reserva:");
        for (int i = 0; i < backup.size(); i++) {
            Personaje personaje = backup.get(i);
            System.out.println(i + "- " + personaje.nombre + "\n\tHP=" + personaje.HP + "\n\tMP=" + personaje.MP + "\n\tAttack P. =" + personaje.attackPoints + "\n\tDefense P. =" + personaje.defensePoints);
        }
    }

    public static int generarRandom(int minlimit, int maxlimit) {
        Random random = new Random();
        return random.nextInt(maxlimit - minlimit + 1) + minlimit;
    }

 

    public static void mostrarDetallesEstadoParty() {
        System.out.println("Te enfrentas a los Heartless con una vida total de " + vidaTotHless);
        showPary();
    }

    public static int showmenu() {
        System.out.println("Elije el personaje con el que deseas pelear:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void mostrarMochila() {
        System.out.println("Mochila actual:");
        for (int i = 0; i < bag.size(); i++) {
            Item item = bag.get(i);
            System.out.println(i + "- " + item.nombre + " HPpoints=" + item.Hp_points + ", MPpoints=" + item.Mp_points);
        }
    }
     public static Item darItem(String tipoItem) {
        for (int i = 0; i < bag.size(); i++) {
            Item item = bag.get(i);
            if (item.nombre.equalsIgnoreCase(tipoItem)) {
                return item;
            }
        }
        return null;
    }
}
