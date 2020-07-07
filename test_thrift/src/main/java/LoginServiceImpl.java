import org.apache.thrift.TException;

import com.game.lll.thrift.LoginService;
import com.game.lll.thrift.Request;
import com.game.lll.thrift.RequestException;

public class LoginServiceImpl implements LoginService.Iface {
    @Override
    public String doAction(Request request) throws RequestException, TException {
        System.out.println("hahaha");
        System.out.println("username:" + request.getUsername());
        System.out.println("password:" + request.getPassword());
        return request.getUsername() + request.getPassword();
    }
}
