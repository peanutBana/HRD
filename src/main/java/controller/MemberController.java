package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

// "/"로 설정 한 이유는 jsp의 파일들이 controller 파일을 전부 경유 할 수 있게 하기 위함이다.
@WebServlet("/")
public class MemberController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MemberController() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      //한글을 정확히 출력하기 위해 UTF-8을 사용하였다
      request.setCharacterEncoding("UTF-8");
      doPro(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      //한글을 정확히 출력하기 위해 UTF-8을 사용하였다
      request.setCharacterEncoding("UTF-8");
      doPro(request, response);
   }

   // get OR post => doPro 실헹
   protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String context = request.getContextPath(); // 톰캣의 context path를 가져온다. (server.xml에서 확인 가능)
      String command = request.getServletPath(); // 경로의 맨 끝 파일명을 가져온다.
      String site = null;

      System.out.println(context + ", " + command); /// HRD_1234, /MemberController

      MemberDAO member = new MemberDAO();

      switch (command) {
      case "/home":
         site = "index.jsp"; break;
      case "/insert":
         site = member.insert(request, response);   break;
      case "/list":
         site = member.selectAll(request, response); break;
      case "/add":
         site = member.nextCustno(request, response); break;
      case "/modify":
         site = member.modify(request, response); break;
      case "/result":
         site = member.selectResult(request, response); break;
      case "/update":
         int result1 = member.update(request, response);
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();
         
         if(result1 == 1) {
            out.println("<script>");
            out.println(" alert('회원수정이 완료 되었습니다!'); location.href='" + context + "';");
            out.println("</script>");
            out.flush();
         } else {
            out.println("<script>");
            out.println(" alert('수정실패!'); location.href='" + context + "';");
            out.println("</script>");
            out.flush();
         }
         break;
         
      case "/delete":
         int result2 = member.delete(request, response);
         response.setContentType("text/html; charset=UTF-8");
         out = response.getWriter();
         
         if(result2 == 1) {
            out.println("<script>");
            out.println(" alert('삭제가 완료 되었습니다!'); location.href='" + context + "';");
            out.println("</script>");
            out.flush();
         } else {
            out.println("<script>");
            out.println(" alert('삭제실패!'); location.href='" + context + "';");
            out.println("</script>");
            out.flush();
         }
         break;
      }
      getServletContext().getRequestDispatcher("/" + site).forward(request, response);
   }
}