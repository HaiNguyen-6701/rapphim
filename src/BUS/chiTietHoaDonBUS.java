
package BUS;

import DAO.chiTietHoaDonDAO;
import DTO.chi_tiet_hoa_don;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class chiTietHoaDonBUS {
    public chiTietHoaDonBUS(){
        
    }
    public boolean AddCTHD(chi_tiet_hoa_don cthd){
        boolean rs=new chiTietHoaDonDAO().AddCTHD(cthd);
        return rs;
    }
    
    public ArrayList<chi_tiet_hoa_don> getList(){
    ArrayList<chi_tiet_hoa_don> list  =new ArrayList<>();
    list = new chiTietHoaDonDAO().getListNV();
    return list;
    
}
}
