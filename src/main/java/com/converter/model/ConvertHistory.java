package com.converter.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "convert_history")
public class ConvertHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "convert_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "from_cur")
    private String fromCurrency;

    @Column(name = "to_cur")
    private String toCurrency;

    @Column(name = "from_amount")
    private String fromAmount;

    @Column(name = "to_amount")
    private String toAmount;

    private Date date;

    private LocalTime time;

    @Column(name = "course_on_date")
    private String courseOnDate;

    public ConvertHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getCourseOnDate() {
        return courseOnDate;
    }

    public void setCourseOnDate(String courseOnDate) {
        this.courseOnDate = courseOnDate;
    }
}
