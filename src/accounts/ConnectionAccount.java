/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class ConnectionAccount{
    private String connectionType;
    private String customerName;
    private String joiningDate;
    private String phoneNumber;
/**
 * 3 argument constructor
 * @param customerName stores the customer Name{String}
 * @param phoneNumber stores the phone Number {String}
 * @param connectionType stores the connection Type {String}
 */
    public ConnectionAccount( String customerName, String phoneNumber,String connectionType) {
        this.connectionType = connectionType;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }
/**
 * 3 argument constructor
 * @param customerName stores the customer Name{String}
 * @param phoneNumber stores the phone Number {String}
 * @param connectionType stores the connection Type {String}
 * @param joiningDate stores the joining date of the customer{String}
 */
    public ConnectionAccount( String customerName, String phoneNumber,String connectionType, String joiningDate) {
        this.connectionType = connectionType;
        this.customerName = customerName;
        this.joiningDate = joiningDate;
        this.phoneNumber = phoneNumber;
    }
/**
 * Getter method
 * @return type of the connection
 */
    public String getConnectionType() {
        return connectionType;
    }
/**
 * Getter method
 * @return name of the customer
 */
    public String getCustomerName() {
        return customerName;
    }
   
/**
 * Getter method
 * @return joining date of the customer
 */
    public String getJoiningDate() {
        return joiningDate;
    }

   
   /**
    * Getter method
    * @return the phone number 
    */

    public String getPhoneNumber() {
       String phoneNumber1 = "";
        switch(phoneNumber.length()){
            case 13:
                phoneNumber1 = "(".concat(phoneNumber.substring(0, 3).concat(")").concat(phoneNumber.substring(3, 6)).concat("-").concat(phoneNumber.substring(6, 9).concat("-")).concat(phoneNumber.substring(9)));
                break;
            case 12:
                phoneNumber1 = "(".concat(phoneNumber.substring(0, 2).concat(")").concat(phoneNumber.substring(2, 5)).concat("-").concat(phoneNumber.substring(5, 8).concat("-")).concat(phoneNumber.substring(8)));
                break;
        }
        return phoneNumber1;
    }
    /**
     * 
     * @return the number of years between joining date and the current date {int}
     */
    public int numberOfYears()
    {
        DateTimeFormatter fmt=DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date=LocalDate.parse(joiningDate,fmt);
        LocalDate no=LocalDate.now();
        int diff=(int) ChronoUnit.YEARS.between(date,no);
        return diff;
    }
    
    
}
