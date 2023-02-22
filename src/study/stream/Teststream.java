package study.stream;

import study.mysql.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Teststream extends Thread{
    public static void main(String[] ars) throws IOException {
        Teststream a=new Teststream();
        a.start();
    }

    @Override
    public synchronized void start() {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'q' 键退出。");
        // 读取字符
        do {
            try {
                c = (char) br.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(c);
        } while (c != 'q');
    }
}
