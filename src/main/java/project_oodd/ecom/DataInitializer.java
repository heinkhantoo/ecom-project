package project_oodd.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import project_oodd.ecom.model.User;
import project_oodd.ecom.repository.UserRepository;
import project_oodd.ecom.util.Role;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.findByEmailIgnoreCase("root@example.com").isEmpty()) {
            User rootUser = new User();
            rootUser.setName("Root Admin");
            rootUser.setEmail("root@example.com");
            rootUser.setPassword("root1234");
            rootUser.setRole(Role.ADMIN);

            userRepository.save(rootUser);
            System.out.println("✅ Root user created.");
        } else {
            System.out.println("ℹ️ Root user already exists. Skipping creation.");
        }
    }
}
