
package BUS;

import DAO.taiKhoanDAO;
import DTO.taiKhoan;
import java.util.ArrayList;

public class taiKhoanBUS {
    public taiKhoanBUS(){
        
    }
    public boolean AddTK(taiKhoan tk){
        boolean rs=new taiKhoanDAO().AddTK(tk);
        return rs;
    }
    public boolean EditTK(taiKhoan tk){
        boolean rs=new taiKhoanDAO().EditTK(tk);
        return rs;
    }
    public boolean DeleteTK(int IdNV){
        boolean rs=new taiKhoanDAO().DeleteTK(IdNV);
        return rs;
    }
    public ArrayList<taiKhoan> getList(){
    ArrayList<taiKhoan> list  =new ArrayList<>();
    list = new taiKhoanDAO().getListTK();
    return list;
    }
}
