/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilador.lexic;

import compilador.lexic.utils;

/**
 *
 * @author emanu
 */
public class test {

    public static String variableCheck;
    
    public static void main(String[] args) {
        variableCheck="1tuple";
        System.out.println(variableCheck);
        utils utilities = new utils();
        variableCheck = utilities.Levenshtein(variableCheck);
        System.out.println(variableCheck);
        
    }
}
