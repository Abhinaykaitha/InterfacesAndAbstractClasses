/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rates;

/**
 *
 * @author Kaitha, Abhinay Reddy;
 */
public enum Tarrif {
    LOCAL(19,20),
    ROAMING(29,30),
    INTERNATIONAL(69,70);
    
    private double postpaid;
    private double prepaid;

    private Tarrif(double postpaid, double prepaid) {
        this.postpaid = postpaid;
        this.prepaid = prepaid;
    }

    public double getPostpaid() {
        return postpaid;
    }

    public double getPrepaid() {
        return prepaid;
    }
    
    
    
}
