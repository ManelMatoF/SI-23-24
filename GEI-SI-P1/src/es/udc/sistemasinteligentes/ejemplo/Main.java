package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.EstrategiaBusquedaGrafo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ProblemaCuadradoMagico;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial1 = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        int[][] matriz = {
                {4, 9, 2},
                {0, 5, 0},
                {0, 0, 0}
        };
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial2 = new ProblemaCuadradoMagico.EstadoCuadrado(matriz);

        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial1);
        ProblemaBusqueda CuadradoMagico = new ProblemaCuadradoMagico(estadoInicial2);

        EstrategiaBusqueda buscador1 = new Estrategia4();
        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaGrafo();

        System.out.println(Arrays.toString(buscador2.soluciona(CuadradoMagico)));
    }
}
