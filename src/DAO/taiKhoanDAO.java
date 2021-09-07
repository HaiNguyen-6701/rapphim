
package DAO;

import DTO.taiKhoan;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class taiKhoanDAO {
    DB_ConnectionDAO connectionDAO=new DB_ConnectionDAO();  
    public ArrayList<taiKhoan>getListTK(){                     
        ArrayList<taiKhoan> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `taiKhoan`";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    taiKhoan tk=new taiKhoan();
                    tk.setIdNV(rs.getInt("IdNV"));
                    tk.setChucVu(rs.getString("Chucvu"));
                    tk.setTenDangNhap(rs.getString("tenDangNhap"));
                    tk.setTenNV(rs.getString("TenNV"));
                    tk.setmKhau(rs.getString("mKhau"));
                    list.add(tk);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;   
    }
    public boolean AddTK(taiKhoan tk){
        String sql="INSERT INTO `taikhoan`"
                + "(`idNV`, `tenDangNhap`, `mKhau`, `TenNV`, `Chucvu`) VALUES "
                + "('"+tk.getIdNV()+"','"+tk.getTenDangNhap()+"','"+tk.getmKhau()+"','"+tk.getTenNV()+"','"+tk.getChucVu()+"')";
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean EditTK(taiKhoan tk){
        String sql="UPDATE `taikhoan` "
                + "SET `idNV`='"+tk.getIdNV()+"',`tenDangNhap`='"+tk.getTenDangNhap()+"',`mKhau`='"+tk.getmKhau()+"',`TenNV`='"+tk.getTenNV()+"',`Chucvu`='"+tk.getChucVu()+"'";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean DeleteTK(int IdNV){
            String sql="DELETE FROM `taikhoan` WHERE idNV='"+IdNV+"'";
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public ArrayList<taiKhoan> FindTK(String attributes) {
        ArrayList<taiKhoan> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `taiKhoan` where (`TenNV` like'%" + attributes + "%') or (`idNV` = '" +attributes+ "')";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    taiKhoan tk=new taiKhoan();
                    tk.setIdNV(rs.getInt("IdNV"));
                    tk.setChucVu(rs.getString("Chucvu"));
                    tk.setTenDangNhap(rs.getString("tenDangNhap"));
                    tk.setTenNV(rs.getString("TenNV"));
                    tk.setmKhau(rs.getString("mKhau"));
                    list.add(tk);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
