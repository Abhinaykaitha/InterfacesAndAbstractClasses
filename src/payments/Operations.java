/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public interface Operations {
    boolean canMakeCall();
    boolean makeCall(Call call);
    boolean useData(double dataUsed);
    
}
