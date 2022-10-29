package com.seminario.dtos;

import java.io.Serializable;

import com.seminario.entitys.User;

public class AccessTokenDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private String username;
    private String rol;
    private String hospital;
    private Long idHospital;
    
	public AccessTokenDTO(String token, String username, String rol, String hospital, Long idHospital) {
		super();
		this.token = token;
		this.hospital = hospital;
		this.rol = rol;
		this.username = username;
		this.idHospital = idHospital;
	}

	public AccessTokenDTO(String access_token, String username, User user) {
		super();
		this.token = access_token;
		this.hospital = user.getHospital().getNombre();
		this.rol = user.getRol().getRol();
		this.username = username;
		this.idHospital = user.getIdHospital();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Long getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}
	
}