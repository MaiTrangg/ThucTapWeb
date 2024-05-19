package Model;

public class Transaction {
    private String transactionId;
    private Customer customer;// tai khoan thuc hien giao dich
    private String fullName;
    private String email;
    private String numberPhone;
    private String amount; //so tien giao dich

    public Transaction(String transactionId, Customer customer, String fullName, String email, String numberPhone, String amount) {
        this.transactionId = transactionId;
        this.customer = customer;
        this.fullName = fullName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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
