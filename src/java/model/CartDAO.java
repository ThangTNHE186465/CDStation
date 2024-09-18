package model;

import entity.Cart;
import java.util.List;

public interface CartDAO {
    
    public boolean addcart(Cart c);
    
    public List<Cart> getCdByUser(int userId);
    
    public boolean removeCD(int cdId, int uId, int cId);
}
