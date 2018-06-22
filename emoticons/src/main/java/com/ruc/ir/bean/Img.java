package com.ruc.ir.bean;

import javax.persistence.*;

@Entity
@Table(name="img")
public class Img {
    @Id
    @Column(name="imgid")
    private Integer imgid;
    @Column(name="name")
    private String name;
    @Column(name="tag")
    private String tag;
    @Column(name="file_name")
    private String file_name;

    @Transient
    private long likes = 0;

    public Img(){}

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }
}
