package by.epam.task4.controller;

import by.epam.task4.model.Medicine;
import by.epam.task4.model.Pack;
import by.epam.task4.model.Version;
import by.epam.task4.service.factory.MedicinsBuilderFactory;
import by.epam.task4.service.parsing.MedicinsAbstractBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(ControllerServlet.class);

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
        String concretePath = path + "xml" + "/" + "Medicins.xml";

        factory = new MedicinsBuilderFactory();

        StringBuilder sb = new StringBuilder();

        try {
            builder = factory.getBuilder(parser);
            if(builder.buildSetMedicins(concretePath)) {
                validMedicinsSet = builder.getMedicins();
                for (Medicine medicine : validMedicinsSet) {
                    int primaryRowspan = 0;
                    for (Version version : medicine.getVersions()) {
                        primaryRowspan += version.getPacks().size();
                    }
                    sb.append("<tr>");
                    sb.append("<td rowspan=\"" + primaryRowspan + "\">" + medicine.getClass().getSimpleName() + "</td>");
                    sb.append("<td rowspan=\"" + primaryRowspan + "\">" + medicine.getName() + "</td>");
                    sb.append("<td rowspan=\"" + primaryRowspan + "\" nowrap>" + medicine.getCas() + "</td>");
                    sb.append("<td rowspan=\"" + primaryRowspan + "\">" + medicine.getDrugBank() + "</td>");
                    sb.append("<td rowspan=\"" + primaryRowspan + "\">" + medicine.getPharm() + "</td>");
                    int versionCounter = 0;
                    for (Version version : medicine.getVersions()) {
                        versionCounter++;
                        int secondaryRowspan = version.getPacks().size();
                        if (versionCounter != 1) {
                            sb.append("<tr>");
                        }
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\">" + version.getTradeName() + "</td>");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\">" + version.getProducer() + "</td>");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\">" + version.getForm() + "</td>");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\">" + version.getCertificate().getRegistredBy() + "</td>");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\" nowrap>" + dateFormat.format(version.getCertificate().getRegistrationDate()) + "</td>");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\" nowrap>" + dateFormat.format(version.getCertificate().getExpireDate()) + "</td>");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\">" + version.getDosage().getAmount() + "</td>");
                        sb.append("<td rowspan=\"" + secondaryRowspan + "\">" + version.getDosage().getFrequency() + "</td>");
                        int packCounter = 0;
                        for (Pack pack : version.getPacks()) {
                            packCounter++;
                            if (packCounter != 1) {
                                sb.append("<tr>");
                            }
                            String size = pack.getSize();
                            sb.append("<td>" + (size == null ? "Not specified" : size) + "</td>");
                            sb.append("<td>" + pack.getQuantity() + "</td>");
                            sb.append("<td>" + pack.getPrice() + "</td>");
                            if (packCounter != 1) {
                                sb.append("</tr>");
                            }
                        }
                        if (versionCounter != 1) {
                            sb.append("</tr>");
                        }
                    }
                    sb.append("</tr>");
                }
            }
        } catch (Exception e) {
            LOG.error("Exception occurred", e);
        }
        request.setAttribute("parserType", parser);
        request.setAttribute("resultSet", sb);
        request.getRequestDispatcher("/result.jsp").forward(request, response);

    }
}
