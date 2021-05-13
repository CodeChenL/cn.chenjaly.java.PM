package org.gec.bean;

import java.util.Date;

public class Notice {
    private Integer id;
    private String title;
    private String remark;
    private Integer typeId;
    private Integer userId;
    private Date createdate;
    private User user;
    private Type type;

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Notice() {
        super();
    }


    public Notice(Integer id, String title, String remark, Integer typeId, Integer userId, Date createdate, User user,
                  Type type) {
        super();
        this.id = id;
        this.title = title;
        this.remark = remark;
        this.typeId = typeId;
        this.userId = userId;
        this.createdate = createdate;
        this.user = user;
        this.type = type;
    }

    public Notice(String title, Integer typeId, Integer userId, String remark) {
        super();
        this.title = title;
        this.remark = remark;
        this.typeId = typeId;
        this.userId = userId;
    }

    public Notice(String title, Integer typeId, String remark) {
        super();
        this.title = title;
        this.remark = remark;
        this.typeId = typeId;

    }

    public Notice(String title, Integer typeId) {
        super();
        this.title = title;
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Notice [id=" + id + ", title=" + title + ", remark=" + remark + ", typeId=" + typeId + ", userId="
                + userId + ", createdate=" + createdate + ", user=" + user + ", type=" + type + "]";
    }


}
