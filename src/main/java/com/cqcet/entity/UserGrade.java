package com.cqcet.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * �û���Ա��
 * Created by �Ǹ�˭ on 2018/9/30.
 */
@Data
public class UserGrade implements Serializable {

    private String id;

    private String name;

    private String integral;

    private static final long serialVersionUID = 1L;

}
