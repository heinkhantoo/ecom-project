package project_oodd.ecom.util;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project_oodd.ecom.exception.AppException;
import project_oodd.ecom.model.User;
import project_oodd.ecom.repository.UserRepository;

@Component
public class Authentication extends OncePerRequestFilter {
	@Autowired
	private Jwt jwt;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = null;

		// Check Header
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}

//	        // Check Cookie
//	        if (token == null && request.getCookies() != null) {
//	            for (Cookie cookie : request.getCookies()) {
//	                if (cookie.getName().equals("jwt")) {
//	                    token = cookie.getValue();
//	                }
//	            }
//	        }

		if (token != null && jwt.isTokenValid(token)) {
			String userCode = jwt.getUserCode(token);
			User user = userRepository.findByUserCodeIgnoreCase(userCode)
					.orElseThrow(() -> new AppException("sth", 401));
			if (user != null) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
						List.of());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		filterChain.doFilter(request, response);
	}

}
