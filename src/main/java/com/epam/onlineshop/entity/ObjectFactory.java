package com.epam.onlineshop.entity;

import com.epam.onlineshop.entity.ordersproducts.OrderProduct;
import com.epam.onlineshop.entity.ordersproducts.OrdersProducts;
import com.epam.onlineshop.entity.orders.Order;
import com.epam.onlineshop.entity.orders.Orders;
import com.epam.onlineshop.entity.producers.Producer;
import com.epam.onlineshop.entity.producers.Producers;
import com.epam.onlineshop.entity.products.Product;
import com.epam.onlineshop.entity.products.Products;
import com.epam.onlineshop.entity.reviews.Comment;
import com.epam.onlineshop.entity.reviews.Reviews;
import com.epam.onlineshop.entity.statistics.Statistics;
import com.epam.onlineshop.entity.types.Type;
import com.epam.onlineshop.entity.types.Types;
import com.epam.onlineshop.entity.users.User;
import com.epam.onlineshop.entity.users.Users;

/**
 * The ObjectFactory class factory,
 * which is responsible for creating objects of classes entities.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ObjectFactory {
    /**
     * @return object of User
     */
    public User createUser() {
        return new User();
    }

    /**
     * @return object of Users
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * @return object of Comment
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * @return object of Reviews
     */
    public Reviews createReviews() {
        return new Reviews();
    }

    /**
     * @return object of Type
     */
    public Type createType() {
        return new Type();
    }

    /**
     * @return object of Types
     */
    public Types createTypes() {
        return new Types();
    }

    /**
     * @return object of Producer
     */
    public Producer createProducer() {
        return new Producer();
    }

    /**
     * @return object of Producers
     */
    public Producers createProducers() {
        return new Producers();
    }

    /**
     * @return object of Product
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * @return object of Products
     */
    public Products createProducts() {
        return new Products();
    }

    /**
     * @return object of Order
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * @return object of Orders
     */
    public Orders createOrders() {
        return new Orders();
    }

    /**
     * @return object of OrderProduct
     */
    public OrderProduct createOrderProduct() {
        return new OrderProduct();
    }

    /**
     * @return object of OrdersProducts
     */
    public OrdersProducts createOrdersProducts() {
        return new OrdersProducts();
    }

    /**
     * @return object of Statistics
     */
    public Statistics createStatistics() {
        return new Statistics();
    }
}
