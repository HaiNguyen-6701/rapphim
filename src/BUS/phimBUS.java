
package BUS;

import DAO.phimDAO;
import DTO.Phim;
import java.util.ArrayList;

public class phimBUS {
    public phimBUS(){
        
    }
    public boolean AddPhim(Phim phim){
        boolean rs=new phimDAO().AddPhim(phim);
        return rs;
    }
    public boolean EditPhim(Phim phim){
        boolean rs=new phimDAO().EditPhim(phim);
        return rs;
    }
    public boolean DeletePhim(int idPhim){
        boolean rs=new phimDAO().DeletePhim(idPhim);
        return rs;
    }
    
    public ArrayList<Phim> getList(){
    ArrayList<Phim> list  =new ArrayList<>();
    list = new phimDAO().getListPhim();
    return list;
}
}
