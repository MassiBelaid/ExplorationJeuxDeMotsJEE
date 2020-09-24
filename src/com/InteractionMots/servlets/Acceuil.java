package com.InteractionMots.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Acceuil")
public class Acceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Acceuil() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String terme = request.getParameter("terme");
		System.out.println(terme);
		if(!(terme==null)) {
			ArrayList<String> listeTR = extract(terme);
			request.setAttribute("listeTR", listeTR);
		}
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
	public static ArrayList<String> extract(String terme) throws IOException {
		ArrayList<String> listTerme = new ArrayList<String>();
		//Lecture à partire d'une URL construite avec le terme
		String urlString = "http://www.jeuxdemots.org/rezo-dump.php?gotermsubmit=Chercher&gotermrel="+terme;
		URL url = new URL(urlString);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String codeSource = "";
		String s;
		Boolean existeTerme = false;

		
		//Lecture du code ligne par ligne
		while ((s = in.readLine()) != null) {
			codeSource += "\n"+s;
			
			//Recherche de la balise <CODE> qui stipule le début des termes/relations
			if(s.equals("<CODE>")){
				existeTerme = true;
			}
			
			//Si on est à l'interieur des termes/relation
			if(existeTerme) {
				//Si une ligne non vide
				if(!s.equals("")) {
					//Ligne d'une relation
					if(s.charAt(0) == 'r') {
						//System.out.println("Une relation  : "+s);
						listTerme.add("Une relation  : "+s);
						
					//Ligne d'un terme
					}else if(s.charAt(0) == 'e') {
						//System.out.println("Un terme : "+s);
						listTerme.add("Un terme : "+s);
					}
				}	
			}
			//System.out.println(s);
		}
			
		System.out.println(existeTerme);
		in.close();
		return listTerme;
	}

}
