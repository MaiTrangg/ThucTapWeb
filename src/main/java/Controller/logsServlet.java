package Controller;

import DAO.LogDao;
import Model.ILog;
import Model.LogEntry;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "logsServlet", value = "/logsServlet")
public class logsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ILog log = new LogDao();
        List<LogEntry> listLog = log.getAllLog();
        session.setAttribute("listLog", listLog);
        request.getRequestDispatcher("/WEB-INF/managerLog.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}