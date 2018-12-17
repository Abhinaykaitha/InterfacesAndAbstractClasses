/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import accounts.ConnectionAccount;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public abstract class MobileService implements Operations{
   ConnectionAccount account;
   double balance;
   double dataAvailable;
/**
 * 
 * @param account stores the account {Connection Account}
 * @param balance stores the balances {double}
 * @param dataAvailable stores the available data {double} 
 */
    public MobileService(ConnectionAccount account, double balance, double dataAvailable) {
        this.account = account;
        this.balance = balance;
        this.dataAvailable = dataAvailable;
    }

    /**
     * abstract method 
     * @return boolean 
     */
    @Override
   
   public abstract boolean canMakeCall();
/**
 * getter method
 * @return account
 */
    public ConnectionAccount getAccount() {
        return account;
    }
/**
 * getter method
 * @return balance of the account
 */
    public double getBalance() {
        return balance;
    }
/**
 * getter method
 * @return available data 
 */
    public double getDataAvailable() {
        return dataAvailable;
    }
/**
 * setter method
 * @param balance stores the balance 
 */
    public void setBalance(double balance) {
        this.balance = balance;
    }
/**
 * setter method
 * @param dataAvailable stores the data available
 */
    public void setDataAvailable(double dataAvailable) {
        this.dataAvailable = dataAvailable;
    }
    
    /**
     *abstract method
     * @param call stores the call 
     * @return boolean 
     */
    @Override
   public abstract boolean makeCall(Call call);

    /**
     *
     * @param dataUsed {double}
     * @return boolean type 
     */
    @Override
  
   public boolean useData(double dataUsed){
       boolean x=false;
       if(dataUsed>dataAvailable){
           return x;       
       }else{
       return true;
       
   }}
   /**
    * return details
    * @return string details
    */
   @Override
   public String toString(){
        return "Customer Name\t:"+account.getCustomerName()+"\n"+
               "Phone Number\t:"+account.getPhoneNumber()+"\n"+
               "Connection Type\t:"+account.getConnectionType();
   }
   
   
   
    
    
}
