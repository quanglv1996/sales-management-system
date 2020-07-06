/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.core.product;

import project1.core.product.Product;

/**
 *
 * @author lequangbkhn
 */
public class Music extends Product {

    public static final int TYPE_MUSIC = 3;
    private String caSi;
    private String NSX;

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }
    
    public String getNSX() {
        return NSX;
    }

    public void setNSX(String NSX) {
        this.NSX = NSX;
    }

}
