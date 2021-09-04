
package DAO;

import DTO.Phim;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class phimDAO {
    DB_ConnectionDAO connectionDAO=new DB_ConnectionDAO();  
    public ArrayList<Phim>getListPhim(){                     
        ArrayList<Phim> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `phim`";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Phim phim=new Phim();
                    phim.setIdPhim(rs.getInt("idPhim"));
                    phim.setTenPhim(rs.getString("tenPhim"));
                    phim.setNgonNgu(rs.getString("ngonNgu"));
                    phim.setDienVien(rs.getString("dienVien"));
                    phim.setNhaSanXuat(rs.getString("nhaSanXuat"));
                    phim.setTheloai(rs.getString("theLoai"));
                    phim.setGiave(rs.getString("giaVe"));
                    phim.setQuocgia(rs.getString("quocGia"));
                    phim.setNgayCongChieu(rs.getString("Ngaycongchieu"));
                    phim.setThoiLuong(rs.getInt("thoiLuong"));
                    list.add(phim);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
        
    }
    public boolean AddPhim(Phim phim){
        String sql="INSERT INTO `phim`"
                + "(`idPhim`, `tenPhim`, `theLoai`, `thoiLuong`, `ngonNgu`, `dienVien`, `quocGia`, `nhaSanXuat`, `Ngaycongchieu`, `giaVe`) VALUES "
                + "("+phim.getIdPhim()+",'"+phim.getTenPhim()+"','"+phim.getTheloai()+"',"+phim.getThoiLuong()+",'"+phim.getNgonNgu()+"','"+phim.getDienVien()+"','"+phim.getQuocgia()+"','"+phim.getNhaSanXuat()+"','"+phim.getNgayCongChieu()+"','"+phim.getGiave()+"')";

        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean EditPhim(Phim phim){
        String sql="UPDATE `phim` "
                + "SET `tenPhim`='"+phim.getTenPhim()+"',`theLoai`='"+phim.getTheloai()+"',`thoiLuong`='"+phim.getThoiLuong()+"',`ngonNgu`='"+phim.getNgonNgu()+"',`dienVien`='"+phim.getDienVien()+"',`quocGia`='"+phim.getQuocgia()+"',`nhaSanXuat`='"+phim.getNhaSanXuat()+"',`Ngaycongchieu`='"+phim.getNgayCongChieu()+"',`giaVe`='"+phim.getGiave()+"' WHERE `idPhim`='"+phim.getIdPhim()+"'";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }

    public boolean DeletePhim(int idPhim){
            String sql="DELETE FROM `phim` WHERE idPhim='"+idPhim+"'";
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public ArrayList<Phim> FindFilm(String attributes) {
        ArrayList<Phim> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `phim` where (`tenPhim` like '%" + attributes + "%') or (`idPhim` = '" +attributes+ "') or (`theLoai` like '%" +attributes+ "%') or (`Ngaycongchieu` = '" +attributes+ "')";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Phim phim=new Phim();
                    phim.setIdPhim(rs.getInt("idPhim"));
                    phim.setTenPhim(rs.getString("tenPhim"));
                    phim.setNgonNgu(rs.getString("ngonNgu"));
                    phim.setDienVien(rs.getString("dienVien"));
                    phim.setNhaSanXuat(rs.getString("nhaSanXuat"));
                    phim.setTheloai(rs.getString("theLoai"));
                    phim.setGiave(rs.getString("giaVe"));
                    phim.setQuocgia(rs.getString("quocGia"));
                    phim.setNgayCongChieu(rs.getString("Ngaycongchieu"));
                    phim.setThoiLuong(rs.getInt("thoiLuong"));
                    list.add(phim);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
