package DAO;
import java.sql.*;
import javax.swing.JOptionPane;

public class DB_ConnectionDAO {
   private String ipAddress="localhost:3306";
   private String userDB="root";
   private String passDB="";
   private String nameDatabase="rapphim1";
   private String urlDtb="jdbc:mysql://"+ipAddress+"/"+nameDatabase+"?useUnicode=true&characterEncoding=utf-8";
    Connection conn=null;
    Statement sttm=null;
    ResultSet rs=null;
    public DB_ConnectionDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(urlDtb, userDB, passDB);
            sttm=conn.createStatement();
            if(conn!=null){
                System.out.println("Kết nối Database thành công!");
            }
        } catch (Exception e) {
            System.out.println("Kết nối Database thất bại!");
        }   
    }
    public void closeConnection() { //    Hàm đóng kết nối 
        try {
            if(conn!=null){
            conn.close();
        }
        if (sttm!=null) {
            sttm.close();  
        }
        if(rs!=null){
            rs.close();
        }
         System.out.println("Success! Đóng kết nối tới '" + nameDatabase + "' thành công.\n**");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Không thể đóng kết nối tới " + nameDatabase + "\n" + e.getLocalizedMessage());
        }
    }
    public boolean  checkConnect(){
        if(conn==null||sttm==null){
            return false;
        }
        return true;
    }
//    Hàm sqlUpdate (insert update delete)
    public boolean  sqlUpdate(String sql){
        if(checkConnect()){
        try {
            sttm.executeUpdate(sql);
            return true;
            
        } catch (Exception e) {
            System.out.println("Update thất bại!"+sql);
            return false;
        }
        
        }
        return false;
    }
//    Ham sqlQuery(Slect)
    public ResultSet sqlQuery(String sql){
        if(checkConnect()){
            try {
                rs=sttm.executeQuery(sql);
                return rs;
            } catch (Exception e) {
                System.out.println("Truy vấn thất bại!"+sql);
                return null;
            }
        }
        return null;
    }
}
