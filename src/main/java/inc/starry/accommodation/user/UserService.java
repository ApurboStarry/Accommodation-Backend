package inc.starry.accommodation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> login(User user) {
        Optional<User> userOptional = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!userOptional.isPresent()) {
            return ResponseEntity.status(400).body("Invalid email or password");
        }

        return ResponseEntity.status(200).body(userOptional.get());
    }

    public ResponseEntity<?> registerUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            return ResponseEntity.status(400).body("Email already taken");
        }

        userRepository.save(user);
        return ResponseEntity.status(200).body("");
    }
}
