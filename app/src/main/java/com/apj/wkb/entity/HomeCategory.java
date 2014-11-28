package com.apj.wkb.entity;

import java.util.List;

/**
 * Created by Liuyu on 2014/11/28.
 */
public class HomeCategory {
    private String name;
    private String type;
    private List<CourserItem> vos;

    public String getName() {return name;}

    public void setName(String name) {this.name=name;}

    public String getType(){return type;}

    public void setType(String type){this.type=type;}

    public List<CourserItem>getVos(){return vos;}

    public void setVos(List<CourserItem>vos){this.vos=vos;}


}
