package com.epam.onlineshop.command.producers;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.ProducersRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ChangeProducerCommand class responsible
 * for update producer's data in database.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ChangeProducerCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ChangeProducerCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to update producer data.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int producerId = Integer.parseInt(request.getParameter(ParameterConstants.ID_PRODUCER_PARAMETER));
        String productionRegion = request.getParameter(ParameterConstants.PRODUCTION_REGION_PRODUCT_PARAMETER);
        String brand = request.getParameter(ParameterConstants.BRAND_PRODUCT_PARAMETER);
        try {
            ProducersRerouting.redirectAfterChangeProducer(request, response, producerId, productionRegion, brand);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
