package com.wellsfargo.stockmarket.loginsignup.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
   
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    
    @Column(name="isadmin")
    private boolean isAdmin;
    
    @Id
    @Column(name="username")
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @Column(name="email")
    private String email;
    
    @Column(name="verified")
    private boolean verified=false;
    
    public User() {
    	
    }
    public User(String userName,String password, String email,boolean isAdmin) {
    	super();
    	this.userName = userName;
    	this.password = password;
    	this.email = email;
    	this.isAdmin = isAdmin;
    	
    }

    public void setUserId() {
        this.userId++;
    }

    public void getUserId(){
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
