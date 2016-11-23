package com.epam.onlineshop.tag;

import com.epam.onlineshop.constants.WebConstants;

/**
 * The NotNullRole class responsible for initializing prompt for the user role.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class NotNullRole {
    public static String makeNotNull(Object ob) {
        String res;
        if (ob == null || ob.toString().isEmpty()) {
            res = WebConstants.GUEST_ROLE_VALUE;
        } else {
            res = ob.toString();
        }
        return res;
    }
}
