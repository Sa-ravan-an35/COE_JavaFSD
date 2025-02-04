class MatrixMultiplier extends Thread {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] result;
    private int row;

    public MatrixMultiplier(int[][] matrixA, int[][] matrixB, int[][] result, int row) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        int columnsB = matrixB[0].length;
        int columnsA = matrixA[0].length;
        for (int j = 0; j < columnsB; j++) {
            result[row][j] = 0;
            for (int k = 0; k < columnsA; k++) {
                result[row][j] += matrixA[row][k] * matrixB[k][j];
            }
        }
    }
}

public class MatrixMul{
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) throws InterruptedException {
        int rowsA = matrixA.length;
        int columnsB = matrixB[0].length;
        int[][] result = new int[rowsA][columnsB];
        
        Thread[] threads = new Thread[rowsA];
        for (int i = 0; i < rowsA; i++) {
            threads[i] = new MatrixMultiplier(matrixA, matrixB, result, i);
            threads[i].start();
        }
        
        for (int i = 0; i < rowsA; i++) {
            threads[i].join();
        }
        
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{2, 0}, {1, 2}};
        
        int[][] result = multiplyMatrices(matrixA, matrixB);
        
        System.out.println("Result of the multiplication:");
        for (int[] row : result) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}