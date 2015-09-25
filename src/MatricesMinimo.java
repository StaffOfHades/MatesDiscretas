/**
 * Default Template. Information about thus class should go here
 * Created by mauriciog on 9/20/15 for MatematicasDiscretas
 */
public class MatricesMinimo {

    public static void main(String[] args) {
        calcularMinimoMultiplicaciones(new int[]{30, 35, 15, 5, 10, 20, 25});
    }

    public static void calcularMinimoMultiplicaciones(int[] ladosMatrizes) {
        if (ladosMatrizes.length < 1)
            return;

        // Siempre habra un lado mas dado del total de las matrizes
        int tamanoLados = ladosMatrizes.length;
        int tamañoArreglo = (tamanoLados) * ( tamanoLados + 1 ) / 2;

        int[] numeroMinimo = new int[tamañoArreglo];
        int[] recurrencias = new int[2];

        for (int i = 0; i < tamañoArreglo; i++) {
            if (i < tamanoLados)
                numeroMinimo[i] = ladosMatrizes[i];
            else if (i > tamanoLados * 2 - 2)
                numeroMinimo[i]= Integer.MAX_VALUE;

        }

        System.out.println("Tamano del Arreglo Principal: " + tamañoArreglo);

        sacarMinimo(numeroMinimo, recurrencias, tamanoLados, 0, tamanoLados - 1);

        for (int i = 0; i < numeroMinimo.length; i++) {
            System.out.println("Indice " + (i < 10 ? "0" : "") + i + ": " + numeroMinimo[i]);
        }

        // Numero de recurrencias = 2^(tamañosLados - 1) - 1
        System.out.println("N: " + tamanoLados);
        System.out.println("Recurrencias de entrada: " + recurrencias[0]);
        System.out.println("Recurrencias de entrada es igual a 2^(n-2)");
        System.out.println("Recurrencias solo de trabajo: " + recurrencias[1]);
        System.out.println("Recurrencias solo de trabajo es igual a (n-2)(n-1)/2");
    }

    private static void sacarMinimo(int[] numeroMinimo, int[] recurrencias, int tamanoLados, int inizio, int end) {

        final int tamano = end - inizio;

        recurrencias[0]++;

        if ( !(tamano > 1) ) {
            return;
        }

        int q;
        int posicionI, posicionE;

        int indice = inizio;
        for (int j = 0; j < tamano; j++)
            indice += tamanoLados - j;

        if (numeroMinimo[indice] < Integer.MAX_VALUE)
            return;

        recurrencias[1]++;

        for (int i = 0; i <= (tamanoLados - tamano + 1); i++) {
            sacarMinimo(numeroMinimo, recurrencias, tamanoLados, inizio + i, end - 1 + i);
            if (i > 0)
                break;
        }

        for (int i = 0; i < tamano - 1; i++) {
            posicionI = inizio;
            posicionE = inizio + i + 1;

            System.out.println("Indice " + indice);

            for (int j = 0; j < i + 1; j++)
                posicionI += tamanoLados - j;

            for (int j = 0; j < tamano - i - 1; j++)
                posicionE += tamanoLados - j;

            q = numeroMinimo[posicionI] + numeroMinimo[posicionE] +
                    numeroMinimo[inizio]*numeroMinimo[inizio + i + 1]*numeroMinimo[end];

            System.out.println("Q es " + q + ", la suma de el minimo en " +
                    posicionI + " con el minimo en " + posicionE +
                    " y la multiplicacion de los numeros que estan en los puntos "
                    + inizio +  ", " + (inizio + i + 1) + ", " + end);

            if (q < numeroMinimo[indice]) {
                numeroMinimo[indice] = q;
                System.out.println("Guardando el valor de q " + q + " en " + indice);
            }

        }

    }

}
