/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;
import accounts.ConnectionAccount;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import rates.Tarrif;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class TelecommDriver {
    private static Tarrif getTarrif(String callTypeIn){
        Tarrif callType = null;
        switch (callTypeIn) {
            case "L":
            case "l":
                callType = Tarrif.LOCAL;
                break;
            case "R":
            case "r":
                callType = Tarrif.ROAMING;
                break;
            case "I":
            case "i":
                callType = Tarrif.INTERNATIONAL;
                break;
            default:
                break;
        }
        return callType;
    }
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<MobileService> connections = new ArrayList<>();
        Scanner scan = new Scanner(new File("usersCallLog.txt"));
        while(scan.hasNext()){
            String pattern = "[a-zA-Z ]+";
            String custName = scan.nextLine(); 
            String phoneNo = scan.nextLine(); 
            String connType = scan.nextLine(); 
            String joiningDate = "";
            if(connType.equals("postpaid")){
                joiningDate = scan.nextLine(); 
            }
            ConnectionAccount conn = new ConnectionAccount(custName,phoneNo,connType,joiningDate);
            PostpaidService p1 = null;
            PrepaidService p2 = null;
            if(connType.equals("postpaid")){
                double data = Double.parseDouble(scan.nextLine());
                p1= new PostpaidService(conn, data);
            }
            else if(connType.equals("prepaid")){
                double balance = Double.parseDouble(scan.nextLine());
                double data = Double.parseDouble(scan.nextLine());
                p2 = new PrepaidService(conn,balance,data); 
            }
            do{
                String callString = scan.nextLine();
                String[] callArray = callString.split("  ");
                Call call = new Call(callArray[0],callArray[1],callArray[2],getTarrif(callArray[3]));
                if(connType.equals("postpaid")){
                    p1.makeCall(call);
                }
                else if(connType.equals("prepaid")){
                    p2.makeCall(call);
                }
                if(!scan.hasNextLine()){
                    break;
                }
            }
            while(!scan.hasNext(pattern));
            if(connType.equals("postpaid")){
                connections.add(p1);
              }
            else if(connType.equals("prepaid")){
               connections.add(p2);
            }
        }
        scan.close();
        System.out.println("************************Postpaid customers invoice***************************");
        for(MobileService connObj : connections){
            if(connObj.getAccount().getConnectionType().equals("postpaid")){
                System.out.println(connObj);
            } 
        }
        System.out.println("************************Prepaid customers invoice***************************");
        for(MobileService connObj : connections){
            if(connObj.getAccount().getConnectionType().equals("prepaid")){
                System.out.println(connObj);
            } 
        }
        System.out.println("**********************Invoking useData() of customer*************************"+
                 "\n-----------------------------------------------------------------------------");
        for(MobileService c: connections){
            if(c.getAccount().getCustomerName().equals("Robert Downey Jr.")){
                c.useData(156774.40);
                System.out.println(c);
            } 
            if(c.getAccount().getCustomerName().equals("Lionel Messi")){
                c.useData(13516.80);
                System.out.println(c);
            } 
            if(c.getAccount().getCustomerName().equals("Ed Sheeran")){
                c.useData(104427.52);
                System.out.println(c);
            } 
        }
        
        System.out.println("************************Best postpaid customer******************************"+
                "\n-----------------------------------------------------------------------------");
        double highestBill = Double.MIN_VALUE;
        String custName = "";
        for(MobileService connectionObj : connections){
            if(connectionObj.getAccount().getConnectionType().equals("postpaid")){
                PostpaidService postObj = (PostpaidService) connectionObj;
                if(postObj.finalBillAfterDiscount() > highestBill){
                    highestBill = postObj.finalBillAfterDiscount();
                    custName = connectionObj.getAccount().getCustomerName();
                } 
            } 
        }
        System.out.println("Best Customer Details:");
         System.out.println("Customer Name:	"+custName+"\n"+
                 "Bill amount	:$"+String.format("%.2f",highestBill));
        
    } 
}
    

