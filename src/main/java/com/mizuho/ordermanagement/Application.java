package com.mizuho.ordermanagement;


import com.mizuho.ordermanagement.model.Order;
import com.mizuho.ordermanagement.service.OrderBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        OrderBook orderBook = new OrderBook();
        Order order1 = new Order(1, 10.1, 'B', 100);
        orderBook.upsertOrder(order1);
        Order order2 = new Order(2, 12.31, 'O', 200);
        orderBook.upsertOrder(order2);
        Order order3 = new Order(1, 12.31, 'B', 200);
        orderBook.upsertOrder(order3);
        Order order4 = new Order(3, 5.1, 'B', 300);
        orderBook.upsertOrder(order4);

        logger.info("Initial Orders : [{}] ",orderBook.orders);

        try {

            orderBook.modifyOrder(1, 500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            orderBook.modifyOrder(2, 500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Orders after modifying order with id[{}]: [{}]",2, orderBook.orders);


        orderBook.orders.values().forEach(o-> {
            logger.info(o.toString());
        });
        logger.info("bid price for B, 2--> {}",orderBook.findBidPrice('B', 2));
        logger.info("bid price for B, 2--> {}",orderBook.findBidPrice('B', 3));
        logger.info("bid price for X, 1--> {}",orderBook.findBidPrice('X', 1));

        logger.info("Total size for B, 1-> {}",orderBook.findTotalSize('B', 1));
        logger.info("Total size for B, 2-> {}",orderBook.findTotalSize('B', 2));
        logger.info("Total size for B, 3-> {}",orderBook.findTotalSize('B', 3));
        logger.info("Total size for B, 4-> {}",orderBook.findTotalSize('B', 4));

        logger.info("Orders for side B -> {} ",orderBook.findOrdersBySide('B'));
        logger.info("Orders for side O -> {} ",orderBook.findOrdersBySide('O'));
        logger.info("Orders for side x -> {} ",orderBook.findOrdersBySide('X'));

        orderBook.removeOrder(1);
        logger.info("Orders after removing order with id[{}]: [{}] ", 1, orderBook.orders);
    }
}
