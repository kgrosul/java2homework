package k.grosul.savefromhackers;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Service
class AddUserService {
    private UserAPI userAPI = new UserAPI();

    public void register(User user) {
        try {
            userAPI.AddUser(user);
        } catch (Exception e) {
            System.out.println("Can't add User");
        }
    }

}
