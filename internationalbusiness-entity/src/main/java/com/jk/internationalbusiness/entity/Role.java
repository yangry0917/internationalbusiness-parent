package com.jk.internationalbusiness.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "tbl_user_info")
public class Role {
    @Column(name="id", length=50)
    private String id;

    @Column(name="name", length=20)
    private String name;

    @Column(name="name_zh", length=20)
    private String nameZh;
}
