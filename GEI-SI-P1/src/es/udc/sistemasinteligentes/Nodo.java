package es.udc.sistemasinteligentes;

public class Nodo {

    private final Nodo padre;
    private Estado estado;
    private Accion accion;

    public Nodo(Estado e, Nodo padre, Accion accion) {
        this.estado = e;
        this.padre = padre;
        this.accion = accion;
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
}
