package coreservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Film;
import dao.FilmDAO;

/**
 * Servlet implementation class GetFilm
 */
@WebServlet("/getfilm")
public class GetFilm extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =  Integer.parseInt(request.getParameter("filmID"));
		
//		if ((id == null) || (id.trim().equals(""))) {
//			id = "<i>missing-id</i>";
//		}
		System.out.println(id);
		FilmDAO dao = new FilmDAO();
		Film film = dao.getFilmByID(id);
		String address;
		if (film == null) {
			request.setAttribute("id", "<i>missing-id</i>");
			address = "/WEB-INF/results/unknown-film.jsp";
		} else {
			request.setAttribute("film", film);
			address = "/WEB-INF/results/get-film.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
