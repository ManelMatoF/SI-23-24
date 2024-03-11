package es.udc.sistemasinteligentes;


public class HeuristicaCuadrado extends Heuristica {
    private int rangoMinimo;
    private int rangoMaximo;

    public HeuristicaCuadrado(int rangoMinimo, int rangoMaximo) {
        this.rangoMinimo = rangoMinimo;
        this.rangoMaximo = rangoMaximo;
    }

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadrado estado = (ProblemaCuadradoMagico.EstadoCuadrado) e;
        int result = 0;
        int n = estado.getMatriz().length;
        int[][] matriz = estado.getMatriz();
        int maxN = (n * ((n * n) + 1)) / 2;

        int sumd1 = 0,sumd2=0;
        boolean sized1 = true, sized2 = true;
        // recorre las diagonales
        for (int i = 0; i < n; i++) {
            sumd1 += matriz[i][i];
            if (matriz[i][i] == 0) sized1 = false;
            sumd2 += matriz[i][n-1-i];
            if (matriz[i][n-1-i] == 0) sized2 = false;
        }

        result += score(sumd1, maxN, sized1);
        result += score(sumd2, maxN, sized2);

        // recorre las filas y columnas
        for (int i = 0; i < n; i++) {
            int rowSum = 0, colSum = 0;
            boolean sizeRow = true, sizedCol = true;
            for (int j = 0; j < n; j++) {
                rowSum += matriz[i][j];
                if (matriz[i][j] == 0) sizeRow = false;
                colSum += matriz[j][i];
                if (matriz[j][i] == 0) sizedCol = false;
            }
            result += score(rowSum, maxN, sizeRow);
            result += score(colSum, maxN, sizedCol);
        }

        return result;
    }

    /**
     * Calcula la puntuacion de una fila,columna o diagonal
     * @param sum suma de la fila,columna o diagonal
     * @param maxN numero maximo que puede tener
     * @param complete si esta completa o no
     * @return puntuacion asignada a la fila, columna o diagonal
     */
    private int score(int sum, int maxN, boolean complete){
        if(sum == maxN && complete) return 0;
        else if(sum < maxN && !complete) return 1;
        else return 1000;
    }

}

