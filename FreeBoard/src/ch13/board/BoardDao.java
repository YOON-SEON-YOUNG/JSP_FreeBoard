package ch13.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.net.aso.p;

public class BoardDao {
	
	private static BoardDao instance;
	
	private BoardDao() { /*singleton*/}
	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		try {
			Context initCtx = new InitialContext();
			Context envCts = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCts.lookup("jdbc/basicjsp");
			Connection conn = ds.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // getConnection
	
	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs)	{
		if (rs != null) 	try {rs.close();}		catch (Exception e) {	}
		if (pstmt != null) 	try {pstmt.close();}	catch (Exception e) {	}
		if (conn != null)	try {conn.close();}		catch (Exception e) {	}
	} // closeAll
	
	
	// 글 입력
	public void insertArticle(BoardVo boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into board(num, writer, email, subject, passwd,"
					+ "						ref, re_step, re_level, content, ip"
					+ "    values (seq_board.nextval, ?, ?, ?, ?,"
					+ "				0,0,0,?,?)";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, boardVo.getWriter());
			pstmt.setString(2, boardVo.getEmail());
			pstmt.setString(3, boardVo.getSubject());
			pstmt.setString(4, boardVo.getPasswd());
			pstmt.setString(5, boardVo.getContent());
			pstmt.setString(6, boardVo.getIp());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}
	
	
	// 글 목록
	public ArrayList<BoardVo> getArticles() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from board"
					+ "   order by num descs";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<BoardVo> list = new ArrayList<>();
			while (rs.next())	{
				int num = rs.getInt("num");
				String subject = rs.getString("subject");
				String writer = rs.getString("writer");
				Timestamp reg_date = rs.getTimestamp("reg_date");
				int readcount = rs.getInt("readcount");
		
				BoardVo boardVo = new BoardVo();
				boardVo.setNum(num);
				boardVo.setSubject(subject);
				boardVo.setWriter(writer);
				boardVo.setReg_date(reg_date);
				boardVo.setReadcount(readcount);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}
	
	
	// 글 1개 얻기
	public void getOneArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from board"
					+ "   where num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
	} 
	
}
