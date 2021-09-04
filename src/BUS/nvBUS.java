
package BUS;

import DAO.NvDAO;
import DTO.nhan_vien;
import java.util.ArrayList;

public class nvBUS {
    public nvBUS(){
        
    }
    public boolean AddNV(nhan_vien nv){
        boolean rs=new NvDAO().AddNV(nv);
        return rs;
    }
    public boolean DeleteNV(String idNhanVien){
        boolean rs=new NvDAO().DeleteNV(idNhanVien);
        return rs;
    }
    public boolean EditNV(nhan_vien nv){
        boolean rs=new NvDAO().EditNV(nv);
        return rs;
    }
    public ArrayList<nhan_vien> getList(){
    ArrayList<nhan_vien> list  =new ArrayList<>();
    list = new NvDAO().getListNV();
    return list;
    
}
}
