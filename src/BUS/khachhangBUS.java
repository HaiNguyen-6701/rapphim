
package BUS;

import DAO.khachhangDAO;
import DTO.khach_hang;
import java.util.ArrayList;

public class khachhangBUS {
    public khachhangBUS(){
        
    }
    public boolean AddKH(khach_hang kh){
        boolean rs=new khachhangDAO().AddKH(kh);
        return rs;
    }
    public boolean DeleteKH(String idKhachHang){
        boolean rs=new khachhangDAO().DeleteKH(idKhachHang);
        return rs;
    }
    public boolean EditKH(khach_hang kh){
        boolean rs=new khachhangDAO().EditKH(kh);
        return rs;
    }
    public ArrayList<khach_hang> getList(){
    ArrayList<khach_hang> list  =new ArrayList<>();
    list = new khachhangDAO().getListKH();
    return list;
}
}
