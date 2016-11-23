package com.epam.onlineshop.command.locale;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.rerouting.LocaleRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ChangeLocaleCommand class responsible
 * for change locale in website.
 * This command is available for all roles: guest, user, admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ChangeLocaleCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ChangeLocaleCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to change locale.
     * Parameter localValue obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String localeValue = request.getParameter(WebConstants.LOCALE_ATTRIBUTE);
        try {
            LocaleRerouting.redirectAfterChangeLocale(request, response, localeValue);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
