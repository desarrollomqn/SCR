/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.planit.scr.metodos;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author VaioDevelopment
 */
public class Redondear {

    public Redondear() {
    }
    
    public static String redondear(double valor, int digitos){
      String val = valor+"";
      BigDecimal big = new BigDecimal(val);
      big = big.setScale(digitos, RoundingMode.HALF_UP);
      return big.toString();
    }
    
}
