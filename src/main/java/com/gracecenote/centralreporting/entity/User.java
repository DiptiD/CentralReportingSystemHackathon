package com.gracecenote.centralreporting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;

	@Column(updatable = false, nullable = false)
	private String username;

	private String password;

	private String email;

	private boolean activated;

	@Column(name = "activationkey")
	private String activationKey;

	@Column(name = "resetpasswordkey")
	private String resetPasswordKey;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "is_externaluser", nullable = false)
	private boolean isExternalUser;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "is_admin", nullable = false)
	private boolean isAdmin;

	@Column(name = "gracenote_id")
	private String gracenoteId;

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

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetPasswordKey() {
		return resetPasswordKey;
	}

	public void setResetPasswordKey(String resetPasswordKey) {
		this.resetPasswordKey = resetPasswordKey;
	}

	public boolean isExternalUser() {
		return isExternalUser;
	}

	public void setIsExternalUser(boolean isExternalUser) {
		this.isExternalUser = isExternalUser;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGracenoteId() {
		return gracenoteId;
	}

	public void setGracenoteId(String gracenoteId) {
		this.gracenoteId = gracenoteId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (!username.equals(user.username))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		return "User{" + "user_id=" + user_id + ", username='" + username + '\'' + ", password='" + password + '\''
				+ ", email='" + email + '\'' + ", activated=" + activated + ", activationKey='" + activationKey + '\''
				+ ", resetPasswordKey='" + resetPasswordKey + '\'' + ", firstName='" + firstName + '\'' + ", lastName='"
				+ lastName + '\'' + ", isExternalUser=" + isExternalUser + ", isDeleted=" + isDeleted + ", isAdmin="
				+ isAdmin + '}';
	}
}
