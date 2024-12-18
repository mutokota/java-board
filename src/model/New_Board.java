package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/new_board")
public class New_Board extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		String url = "jdbc:mysql://localhost:3306/testdb";
		String user = "root";
		String password = "Basketball610";

		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String message = request.getParameter("message");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);

			// SQLクエリの準備
			String sql = "INSERT INTO posts (name, age, message, created_at) VALUES (?, ?, ?, NOW())";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, Integer.parseInt(age));
			pstmt.setString(3, message);

			// データベースに挿入
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("投稿を追加しました: 名前=" + name + ", 年齢=" + age + ", メッセージ=" + message);
			}

			// リソース解放
			pstmt.close();
			conn.close();

			
			// 成功ページにリダイレクト
			response.sendRedirect("/BBS/main");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
