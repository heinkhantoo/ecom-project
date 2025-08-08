package project_oodd.ecom.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {
	
	public PasswordEncoder passwordEncoder(int salt) {
		return new BCryptPasswordEncoder(salt);
	}

}
