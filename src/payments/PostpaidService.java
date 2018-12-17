/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;
import rates.Tarrif;
import accounts.ConnectionAccount;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import rates.PostpaidDiscounts;
import rates.Tarrif;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class PostpaidService extends MobileService{
    ArrayList<Call> calls=new ArrayList<Call>();
/**
 * 
 * @param account stores the account
 * @param dataAvailable  stores the data available
 */
    public PostpaidService(ConnectionAccount account, double dataAvailable) {
        super(account,0.0, dataAvailable);
    }
    /**
     * 
     * @return the calculated bill
     */
    public double calcBill()
    {
        double calcBill = 0.0;
        for (Call call2 : calls) {
            Tarrif t1 = call2.getCallType();
            switch (t1) {
                case LOCAL:
                    calcBill += Tarrif.LOCAL.getPostpaid() * (call2.getSeconds() / 60);
                    break;
                case INTERNATIONAL:
                    calcBill += Tarrif.INTERNATIONAL.getPostpaid() * (call2.getSeconds() / 60);
                    break;
                case ROAMING:
                    calcBill += Tarrif.ROAMING.getPostpaid() * (call2.getSeconds() / 60);
                    break;
            }
        }
        return calcBill / 100.0;
    }
       /**
        * 
        * @return boolean if make a call
        */
    
    @Override
    public boolean canMakeCall(){
        return true;
        
    }
    /**
     * 
     * @return the discount for returning customer
     */
    public double discountForReturningCustomer()
    {
       double percentage  = 0.0;
        int nofYears = this.getAccount().numberOfYears();
        if(nofYears == 0){
            return 0.0;
        }
        if(nofYears > 5){
           percentage = PostpaidDiscounts.valueOf("YEAR5").getDiscount(); 
           
        }
        else{
           percentage = PostpaidDiscounts.valueOf("YEAR"+nofYears).getDiscount();
        }
        return percentage;   
     
       
    }
    /**
     * 
     * @return the final bill after discount{double}
     */
      public double finalBillAfterDiscount(){
        double finalBill =this.calcBill() - this.calcBill() * (this.discountForReturningCustomer()/100);
        return finalBill;
    }
      /**
       * 
       * @param call stores the call details
       * @return if the customer can make a call
       */
    @Override
    
      public boolean makeCall(Call call) {
       boolean canCall = false;
       calls.add(call);
       if(calls.contains(call)){
           canCall = true;
       }
       return canCall;
    }
      /**
       * 
       * @param dataUsed stores the data used
       * @return the used data
       */
       @Override
    public boolean useData(double dataUsed) {
        boolean isData = false;
        dataUsed = dataUsed/1024.0;
        this.dataAvailable = (this.getDataAvailable() - dataUsed);
        isData = true;
        return isData;
    }/**
     * 
     * @return the details of the call{String}
     */
    @Override
     public String toString(){
        String callString = "";
        String headerString = "\nPhone number            From                 To           Call Type\n";
        for(Call call : calls ){
            callString += call+"\t\n";
        }
        String x= "-----------------------------------------------------------------------------";
        String billString = "\n       Additional mobile data used     :"+getAdditionalDataUsed()+"MB\n"+
                            "       Bill Amount                     :$"+String.format("%.2f",calcBill())+"\n"+
                            "       Returning Customer Discount("+discountForReturningCustomer()+"%)   :$"+String.format("%.2f",calcBill() * (discountForReturningCustomer()/100))+"\n"+
                            "       Final Bill Amount               :$"+String.format("%.2f",finalBillAfterDiscount())+"\n";
        String connectionDate="";
        connectionDate = "Connection Date :"+getDateFormat(getAccount().getJoiningDate())+"\n";
       
        return super.toString()+"\t\t"+connectionDate+x+headerString+callString+x+billString+x+"\n"+x;
    }
    
    /**
     * 
     * @return the additional data used
     */
    private double getAdditionalDataUsed(){
        double additionalData = 0.00;
        if(this.getDataAvailable() < 0){
               additionalData = Math.abs(this.getDataAvailable());
        } 
        return additionalData;
    }
    /**
     * 
     * @param dateString stores the date
     * @return the date String after formatting
     */
    private String getDateFormat(String dateString){
     
       DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
       LocalDate date = LocalDate.parse(dateString,format);
       String dateStr = date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
       return dateStr;
    }
   

    
    
}
