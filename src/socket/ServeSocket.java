package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServeSocket implements IServerSocket{
    private static ServerSocket server;
    private static boolean severState=false;
    private static Thread[] threads =new Thread[100];
    private static Socket[] sockets =new Socket[100];
    private static BufferedReader[] bufferedReaders=new BufferedReader[100];
    private static PrintWriter[] printWriters=new PrintWriter[100];
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
                        //若线程已被打断，则关闭服务器
                        if (Thread.currentThread().isInterrupted()) {
                            server.close();
                            threads[0] = null;
                            break;
                        }
                        if (socket.isConnected()) {
                            int id=prepareConnect(socket);
                            if(id>0){
                                connect(id);
                            }
                        }
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
        //打断所有线程
        for (Thread th:threads){
            if(th!=null){
                th.interrupt();
            }
        }
        //发送请求解除线程阻塞
        try {
            new Socket("127.0.0.1",8080).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //关闭所有socket，引起所有通讯线程进入异常处理并自动清理
        for (Socket socket:sockets){
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
    @Override
    public int prepareConnect(Socket socket) {
        int a=-3;
        Thread thread=new Thread(() -> {
            try {
                Thread.sleep(10000);
                socket.close();
            } catch (InterruptedException e) {

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        try {
            BufferedReader is= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw =new PrintWriter(socket.getOutputStream());
            a++;
            pw.println("hello");
            pw.flush();
            String reply=is.readLine();
            a++;
            if (!reply.equalsIgnoreCase("hello")){
                return a;
            }
            a++;
            for (a=1;a<=100;a++){
                //使用id自动将输入输出流和socket存入数组
                if(threads[a]==null){
                    printWriters[a]=pw;
                    bufferedReaders[a]=is;
                    sockets[a]=socket;
                    thread.interrupt();
                    return a;
                }
            }
        } catch (IOException e) {
            return a;
        }
        return a;
    }
    @Override
    public void connect(int id){
        Thread th=new Thread(() -> {
            try {
                System.out.println(sockets[id].getLocalAddress().getHostAddress());
                while (true) {
                    String line = bufferedReaders[id].readLine();
                    //设置一秒延时，防止客户端发送过快影响服务器性能
                    Thread.sleep(1000);
                    System.out.println("received frome client:" + line);
                }
            }catch (IOException e){
                if (!Thread.currentThread().isInterrupted()) {
                    System.out.println(id+"断开链接");
                }
                disconnect(id,false);
                threads[id]=null;
            } catch (InterruptedException e) {
                System.out.println("000");
            }
        });
        //将线程存入数组并启动
        threads[id]=th;
        th.start();
    }
    @Override
    public void disconnect(int id,boolean m){
        try {
            printWriters[id].flush();
            printWriters[id].close();
            bufferedReaders[id].close();
            printWriters[id] =null;
            bufferedReaders[id]=null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(m){
            if(threads[id]!=null)
                if (!threads[id].isInterrupted())
                    threads[id].interrupt();
        }
    }
}
