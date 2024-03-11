package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial1 = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);

        int[][] matriz = {
                {2, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        int[][] matriz2 = {
                {2, 8, 15, 9},
                {14, 12, 5, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial2 = new ProblemaCuadradoMagico.EstadoCuadrado(matriz);

        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial1);
        ProblemaBusqueda CuadradoMagico = new ProblemaCuadradoMagico(estadoInicial2);

        HeuristicaCuadrado h = new HeuristicaCuadrado(1, 9);

        EstrategiaBusqueda buscador1 = new Estrategia4();
        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaGrafo();
        EstrategiaBusquedaInformada buscador3 = new EstrategiaBusquedaA();

        System.out.println(Arrays.toString(buscador3.soluciona(CuadradoMagico, h)));
    }
}
