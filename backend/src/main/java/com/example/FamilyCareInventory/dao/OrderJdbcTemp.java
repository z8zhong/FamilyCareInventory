package com.example.FamilyCareInventory.dao;

import com.example.FamilyCareInventory.model.Order;
import com.example.FamilyCareInventory.model.OrderLine;
import com.example.FamilyCareInventory.model.Order;
import com.example.FamilyCareInventory.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class OrderJdbcTemp {
    private static final String INSERT_ORDER_SQL =
            "INSERT INTO `Order`(employee_name, customer_name, trans_time, note, flag, total, method, name) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_ORDER_LINE_SQL =
            "INSERT INTO OrderLine(orderId, orderlineId, product_name, product_version, quantity, price) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDER_SQL =
            "SELECT name, orderId, employee_name, customer_name, trans_time, note, CASE WHEN flag='online' THEN '线上' ELSE '线下' END AS flag, " +
                    "CASE WHEN `method`='cash' THEN '现金' ELSE '电子支付' END AS `method`, total FROM `Order` ORDER BY trans_time DESC";

    private static final String SELECT_ORDER_BY_ORDERID_SQL =
            "SELECT name, orderId, employee_name, customer_name, trans_time, note, CASE WHEN flag='online' THEN '线上' ELSE '线下' END AS flag, " +
                    "CASE WHEN `method`='cash' THEN '现金' ELSE '电子支付' END AS `method`, total FROM `Order` WHERE orderId = ?";

    private static final String SELECT_ORDER_LINE_BY_ORDERID_SQL =
            "SELECT * FROM OrderLine WHERE orderId = ? ORDER BY orderlineId DESC";

    private static final String SELECT_LAST_INSERT_ORDER_ID_SQL =
            "SELECT LAST_INSERT_ID() AS orderId FROM `Order` LIMIT 1;";

    private static final String DELETE_ORDER_BY_ORDERID_SQL =
            "DELETE FROM `Order` WHERE orderId = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addOrder(Order order) {
        int orderId = 0;
        jdbcTemplate.update(INSERT_ORDER_SQL,
                order.getEmployee_name(),
                order.getCustomer_name(),
                order.getTrans_time(),
                order.getNote(),
                order.getFlag(),
                order.getTotal(),
                order.getMethod(),
                order.getName());
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_LAST_INSERT_ORDER_ID_SQL);
        if (rs.next()) {
            orderId = rs.getInt("orderId");
        }
        return orderId;
    }

    public void addOrderLine(OrderLine orderLine) {
        jdbcTemplate.update(INSERT_ORDER_LINE_SQL,
                orderLine.getOrderId(),
                orderLine.getOrderlineId(),
                orderLine.getProduct_name(),
                orderLine.getProduct_version(),
                orderLine.getQuantity(),
                orderLine.getPrice());
    }

    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> OrderList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ALL_ORDER_SQL);
        while (rs.next()) {
            Order Order = new Order(rs.getInt("orderId"), rs.getString("employee_name"), rs.getString("customer_name"),
                    rs.getDate("trans_time"), rs.getString("note"), rs.getString("flag"), null, rs.getFloat("total"),
                    rs.getString("method"), rs.getString("name"));
            OrderList.add(Order);
        }
        return OrderList;
    }

    public Order getOrderByOrderId(int orderId) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ORDER_BY_ORDERID_SQL, orderId);
        Order order = new Order();
        while (rs.next()) {
            order = new Order(rs.getInt("orderId"), rs.getString("employee_name"), rs.getString("customer_name"),
                    rs.getDate("trans_time"), rs.getString("note"), rs.getString("flag"), null, rs.getFloat("total"),
                    rs.getString("method"), rs.getString("name"));
        }
        return order;
    }

    public ArrayList<OrderLine> getOrderLine(int orderId) {
        ArrayList<OrderLine> OrderLineList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ORDER_LINE_BY_ORDERID_SQL, orderId);
        while (rs.next()) {
            OrderLine OrderLine = new OrderLine(rs.getInt("OrderId"), rs.getInt("OrderlineId"), rs.getString("product_name"),
                    rs.getString("product_version"), rs.getInt("quantity"), rs.getFloat("price"));
            OrderLineList.add(OrderLine);
        }
        return OrderLineList;
    }

    public void deleteOrder(int orderId) {
        jdbcTemplate.update(DELETE_ORDER_BY_ORDERID_SQL, orderId);
    }
}
