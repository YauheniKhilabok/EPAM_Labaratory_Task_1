package com.epam.onlineshop.tag;

import com.epam.onlineshop.constants.MessageConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.constants.WebConstants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The HelloTag class responsible for the implementation of a custom tag,
 * which performs the operation on greeting user after login.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
@SuppressWarnings(WebConstants.SERIAL)
public class HelloTag extends TagSupport {
    /**
     * Property - username
     */
    private String nameUser;
    /**
     * Property - role of user on website
     */
    private String role;
    private static final String OPEN_TAG_P = "<p>";
    private static final String CLOSE_TAG_P = "</p>";

    /**
     * @param nameUser new username
     */
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    /**
     * @param role new role of user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method performs user greeting on the basis of their role
     *
     * @return SKIP_BODY
     * @throws JspException A generic exception known to the JSP engine;
     *                      uncaught JspExceptions will result in an invocation of the error page machinery.
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            String greeting;
            if (role.equals(WebConstants.USER_ROLE_VALUE)) {
                greeting = MessageConstants.FIRST_PART_WELCOME_MESSAGE + nameUser + SymbolConstants.EXCLAMATION_POINT +
                        OPEN_TAG_P + MessageConstants.SECOND_PART_WELCOME_MESSAGE + CLOSE_TAG_P;
            } else {
                greeting = MessageConstants.FIRST_ADMIN_WELCOME_MESSAGE + nameUser + SymbolConstants.EXCLAMATION_POINT +
                        OPEN_TAG_P + MessageConstants.SECOND_ADMIN_WELCOME_MESSAGE + CLOSE_TAG_P;
            }
            pageContext.getOut().write(greeting);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
