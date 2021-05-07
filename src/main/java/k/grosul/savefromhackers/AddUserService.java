package k.grosul.savefromhackers;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
class AddUserService {
    
    @Autowired
    private UserRepository repository;

    public void register(User user) {
        repository.save(user);
    }

}
