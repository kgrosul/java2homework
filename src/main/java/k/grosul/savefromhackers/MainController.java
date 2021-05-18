package k.grosul.savefromhackers;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allUsers")
    @ResponseBody
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }

    @GetMapping("/addUser")
    public ResponseEntity addUser(@RequestParam(name="name", required=true) String name,
                          @RequestParam(name="birth", required=true) String birth,
                          @RequestParam(name="passportNumber", required=true) String passportNumber,
                          @RequestParam(name="passportGiven", required=true) String passportGiven,
                          @RequestParam(name="passportRegistration", required=true) String passportRegistration
    ) {

        User newUser = new User(name.replace('$', ' '),
                birth.replace('$', ' '),
                passportNumber.replace('$', ' '),
                passportGiven.replace('$', ' '),
                passportRegistration.replace('$', ' '));
        return ResponseEntity.ok(userRepository.save(newUser));
    }


}
