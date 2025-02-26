package app;

import entities.Member;
import entities.Registration;
import persistence.Database;
import persistence.MemberMapper;
import persistence.RegistrationMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {

    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";
    private final static String URL = "jdbc:postgresql://localhost:5432/sportsclub";

    public static void main(String[] args) throws SQLException {

        Database db = new Database(USER, PASSWORD, URL);
        MemberMapper memberMapper = new MemberMapper(db);
        RegistrationMapper registrationMapper = new RegistrationMapper(db);
        List<Member> members = memberMapper.getAllMembers();


        showMembers(members);
        showMemberById(memberMapper, 13);

        // Assignment method calls:
        numOfMembers(memberMapper); // Presents total number of members
        numOfMen(memberMapper); // Presents total number of male members
        numOfWomen(memberMapper); // Presents total number of female members
        teamMemberCount(memberMapper); // Presents number of members for every team
        sportMemberCount(memberMapper); // Present number of members for every sport
        totalTeamIncome(memberMapper); // Presents total income for all teams
        eachTeamIncome(memberMapper); // Present total income for each team
        averageTeamPayment(memberMapper); // Present average payment for each team

        // Task 14 - RegistrationMapper method
        // registrationMapper.addToTeam(new Registration(13, "ten01", 175));

        // In the method there's a for loop to print out the list.
        getAllRegistrations(registrationMapper);  // Call the method here


        /*  
            int newMemberId = insertMember(memberMapper);
            deleteMember(newMemberId, memberMapper);
            showMembers(members);
            updateMember(13, memberMapper);
        */
    }
    private static void numOfMembers(MemberMapper memberMapper) throws SQLException {
        int count = memberMapper.numOfMembers();
        System.out.println("***** Members Count *****");
        System.out.println("Total number of members: " + count);
    }

    private static void numOfMen(MemberMapper memberMapper) throws SQLException {
        int count = memberMapper.numOfMen();
        System.out.println("***** Male Members Count *****");
        System.out.println("Total number of men: " + count);
    }

    private static void numOfWomen(MemberMapper memberMapper) throws SQLException {
        int count = memberMapper.numOfWomen();
        System.out.println("***** Female Members Count *****");
        System.out.println("Total number of women: " + count);
    }

    private static void teamMemberCount(MemberMapper memberMapper) throws SQLException {
        System.out.println("***** Team Member Count *****");
        Map<String, Integer> memberCountPerTeam = memberMapper.teamMemberCount();
        for (Map.Entry<String, Integer> entry : memberCountPerTeam.entrySet()) {
            System.out.println("team_id: " + entry.getKey() + "   Members: " + entry.getValue());
        }
    }

    private static void sportMemberCount(MemberMapper memberMapper) throws SQLException {
        System.out.println("***** Sport Member Count *****");
        Map<String, Integer> memberCountPerSport = memberMapper.sportMemberCount();
        for (Map.Entry<String, Integer> entry : memberCountPerSport.entrySet()) {
            System.out.println("sport_id: " + entry.getKey() + "   Members: " + entry.getValue());
        }
    }

    private static void totalTeamIncome(MemberMapper memberMapper) throws SQLException {
        int total = memberMapper.totalTeamIncome();
        System.out.println("***** Total Team Income *****");
        System.out.println("Total number of team income: " + total);
    }

    private static void eachTeamIncome(MemberMapper memberMapper) throws SQLException {
        System.out.println("***** Each Team Income *****");
        Map<String, Integer> teamIncome = memberMapper.eachTeamIncome();
        for (Map.Entry<String, Integer> entry : teamIncome.entrySet()) {
            System.out.println("team_id: " + entry.getKey() + "   income: " + entry.getValue());
        }
    }

    private static void averageTeamPayment(MemberMapper memberMapper) throws SQLException {
        System.out.println("***** Average Team Payment *****");
        Map<String, Integer> avgPayment = memberMapper.averageTeamPayment();
        for (Map.Entry<String, Integer> entry : avgPayment.entrySet()) {
            System.out.println("team_id: " + entry.getKey() + "   payment: " + entry.getValue());
        }
    }

    private static void getAllRegistrations(RegistrationMapper registrationMapper) throws SQLException {
        System.out.println("***** All Registrations *****");
        List<Registration> registrations = registrationMapper.getAllRegistrations();

        for (Registration registration : registrations) {
            System.out.println("Member: " + registration.getMember_name() +
                    ", Team: " + registration.getTeam_id() +
                    ", Sport: " + registration.getSport());
        }
    }


    private static void deleteMember(int memberId, MemberMapper memberMapper) {
        if (memberMapper.deleteMember(memberId)){
            System.out.println("Member with id = " + memberId + " is removed from DB");
        }
    }

    private static int insertMember(MemberMapper memberMapper) {
        Member m1 = new Member("Ole Olsen", "Banegade 2", 3700, "Rønne", "m", 1967);
        Member m2 = memberMapper.insertMember(m1);
        showMemberById(memberMapper, m2.getMemberId());
        return m2.getMemberId();
    }

    private static void updateMember(int memberId, MemberMapper memberMapper) {
        Member m1 = memberMapper.getMemberById(memberId);
        m1.setYear(1970);
        if(memberMapper.updateMember(m1)){
            showMemberById(memberMapper, memberId);
        }
    }

    private static void showMemberById(MemberMapper memberMapper, int memberId) {
        System.out.println("***** Vis medlem nr. 13: *******");
        System.out.println(memberMapper.getMemberById(memberId).toString());
    }

    private static void showMembers(List<Member> members) {
        System.out.println("***** Vis alle medlemmer *******");
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }

}
