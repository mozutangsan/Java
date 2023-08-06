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
     * @param id 由prepareConnect()生成的id
     */
    void connect(int id) throws IOException;

    /**
     * 校验一个socket是否能使用，若能使用，返回一个临时id，该id同时代表输入输出流和socket存入的数组位。
     * 返回-3代表与客户端建立流超时；
     * -2代表客户端回应超时；
     * -1代表客户端回应内容错误；
     * 0代表数组已无空闲位。
     * @param socket 要检验的socket
     * @return 一个临时id
     */
    int prepareConnect(Socket socket);

    /**
     * 关闭一个指定连接
     * @param id 要关闭连接的临时id
     * @param m 是否检查并打断线程，如果为手动调用则必须为true
     */
    void disconnect(int id,boolean m);
}
