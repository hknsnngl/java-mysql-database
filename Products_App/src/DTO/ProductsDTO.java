package DTO;

/**
 *
 * @author hakan
 */
public class ProductsDTO {
    
    private int products_id;
    private String products_Name;
    private float products_Price;
    private String products_Date;
    private byte[] products_Image;
     
    public ProductsDTO(){
        
    }
    
    public ProductsDTO(int p_id, String p_name, float p_price, String p_date, byte[] p_ımage){
        this.products_id = p_id;
        this.products_Name = p_name;
        this.products_Price = p_price;
        this.products_Date = p_date;
        this.products_Image = p_ımage;
    }
    
    public int getP_id() {
        return this.products_id;
    }

    
    public String getP_Name(){
        return this.products_Name;
    }

    
    public float getP_Price(){
        return this.products_Price;
    }

    
    public String getP_Date(){
        return this.products_Date;
    }

    
    public byte[] getP_Image(){
        return this.products_Image;
    }

}
