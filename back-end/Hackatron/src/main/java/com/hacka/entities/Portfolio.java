package com.hacka.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany
    private List<Tag> tag;

    private String link;

    @Lob
    private String descricao;

    private String nomeImagem;

    @ManyToOne
    private Usuario criador;

    public Portfolio() {
    }

    public Portfolio(Long id, String titulo, List<Tag> tag, String link, 
                     String descricao, String nomeImagem, Usuario criador) {
        this.id = id;
        this.titulo = titulo;
        this.tag = tag;
        this.link = link;
        this.descricao = descricao;
        this.nomeImagem = nomeImagem;
        this.criador = criador;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }
}
