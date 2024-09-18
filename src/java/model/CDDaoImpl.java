package model;

import entity.CDDetails;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CDDaoImpl implements CDDao {

    private Connection conn;

    public CDDaoImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public boolean addCds(CDDetails cd) {
        boolean f = false;
        try {
            String sql = "insert into cd_details(cdName, artist, price, cdCategory, status, quantity, photo, sold) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cd.getCdName());
            ps.setString(2, cd.getArtist());
            ps.setString(3, cd.getPrice());
            ps.setString(4, cd.getCdCategory());
            ps.setString(5, cd.getStatus());
            ps.setInt(6, cd.getQuantity());
            ps.setString(7, cd.getPhoto());
            ps.setInt(8, cd.getSold());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<CDDetails> getAllCDs() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<CDDetails> getAllCDsBySold() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details order by sold desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void updateSold(CDDetails cd, int sold) {
        try {
            String sql = "update cd_details set sold  += ? where cdId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sold);
            ps.setInt(2, cd.getCdId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CDDetails getCDById(int cdId) {
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cdId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cd;
    }

    @Override
    public boolean editCds(CDDetails cd) {
        boolean f = false;
        try {
            String sql = "update cd_details set cdName = ?, artist = ?, price = ?, status = ?, quantity = ?"
                    + " where cdId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cd.getCdName());
            ps.setString(2, cd.getArtist());
            ps.setString(3, cd.getPrice());
            ps.setString(4, cd.getStatus());
            ps.setInt(5, cd.getQuantity());
            ps.setInt(6, cd.getCdId());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public boolean deleteCds(int cdId) {
        String sql = "delete from cd_details where cdId = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cdId);

            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<CDDetails> getNewArrivals() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "New");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            while (rs.next() && i <= 4) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CDDetails> getTrending() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Trend");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            while (rs.next() && i <= 4) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CDDetails> getSale() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Sale");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            while (rs.next() && i <= 4) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CDDetails> getAllProducts() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 1;
            while (rs.next() && i <= 4) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CDDetails> allProducts() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CDDetails> newArrivals() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "New");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CDDetails> trending() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Trend");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CDDetails> sale() {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Sale");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateCDQuantity(int cdId, int quantity) {
        boolean f = false;
        try {
            String sql = "update cd_details set quantity = ? where cdId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cdId);

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean updateCDStatus(int cdId, String status) {
        boolean f = false;
        try {
            String sql = "update cd_details set status = ? where cdId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, cdId);

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    
    public List<CDDetails> getCdBySearch(String key) {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdName like ? or artist like ? order by status";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<CDDetails> getRelatedCDs(CDDetails c) {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? and cdId != ? and status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getCdCategory());
            ps.setInt(2, c.getCdId());
            ps.setString(3, "Active");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            while (rs.next() && i <= 4) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<CDDetails> allProducts(String orderBy) {
        List<CDDetails> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM cd_details ORDER BY " + orderBy;
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CDDetails cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<CDDetails> newArrivals(String orderBy) {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by " + orderBy;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "New");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));
                cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<CDDetails> trending(String orderBy) {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by " + orderBy;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Trend");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CDDetails> sale(String orderBy) {
        List<CDDetails> list = new ArrayList<>();
        CDDetails cd = null;
        try {
            String sql = "select * from cd_details where cdCategory = ? order by " + orderBy;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Sale");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cd = new CDDetails();
                cd.setCdId(rs.getInt(1));
                cd.setCdName(rs.getString(2));
                cd.setArtist(rs.getString(3));
                cd.setPrice(rs.getString(4));
                cd.setCdCategory(rs.getString(5));
                cd.setStatus(rs.getString(6));
                cd.setPhoto(rs.getString(7));
                cd.setQuantity(rs.getInt(8));cd.setSold(rs.getInt(9));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
