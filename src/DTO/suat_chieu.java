/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author phamp
 */
public class suat_chieu {
    private String idSuatChieu;
    private String idPhim;
    private String idDinhDang;
    private Time gioBatDau;
    private Time gioKetThuc;
    private Date ngayChieu;
    private int idPhongChieu;

    public String getIdSuatChieu() {
        return idSuatChieu;
    }

    public void setIdSuatChieu(String idSuatChieu) {
        this.idSuatChieu = idSuatChieu;
    }

    public String getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(String idPhim) {
        this.idPhim = idPhim;
    }

    public String getIdDinhDang() {
        return idDinhDang;
    }

    public void setIdDinhDang(String idDinhDang) {
        this.idDinhDang = idDinhDang;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public int getIdPhongChieu() {
        return idPhongChieu;
    }

    public void setIdPhongChieu(int idPhongChieu) {
        this.idPhongChieu = idPhongChieu;
    }
    
    
}
