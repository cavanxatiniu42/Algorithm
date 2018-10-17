import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation extends WeightedQuickUnionUF {
    boolean isOpen = false;
   int n;
    boolean[][] grid;
    public Percolation(int n){
        super(n*n);
        this.n = n;
        grid = new boolean[n][n];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
    }

    public boolean isOpen(int row, int col){
        return grid[row][col];
    }
    public void open(int row, int col){
       if (isOpen(row,col) == false){
           grid[row][col]=true;
       }

        if (row ==0 || row == (n-1)){
            if (col==0){
                if (isOpen(row, col+1)){
                    union(row*n+col,row*n+col+1);
                }
                if (row==0) {
                    if (isOpen(row + 1, col)) {
                        union(row * n + col, row * n + n + col);
                    }
                }
                if (row ==n-1){
                    if (isOpen(row - 1, col)) {
                        union(row * n + col, row * n - n + col);
                    }
                }
            } else if (col==n-1){
                if (isOpen(row, col-1)){
                    union(row*n+col, row*n+col-1);
                }
                if (row==0) {
                    if (isOpen(row + 1, col)) {
                        union(row * n + col, n * (row + 1) + col);
                    }
                }
                if (row==n-1){
                    if (isOpen(row - 1, col)) {
                        union(row * n + col, n * (row - 1) + col);
                    }
                }
            } else {
                if (isOpen(row,col+1)){
                    union(row*n+col, row*n+col+1);
                }
                if (isOpen(row,col-1)){
                    union(row*n+col, row*n+col-1);
                }
                if (row==0){
                     if (isOpen(row+1,col)) {
                         union(row * n + col, n * (row + 1) + col);
                     }
                }
                if (row==n-1){
                    if (isOpen(row-1, col)){
                   union(row*n+(col), (row -2)*n+(col));
                    }
                }
            }

        }
//       if (row ==0){
//           if (col==0){
//               if (isOpen(row, col+1)){
//                   union(col+1,col+2);
//               }
//               if (isOpen(row+1, col)){
//                   union(col+1, n+col+1);
//                   System.out.println((col+1)+","+(n+col+1));
//               }
//           } else if (col==n-1){
//               if (isOpen(row, col-1)){
//                   union(col+1, col-1);
//               }
//               if (isOpen(row+1, col)){
//                   union(col+1, n*(row+1)+col+1);
//               }
//           } else {
//               if (isOpen(row,col+1)){
//                   union(col+1, col+2);
//               }
//               if (isOpen(row,col-1)){
//                   union(col+1, col);
//               }
//               if (isOpen(row+1,col)){
//                   union(col+1, n*(row+1)+col+1);
//               }
//           }
//
//       }
//       if (row ==n-1){
//           if (col==0){
//               if (isOpen(row,col+1)){
//                   union((n-1)*n+(col+1), (n-1)*n+(col+2));
//               }
//               if (isOpen(row-1,col)){
//                   union((n-1)*n+(col+1), (n-2)*n+(col+1));
//               }
//           } else if (col ==n-1){
//               if (isOpen(row,col-1)){
//                   union((n-1)*n+(col+1), (n-1)*n+col);
//               }
//               if (isOpen(row-1,col)){
//                   union((n-1)*n+(col+1), (n-2)*n+(col+1));
//               }
//           } else {
//               if (isOpen(row,col-1)){
//                   union((n-1)*n+(col+1),(n-1)*n+col );
//               }
//               if (isOpen(row, col+1)){
//                   union((n-1)*n+(col+1), (n-1)*n+(col+2));
//               }
//               if (isOpen(row-1, col)){
//                   union((n-1)*n+(col+1), (n-2)*n+(col+1));
//               }
//           }
//       }
       if (row!=0 && row!=(n-1)){
            if (col==0){
                if (isOpen(row,col+1)){
                    union(row*n , row*n+1);
                }
                if (isOpen(row+1, col)){
                    union(row*n, (row+1)*n);
                }
                if (isOpen(row-1, col)){
                    union(row*n, (row-1)*n);
                }
            }
             else if (col ==n-1){
                if (isOpen(row, col-1)){
                    union(row*n+col, row*n+col-1);
                }
                if (isOpen(row+1, col)){
                    union(row*n+col,(row+1)*n+col );
                }
                if (isOpen(row-1, col)){
                    union(row*n+col, (row-1)*n+col);
                }
            }
            else {
                if (isOpen(row, col+1)){
                    union(row*n+col, row*n+col+1);
                }
                if (isOpen(row, col-1)){
                    union(row*n+col, row*n+col-1);
                }
                if (isOpen(row-1, col)){
                    union(row*n+col, (row-1)*n+col);
                }
                if (isOpen(row+1, col)){
                    union(row*n+col, (row+1)*n+col);
                }
            }

       }


    }

    public boolean isFull(int row, int col){
        for (int i = 0; i < n; i++) {
            if (connected(n*row+col, n*row+i)){
                return true;
            }
        }
        return false;
    }
    public int numberOfOpenSite(){
        int count =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                if (grid[i][j]==true){
                    count++;
                }
            }
        }
        return count;
    }
    public boolean percolates(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (connected(i, (n-1)*n+j)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
       Percolation percolation = new Percolation(20);
       percolation.open(0,0);
       percolation.open(1,0);
       percolation.open(2,0);
       percolation.open(3,0);
       percolation.open(4,0);
       percolation.open(5,0);
       percolation.open(6,0);
//       percolation.open(2,2);
        System.out.println(percolation.isFull(6,0));
        System.out.println(percolation.numberOfOpenSite());

    }

}
