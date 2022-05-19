import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main (String[] args){
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};

        System.out.println(longestIncreasingPath(matrix));
    }

    public static int longestIncreasingPath(int[][] matrix) {
        //check the matrix is empty
        if (matrix == null || matrix.length == 0) return 0;

        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];

        int result = 0;
        for (int i = 0; i < row; i ++){
            for (int j=0; j < col; j++){
                if(dp[i][j] == 0){
                    dfs(matrix, i, j, dp, Integer.MIN_VALUE);
                    result = Math.max(result, dp[i][j]);

                }
            }
        }
        return result;
    }

    private static int dfs(int[][] matrix, int row, int col, int[][] dp, int prevNum){
        if (row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[0].length -1 ||
                matrix[row][col] <= prevNum) return 0;

        if(dp[row][col] != 0 ) return dp[row][col];

        int left = dfs(matrix, row, col-1, dp, matrix[row][col]);
        int right = dfs(matrix, row, col+1, dp, matrix[row][col]);
        int up = dfs(matrix, row-1, col, dp, matrix[row][col]);
        int down = dfs(matrix, row+1, col, dp, matrix[row][col]);

        dp[row][col] = Math.max(left, Math.max(right, Math.max(down, up)))+1;

        return dp[row][col];
    }
}
