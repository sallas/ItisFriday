package dk.cphbusiness.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Cities", urlPatterns = {"/Cities"})
public class Cities extends HttpServlet {

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String country = request.getParameter("country");
        URL urlCountry = new URL("http://eventful.com/events?l=" + country);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(urlCountry.openStream()));
        String inputLine2 = in2.readLine();
        int countryCode = 0;
        boolean canContinue = true;
        while(canContinue && inputLine2 != null) {
            if(inputLine2.contains("geo=country")) {
                String[] thing = inputLine2.split("%");
                int index = thing[1].indexOf("\"");
                countryCode = Integer.parseInt(thing[1].substring(2, index));
                canContinue = false;
            }
            inputLine2 = in2.readLine();
        }
        URL url = new URL("http://eventful.com/events?q=*&ga_search=*&ga_type=events&t=Today&geo=country_id%3A" + countryCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        
        String inputLine = in.readLine();
        out.println("<ul>");
        while(inputLine != null) {
            if(inputLine.contains("event-info")) {
                //out.println(inputLine);
                //out.println("----First line ended----");
                in.readLine();
                inputLine = in.readLine();
                out.println("<li>");
                out.println(inputLine);
                out.println("</li>");
//                String[] stuff = inputLine.split("\"");
//                out.println(stuff[1]);
//                String subStuff = stuff[4].substring(1, stuff[4].length()-20);
//                out.println(subStuff);
//
//                out.println("--------Last line ended---------");
            }
            inputLine = in.readLine();
        }
        out.println("</ul>");
    }

}
