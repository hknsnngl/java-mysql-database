package BAGLANTI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hakan
 */
public class VT_Baglanti {
    public static Connection conn;
    public static final String durl = "jdbc:mysql://localhost:3306/productsdb?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    public static final String user = "root";
    public static final String password = "168200";
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    
    public static Connection   baglanti(){ 
        try{
            Class.forName(driver).newInstance();   
            conn = DriverManager.getConnection(durl, user, password) ;    
            System.out.println("VT baglanti  basarili");
            ///JOptionPane.showMessageDialog(null, "VT baglanti basarili");
            return conn;
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){           
            Logger.getLogger(VT_Baglanti.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("veri tabanı bağlanti sorun var");
            JOptionPane.showMessageDialog(null, "VT baglanti basarisiz");
            return null;
        }
             
    }
    
    public static void baglantiKapat(Connection p_con){
        try{
            if(!p_con.isClosed()){
                p_con.close();
            }
        }
        catch(SQLException e){
            System.out.println("VT baglanti kapanirken sorun var");
        }
    }
}
