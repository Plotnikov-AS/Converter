package com.converter.model;

import javax.persistence.*;
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
    private Double fromAmount;

    @Column(name = "to_amount")
    private Double toAmount;

    private Date date;

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

    public Double getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(Double fromAmount) {
        this.fromAmount = fromAmount;
    }

    public Double getToAmount() {
        return toAmount;
    }

    public void setToAmount(Double toAmount) {
        this.toAmount = toAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
