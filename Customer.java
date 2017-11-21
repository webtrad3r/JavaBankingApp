package bankapplication.BankConsoleApp;

public abstract class Customer {

    private String name;
    private String surname;
    private String postcode;
    private String emailAddress;
    private String address;
    private String telephoneNumber;
    private int age;
    private int customerNumber;

    // Constructor 
    public Customer(String name, String surname, String postcode, String emailAddress, String address, int age, String telephoneNumber, int customerNumber) {
        this.name = name;
        this.surname = surname;
        this.postcode = postcode;
        this.emailAddress = emailAddress;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.customerNumber = customerNumber;
        this.age = age;
    }

    // Modifier methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setEmail(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public void setPostCode(String postcode) {
        this.postcode = postcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    // Accessor methods
    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public String getPostCode() {
        return postcode;
    }
    
    public String getEmail() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getNumber() {
        return customerNumber;
    }

    @Override
    public String toString() {
        return (name + " | " + surname + " | " + age + " | " + address + " | " + postcode + " | " + telephoneNumber + " | " + emailAddress + " | " + customerNumber);
    }

    public abstract double getSavingsInterest();

    public abstract double getCheckingInterest();

    public abstract double getCheckCharge();

    public abstract double getOverdraftPenalty();

}
