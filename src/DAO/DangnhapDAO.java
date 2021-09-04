
package DAO;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class DangnhapDAO {
    DB_ConnectionDAO connectionDAO=new DB_ConnectionDAO();  
    public int check(String User,String Password){             
        String sql ="SELECT * FROM `taikhoan` WHERE "
                + "tenDangNhap = '"+User+"' AND "
                + "mKhau = '"+Password+"'";
        
        ResultSet rs=connectionDAO.sqlQuery(sql);
        try {
            if(rs.next()){
                
                if(rs.getString("Chucvu").equals("Admin")){
                    connectionDAO.closeConnection();
                    return 1;
                }
                else if(rs.getString("Chucvu").equals("nhân viên")){
                    connectionDAO.closeConnection();
                    return 0;
                }
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(DangnhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectionDAO.closeConnection();
        
     return -1;   
    }
}
