/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import rates.Tarrif;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public class Call { 
    String phoneNumber;
    String startTime;
    String endTime;
    Tarrif callType;
/**
 * 
 * @param phoneNumber stores the phone number {string}
 * @param startTime stores the start time {string}
 * @param endTime stores the end time {string}
 * @param callType stores the type of call {Tarrif}
 */
    public Call(String phoneNumber, String startTime, String endTime, Tarrif callType) {
        this.phoneNumber = phoneNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.callType = callType;
    }
    /**
     * getter methods
     * @return type of call
     */
     public Tarrif getCallType()
     {
         return callType;
     }
/**
 * getter methods
 * @return phone number 
 */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * getter methods
     * @return the seconds
     */
     public double getSeconds()
     {
   DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         LocalDateTime startDate = LocalDateTime.parse(startTime, fmt);
         LocalDateTime endDate = LocalDateTime.parse(endTime, fmt);
         return ChronoUnit.SECONDS.between(startDate, endDate);
    }
    @Override
    /**
     * details of the string
     */
     public String toString(){
         return phoneNumber+"\t"+startTime+"\t"+endTime+"\t"+callType;
         
     }

    
}
