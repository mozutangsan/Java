package study;

public class Test {
    public static void main(String[] args){
        String a= BCrypt.hashpw("114514",BCrypt.gensalt());
        System.out.println(a);
        System.out.println(BCrypt.checkpw("114514",a));
    }
}
