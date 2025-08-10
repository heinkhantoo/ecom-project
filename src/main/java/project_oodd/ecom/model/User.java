package project_oodd.ecom.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import project_oodd.ecom.security.Encoder;
import project_oodd.ecom.util.Role;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long uid;
	
	@Column(name = "code", length = 36)
	private String userCode;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, unique = true, nullable = false)
	private String email;

	@Column(name = "phNumber", length = 20)
	private String phno;

	@Column(nullable = false)
	@Size(min = 8, message = "Password must be at least 8 characters long!")
	private String password;

	@Transient
	private String confirmPassword;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Role role;

	private boolean active;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public boolean passwordMatch() {
		return password != null && password.equals(confirmPassword);
	}
	
	public boolean correctPassword(String userPassword, String password) {
		return new Encoder().passwordEncoder(12).matches(userPassword, password);
	}

	@PrePersist
	public void onCreate() {
		this.userCode = UUID.randomUUID().toString().replace("-", "");
		if (phno == null)
			this.phno = "";
		if (role == null) this.role = Role.USER;
		this.confirmPassword = "";
		this.active = true;
		this.password = new Encoder().passwordEncoder(12).encode(password);
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}

	@PreUpdate
	public void onUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
