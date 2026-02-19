package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.db.DBConnection;

public class MemberDAO {

    Connection con = DBConnection.getConnection();

    // Add Member
    public void addMember(String name, String phone, String email) {
        try {
            String query = "INSERT INTO members(name, phone, email) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);

            ps.executeUpdate();
            System.out.println("✅ Member Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Members
    public void viewMembers() {
        try {
            String query = "SELECT * FROM members";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== 👤 MEMBER LIST ==========");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("member_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("phone") + " | " +
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
