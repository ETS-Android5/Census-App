package com.example.jpro;

import java.io.Serializable;

public class IdData implements Serializable {

    private String refnum;
    private String enumna;

    public IdData(String refnum, String enumna) {
        this.refnum = refnum;
        this.enumna = enumna;
    }

    public IdData() {
    }



    public String getRefnum() {
        return refnum;
    }

    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    public String getEnumna() {
        return enumna;
    }

    public void setEnumna(String enumna) {
        this.enumna = enumna;
    }
}
