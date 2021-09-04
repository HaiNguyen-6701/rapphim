
package DAO;

import DTO.Hoadon;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class hoadonDAO {
    DB_ConnectionDAO connectionDAO=new DB_ConnectionDAO();  
    public ArrayList<Hoadon>getListHD(){                     
        ArrayList<Hoadon> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `Hoadon`";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Hoadon hd=new Hoadon();
                    hd.setIdHoaDon(rs.getInt("idHoadon"));
                    hd.setIdNhanvien(rs.getInt("idNV"));
                    hd.setIdKhachhang(rs.getInt("idKH"));
                    hd.setTongGia(rs.getInt("TongGia"));
                    hd.setNgayLap(rs.getString("ngayLap"));                 
                    list.add(hd);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
    public boolean AddHD(Hoadon hd){
        String sql="INSERT INTO `hoadon`"
                + "(`idHoadon`, `idNV`, `idKH`, `TongGia`, `ngayLap`) VALUES"
                + " ('"+hd.getIdHoaDon()+"','"+hd.getIdNhanvien()+"','"+hd.getIdKhachhang()+"','"+hd.getTongGia()+"','"+hd.getNgayLap()+"')";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    
    public boolean DeleteHD(int idHoadon){
        String sql="DELETE FROM `hoadon` WHERE idHoadon='"+idHoadon+"'";
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    
    public ArrayList<Hoadon> FindHD(String attributes) {
        ArrayList<Hoadon> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `Hoadon` where(`idHoadon` = '" +attributes+ "') or (`idNV` = '" +attributes+ "') or (`idKH` = '" +attributes+ "') or (`ngayLap` = '" +attributes+ "')";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    Hoadon hd=new Hoadon();
                    hd.setIdHoaDon(rs.getInt("idHoadon"));
                    hd.setIdNhanvien(rs.getInt("idNV"));
                    hd.setIdKhachhang(rs.getInt("idKH"));
                    hd.setTongGia(rs.getInt("TongGia"));
                    hd.setNgayLap(rs.getString("ngayLap"));                 
                    list.add(hd);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
