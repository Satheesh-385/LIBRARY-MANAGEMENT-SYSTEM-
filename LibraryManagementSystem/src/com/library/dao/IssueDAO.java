package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.db.DBConnection;

public class IssueDAO {

    Connection con = DBConnection.getConnection();

    // Issue Book
    public void issueBook(int bookId, int memberId) {
        try {
            // Check quantity
            String checkQuery = "SELECT quantity FROM books WHERE book_id=?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setInt(1, bookId);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                int qty = rs.getInt("quantity");

                if (qty > 0) {
                    // Insert issue record
                    String issueQuery = "INSERT INTO issued_books(book_id, member_id, issue_date, status) VALUES(?,?,SYSDATE,?)";
                    PreparedStatement ps = con.prepareStatement(issueQuery);

                    ps.setInt(1, bookId);
                    ps.setInt(2, memberId);
                    ps.setString(3, "Issued");

                    ps.executeUpdate();

                    // Reduce quantity
                    String updateQty = "UPDATE books SET quantity = quantity - 1 WHERE book_id=?";
                    PreparedStatement updatePs = con.prepareStatement(updateQty);
                    updatePs.setInt(1, bookId);
                    updatePs.executeUpdate();

                    System.out.println("✅ Book Issued Successfully!");
                } else {
                    System.out.println("❌ Book Out of Stock!");
                }

            } else {
                System.out.println("❌ Book ID Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return Book
    public void returnBook(int issueId) {
        try {
            // Get book_id for this issue_id
            String getBookQuery = "SELECT book_id FROM issued_books WHERE issue_id=? AND status='Issued'";
            PreparedStatement getPs = con.prepareStatement(getBookQuery);
            getPs.setInt(1, issueId);

            ResultSet rs = getPs.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");

                // Update issued_books
                String returnQuery = "UPDATE issued_books SET return_date=SYSDATE, status='Returned' WHERE issue_id=?";
                PreparedStatement ps = con.prepareStatement(returnQuery);
                ps.setInt(1, issueId);
                ps.executeUpdate();

                // Increase quantity
                String updateQty = "UPDATE books SET quantity = quantity + 1 WHERE book_id=?";
                PreparedStatement updatePs = con.prepareStatement(updateQty);
                updatePs.setInt(1, bookId);
                updatePs.executeUpdate();

                System.out.println("✅ Book Returned Successfully!");

            } else {
                System.out.println("❌ Invalid Issue ID OR Already Returned!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Issued Books (JOIN)
    public void viewIssuedBooks() {
        try {
            String query = "SELECT i.issue_id, b.title, m.name, i.issue_date, i.return_date, i.status " +
                           "FROM issued_books i " +
                           "JOIN books b ON i.book_id = b.book_id " +
                           "JOIN members m ON i.member_id = m.member_id";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== 📌 ISSUED BOOKS REPORT ==========");
            while (rs.next()) {
                System.out.println(
                        "IssueID: " + rs.getInt("issue_id") +
                        " | Book: " + rs.getString("title") +
                        " | Member: " + rs.getString("name") +
                        " | IssueDate: " + rs.getDate("issue_date") +
                        " | ReturnDate: " + rs.getDate("return_date") +
                        " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

