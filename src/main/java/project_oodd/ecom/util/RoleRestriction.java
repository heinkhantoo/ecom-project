package project_oodd.ecom.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.User;

@Component
public class RoleRestriction {

	public static void restrictTo(User user, Role... allowedRoles) {
		List<Role> roles = Arrays.asList(allowedRoles);
		if (!roles.contains(user.getRole())) {
			throw new AppException("You don't have permission to perform this action", 403);
		}
	}
}
