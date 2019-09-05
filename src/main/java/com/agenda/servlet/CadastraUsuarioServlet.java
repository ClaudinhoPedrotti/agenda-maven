package com.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agenda.model.Contato;
import com.agenda.model.Endereco;
import com.agenda.model.Pessoa;
import com.agenda.service.CadastraUsuarioService;

public class CadastraUsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private CadastraUsuarioService service;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();
		
		if(null != request.getParameter("id") && !"".equals(request.getParameter("id"))) {
			pessoa.setId(Long.parseLong(request.getParameter("id")));
		}
		pessoa.setNome(request.getParameter("nome"));
		System.out.println(request.getParameter("dataNascimento"));

		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setCep(request.getParameter("cep"));

		contato.setEmail(request.getParameter("email"));
		contato.setTelefone(request.getParameter("telefone"));
		
		pessoa.setContato(contato);
		pessoa.setEndereco(endereco);
		
//		this.service = new CadastraUsuarioService();
		
		PrintWriter out = response.getWriter();

		try {
			
			this.service.salvarOuAtualizar(pessoa);
			
//			response.sendRedirect("busca-contatos");
			response.sendRedirect("index.html");
			
		} catch (Exception e) {
			out.println("<html>");
			out.println("<body>");
			out.println("Falha ao realizar o cadastro!");
			out.println("</body>");
			out.println("</html>");
		}
		
	}
	
}
