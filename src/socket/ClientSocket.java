package socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args) {
        String msg;
        Scanner sca=new Scanner(System.in);
        try {
            //创建一个Socket，跟服务器的8080端口链接
            Socket socket = new Socket("127.0.0.1",8080);
            //使用PrintWriter和BufferedReader进行读写数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据
            msg=sca.nextLine();
            pw.println(msg);
            pw.flush();
            //接收数据
            String line = is.readLine();
            System.out.println("received from server:" + line);
            //关闭资源
            pw.close();
            is.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
