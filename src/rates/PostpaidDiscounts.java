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
public enum PostpaidDiscounts {
    YEAR1(4),
    YEAR2(8),
    YEAR3(10),
    YEAR4(15),
    YEAR5(20);
    
    private double discount;

    private PostpaidDiscounts(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
    
    
    
}
