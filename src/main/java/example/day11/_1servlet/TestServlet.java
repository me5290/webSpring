package example.day11._1servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet")
public class TestServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doGet");
        // 요청 객체 : HttpServletRequest
        
        String id = req.getParameter("id");
        System.out.println("id = " + id);

        int type = Integer.parseInt(req.getParameter("type"));
        System.out.println("type = " + type);
        
        // 요청 객체 : HttpServletResponse
        // resp.setContentType("text/html"); // 데이터의 타입(받는 입장에서의 데이터를 사용하는 방법)
        resp.setContentType("application/json"); // 데이터의 타입(받는 입장에서의 데이터를 사용하는 방법)
        // resp.getWriter().println("서블릿이 보내온 메시지");
        resp.getWriter().println("{\"msg\":\"클라이언트에게 응답하는 메시지\" , \"type\":\"1\"}");
        // 자바 데이터를 JSON 데이터 형식 변환
            // 1. 직접한다.(실무에서는 불가능)
            // 2. 라이브러리 사용한다.
            // 3. 스프링MVC @ResponseBody
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPost");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPut");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doDelete");
    }
}
