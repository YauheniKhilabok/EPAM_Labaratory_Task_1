package com.epam.onlineshop.entity.users;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.Entity;

import java.io.Serializable;

/**
 * The User It is the class responsible for table users in database.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class User extends Entity implements Cloneable, Serializable {
    /**
     * Property - user name
     */
    private String nameUser;
    /**
     * Property - user email
     */
    private String email;
    /**
     * Property - phone user
     */
    private String phone;
    /**
     * Property - address user
     */
    private String address;
    /**
     * Property - login
     */
    private String login;
    /**
     * Property - password
     */
    private String password;
    /**
     * Property - role
     */
    private String role;
    /**
     * Property - status
     */
    private String status;
    /**
     * Property - avatar
     */
    private String avatar;

    /**
     * Create a new empty object
     *
     * @see User#User(int, String, String, String, String, String, String, String, String, String)
     */
    public User() {
        super();
        this.nameUser = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.email = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.phone = DefaultValueConstants.DEFAULT_USER_PHONE;
        this.address = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.login = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.password = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.role = DefaultValueConstants.DEFAULT_USER_ROLE;
        this.status = DefaultValueConstants.DEFAULT_USER_STATUS;
        this.avatar = DefaultValueConstants.DEFAULT_USER_AVATAR;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param idUser   unique id for user
     * @param nameUser user name
     * @param email    user email
     * @param phone    phone user
     * @param address  address user
     * @param login    login
     * @param password password
     * @param role     role
     * @param status   status
     * @param avatar   avatar
     * @see User#User()
     */
    public User(int idUser, String nameUser, String email, String phone, String address, String login,
                String password, String role, String status, String avatar) {
        super(idUser);
        this.nameUser = nameUser;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
    }

    /**
     * Method to get the value field {@link User#nameUser}
     *
     * @return Return nameUser
     */
    public String getNameUser() {
        return nameUser;
    }

    /**
     * @param nameUser new user name
     */
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    /**
     * Method to get the value field {@link User#email}
     *
     * @return Return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get the value field {@link User#phone}
     *
     * @return Return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone new phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Method to get the value field {@link User#address}
     *
     * @return Return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method to get the value field {@link User#login}
     *
     * @return Return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Method to get the value field {@link User#password}
     *
     * @return Return nameUser
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to get the value field {@link User#role}
     *
     * @return Return role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method to get the value field {@link User#status}
     *
     * @return Return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Method to get the value field {@link User#avatar}
     *
     * @return Return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar new avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " Username: " + this.nameUser + '\n' + " Email: " + this.email + '\n' +
                " Phone: " + this.phone + '\n' + " Address: " + this.address + '\n' + " Login: " + this.login + '\n' +
                " Password: " + this.password + '\n' + " Role: " + this.role + '\n' + " Status: " + this.status + '\n'
                + " Avatar: " + this.avatar + '\n';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj object to be checked for equivalence
     * @return return true if objects is equal and false in otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof User) {
            User temp = (User) obj;
            return this.getId() == temp.getId() &&
                    this.nameUser.equals(temp.nameUser) &&
                    this.email.equals(temp.email) &&
                    this.phone.equals(temp.phone) &&
                    this.address.equals(temp.address) &&
                    this.login.equals(temp.login) &&
                    this.password.equals(temp.password) &&
                    this.role.equals(temp.role) &&
                    this.status.equals(temp.status) &&
                    this.avatar.equals(temp.avatar);
        } else return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return hash code of object
     */
    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
