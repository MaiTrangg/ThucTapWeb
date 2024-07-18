package Model;

import java.sql.Timestamp;

public class Payment {
   private String paymentId;
   private Order order;
   private String description;
   private Double amount;
   private Timestamp date;
   private String accountNumber;

    public Payment(String paymentId, Order order, String description, Double amount, Timestamp date, String accountNumber) {
        this.paymentId = paymentId;
        this.order = order;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.accountNumber = accountNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

