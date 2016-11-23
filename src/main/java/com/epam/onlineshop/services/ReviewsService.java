package com.epam.onlineshop.services;

import com.epam.onlineshop.dao.CommentDAO;
import com.epam.onlineshop.entity.reviews.Comment;
import com.epam.onlineshop.entity.ObjectFactory;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The ReviewsService class responsible for performing operations on data in the reviews table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ReviewsService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ReviewsService.class);

    /**
     * @param userId  id of user who add comment
     * @param message message in comment
     */
    public static void addComment(String userId, String message) {
        ObjectFactory factory = new ObjectFactory();
        Comment comment = factory.createComment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setMessage(message);
        try {
            CommentDAO.getInstance().insert(comment);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param reviewId id of comment which will be deleted
     */
    public static void deleteComment(String reviewId) {
        int key = Integer.parseInt(reviewId);
        try {
            CommentDAO.getInstance().delete(key);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param reviewId id of review which will be updated
     * @param message  new message in comment
     */
    public static void changeComment(String reviewId, String message) {
        int key = Integer.parseInt(reviewId);
        ObjectFactory factory = new ObjectFactory();
        Comment comment = factory.createComment();
        comment.setMessage(message);
        try {
            CommentDAO.getInstance().change(key, comment);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @return list with comments
     */
    public static List<Comment> printReviews(int offset, int numberRecords) {
        List<Comment> comments = null;
        try {
            comments = CommentDAO.getInstance().getPartForPagination(offset, numberRecords);
        } catch (DaoException e) {
            log.error(e);
        }
        return comments;
    }

    /**
     * @return number of replies
     */
    public static int getNumberOfReplies() {
        int numberOfReplies = 0;
        try {
            numberOfReplies = CommentDAO.getInstance().getAll().size();
        } catch (DaoException e) {
            log.error(e);
        }
        return numberOfReplies;
    }

    /**
     * @return number of records
     */
    public static int getNumberRecords() {
        return CommentDAO.getInstance().getNumberRecords();
    }
}
