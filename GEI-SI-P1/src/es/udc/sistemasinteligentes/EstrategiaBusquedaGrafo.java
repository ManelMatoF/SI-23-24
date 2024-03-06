package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados = new ArrayList<>();
        //Queue<Nodo> Frontera = new LinkedList<>(); //Anchura
        Stack<Nodo> Frontera = new Stack<>(); //Profundidad
        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(estadoActual, null, null);
        Frontera.push(nodoActual);
        int i = 1;
        int nodosCreados = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

            while (true) {
                if (Frontera.isEmpty()){
                    throw new Exception("No se ha podido encontrar una solución");
                }
                // nodoActual = Frontera.poll();//Anchura
                nodoActual = Frontera.pop(); //Profundidad
                assert nodoActual != null;
                estadoActual = nodoActual.getEstado();
                System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

                if (p.esMeta(estadoActual)) break;
                else {
                    System.out.println((i++) + " - " + estadoActual + " no es meta");
                    explorados.add(nodoActual);

                    Accion[] accionesDisponibles = p.acciones(estadoActual);
                    for (Accion acc : accionesDisponibles) {
                        Estado sc = p.result(estadoActual, acc);
                        Nodo nodo = new Nodo(sc, nodoActual, acc);
                        nodosCreados++;
                        System.out.println((i++) + " - RESULT(" + estadoActual + "," + acc + ")=" + sc);
                        if (!estaExplorado(Frontera, sc) && !estaExplorado(explorados, sc)) {
                            Frontera.push(nodo);
                            System.out.println((i++) + " - " + sc + " NO explorado");
                            System.out.println((i++) + " - Nodo anadido a frontera " + nodo);
                        }else  System.out.println((i++) + " - " + sc + " ya explorado");
                    }
                }
            }

        System.out.println((i++) + " - FIN - " + estadoActual);
        System.out.println("Nodos expandidos: " + explorados.size());
        System.out.println("Nodos creados: " + nodosCreados);
        return reconstruyeSol(nodoActual);
    }

    private boolean estaExplorado(ArrayList<Nodo> explorados, Estado estado) {
        for (Nodo nodo : explorados) {
            if (nodo.getEstado().equals(estado)) {
                return true;
            }
        }
        return false;
    }

    private boolean estaExplorado(Queue<Nodo> explorados, Estado estado) {
        for (Nodo nodo : explorados) {
            if (nodo.getEstado().equals(estado)) {
                return true;
            }
        }
        return false;
    }

    private boolean estaExplorado(Stack<Nodo> explorados, Estado estado) {
        for (Nodo nodo : explorados) {
            if (nodo.getEstado().equals(estado)) {
                return true;
            }
        }
        return false;
    }

    private Nodo[] reconstruyeSol(Nodo n){
        ArrayList<Nodo> solucion = new ArrayList<>();
        Nodo a = n;
        while (a != null){
            solucion.addFirst(a);
            a = a.getPadre();
        }
        return solucion.toArray(new Nodo[0]);
    }
}
