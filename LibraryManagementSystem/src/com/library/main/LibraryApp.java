package com.library.main;

import java.util.Scanner;

import com.library.dao.BookDAO;
import com.library.dao.IssueDAO;
import com.library.dao.MemberDAO;

public class LibraryApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        IssueDAO issueDAO = new IssueDAO();

        while (true) {

            System.out.println("\n========= 📚 LIBRARY MANAGEMENT SYSTEM =========");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Add Member");
            System.out.println("6. View Members");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Issued Books Report");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    bookDAO.addBook(title, author, category, qty);
                    break;

                case 2:
                    bookDAO.viewBooks();
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("Enter Book Title to Search: ");
                    String searchTitle = sc.nextLine();

                    bookDAO.searchBook(searchTitle);
                    break;

                case 4:
                    System.out.print("Enter Book ID to Delete: ");
                    int bookId = sc.nextInt();

                    bookDAO.deleteBook(bookId);
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("Enter Member Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    memberDAO.addMember(name, phone, email);
                    break;

                case 6:
                    memberDAO.viewMembers();
                    break;

                case 7:
                    System.out.print("Enter Book ID: ");
                    int issueBookId = sc.nextInt();

                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();

                    issueDAO.issueBook(issueBookId, memberId);
                    break;

                case 8:
                    System.out.print("Enter Issue ID: ");
                    int issueId = sc.nextInt();

                    issueDAO.returnBook(issueId);
                    break;

                case 9:
                    issueDAO.viewIssuedBooks();
                    break;

                case 10:
                    System.out.println("🙏 Thank you! Project Closed.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid Choice! Try Again.");
            }
        }
    }
}
