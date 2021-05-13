package org.gec.bean;

import java.io.Serializable;

public class Type implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public Type(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Type(String name) {
        super();
        this.name = name;
    }

    public Type() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type [id=" + id + ", name=" + name + "]";
    }

}
