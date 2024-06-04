package Model;

public class Transaction {
    private int transactionId;
    private Customer customer;// tai khoan thuc hien giao dich
    private String fullName;
    private String email;   
    private String numberPhone;
    private double amount; //so tien giao dich

    public Transaction(int transactionId, Customer customer, String fullName, String email, String numberPhone, double amount) {
        this.transactionId = transactionId;
        this.customer = customer;
        this.fullName = fullName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.amount = amount;
    }

    public Transaction(String fullName, String email, String numberPhone, double amount) {
        this.fullName = fullName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", customer=" + customer +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
