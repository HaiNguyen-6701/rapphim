
package DTO;

import java.sql.Date;

public class ve_ban {
    private String idVe;
    private String idSuatChieu;
    private String idNhanVien;
    private int tongTien;
    private int idGiaVe;
    private int idGhe;
    private Date ngayBan;

    public String getIdVe() {
        return idVe;
    }

    public void setIdVe(String idVe) {
        this.idVe = idVe;
    }

    public String getIdSuatChieu() {
        return idSuatChieu;
    }

    public void setIdSuatChieu(String idSuatChieu) {
        this.idSuatChieu = idSuatChieu;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getIdGiaVe() {
        return idGiaVe;
    }

    public void setIdGiaVe(int idGiaVe) {
        this.idGiaVe = idGiaVe;
    }

    public int getIdGhe() {
        return idGhe;
    }

    public void setIdGhe(int idGhe) {
        this.idGhe = idGhe;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }
    
    
}
