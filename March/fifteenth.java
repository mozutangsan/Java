package March;
import java.util.Scanner;
public class fifteenth {
public static void main(String[] args){
Scanner sca=new Scanner(System.in);
int a=sca.nextInt();
for(int b=a;b>0;b--){
  for(int c=b;c>1;c--){
    System.out.print(" ");
  }
  System.out.print("*");
  if(b<a){
    for(int c=(a-b)*2;c>1;c--){
      System.out.print(" ");
    }
    System.out.print("*");
  }
  System.out.println();
}
for(int b=2;b<a+1;b++){
  for(int c=b;c>1;c--){
    System.out.print(" ");
  }
  System.out.print("*");
  if(b<a){
    for(int c=(a-b)*2;c>1;c--){
      System.out.print(" ");
    }
    System.out.print("*");
  }
  System.out.println();
}
}
}
