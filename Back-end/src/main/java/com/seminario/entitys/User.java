package com.seminario.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_hospital", unique = true)
    private Long idHospital;
    
    @Column(name = "id_rol", unique = true)
    private Long idRol;
    
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_hospital", referencedColumnName = "id")
    private Hospital hospital;
    
    @ManyToOne
    @JoinColumn(name="id_rol", nullable=false)
    private UserRol rol;

    public User(String username, String password, String email, Long idHospital, Long idRol) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.idHospital = idHospital;
        this.idRol = idRol;
    }
    
	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public UserRol getRol() {
		return rol;
	}

	public void setRol(UserRol rol) {
		this.rol = rol;
	}
}
