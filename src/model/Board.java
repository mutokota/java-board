package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//サーブレットのURLをマッピング
@WebServlet("/main")
//投稿閲覧機能
public class Board extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		// TODO 自動生成されたメソッド・スタブ
		String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "Basketball610";
        
        // データ格納用リスト
        List<Object[]> posts = new ArrayList<>();
        
        try {
            // MySQL接続
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            // データ取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM posts");

            while (rs.next()) {
                // データをリストに追加 (配列を使用)
                posts.add(new Object[] {
                        rs.getInt("id"),         // ID
                        rs.getString("name"),    // 名前
                        rs.getInt("age"), // age
                        rs.getString("message"), //message
                        rs.getString("created_at") // 作成日時
                });
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // リクエストにデータを格納
        request.setAttribute("posts", posts);
        
        // JSPに転送
        RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/main.jsp");
        dispatcher.forward(request, response);
	}
	
}


