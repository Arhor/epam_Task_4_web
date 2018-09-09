package by.epam.task4.web;

import by.epam.task4.model.Medicine;
import by.epam.task4.service.factory.MedicinsBuilderFactory;
import by.epam.task4.service.parsing.MedicinsAbstractBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String parser = request.getParameter("parser");

        MedicinsBuilderFactory factory;
        MedicinsAbstractBuilder builder;
        Set<Medicine> validMedicinsSet;

        String path = this.getServletContext().getRealPath("/");
        String concretePath = path + "xml\\Medicins.xml";

        factory = new MedicinsBuilderFactory();

        StringBuilder sb = new StringBuilder();

        try {
            builder = factory.getBuilder(parser);
            if(builder.buildSetMedicins(concretePath)) {
                validMedicinsSet = builder.getMedicins();
                for (Medicine medicine : validMedicinsSet) {
                    sb.append("<p>" + medicine.getName() + "</p>");
                }
            }
        } catch (Exception e) {
            log("Exception occured", e);
        }


        request.setAttribute("resultSet", sb);
        request.getRequestDispatcher("/result.jsp").forward(request, response);

    }
}
