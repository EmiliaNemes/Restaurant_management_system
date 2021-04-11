package org.businessLayer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private static int contor;

    private int orderID;
    private Date date;
    private int table;

    public Order(int table) {
        this.orderID = contor;
        this.date = new Date();
        this.table = table;
        contor++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderID() == order.getOrderID() &&
                getTable() == order.getTable() &&
                getDate().equals(order.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderID(), getDate(), getTable());
    }

    public int getOrderID() {
        return orderID;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-YYYY 'at' hh:mm:ss");
        return dateFormat.format(this.date);
    }

    public int getTable() {
        return table;
    }

}
