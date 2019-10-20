/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineDTO;

import java.math.BigDecimal;

/**
 *
 * @author daler
 */
public class Change {
    
    public enum Coins {
        PENNY, NICKEL, DIME, QUARTER;
    }
    
   public int quarters;
   public int dimes;
   public int nickels;
   public int pennies;

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }
    
    
    public void getChange(BigDecimal userMoney, BigDecimal itemPrice) {
        BigDecimal totalChange = userMoney.subtract(itemPrice);
        BigDecimal quarter = new BigDecimal("0.25");
        setQuarters((totalChange.divideToIntegralValue(quarter)).intValue());
        BigDecimal remainingChange = totalChange.subtract(totalChange.divideToIntegralValue(quarter).multiply(quarter));
        BigDecimal dime = new BigDecimal("0.10");
        setDimes(remainingChange.divideToIntegralValue(dime).intValue());
        remainingChange = remainingChange.subtract(remainingChange.divideToIntegralValue(dime).multiply(dime));
        BigDecimal nickel = new BigDecimal("0.05");
        setNickels(remainingChange.divideToIntegralValue(nickel).intValue());
        remainingChange = remainingChange.subtract(remainingChange.divideToIntegralValue(nickel).multiply(nickel));
        BigDecimal penny = new BigDecimal("0.01");
        setPennies(remainingChange.intValue());
    }

}
