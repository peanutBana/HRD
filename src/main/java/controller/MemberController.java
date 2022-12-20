package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request, response);
		
	}
	
	//get OR post => doPro 실헹
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();		//톰캣의 context path를 가져온다.
		String command = request.getServletPath();		//rudfh
		String site = null;
		
		System.out.println(context + ", " + command);	///HRD_1234, /MemberController
		
		MemberDAO member = new MemberDAO();
		
		switch(command) {
		case "/home":
			site = "index.jsp";
		case "/add":
			site = member.insert(request, response);
		}

		
	}

}
