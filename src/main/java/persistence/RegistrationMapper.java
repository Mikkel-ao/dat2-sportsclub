package persistence;

import entities.Member;
import entities.Registration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationMapper {
    private Database database;
    public RegistrationMapper(Database database) {
        this.database = database;
    }


    public boolean addToTeam(Registration registration){
        boolean result = false;
        String sql = "insert into registration (member_id, team_id, price) values (?, ?, ?)";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, registration.getMember_id());
                ps.setString(2, registration.getTeam_id());;
                ps.setInt(3, registration.getPrice());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1){
                    return true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public List<Registration> getAllRegistrations() {

        List<Registration> registrationsList = new ArrayList<>();

        String sql = "select m.name as member_name, t.team_id as team_id, s.sport as sport_name from member m\n" +
                "inner join registration r using(member_id)\n" +
                "inner join team t using(team_id)\n" +
                "inner join sport s using(sport_id)\n" +
                "order by sport_name;";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String member_name = rs.getString("member_name");
                    String team_id = rs.getString("team_id");
                    String sport = rs.getString("sport_name");
                    registrationsList.add(new Registration(member_name, team_id, sport));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return registrationsList;
    }
}
