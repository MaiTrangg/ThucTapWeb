package Model;

public class Customer {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String numberPhone;
    private int isAdmin;
    private String country;
    private String fristName;
    private String lastName;
    private String address;
    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * @return the fristName
     */
    public String getFristName() {
        return fristName;
    }
    /**
     * @param fristName the fristName to set
     */
    public void setFristName(String fristName) {
        this.fristName = fristName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    //	private detailCustomer DetailCustomer;
    /**
     * @param username
     * @param password
     */
//    public Customer(String username, String password) {
//        this.username = username;
//        this.password = password;
//
////		DetailCustomer = detailCustomer;
//    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @param username
     * @param password
     * @param email
     * @param numberPhone
     */
    public Customer(String username, String password, String email, String numberPhone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.numberPhone = numberPhone;
    }

    /**
     * @param username
     * @param password
     * @param email
     * @param numberPhone
     * @param isAdmin
     * @param user_id
     */
    public Customer(int user_id, String username, String password, String email, String numberPhone, int isAdmin) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.numberPhone = numberPhone;
        this.isAdmin = isAdmin;
    }
    public Customer(String username, String password, String email, String numberPhone, int isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.numberPhone = numberPhone;
        this.isAdmin = isAdmin;
    }
    public Customer() {

    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param numberPhone the numberPhone to set
     */
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
    @Override
    public String toString() {
        return "Customer [username=" + username + ", password=" + password + ", email=" + email + ", numberPhone="
                + numberPhone + ", isAdmin=" + isAdmin + "]";
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getNumberPhone() {
        return numberPhone;
    }
    public int getIsAdmin() {
        return isAdmin;
    }


}


