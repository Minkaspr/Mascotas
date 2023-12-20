
package web.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.validator.MascotaValidator;

@WebServlet(name = "MascotaServlet", urlPatterns = {"/Mascota"})
public class MascotaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        accion = (accion == null) ? "" : accion;

        String result; 
        String target = "mascotaSel.jsp"; 

        MascotaValidator validator = new MascotaValidator(request);
        switch (accion) {
            case "SEL": 
                result = validator.mascotaSel();
                break;
            case "CBO": 
                result = validator.mascotaCBO();
                target = "mascotaIns.jsp"; 
                break;
            case "INS": 
                result = validator.mascotaInsUpd(false);
                target = result == null ? "Mascota?accion=SEL" : "mascotaIns.jsp";
                break;
            case "DEL": 
                result = validator.mascotaDel();
                target = "Mascota?accion=SEL"; 
                break;
            case "GET": 
                result = validator.mascotaGet();
                target = "mascotaUpd.jsp"; 
                break;
            case "UPD":
                result = validator.mascotaInsUpd(true);
                target = result == null ? "Mascota?accion=SEL" : "mascotaUpd.jsp";
                break;
            case "":
                result = "Solicitud requerida";
                break;
            default:
                result = "Solicitud no reconocida";
        }

        if (result != null) { 
            request.setAttribute("message", result);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
