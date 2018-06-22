package com.ruc.ir.bean;

import javax.persistence.*;

@Entity
@Table(name="likes")
public class Likes {
    @Id
    @GeneratedValue
    @Column(name="likeid")
    private Integer likeid;
    @Column(name="imgid")
    private Integer imgid;
    @Column(name="time")
    private String time;

    public Likes(){}

    public Integer getLikeid() {
        return likeid;
    }

    public void setLikeid(Integer likeid) {
        this.likeid = likeid;
    }

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
