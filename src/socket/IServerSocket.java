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
    void connect(Socket socket,int id) throws IOException;

    /**
     * 校验一个socket是否能使用，若能使用，返回一个空闲数组位。
     * 可能返回非正数的情况如下：
     * -3代表与客户端建立流超时；
     * -2代表客户端回应超时；
     * -1代表客户端回应内容错误；
     * 0代表数组已无空闲位；
     * @param socket 要检验的socket
     * @return 一个可用的数组位
     */
    int prepareConnect(Socket socket);
}
