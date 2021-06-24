package ru.khrebtov;

import ru.khrebtov.persist.Product;
import ru.khrebtov.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        if (req.getPathInfo() == null) {
            List<Product> products = productRepository.findAll();
            wr.println("<table>");
            drawTableHead(wr);
            for (Product p : products) {
                drawProduct(wr, p);
            }
            wr.println("</table>");
        } else { // pathInfo = "/12213"
            String pathInfo = req.getPathInfo(); // => id
            Long id = Long.parseLong(pathInfo.substring(1));
            Product product = productRepository.findById(id);
            wr.println("<table>");
            drawTableHead(wr);
            drawProduct(wr, product);
            wr.println("</table>");
        }
    }

    private void drawTableHead(PrintWriter wr) {

        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Name</th>");
        wr.println("<th>Cost</th>");
        wr.println("</tr>");

    }

    public void drawProduct(PrintWriter wr, Product p) {
        wr.println("<tr>");
        wr.println("<td>" + p.getId() + "</td>");
        wr.println("<td><a href='http://localhost:8080/servlet-app/product/" + p.getId() + "'>" + p.getName() + "</a></td>");
        wr.println("<td>" + p.getCost() + "</td>");
        wr.println("</tr>");
    }
}
