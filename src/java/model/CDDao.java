package model;

import entity.CDDetails;
import java.util.List;

public interface CDDao {
    
    public boolean addCds(CDDetails cd);
    
    public List<CDDetails> getAllCDs();
    
    public CDDetails getCDById(int cdId);
    
    public boolean editCds(CDDetails cd);
    
    public boolean deleteCds(int cdId);
    
    public List<CDDetails> getAllProducts();
    
    public List<CDDetails> getNewArrivals();
    
    public List<CDDetails> getTrending();
    
    public List<CDDetails> getSale();
    
    public List<CDDetails> allProducts();
    
    public List<CDDetails> newArrivals();
    
    public List<CDDetails> trending();
    
    public List<CDDetails> sale();
}
