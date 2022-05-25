package com.myStores.domain.item;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class WagePrice {

    private int basic;
    private int main;
    private int support;

    protected WagePrice(){
    }

    public WagePrice(int basic,int main,int support){
        this.basic = basic;
        this.main = main;
        this.support = support;
    }
}
