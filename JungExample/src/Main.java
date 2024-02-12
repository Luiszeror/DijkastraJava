import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.util.*;
import edu.uci.ics.jung.algorithms.shortestpath.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        
        DirectedGraph<Persona, Relacion> redSocial = new DirectedSparseGraph<>();

        Persona persona1 = new Persona("Juan");
        Persona persona2 = new Persona("Maria");
        Persona persona3 = new Persona("Carlos");
        Persona persona4 = new Persona("Ana");

        redSocial.addVertex(persona1);
        redSocial.addVertex(persona2);
        redSocial.addVertex(persona3);
        redSocial.addVertex(persona4);

        redSocial.addEdge(new Relacion("Juan, María"), persona1, persona2);
        redSocial.addEdge(new Relacion("María, Carlos"), persona2, persona3);
        redSocial.addEdge(new Relacion("Carlos, Ana"), persona3, persona4);
        redSocial.addEdge(new Relacion("Ana, Juan"), persona4, persona1);

        DijkstraShortestPath<Persona, Relacion> dijkstra = new DijkstraShortestPath<>(redSocial);
        List<Relacion> rutaMasCorta = dijkstra.getPath(persona1, persona4);

        Set<String> nombresImpresos = new HashSet<>();

        System.out.println("Ruta más corta entre Juan y Carlos:");
        for (Relacion relacion : rutaMasCorta) {
            String[] nombres = relacion.getTipo().split(", ");
            for (String nombre : nombres) {
                if (!nombresImpresos.contains(nombre)) {
                    System.out.println("[" + nombre + "]");
                    nombresImpresos.add(nombre);
                }
            }
        }
    }
}

class Persona {
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

class Relacion {
    private String tipo;

    public Relacion(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
