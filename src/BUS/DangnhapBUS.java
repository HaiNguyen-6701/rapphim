
package BUS;

import DAO.DangnhapDAO;

public class DangnhapBUS {

    public DangnhapBUS() {
        
    }
    public int check(String User,String Password){
        DangnhapDAO dn=new DangnhapDAO();
        int result = dn.check(User, Password);
        return result;
    }
    
}
