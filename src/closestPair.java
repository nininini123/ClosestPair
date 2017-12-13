import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class closestPair {
    private Point[] pointArray;
    int pointNum;

    public closestPair (String fileName) {
        loadPoints(fileName);
        System.out.println("sorting points according to x axis...");
        quickSortX(pointArray,0,pointNum-1);
        for (int i=0;i<pointNum;i++) {
            System.out.println(pointArray[i].getX()+ " " + pointArray[i].getY());
        }
        System.out.println("distance of closest pair is " + FindShortPairDC(0, pointNum-1) );
    }

    /***
     * find the shortest pair when there are fewer than or equal to 3 pair
     * @return distance of the closest pair
     */
    private double FindShortPair(int left,int right) {
        double min = Double.MAX_VALUE ;
        double temp;
        for(int i=left ; i <right; i++ ) {
            for (int j = i+1; j<= right; j++) {
                if ( ( temp = Point.getDistance(pointArray[i] ,pointArray[j]) ) < min ) {
                    min = temp;
                }
            }
        }
        return min;
    }
    private double FindShortPairDC(int left,int right)   //DC代表divide and conquer，分治。 left、right为元素下标
    {
        if (right - left <= 2) //子集中少于三个点为最小分治状态。如果三个点仍继续递归，会出现一边只有1个点的情况。
            return FindShortPair(left, right);
        int mid = (left+right)/2;
        double dL = FindShortPairDC(left, mid);
        double dR = FindShortPairDC(mid+1, right);
        double d =Math.min(dL, dR) ,temp;
        /* i 是左边的点 j是右边的点 */
        for (int i=mid; i >= left; i--)
        {
            if(pointArray[mid].getX() - pointArray[i].getX() > d) //横坐标之差
                break;
            for(int j=mid+1;i<=right;i++)
            {
                if(pointArray[j].getX() - pointArray[mid].getX() > d)
                    break;
                temp = Point.getDistance(pointArray[i],pointArray[j]) ;
                if(temp < d) {
                    d = temp;
                }
            }
        }
        return d;
    }

    /***
     * 从文件读入所有点的坐标
     * 文件格式:
     * Xi Yi \n (i=0,1,2,3,4...)
     * @param fileName file from where to load the points
     * @return the number of pairs
     */
    public int loadPoints(String fileName) {
        ArrayList<Point> points = new ArrayList<>();
        try {
            FileReader fin=new FileReader(fileName);
            Scanner scanner=new Scanner(fin);//注意这里的参数是FileReader类型的fin

             while (scanner.hasNextInt()) {
                 points.add(new Point(scanner.nextInt(), scanner.nextInt() ) ) ;
             }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        pointArray = new Point[points.size()] ;
        pointArray = (Point[]) points.toArray(pointArray);
        pointNum = points.size();
        System.out.println(pointNum + " points loaded");

        return pointNum;

    }

    public static void main(String[] args) {
        new closestPair("test.txt");
    }

    public void quickSortX(Point[] A,int p, int r) {
        if (p < r) {
            int q = partitionX(A,p,r);
            quickSortX(A, p, q-1);
            quickSortX(A, q+1, r);
        }
    }
    int partitionX(Point[] A,int p, int r) {
        int x = A[r].getX();
        int i = p-1;
        Point temp ;
        for (int j = p; j<r; j++) {
            if ( A[j].getX() <= x ) {
                i ++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
        A[i+1] = A[r];
        A[r] = temp;
        return i+1 ;
    }
}
