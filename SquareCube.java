public class IsSquare {
    /*
     * A 2 dimensional matrix is considered square if all of its rows have the same number of elements and 
     * the number of rows is equal to this number of elements. Write a method isSquare() that takes in a 
     * 2 dimensional integer matrix and returns the size if it is square. Otherwise it should return -1.
     * Remeber, for a matrix to be square it has to have dimensions of at least 2x2.
     * 
     * After doing this, write a method isCube() that determines if a 3 dimensional matrix consists of all square matrices
     * and the number of matrices in this 3 dimensional array is equal to the dimension of each of these matricies. This should
     * also return the dimension or -1. This function should call your isSquare function.
     * 
     * you can assume that inputs will be valid arrays
     */

    public static int isSquare(int[][] matrix) { //these methods must be static
        int dim = -1;
        for (int[] row : matrix) {
            if (dim != -1 && row.length != dim) {
                return -1;
            }
            dim = row.length;
        }
        if (matrix.length == dim && dim > 1) {
            return dim;
        }
        return -1;
    }

    public static int isCube(int [][][] Vspace) {
        int dim = -1;
        for (int[][] matrix : Vspace) {
            if (isSquare(matrix) == -1) {
                return -1;
            }
            if (dim != -1 && isSquare(matrix) != dim) {
                return -1;
            }
            dim = isSquare(matrix);
        }
        if (Vspace.length == dim && dim > 1) {
            return dim;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] square = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] notSquare = {{1,2,3,4},{5,6,7,8},{9,10}};
        System.out.println(isSquare(square));
        System.out.println(isSquare(notSquare));

        int[][][] cube = {{{1,2},{3,4}},{{5,6},{7,8}}};
        int[][][] notCube = {{{1,2},{3,3,4}},{{5,6},{7,8}}};
        int[][][] alsoNotCube = {{{1,2},{3,4}},{{5,6},{7,8}},{{9,10},{11,12}}};
        System.out.println(isCube(cube));
        System.out.println(isCube(notCube));
        System.out.println(isCube(alsoNotCube));
    }
}
