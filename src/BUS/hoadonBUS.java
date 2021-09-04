
package BUS;

import DAO.hoadonDAO;
import DTO.Hoadon;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class hoadonBUS {
    public hoadonBUS(){
        
    }
    public boolean AddHD(Hoadon hd){
        boolean rs=new hoadonDAO().AddHD(hd);
        return rs;
    }
    
    public boolean DeleteHD(int idHoadon){
        boolean rs=new hoadonDAO().DeleteHD(idHoadon);
        return rs;
    }
    
    public ArrayList<Hoadon> getList(){
    ArrayList<Hoadon> list  =new ArrayList<>();
    list = new hoadonDAO().getListHD();
    return list;
    
}
}
