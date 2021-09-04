/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.khach_hang;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author USer
 */
public class khachhangDAO {
    DB_ConnectionDAO connectionDAO=new DB_ConnectionDAO();  
    public ArrayList<khach_hang>getListKH(){                     
        ArrayList<khach_hang> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `khach_hang`";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    khach_hang kh=new khach_hang();
                    kh.setIdKhachHang(rs.getString("idKhachHang"));
                    kh.setHoTen(rs.getString("hoTen"));
                    kh.setSoDienThoai(  rs.getString("soDienThoai"));
                    kh.setGioiTinh(rs.getString("gioiTinh"));
                    kh.setEmail(rs.getString("email"));
                    kh.setMatKhau(rs.getString("matKhau"));
                    list.add(kh);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;       
    }
    public boolean AddKH(khach_hang kh){
        String sql="INSERT INTO `khach_hang`"
                + "(`idKhachHang`, `hoTen`, `soDienThoai`, `email`, `gioiTinh`, `matKhau`) VALUES"
                + " ('"+kh.getIdKhachHang()+"','"+kh.getHoTen()+"','"+kh.getSoDienThoai()+"','"+kh.getEmail()+"','"+kh.getGioiTinh()+"','"+kh.getMatKhau()+"')";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean EditKH(khach_hang kh){
        String sql="UPDATE `khach_hang` SET `hoTen`='"+kh.getHoTen()+"',`soDienThoai`='"+kh.getSoDienThoai()+"',`email`='"+kh.getEmail()+"',`Gioitinh`='"+kh.getGioiTinh()+"',`matKhau`='"+kh.getMatKhau()+"' WHERE `idKhachHang`='"+kh.getIdKhachHang()+"'";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean DeleteKH(String idKhachHang){
            String sql="DELETE FROM `khach_hang` WHERE idKhachHang='"+idKhachHang+"'";
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public ArrayList<khach_hang> FindKH(String attributes) {
        ArrayList<khach_hang> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `khach_hang` where (`hoTen` like '%" + attributes + "%') or (`idKhachHang` = '" +attributes+ "') or (`soDienThoai` = '" +attributes+ "') or (`email` = '" +attributes+ "')";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    khach_hang kh=new khach_hang();
                    kh.setIdKhachHang(rs.getString("idKhachHang"));
                    kh.setHoTen(rs.getString("hoTen"));
                    kh.setSoDienThoai(  rs.getString("soDienThoai"));
                    kh.setGioiTinh(rs.getString("gioiTinh"));
                    kh.setEmail(rs.getString("email"));
                    kh.setMatKhau(rs.getString("matKhau"));
                    list.add(kh);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
