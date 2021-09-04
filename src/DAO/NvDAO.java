/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.nhan_vien;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author USer
 */
public class NvDAO {
    DB_ConnectionDAO connectionDAO=new DB_ConnectionDAO();  
    public ArrayList<nhan_vien>getListNV(){                     
        ArrayList<nhan_vien> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `nhan_vien`";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    nhan_vien nv=new nhan_vien();
                    nv.setIdNhanVien(rs.getString("idNhanVien"));
                    nv.setHoTen(rs.getString("hoTen"));
                    nv.setSoDienThoai(rs.getString("soDienThoai"));
                    nv.setEmail(rs.getString("email"));
                    nv.setGioitinh(rs.getString("Gioitinh"));
                    nv.setChucVu(rs.getString("Chucvu"));
                    list.add(nv);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
        
    }
    public boolean AddNV(nhan_vien nv){
        String sql="INSERT INTO `nhan_vien`"
                + "(`idNhanVien`, `hoTen`, `soDienThoai`, `email`, `Gioitinh`, `Chucvu`) VALUES"
                + " ('"+nv.getIdNhanVien()+"','"+nv.getHoTen()+"','"+nv.getSoDienThoai()+"','"+nv.getEmail()+"','"+nv.getGioitinh()+"','"+nv.getChucVu()+"')";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean EditNV(nhan_vien nv){
        String sql="UPDATE `nhan_vien` SET `hoTen`='"+nv.getHoTen()+"',`soDienThoai`='"+nv.getSoDienThoai()+"',`email`='"+nv.getEmail()+"',`Gioitinh`='"+nv.getGioitinh()+"',`Chucvu`='"+nv.getChucVu()+"' WHERE `idNhanVien`='"+nv.getIdNhanVien()+"'";                
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean DeleteNV(String idNhanVien){
        String sql="DELETE FROM `nhan_vien` WHERE idNhanVien='"+idNhanVien+"'";
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public boolean findNVByID(String idNhanVien) {
        String sql="SELECT * FROM `nhan_vien` WHERE idNhanVien'"+idNhanVien+"'";
        boolean rs=connectionDAO.sqlUpdate(sql);
        connectionDAO.closeConnection();
        return rs;
    }
    public ArrayList<nhan_vien> FindNV(String attributes) {
        ArrayList<nhan_vien> list  =new ArrayList<>();
        try {
            
            String sql="SELECT * FROM `nhan_vien` where (`hoTen` like '%" + attributes + "%') or (`idNhanVien` = '" + attributes + "') or (`soDienThoai` = '" +attributes+ "') or (`email` = '" +attributes+ "')";
            ResultSet rs=connectionDAO.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    nhan_vien nv=new nhan_vien();
                    nv.setIdNhanVien(rs.getString("idNhanVien"));
                    nv.setHoTen(rs.getString("hoTen"));
                    nv.setSoDienThoai(rs.getString("soDienThoai"));
                    nv.setEmail(rs.getString("email"));
                    nv.setGioitinh(rs.getString("Gioitinh"));
                    nv.setChucVu(rs.getString("Chucvu"));
                    list.add(nv);
                }
            }
            connectionDAO.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
}
