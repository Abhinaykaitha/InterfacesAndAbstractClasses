/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import accounts.ConnectionAccount;
import java.text.DecimalFormat;
import rates.Tarrif;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class PrepaidService extends MobileService{
    private Call call;

    public PrepaidService(ConnectionAccount account, double balance, double dataAvailable) {
        super(account, balance, dataAvailable);
    }/**
     * 
     * @return the calculated bill
     */
    public double calcBill(){
       double calcBill = 0.0;
            Tarrif t1 = call.getCallType();
            switch (t1) {
                case LOCAL:
                    calcBill = Tarrif.LOCAL.getPrepaid() * (call.getSeconds() / 60);
                    break;
                case INTERNATIONAL:
                    calcBill = Tarrif.INTERNATIONAL.getPrepaid() * (call.getSeconds() / 60);
                    break;
                case ROAMING:
                    calcBill = Tarrif.ROAMING.getPrepaid() * (call.getSeconds() / 60);
                    break;
            }
        return calcBill/100.0;
    }
    /**
     * 
     * @return if the  customer can make a call
     */
    @Override
    public boolean canMakeCall(){
        if(super.getBalance()>0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 
     * @return the discount for returning customer
     */
    public double discountForReturningCustomer(){
        return 0.0;
    }
    /**
     * 
     * @param call stores the call
     * @return if the customer can make a call{boolean}
     */
    @Override
    public boolean makeCall(Call call){
        
        if(canMakeCall()==true){
            this.call=call;
          super.setBalance(super.getBalance() - calcBill());
            return true;
        }else{
            return false;
        }
    }
   /**
    * 
    * @return details of the call{string}
    */
    @Override
    public String toString(){
        String x = "-----------------------------------------------------------------------------";
        return x + "\n" + super.toString() + "\n" + x + "\nLast Call Details:\n" + "Phone number\tFrom\t\t\t\tTo\t\t\tCall Type\n" + 
                call + "\n"+x + "\n" + "\tAvailable mobile data \t:" + super.getDataAvailable() + 
                "MB" + "\n\tBalance\t\t\t:$" + String.format("%.2f",super.getBalance())+ 
                "\n\tLast Call Amount  \t:$" + String.format("%.2f",calcBill())
                 + "\n" + x;
    }
   
}
