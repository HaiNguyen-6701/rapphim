
package DAO;

import DTO.chi_tiet_hoa_don;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class chiTietHoaDonDAO {
    DB_ConnectionDAO connectionDAO=new DB_ConnectionDAO();  
    public ArrayList<chi_tiet_hoa_don>getListNV(){                     
        ArrayList<chi_tiet_hoa_don> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `chi_tiet_hoa_don`";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    chi_tiet_hoa_don cthd=new chi_tiet_hoa_don();
                    cthd.setIdHoaDon(rs.getInt("idHoaDon"));
                    cthd.setIdPhim(rs.getInt("idPhim"));
                    cthd.setIdKhachhang(rs.getInt("idKhachhang"));
                    cthd.setIdGhe(rs.getString("idGhe"));
                    cthd.setIdPhong(rs.getInt("idPhong"));
                    cthd.setIdNhanvien(rs.getString("idNhanVien"));
                    cthd.setSoLuong(rs.getString("soLuong"));
                    cthd.setGia(rs.getInt("Gia")); 
                    list.add(cthd);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
    public boolean AddCTHD(chi_tiet_hoa_don cthd){
        String sql="INSERT INTO `chi_tiet_hoa_don`"
                + "(`idHoaDon`, `idPhim`, `idKhachhang`, `idGhe`, `idPhong`, `idNhanVien`, `soLuong`, `Gia`) VALUES"
                + " ('"+cthd.getIdHoaDon()+"','"+cthd.getIdPhim()+"','"+cthd.getIdKhachhang()+"','"+cthd.getIdGhe()+"','"+cthd.getIdPhong()+"','"+cthd.getIdNhanvien()+"','"+cthd.getSoLuong()+"','"+cthd.getGia()+"')";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
}
