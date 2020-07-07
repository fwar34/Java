import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.game.lll.thrift.LoginService;
import com.game.lll.thrift.Request;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        TTransport transport = null;
        try {
            //创建TTransport
            transport = new TSocket("localhost", 8888);
            //创建TProtocal，协议要与服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            //创建client
            LoginService.Client client = new LoginService.Client(protocol);
            transport.open(); //建立连接

            Request request = new Request().setUsername("Xiaoming").setPassword("123456");

            //client调用server方法
            System.out.println(client.doAction(request));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transport.close(); //请求结束，断开连接
        }
    }
}
