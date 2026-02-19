package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.db.DBConnection;

public class BookDAO {

    Connection con = DBConnection.getConnection();

    // Add Book
    public void addBook(String title, String author, String category, int quantity) {
        try {
            String query = "INSERT INTO books(title, author, category, quantity) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setInt(4, quantity);

            ps.executeUpdate();
            System.out.println("✅ Book Added Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Books
    public void viewBooks() {
        try {
            String query = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== 📚 BOOK LIST ==========");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("book_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | " +
                        rs.getString("category") + " | Qty: " +
                        rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Book
    public void deleteBook(int bookId) {
        try {
            String query = "DELETE FROM books WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Book Deleted Successfully!");
            } else {
                System.out.println("❌ Book ID Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Book
    public void searchBook(String title) {
        try {
            String query = "SELECT * FROM books WHERE title LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "%" + title + "%");

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== 🔍 SEARCH RESULT ==========");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("book_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | Qty: " +
                        rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
