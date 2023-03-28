package study.reflection;

public class reflect {
    public int[][] a={{1,0,0,0,0}
                     ,{0,0,0,0,0}
                     ,{0,0,0,0,0}
                     ,{0,0,0,0,0}
                     ,{0,0,0,0,0}};
    public void tap(int x, int y){
        a[x][y]=a[x][y]+1;
        a[x][y+1]=a[x][y+1]+1;
        a[x][y-1]=a[x][y-1]+1;
        a[x+1][y]=a[x+1][y]+1;
        a[x-1][y]=a[x-1][y]+1;
    }
    public static void main(String[] args){
        int ti=1;
    }
}
