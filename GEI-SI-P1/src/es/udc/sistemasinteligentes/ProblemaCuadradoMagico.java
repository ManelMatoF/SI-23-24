package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Arrays;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{

    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);

    }

    public static class EstadoCuadrado extends Estado{
        private int[][] matriz;

        public EstadoCuadrado(int[][] matriz) {
            this.matriz = matriz;
        }

        public int[][] getMatriz() {
            return matriz;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int[] fila : matriz) {
                sb.append(Arrays.toString(fila)).append("\n");
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true; // Son la misma instancia
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false; // El objeto no es una instancia de EstadoCuadrado
            }

            EstadoCuadrado otroEstado = (EstadoCuadrado) obj;
            // Compara las matrices elemento por elemento
            return Arrays.deepEquals(this.matriz, otroEstado.matriz);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(matriz);
        }
    }

    public static class AccionCuadrado extends Accion{
        private int fila;
        private int columna;
        private int numero;

        public AccionCuadrado(int fila, int columna, int numero) {
            this.fila = fila;
            this.columna = columna;
            this.numero = numero;
        }

        @Override
        public String toString() {
            return "Insertar el número " + numero + " en la posición (" + fila + ", " + columna + ")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado estado = (EstadoCuadrado) es;
            int[][] matriz = estado.getMatriz();
            int N = matriz.length;

            // Verificar si la posición (fila, columna) es válida en la matriz
            if (fila < 0 || fila >= N || columna < 0 || columna >= N) {
                return false;
            }

            // Verificar si la posición ya está ocupada
            if (matriz[fila][columna] != 0) {
                return false;
            }

            // Verificar si el número es menor o igual a N^2
            if (numero <= 0 || numero > N * N) {
                return false;
            }

            // Verificar si el número no ha sido usado anteriormente en la matriz
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matriz[i][j] == numero) {
                        return false; // El número ya ha sido usado
                    }
                }
            }
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado estado = (EstadoCuadrado) es;
            int[][] matrizOriginal = estado.getMatriz();
            int N = matrizOriginal.length;

            int[][] matrizNueva = new int[N][N];
            for (int i = 0; i < N; i++) {
                matrizNueva[i] = Arrays.copyOf(matrizOriginal[i], N);
            }

            matrizNueva[fila][columna] = numero;

            // Crear un nuevo EstadoCuadrado con la matriz actualizada
            return new EstadoCuadrado(matrizNueva);
        }
    }
    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadrado estado = (EstadoCuadrado) es;
        int[][] matriz = estado.getMatriz();
        int N = matriz.length;

        int sumaMagica = (N * (N * N + 1)) / 2;

        // Verificar si la suma de cada fila es igual a la suma mágica
        for (int i = 0; i < N; i++) {
            int sumaFila = 0;
            for (int j = 0; j < N; j++) {
                sumaFila += matriz[i][j];
            }
            if (sumaFila != sumaMagica) {
                return false;
            }
        }

        // Verificar si la suma de cada columna es igual a la suma mágica
        for (int j = 0; j < N; j++) {
            int sumaColumna = 0;
            for (int i = 0; i < N; i++) {
                sumaColumna += matriz[i][j];
            }
            if (sumaColumna != sumaMagica) {
                return false;
            }
        }

        // Verificar si la suma de la diagonal principal es igual a la suma mágica
        int sumaDiagonalPrincipal = 0;
        for (int i = 0; i < N; i++) {
            sumaDiagonalPrincipal += matriz[i][i];
        }
        if (sumaDiagonalPrincipal != sumaMagica) {
            return false;
        }

        // Verificar si la suma de la diagonal secundaria es igual a la suma mágica
        int sumaDiagonalSecundaria = 0;
        for (int i = 0; i < N; i++) {
            sumaDiagonalSecundaria += matriz[i][N - 1 - i];
        }
        if (sumaDiagonalSecundaria != sumaMagica) {
            return false;
        }

        return true;
    }

    @Override
    public Accion[] acciones(Estado es) {
        EstadoCuadrado estado = (EstadoCuadrado) es;
        int N = estado.getMatriz().length;
        ArrayList<AccionCuadrado> acciones = new ArrayList<>();

        // Generar todas las acciones posibles para el estado dado
        for (int fila = 0; fila < N; fila++) {
            for (int columna = 0; columna < N; columna++) {
                for (int numero = 1; numero <= N * N; numero++) {
                    AccionCuadrado accion = new AccionCuadrado(fila, columna, numero);
                    if (accion.esAplicable(estado)) {
                        acciones.add(accion);
                    }
                }
            }
        }
        return acciones.toArray(new AccionCuadrado[0]);
    }
}
