package javaileveritabanınabaglanmaselectinsertupdatedeleteislemleri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author hakan
 */
public class VeriTabanı {
  
    public static void main(String[] args) {
        Baglanti();
    }
    
    public static void Baglanti(){
        try{
            String address = "jdbc:mysql://localhost:3306/world?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
            String address1 = "jdbc:mysql://localhost:3306/Ogrenciler?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
            String username = "root";
            String password = "168200";
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            //JDBC sürücü yüklendi.
            Connection connection1 = DriverManager.getConnection(address1,username,password);
            Connection connection = DriverManager.getConnection(address,username,password);
            //Veri tabanına baglanti olustu.
            Statement state = connection.createStatement();
            Statement state1 = connection.createStatement();            
            
            /*Listeleme*/
            String sql = " SELECT * FROM world.city LIMIT 5 "; 
            ResultSet sonuc = state.executeQuery(sql);   
            
            String sql1 = " SELECT Name FROM world.city WHERE Name LIKE  'A%' LIMIT 5 ";
            ResultSet sonuc1 = state1.executeQuery(sql1);            
            
//            /*Ekleme islemi*/
//            String sql2 = "INSERT INTO Ogrenci(OgrenciNo,OgrenciAd,OgrenciSoyadı,OgrenciBolum) VALUES (?,?,?,?)";
//            PreparedStatement pst = connection1.prepareStatement(sql2);
//            pst.setInt(1, 6);
//            pst.setString(2, "Ali");
//            pst.setString(3,"Can");
//            pst.setString(4,"Bilgisayar Mühendisliği");
//            pst.executeUpdate();
            
            /*Silme islemi*/
            String sql3 = "DELETE FROM Ogrenci Where OgrenciNo=6";
            PreparedStatement pst1 = connection1.prepareStatement(sql3);
            pst1.executeUpdate();
            
//            /*Güncelleme islemi*/
//            String sql4= "UPDATE ogrenci SET OgrenciAd='hakan',OgrenciSoyadı='Sinanoglu' WHERE OgrenciNo=6";
//            PreparedStatement pst2 = connection1.prepareStatement(sql4);
//            pst2.executeUpdate();
            
            
            System.out.println("Sehirlerin ve nufusunun ilk 5'i listele");            
            while(sonuc.next()){ //next: Kaydın sonuna gelene kadar okur.
                System.out.println(sonuc.getString("Name") +" = "+ sonuc.getInt("Population"));
            }
            
            System.out.println("\nİlk Harfi A olan sehirlerin ilk 5'i listele");           
             while(sonuc1.next()){ //next: Kaydın sonuna gelene kadar okur.
                System.out.println(sonuc1.getString("Name") );
            }
             
            System.out.println("\nBasarılı ");
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }   
}
