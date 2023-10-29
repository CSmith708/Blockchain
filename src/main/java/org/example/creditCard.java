package org.example;
public class creditCard {

    private long cardNumber;
    private String customerName;
    private int cvc;
    private String issuingAuthority;
    private double transactionValue;

    public creditCard(long cardNumber, String customerName, int cvc, String issuingAuthority, double transactionValue) {
        this.cardNumber = cardNumber;
        this.customerName = customerName;
        this.cvc = cvc;
        this.issuingAuthority = issuingAuthority;
        this.transactionValue = transactionValue;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }
}
