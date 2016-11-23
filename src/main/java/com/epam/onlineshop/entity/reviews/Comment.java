package com.epam.onlineshop.entity.reviews;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.Entity;

import java.io.Serializable;

/**
 * The Comment It is the class responsible for table reviews in database.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Comment extends Entity implements Cloneable, Serializable {
    /**
     * Property - id of user who make comment
     */
    private int userId;
    /**
     * Property - name of user who make comment
     */
    private String userName;
    /**
     * Property - user avatar
     */
    private String userAvatar;
    /**
     * Property - message
     */
    private String message;
    /**
     * Property - sending time
     */
    private String sendingTime;

    /**
     * Create a new empty object
     *
     * @see Comment#Comment(int, int, String, String, String, String)
     */
    public Comment() {
        super();
        this.userId = DefaultValueConstants.DEFAULT_ID;
        this.userName = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.userAvatar = DefaultValueConstants.DEFAULT_USER_AVATAR;
        this.message = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.sendingTime = DefaultValueConstants.DEFAULT_EMPTY_STRING;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param commentId   unique id of entity
     * @param userId      id of user
     * @param userName    name of user
     * @param userAvatar  avatar of user
     * @param message     massage
     * @param sendingTime sending time
     * @see Comment#Comment()
     */
    public Comment(int commentId, int userId, String userName, String userAvatar, String message, String sendingTime) {
        super(commentId);
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.message = message;
        this.sendingTime = sendingTime;
    }

    /**
     * Method to get the value field {@link Comment#userId}
     *
     * @return Return user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId new user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Method to get the value field {@link Comment#userName}
     *
     * @return Return user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Method to get the value field {@link Comment#userAvatar}
     *
     * @return Return user avatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * @param userAvatar new user avatar
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * Method to get the value field {@link Comment#message}
     *
     * @return Return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method to get the value field {@link Comment#sendingTime}
     *
     * @return Return sending time
     */
    public String getSendingTime() {
        return sendingTime;
    }

    /**
     * @param sendingTime new sending time
     */
    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Comment clone() throws CloneNotSupportedException {
        return (Comment) super.clone();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " User ID: " + this.userId + '\n' + " Username: " + this.userName + '\n' +
                " User avatar: " + this.userAvatar + '\n' + " Message: " + this.message + '\n' + " Sending time: " + this.sendingTime + '\n';
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
        if (obj instanceof Comment) {
            Comment temp = (Comment) obj;
            return this.getId() == temp.getId() &&
                    this.userId == temp.userId &&
                    this.userName.equals(temp.userName) &&
                    this.userAvatar.equals(temp.userAvatar) &&
                    this.message.equals(temp.message) &&
                    this.sendingTime.equals(temp.sendingTime);
        } else return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return hash code of object
     */
    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + sendingTime.hashCode();
        return result;
    }
}
