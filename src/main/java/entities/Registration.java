package entities;

public class Registration {
    private int member_id;
    private String member_name;
    private String team_id;
    private String sport;
    int price;

    public Registration(int member_id, String team_id, int price) {
        this.member_id = member_id;
        this.team_id = team_id;
        this.price = price;
    }

    public Registration(String member_name, String team_id, String sport) {
        this.member_name = member_name;
        this.team_id = team_id;
        this.sport = sport;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getSport() {
        return sport;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "member_id=" + member_id +
                ", team_id='" + team_id + '\'' +
                ", price=" + price +
                '}';
    }
}
