package com.despair.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "POINTS")
public class Point implements Serializable {
    @Column(name = "X")
    private String x;
    @Column(name = "Y")
    private String y;
    @Column(name = "R")
    private String r;
    @Column(name = "HIT")
    private String hit;
    @Id
    private OffsetDateTime time;
    //    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
//    private User user;
    @Column(name = "USERNAME")
    private String username;
    @Transient
    private String stringTime;

    public Point() {
    }

    public Point(String x, String y, String r, String offset) {
        BigDecimal X = new BigDecimal(x);
        BigDecimal Y = new BigDecimal(y);
        BigDecimal R = new BigDecimal(r);
        int off = Integer.parseInt(offset);
        this.x = x;
        this.y = y;
        this.r = r;
        check(X, Y, R);
        time = OffsetDateTime.now().plusMinutes(-180 - off);
        setStringTime();
    }

    private void check(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(new BigDecimal(3.0)) > 0 || x.compareTo(new BigDecimal(-5.0)) < 0 ||
                y.compareTo(new BigDecimal(3.0)) > 0 || y.compareTo(new BigDecimal(-5.0)) < 0 ||
                r.compareTo(new BigDecimal(0)) < 0 || r.compareTo(new BigDecimal(3)) > 0)
            throw new NumberFormatException();
        boolean hit = true;
        if (x.compareTo(new BigDecimal("0")) > 0 && y.compareTo(new BigDecimal("0")) < 0) //x > 0 and y < 0
            hit = false;
        else if (x.compareTo(new BigDecimal("0")) <= 0 && y.compareTo(new BigDecimal("0")) < 0) //x <= 0 and y < 0
            hit = x.compareTo(r.divide(new BigDecimal("-2"), 25, BigDecimal.ROUND_HALF_UP)) >= 0
                    && y.compareTo(r.multiply(new BigDecimal("-1"))) >= 0; //x >= -r/2 and y >= -r
        else if (x.compareTo(new BigDecimal("0")) < 0 && y.compareTo(new BigDecimal("0")) >= 0)//x < 0 y >= 0
            hit = y.compareTo(r.divide(new BigDecimal("2"), 25, BigDecimal.ROUND_HALF_UP).add(x.divide(new BigDecimal("2"), 25, BigDecimal.ROUND_HALF_UP))) <= 0;
            //y <= r/2 + x/2
        else if (x.compareTo(new BigDecimal("0")) >= 0 && y.compareTo(new BigDecimal("0")) >= 0)//x >= 0 and y >=0
            hit = r.multiply(r).divide(new BigDecimal("4"), 25, BigDecimal.ROUND_HALF_UP).compareTo(x.multiply(x).add(y.multiply(y))) >= 0;
        this.hit = hit ? "Попадание" : "Промах";
    }

    public String isHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getStringTime() {
        return stringTime;
    }

    public void setStringTime() {
        int hours = time.getHour();
        String hour = (hours < 10 ? "0" : "") + hours;
        int minutes = time.getMinute();
        String minute = (minutes < 10 ? "0" : "") + minutes;
        int seconds = time.getSecond();
        String second = (seconds < 10 ? "0" : "") + seconds;
        int days = time.getDayOfMonth();
        String day = (days < 10 ? "0" : "") + days;
        int months = time.getMonthValue();
        String month = (months < 10 ? "0" : "") + months;
        int years = time.getYear();
        this.stringTime = day + "." + month + "." + years + " " +
                hour + ":" + minute + ":" + second;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
