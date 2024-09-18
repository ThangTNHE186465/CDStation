package entity;

public class CDDetails {
    
    private int cdId, quantity, sold;
    private String cdName, artist, price, cdCategory, status, photo;

    public CDDetails() {
    }

    public CDDetails(String cdName, String artist, String price, String cdCategory, String status, String photo, int quantity, int sold) {
        this.cdName = cdName;
        this.artist = artist;
        this.price = price;
        this.cdCategory = cdCategory;
        this.status = status;
        this.photo = photo;
        this.quantity = quantity;
        this.sold = sold;
    }

    public int getCdId() {
        return cdId;
    }

    public void setCdId(int cdId) {
        this.cdId = cdId;
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCdCategory() {
        return cdCategory;
    }

    public void setCdCategory(String cdCategory) {
        this.cdCategory = cdCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
