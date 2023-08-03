package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServeSocket implements IServerSocket{
    private static ServerSocket server;
    private static boolean severState=false;
    private static Thread[] threads = {null};

    public static void main(String[] args) {
        ServeSocket s=new ServeSocket();
        s.start(8080);
        Scanner sca=new Scanner(System.in);
        if(sca.next().equalsIgnoreCase("close")){
            s.stop();
        }
    }

    @Override
    public void start(int port) {
        if(!severState) {
            severState = true;
            //启动服务器接受请求
            Thread st = new Thread(() -> {
                try {
                    server = new ServerSocket(port);
                    while (true) {
                        Socket socket = server.accept();
                        if (!severState) {
                            server.close();
                            threads[0] = null;
                        }
                        connect(socket);
                    }
                } catch (IOException e) {
                    try {
                        server.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            threads[0] = st;
            st.start();
        }
    }

    @Override
    public void stop(){
        severState = false;
        try {
            new Socket("127.0.0.1",8080).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Thread th:threads){
            if(th!=null){
                th.interrupt();
            }
        }
    }

    @Override
    public void connect(Socket socket) throws IOException {
        //接受请求后使用Socket进行通信，创建BufferedReader用于读取数据
        BufferedReader is= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //创建PrintWriter，用于发送数据
        PrintWriter pw =new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        String line = is.readLine();
        System.out.println("received frome client:" + line);
        pw.println("this data is from server");
        pw.flush();
        pw.close();
        is.close();
    }
}
