package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;

import java.sql.DriverManager;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//데이터베이스 연결
	public static Connection getConnection() throws Exception {
	      Class.forName("oracle.jdbc.OracleDriver");
	      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1524:xe", "system", "sys1234");
	      return con;
	   }
	
	//insert 회원 등록
	public String insert(HttpServletRequest request, HttpServletResponse response) {
			int custno = Integer.parseInt(request.getParameter("custno"));
			String custname = request.getParameter("custname");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String joindate = request.getParameter("joindate");
			String grate = request.getParameter("grade");
			String city = request.getParameter("city");
			int result = 0;
		
			try {
		
				conn = getConnection();
				String sql = "insert into member_tbl_02 values(?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, custno);
				ps.setString(2, custname);
				ps.setString(3, phone);
				ps.setString(4, address);
				ps.setString(5, joindate);
				ps.setString(6, grate);
				ps.setString(7, city);
				
				//insert, update, delete 성공한 레코드의 갯수를 반환 
				result = ps.executeUpdate();
				System.out.println(result);
				
				conn.close();
				ps.close();
	
			} catch (Exception e) {		//db연결
				e.printStackTrace();
			}
			
			return "add";
	}
	
	//회원목록 수정 조회
	public String selectAll(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			conn = getConnection();	//db 연결
			String sql = "select custno, custname, phone, address, TO_CHAR(joindate, 'YYYY-MM-DD') as joindate, ";
			sql+="DECODE(grade, 'A','VIP', 'B','일반', '직원')grade, city from member_tbl_02 order by custno";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setCustno(rs.getInt(1));
				member.setCustname(rs.getString(2));
				member.setPhone(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setJoindate(rs.getString(5));
				member.setGrade(rs.getString(6));
				member.setCity(rs.getString(7));
				
				list.add(member);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return "list.jsp";
 	}
}
