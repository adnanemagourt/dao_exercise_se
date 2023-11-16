package MDP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDao extends AbstractDAOA {

    public void add(Place obj) {
        PreparedStatement pst = null;
        String sql = "insert into Place (name) values (?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, obj.getName());
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    public void delete(long id) {
        PreparedStatement pst = null;
        String sql = "delete from Place where id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }
    
    public void edit(long id, String name) {
    	PreparedStatement pst = null;
        String sql = "update Place set name = ? where id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, name);
            pst.setLong(2, id);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }
    
    public Place getOne(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from Place where id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getLong("id") + ": " + rs.getString("name"));
                return new Place(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    public List<Place> getAll() {
        List<Place> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from Place";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + ": " + rs.getString("name"));
                list.add(new Place(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    
    public List<Place> getAll(String nm) {
        List<Place> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from place where name like ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nm + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("id") + ": " + rs.getString("name"));
                list.add(new Place(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
}
