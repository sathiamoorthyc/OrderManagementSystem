package com.mizuho.ordermanagement.service;


import com.mizuho.ordermanagement.model.Order;

import java.util.*;
import java.util.stream.Collectors;

public class OrderBook {

    public Map<Long, Order> orders = new HashMap<Long, Order>();

    // Given an Order, add it to the OrderBook
    public Order upsertOrder(Order order){
        orders.put(order.getId(), order);
        return order;
    }

    // Given an order id, remove an Order from the OrderBook
    public void removeOrder(long orderId){
        orders.remove(orderId);
    }

    // Given an order id and a new size, modify an existing order
    public Order modifyOrder(long orderId, long size) throws Exception {
        Order order = orders.get(orderId);
        if(null == order){
            throw new Exception("There is no order found with id:[" +orderId + "]");
        }
        order.setSize(size);
        orders.put(order.getId(), order);
        return order;
    }

    // Given a side and a level (an integer value >0) return the price for that level (
    public double findBidPrice(char side, int level){
        Optional<Order> order = this.orders.values().stream()
                .filter(o -> o.getSide() == side)
                .sorted(Comparator.comparing(Order::getPrice))
                .skip(level-1)
                .findFirst();

        return order.map(Order::getPrice).orElse(999999.999999);
    }

    // Given a side and a level return the total size available for that level
    public Long findTotalSize(char side, int level){
        Optional<Long> totalSize = this.orders.values().stream()
                .filter(o -> o.getSide() == side)
                .limit(level)
                .map(x -> x.getSize())
                .reduce(Long::sum);
        return totalSize.isPresent() ? totalSize.get() : 0l;

    }

    // Given a side return all the orders from that side of the book, in level- and time-order
    public List<Order> findOrdersBySide(char side){
        return orders.values().stream()
                .filter(o -> o.getSide() == side)
                .sorted(Comparator.comparing(Order::getPrice))
                .collect(Collectors.toList());
    }

}
