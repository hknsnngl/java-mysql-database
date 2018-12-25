package EKRAN;

import static BAGLANTI.VT_Baglanti.baglanti;
import DTO.ProductsDTO;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hakan
 */
public class Main_Window extends javax.swing.JFrame {


    public Main_Window() {
        initComponents();   
        Show_ProductsInJTable();//Tabloda gösterme
    }
    
    String ImagePath = null;  
    int pos = 0;
    ///Görüntüyü Yeniden Boyutlandırmak Icin///
    public ImageIcon ResizeImage(String imagePath, byte[] pic){
        
        ImageIcon myImage = null;
        
        if(imagePath != null)
        {
            myImage = new ImageIcon(imagePath);
        }
        else{
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image img2 =img.getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
    ///Input kontrolu///
    public boolean CheckInput(){
        if(txt_AddDate.getDateFormat() == null || txt_Name.getText() == null || txt_Price.getText() == null){
            return false;
        }
        else{
            try{
                Float.parseFloat(txt_Price.getText());
                return true;
            }
            catch(Exception ex){
                return false;
            }
        }
    }
    ///Verileri JTable da gösterimi
    //11-Fill ArrayList ile Veri doldurma
    public ArrayList<ProductsDTO> GetProductList(){
        ArrayList<ProductsDTO> productList = new ArrayList<ProductsDTO>();
        Connection con = baglanti();
        String query = "SELECT * FROM productsdb.products";

        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            ProductsDTO product;
            
            while(rs.next()){ //Tablodaki attribute isimleri
                product = new ProductsDTO(rs.getInt("ProductsId"),rs.getString("ProductsName"),Float.parseFloat(rs.getString("Products_Price")),rs.getString("Products_Date"),rs.getBytes("Products_Image"));
                productList.add(product);
            }
        }
        catch(Exception ex){
            
        }
        
        return productList;
    
    }
    
    ///2-Populate ile JTable
    public void Show_ProductsInJTable(){
        ArrayList<ProductsDTO> list = GetProductList();
        DefaultTableModel model = (DefaultTableModel)jTable_Product.getModel();
        
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getP_id();
            row[1] = list.get(i).getP_Name();
            row[2] = list.get(i).getP_Price();
            row[3] = list.get(i).getP_Date();
            
            model.addRow(row);
        }
    
    }
    
    //Tek bir veri gösterme
    public void ShowItem(int index){
        txt_Id.setText(Integer.toString(GetProductList().get(index).getP_id()));
        txt_Name.setText(GetProductList().get(index).getP_Name());
        txt_Price.setText(Float.toString(GetProductList().get(index).getP_Price()));
        try{
//            Date addDate = null;
//            addDate = new SimpleDateFormat("dd-MM-yyyy").parse((String)GetProductList().get(index).getP_Date()); 
//            txt_AddDate.setDateFormat(addDate);
            
            DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            String inputText = GetProductList().get(index).getP_Date();
            Date date = inputFormat.parse(inputText);
            txt_AddDate.setDateFormat(inputFormat);                                 
        }
        catch(Exception ex){
            
        }
        
        lbl_Image.setIcon(ResizeImage(null,GetProductList().get(index).getP_Image()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_Id = new javax.swing.JTextField();
        txt_Name = new javax.swing.JTextField();
        txt_Price = new javax.swing.JTextField();
        txt_AddDate = new datechooser.beans.DateChooserCombo();
        lbl_Image = new javax.swing.JLabel();
        Btn_Choose_Image = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Product = new javax.swing.JTable();
        Btn_Insert = new javax.swing.JButton();
        Btn_Update = new javax.swing.JButton();
        Btn_Delete = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn__Previous = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel1.setText("ID ");

        jLabel2.setText("Name");

        jLabel3.setText("Price");

        jLabel4.setText("Add Date");

        jLabel5.setText("Image");

        txt_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdActionPerformed(evt);
            }
        });

        lbl_Image.setOpaque(true);

        Btn_Choose_Image.setText("Choose Image");
        Btn_Choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choose_ImageActionPerformed(evt);
            }
        });

        jTable_Product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Price", "Date"
            }
        ));
        jTable_Product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Product);

        Btn_Insert.setText("Insert");
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        Btn_Update.setText("Update");
        Btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_UpdateActionPerformed(evt);
            }
        });

        Btn_Delete.setText("Delete");
        Btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DeleteActionPerformed(evt);
            }
        });

        Btn_First.setText("First");
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        Btn_Next.setText("Next");
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        Btn__Previous.setText("Previous");
        Btn__Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn__PreviousActionPerformed(evt);
            }
        });

        Btn_Last.setText("Last");
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Btn_Choose_Image)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_AddDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_Id)
                                    .addComponent(txt_Name)
                                    .addComponent(txt_Price)
                                    .addComponent(lbl_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btn_Insert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Delete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btn_First)
                        .addGap(92, 92, 92)
                        .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn__Previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Last))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Choose_Image)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Insert)
                    .addComponent(Btn_Update)
                    .addComponent(Btn_Delete)
                    .addComponent(Btn_First)
                    .addComponent(Btn_Next)
                    .addComponent(Btn__Previous)
                    .addComponent(Btn_Last))
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdActionPerformed

    ///Resim Secme///
    private void Btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choose_ImageActionPerformed
        
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_Image.setIcon(ResizeImage(path,null));
            ImagePath = path;
                   
        }
        else{
            System.out.println("Resim secilmedi!");
            JOptionPane.showMessageDialog(null, "Resim secilmedi!");
        }
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        if(CheckInput() && ImagePath != null){
            try{
                Connection con = baglanti();
                PreparedStatement ps = con.prepareStatement("insert into productsdb.products (ProductsName,Products_Price,Products_Date,Products_Image)" 
                        +  "values(?,?,?,?)");                         
                ps.setString(1,txt_Name.getText());
                ps.setString(2,txt_Price.getText());  
               
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
                String addDate = sdf.format(txt_AddDate.getSelectedDate().getTime());
                ps.setString(3,addDate);          

                InputStream img = new FileInputStream(new File(ImagePath));
                ps.setBlob(4,img);                
                
                ps.executeUpdate(); 
                Show_ProductsInJTable();//Tabloda gösterme

                JOptionPane.showMessageDialog(null,"Eklendi!!!");

            }catch(Exception ex){
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Eklenmedi!!!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Bir yada daha fazla alan boş!!!");
        }
        System.out.println(txt_Name.getText());
        System.out.println(txt_Price.getText());
        System.out.println(txt_AddDate.getText());
        System.out.println(ImagePath); 

        
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void Btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_UpdateActionPerformed
        
        if(CheckInput() && txt_Id.getText() != null ){
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = baglanti();
            ///Resim olmadan ki güncelleştirme
            if(ImagePath == null)
            {
                try {
                    UpdateQuery = "UPDATE productsdb.products "
                            + "SET ProductsName = ?,"
                            + "Products_Price = ?,"
                            + "Products_AddDate = ?,"
                            + "WHERE ProductsId = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1,txt_Name.getText());
                    ps.setString(2,txt_Price.getText());  

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String addDate = sdf.format(txt_AddDate.getSelectedDate().getTime());
                    ps.setString(3,addDate);          

                    ps.setInt(4,Integer.parseInt(txt_Id.getText()));
                    
                    ps.executeUpdate(); 

                } 
                catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ///Resim ile güncellemeştirme
            else{
                try{
                InputStream img = new FileInputStream(new File(ImagePath));
                UpdateQuery = "UPDATE productsdb.products "
                            + "SET ProductsName = ?,"
                            + "Products_Price = ?,"
                            + "Products_Date = ?,"
                            + "Products_Image = ?"
                            + "WHERE ProductsId = ?";
                
                ps = con.prepareStatement(UpdateQuery);
                    
                ps.setString(1,txt_Name.getText());
                ps.setString(2,txt_Price.getText());  

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String addDate = sdf.format(txt_AddDate.getSelectedDate().getTime());
                ps.setString(3,addDate);    
                
                ps.setBlob(4,img);

                ps.setInt(5,Integer.parseInt(txt_Id.getText()));                

                ps.executeUpdate(); 
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        }else{
                JOptionPane.showMessageDialog(null,"Bir yada daha fazla alan bış yada hatalı!!!");
            
        }
        JOptionPane.showMessageDialog(null, "Guncelleme basarılı");
    }//GEN-LAST:event_Btn_UpdateActionPerformed

    private void Btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DeleteActionPerformed
        
        if(!txt_Id.getText().equals("")){
            try {
                Connection con = baglanti();
                PreparedStatement ps = con.prepareStatement("DELETE FROM productsdb.products WHERE ProductsId = ? ");
                int id  = Integer.parseInt(txt_Id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Sılme Basarılı!!!");
            
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Sılme Basarısız!!!");
            }            
        }else{
            JOptionPane.showMessageDialog(null,"Id yazılıp yazılmadığına kontrol edin!!! ");
        }
    }//GEN-LAST:event_Btn_DeleteActionPerformed

    private void jTable_ProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ProductMouseClicked

        int index = jTable_Product.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_jTable_ProductMouseClicked

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
       
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        
        pos = GetProductList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
        
        pos++;
        if(pos >= GetProductList().size()){
            pos = GetProductList().size()-1;        
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn__PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn__PreviousActionPerformed
        
        pos--;
        if(pos < 0){
            pos = 0;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn__PreviousActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JButton Btn_Delete;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Update;
    private javax.swing.JButton Btn__Previous;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Product;
    private javax.swing.JLabel lbl_Image;
    private datechooser.beans.DateChooserCombo txt_AddDate;
    private javax.swing.JTextField txt_Id;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JTextField txt_Price;
    // End of variables declaration//GEN-END:variables
}
