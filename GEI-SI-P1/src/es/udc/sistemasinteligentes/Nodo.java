package es.udc.sistemasinteligentes;

public class Nodo implements Comparable<Nodo> {

    private final Nodo padre;
    private Estado estado;
    private Accion accion;
    float coste;
    float f;

    public Nodo(Estado e, Nodo padre, Accion accion) {
        this.estado = e;
        this.padre = padre;
        this.accion = accion;
    }
    public Nodo(Estado e, Nodo padre, Accion accion, Heuristica heuristica) {
        this.estado = e;
        this.padre = padre;
        this.accion = accion;
        if(padre != null){
            this.coste = padre.coste + accion.getCoste();
            if(heuristica != null)
                this.f = this.coste + heuristica.evalua(estado);
        }
    }

    public Estado getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Nodo nodo) {
        // el orden se basa en la funcion f
        return nodo.f < this.f ? 1 : -1;
    }
}
