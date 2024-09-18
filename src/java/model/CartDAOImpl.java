package model;

import entity.CDDetails;
import entity.Cart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    private Connection conn;

    public CartDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public boolean addcart(Cart c) {
        boolean f = false;
        try {
            String sql = "insert into cart(cdId, [uId], quantity, total_price) "
                    + "values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCdId());
            ps.setInt(2, c.getuId());
            ps.setInt(3, c.getQuantity());
            ps.setFloat(4, c.getTotal_price());

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
    public List<Cart> getCdByUser(int userId) {
        List<Cart> list = new ArrayList<>();
        Cart c = null;
        float totalPrice = 0;
        try {
            String sql = """
                         select cId, c.cdId, uId, cdName, artist, price, c.quantity, total_price 
                         from cart c join [user] u on c.uId = u.id 
                         join cd_details cd on cd.cdId = c.cdId 
                         where uId = ?""";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cart();
                c.setcId(rs.getInt(1));
                c.setCdId(rs.getInt(2));
                c.setuId(userId);
                c.setCdName(rs.getString(4));
                c.setArtist(rs.getString(5));
                c.setPrice(rs.getFloat(6));
                c.setQuantity(rs.getInt(7));
                totalPrice = totalPrice + rs.getFloat(8);
                c.setTotal_price(totalPrice);

                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean removeCD(int cdId, int uId, int cId) {
        boolean f = false;
        try {
            String sql = "delete from cart where cdId = ? and uId = ? and cId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cdId);
            ps.setInt(2, uId);
            ps.setInt(3, cId);
            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean deleteCartByUserId(int uId) {
        boolean f = false;
        try {
            String sql = "delete from cart where uId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, uId);
            int i = ps.executeUpdate();
            if (i > 0) {
                f = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public Cart getCartByUserAndCdId(int userId, int cdId) {
        Cart c = null;
        try {
            String sql = "SELECT * FROM cart WHERE uId = ? AND cdId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, cdId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cart();
                c.setcId(rs.getInt("cId"));
                c.setCdId(rs.getInt("cdId"));
                c.setuId(rs.getInt("uId"));
                c.setQuantity(rs.getInt("quantity"));
                c.setTotal_price(rs.getFloat("total_price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public boolean updateCartQuantity(int cartId, int quantity, float totalPrice) {
        boolean f = false;
        try {
            String sql = "UPDATE cart SET quantity = ?, total_price = ? WHERE cId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setFloat(2, totalPrice);
            ps.setInt(3, cartId);
            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}
