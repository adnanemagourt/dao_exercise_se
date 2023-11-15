package MDP;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl extends AbstractDAOA implements produitIdao {

    @Override
    public void add(Produit obj) {
        PreparedStatement pst = null;
        String sql = "insert into Produit (id,designation, qte, prix,date) values (?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, obj.getId());
            pst.setString(2, obj.getDesignation());
            pst.setInt(3, obj.getQte());
            pst.setDouble(4, obj.getPrix());
            Date date = Date.valueOf(obj.getDate());
            pst.setDate(5, date);

            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        String sql = "delete from produit where id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public Produit getOne(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from produit where id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getLong("id") + "" + rs.getString("designation"));
                Date date = rs.getDate("date");
                return new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"), rs.getDouble("prix"), date.toLocalDate());
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from produit";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + "" + rs.getString("designation"));
                Date date = rs.getDate("date");
                list.add(new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"), rs.getDouble("prix"), date.toLocalDate()));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Produit> getAll(String des) {
        List<Produit> list = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from produit where designation like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, des + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + "" + rs.getString("designation"));
                Date date = rs.getDate("date");
                list.add(new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"), rs.getDouble("prix"), date.toLocalDate()));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
}