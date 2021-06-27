package com.mpy.activiti.day5;

import java.io.Serializable;
import java.util.Date;

/**
 *请假实体类
 */
public class Holiday implements Serializable {
    private Integer id;
    private String HolidayName;//申请人名字
    private Date beginDate;//开始时间
    private Date endDate;//结束时间
    private Float num;//请假天数
    private String reason;//    事由
    private String type;//请假类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHolidayName() {
        return HolidayName;
    }

    public void setHolidayName(String holidayName) {
        HolidayName = holidayName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
