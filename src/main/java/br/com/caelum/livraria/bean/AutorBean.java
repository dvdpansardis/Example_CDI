package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	@Inject
	private AutorDao dao;

	@PostConstruct
    void init() {
        System.out.println("AutorBean está nascendo ....");
    }
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPelaId() {
		this.autor = dao.buscaPorId(autorId);
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
			dao.adiciona(this.autor);
		} else {
			dao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		dao.remove(autor);
	}
	
	public List<Autor> getAutores() {
		return dao.listaTodos();
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	@PreDestroy
    void morte() {
        System.out.println("AutorBean está morrendo ....");
    }
}
