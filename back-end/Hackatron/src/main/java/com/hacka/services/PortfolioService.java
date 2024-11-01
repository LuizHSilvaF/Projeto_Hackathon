package com.hacka.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacka.entities.Portfolio;
import com.hacka.entities.Tag;
import com.hacka.entities.Usuario;
import com.hacka.repositories.PortfolioRepository;
import com.hacka.repositories.TagRepository;
import com.hacka.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class PortfolioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PortfolioRepository repo;

    @Transactional
    public Portfolio criarPortfolio(String titulo, String link, String descricao,
                                    Long criadorId, List<String> nomesTags, String nomeImagem) {

        Usuario criador = usuarioRepository.findById(criadorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + criadorId));

        List<Tag> tags = nomesTags.stream()
                .map(nome -> tagRepository.findByNome(nome)
                        .orElseGet(() -> tagRepository.save(new Tag(nome))))
                .collect(Collectors.toList());

        Portfolio portfolio = new Portfolio();
        portfolio.setTitulo(titulo);
        portfolio.setLink(link);
        portfolio.setDescricao(descricao);
        portfolio.setCriador(criador);
        portfolio.setTag(tags);
        portfolio.setNomeImagem(nomeImagem); 
        return repo.save(portfolio);
    }

    public List<Portfolio> acharTodos() {
        return repo.findAll();
    }
    
    public void deletePortfolio(Long id) {
    	repo.deleteById(id);
    }

    public List<Portfolio> meusProjetos(Long id) {
        return repo.meusProjetos(id);
    }

    public List<Portfolio> pesquisaTag(Long id) {
        return repo.pesquisaTag(id);
    }

    public Portfolio abrirProjeto(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado com ID: " + id));
    }
}
