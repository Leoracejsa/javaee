package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans; //adicionando para captar os dados do form

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato  =  new JavaBeans();//adicionando para captar os dados do form
  
    public Controller() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dao.testeConexao(); //teste de conexao
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/main")) {
			contatos(request, response);
		}else if(action.equals("/insert")) { //requisição para a action do form
			novoContato(request, response);
		}else if (action.equals("/select")) {
			listarContato(request, response);
		}else if (action.equals("/update")) {
			editarContato(request, response);
		}else if (action.equals("/delete")) {
			removerContato(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}
	}
	
	//listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendRedirect("agenda.jsp");
		//Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		
		//teste de recebimento da lista
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getIdcon());
//			System.out.println(lista.get(i).getNome());
//			System.out.println(lista.get(i).getFone());
//			System.out.println(lista.get(i).getEmail());
//			
//		}
		
		//Encaminhar a lista de contatos ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
	}
	
	//Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//teste de recebimento dos dados do form
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));
		
		//setar as variáveis java Beans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//invocar o método inserir contato passando o objeto contato
		dao.inserirContato(contato);
		
		//redirecionar para documento agenda.jsp
		response.sendRedirect("main");
		
	}
	
	//Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recebimento do id do contato que será editado.
		String idcon = request.getParameter("idcon");
//		System.out.println(idcon);
		
		//setar a variável javaBeans
		contato.setIdcon(idcon);
		
		//executar o método selecionarContato(DAO)
		dao.selecionarContato(contato);
		
//		//teste de recebimento
//		System.out.println(contato.getIdcon());
//		System.out.println(contato.getNome());
//		System.out.println(contato.getFone());
//		System.out.println(contato.getEmail());
		
		//setar os atributos do form com JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		//Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
		
	}
	
	//Editar Contato
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//teste de recebimento
		/*
		 * System.out.println(request.getParameter("idcon"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 */
		
		//setar as variáveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		//Executar o método alterarContato
		dao.alterarContato(contato);
		
		//redirecionar para o documento agenda.jsp(atualizando as alterações)
		response.sendRedirect("main");
	}
	
	//Remover Contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		//recebimento do id do contato a ser excluído(validador js)
		//System.out.println(idcon);
		
		//setar idcon JavaBeans
		contato.setIdcon(idcon);	
		
		//executar o método deletarContato (DAO) passando o obj contato
		dao.deletarContato(contato);
		
		//redirecionar para o documento agenda.jsp(atualizando as alterações)
		response.sendRedirect("main");
	}

}
	
	
