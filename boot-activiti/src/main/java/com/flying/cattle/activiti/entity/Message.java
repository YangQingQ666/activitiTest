package com.flying.cattle.activiti.entity;

import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makempy
 * @since 2021-05-21 15:55:41
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 700161079641590664L;

    private Integer id;

    private String shenpeo;

    private String shijain;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShenpeo() {
        return shenpeo;
    }

    public void setShenpeo(String shenpeo) {
        this.shenpeo = shenpeo;
    }

    public String getShijain() {
        return shijain;
    }

    public void setShijain(String shijain) {
        this.shijain = shijain;
    }

}
