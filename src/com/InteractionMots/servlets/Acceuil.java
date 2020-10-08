package com.InteractionMots.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InteractionMots.dao.DaoFactory;
import com.InteractionMots.extraction.Extraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Acceuil")
public class Acceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Extraction extraction;
       

    public void init() throws ServletException {
        this.extraction = new Extraction(DaoFactory.getInstance());
    }
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*String terme = request.getParameter("terme");
		System.out.println(terme);
		if(!(terme==null)) {
			ArrayList<String> listeTR = extract(terme);
			request.setAttribute("listeTR", listeTR);
		}
		*/
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String terme = request.getParameter("terme");
		System.out.println("Le terme : "+terme);
		if(!(terme==null)) {
			ArrayList<String> listeTR = this.extraction.getData(terme);
			request.setAttribute("listeTR", listeTR);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
	}
	
	
	
	

}
