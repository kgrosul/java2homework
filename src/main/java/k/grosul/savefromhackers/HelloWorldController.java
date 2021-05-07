package k.grosul.savefromhackers;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @Autowired
    UserRepository repository;

    @GetMapping("/all")
    @ResponseBody
    public List<User> getAll() {
        return repository.findAll();
    }

    @GetMapping("/add")
    public ResponseEntity add(@RequestParam(name="name", required=false, defaultValue="") String name,
                          @RequestParam(name="birth", required=false, defaultValue="") String birth,
                          @RequestParam(name="passportNumber", required=false, defaultValue="") String passportNumber,
                          @RequestParam(name="passportGiven", required=false, defaultValue="") String passportGiven,
                          @RequestParam(name="passportRegistry", required=false, defaultValue="") String passportRegistry
    ) {
        User newUser = new User(name, birth, passportNumber, passportGiven, passportRegistry);
        return ResponseEntity.ok(repository.save(newUser));
    }

}
