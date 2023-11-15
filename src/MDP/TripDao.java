package MDP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDao extends AbstractDAOA{
	public void add(Trip obj) {
        PreparedStatement pst = null;
        String sql = "insert into Trip (departure_id, destination_id, price) values (?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, obj.getDeparture().getId());
            pst.setLong(2, obj.getDestination().getId());
            pst.setDouble(3, obj.getPrice());
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    public void delete(long id) {
        PreparedStatement pst = null;
        String sql = "delete from Trip where id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    public Trip getOne(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from Trip where id= ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            PlaceDao tmp = new PlaceDao();
            Place departure = tmp.getOne(rs.getLong("departure_id"));
            Place destination = tmp.getOne(rs.getLong("destination_id"));
            if (rs.next()) {
                System.out.println(rs.getLong("id") + "" + departure.getName() + " " + destination.getName() +" " + rs.getDouble("price"));
                return new Trip(rs.getLong("id"), departure, destination, rs.getDouble("price"));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    public List<Trip> getAll() {
        List<Trip> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from Trip";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            PlaceDao tmp = new PlaceDao();
            
            while (rs.next()) {
                Place departure = tmp.getOne(rs.getLong("departure_id"));
                Place destination = tmp.getOne(rs.getLong("destination_id"));
                System.out.println(rs.getLong("id") + "" + departure.getName() + " " + destination.getName() +" " + rs.getDouble("price"));
                list.add(new Trip(rs.getLong("id"), departure, destination, rs.getDouble("price")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }

    
    public List<Trip> getAll(long deprt, long destn) {
        List<Trip> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "select * from Trip where departure_id =  ? or destination_id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, deprt);
            pst.setLong(2, destn);
            rs = pst.executeQuery();
            PlaceDao tmp = new PlaceDao();
            while (rs.next()) {
            	Place departure = tmp.getOne(rs.getLong("departure_id"));
                Place destination = tmp.getOne(rs.getLong("destination_id"));
                System.out.println(rs.getLong("id") + "" + departure.getName() + " " + destination.getName() +" " + rs.getDouble("price"));
                list.add(new Trip(rs.getLong("id"), departure, destination, rs.getDouble("price")));
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return list;
    }
}
