package random;

public class PrintNumbersInSpiral
{
    public static final int ROWS = 4;
    public static final int COLUMNS = 4;

    public static void main(String[] args) {
        int[][] array = new int[ROWS][COLUMNS];

        for(int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                array[i][j] = i * ROWS + j + 1;
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("************");
        System.out.println("Spiral");

        spiralPrint(ROWS, COLUMNS, array);
    }

    public static void spiralPrint(int rows, int columns, int[][] matrix){
        rows--;
        columns--;

        int row = 0;
        int column = 0;
        while(row <= rows && column <= columns){
            for(int i = column; i <= columns; ++i) {
                System.out.print(matrix[row][i]+ " ");
            }
            row++;

            for(int i = row; i <= rows; ++i) {
                System.out.print(matrix[i][columns] + " ");
            }
            columns--;

            for(int i = columns; i >= column; --i) {
                    System.out.print(matrix[rows][i] + " ");
            }
            rows--;


            for(int i = rows; i >= row; --i) {
                System.out.print(matrix[i][column] + " ");
            }
            column++;
        }
    }
}
