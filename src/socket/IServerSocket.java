package socket;

import java.io.IOException;
import java.net.Socket;

public interface IServerSocket {
    /**
     * 启动服务端并等待请求
     * @param port 需要监听的端口
     */
    void start(int port);

    /**
     * 关闭服务端并打断所有线程
     */
    void stop();

    /**
     * 打开一个持久化线程用于连接
     * @param socket 连接用套字节
     */
    void connect(Socket socket) throws IOException;

    /**
     * 检验一个socket是否正常链接
     * @param s 要检验的socket
     * @return 是否正常链接
     */
    boolean IsSocketConnected(Socket s);
}
