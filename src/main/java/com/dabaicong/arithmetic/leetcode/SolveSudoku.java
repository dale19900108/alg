package com.dabaicong.arithmetic.leetcode;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * 空白格用'.'表示
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 第一种解法，暴力求解，循环计算，直到计算出所有结果
 */
public class SolveSudoku {

    public void traceBacking(char[][] board) {

        // 直到全部计算出solve结果
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    // 如果不是 . 跳过
                    if (board[i][j] != '.') {
                        continue;
                    }
                }
            }
    }

    // 递归方式实现
    public boolean solveSudoku1(char[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {
                    // (i, j) 这个位置放k是否合适
                    if (validate(board, i, j, k)) {
                        board[i][j] = k;
                        if (solveSudoku1(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 计算第ij个元素
     * <p>
     * 这里需要注意，如果是存在多个解，
     * 则表示该位置还无法得出答案
     *
     * @param board board
     * @param i     行
     * @param j     列
     * @return 该位置的返回值。无解返回.
     */
    private char solve(char[][] board, int i, int j) {
        int solveCount = 0;
        char solveValue = '.';
        for (int k = 1; k <= 9; k++) {
            if (validate(board, i, j, (char) k)) {
                solveCount++;
                solveValue = (char) k;
            }
        }
        if (solveCount == 1) {
            return solveValue;
        }
        return '.';
    }


    /**
     * 验证 横竖行
     *
     * @param board 数独
     * @param i     数字所在的行
     * @param j     数字所在的列
     * @param value 待填入值
     * @return 该数字是否满足
     */
    private boolean validate(char[][] board, int i, int j, char value) {
        // 验证行列
        for (int k = 0; k < 9; k++) {
            // 行验证
            if (value == board[i][k]) {
                return false;
            }
            // 列验证
            if (value == board[k][j]) {
                return false;
            }
        }

        // 验证方格
        // 找到是第几个方格 ，先确定大方格坐标
        int squareLine = i / 3;
        int squareRow = j / 3;

        for (int k = 0; k < 3; k++) {
            for (int t = 0; t < 3; t++) {
                // 计算方格的横纵坐标
                int linePos = squareLine * 3 + k;
                int rowPos = squareRow * 3 + t;
                if (value == board[linePos][rowPos]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        SolveSudoku sudoku = new SolveSudoku();
        //sudoku.solveSudoku(board);
        sudoku.solveSudoku1(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
