package com.seminario.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.seminario.config.Consts.*;

public class UserDTO {

    @NotNull(message = USERNAME_NOT_NULL)
    @NotEmpty(message = USERNAME_NOT_EMPTY)
    @Size(min = 4, message = USERNAME_INVALID_SIZE)
    private String username;

    @NotNull(message = PASSWORD_NOT_NULL)
    @NotEmpty(message = PASSWORD_NOT_EMPTY)
    @Size(min = 4, message = PASSWORD_INVALID_SIZE)
    private String password;

    @Email(message = EMAIL_INVALID)
    @NotNull(message = EMAIL_NOT_NULL)
    @NotEmpty(message = EMAIL_NOT_EMPTY)
    private String mail;

    @NotNull(message = HOSPITAL_NOT_NULL)
    @NotEmpty(message = HOSPITAL_NOT_EMPTY)
    private String hospital;
    
    @NotNull(message = HOSPITAL_NOT_NULL)
    @NotEmpty(message = HOSPITAL_NOT_EMPTY)
    private String rol;
    
	public UserDTO(
			@NotNull(message = "Username may not be null") @NotEmpty(message = "Username may not be empty") @Size(min = 4, message = "Invalid username size") String username,
			@NotNull(message = "Password may not be null") @NotEmpty(message = "Password may not be empty") @Size(min = 4, message = "Invalid password size") String password,
			@Email(message = "Invalid mail") @NotNull(message = "Email may not be null") @NotEmpty(message = "Email may not be empty") String mail,
			@NotNull(message = "Hospital may not be null") @NotEmpty(message = "Hospital may not be empty") String hospital,
			@NotNull(message = "Rol may not be null") @NotEmpty(message = "Rol may not be empty") String rol) {
		super();
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.hospital = hospital;
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

    
}
