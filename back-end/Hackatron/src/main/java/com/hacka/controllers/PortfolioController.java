package com.hacka.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hacka.dto.PortfolioDTO;
import com.hacka.entities.Portfolio;
import com.hacka.services.PortfolioService;

@RestController
@RequestMapping("/portfolios")
@CrossOrigin(origins = "http://localhost:5173")
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }

    @PostMapping("/criarPortfolio")
    public ResponseEntity<Portfolio> criarPortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        try {
            Portfolio portfolio = service.criarPortfolio(
                    portfolioDTO.getTitulo(),
                    portfolioDTO.getLink(),
                    portfolioDTO.getDescricao(),
                    portfolioDTO.getCriadorId(),
                    portfolioDTO.getTags(),
                    portfolioDTO.getNomeImagem() 
            );

            return ResponseEntity.ok(portfolio);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/uploadImagem")
    public ResponseEntity<String> uploadImagem(@RequestParam("imagem") MultipartFile imagem) {
        try {
            String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
            String diretorio = "imagens/";
            Path caminho = Paths.get(diretorio + nomeArquivo);

            Files.createDirectories(caminho.getParent()); 
            Files.write(caminho, imagem.getBytes());

            return ResponseEntity.ok(nomeArquivo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar imagem");
        }
    }
    @GetMapping("/acharTodos")
	public List<Portfolio> acharTodos(){
		return service.acharTodos();
	}
	
	@GetMapping("/meusProjetos/{id}")
	public List<Portfolio> meusProjetos(@PathVariable Long id){
		return service.meusProjetos(id);
	}
	
	@GetMapping("pesquisaTag/{id}")
	public List<Portfolio> pesquisaTag(@PathVariable Long id){
		return service.pesquisaTag(id);
	}
	
	@GetMapping("abrirProjeto/{id}")
	public Portfolio abrirProjeto(@PathVariable Long id) {
		return service.abrirProjeto(id);
	}
	
	
	@DeleteMapping("delete/{id}")
	public void deletePortfolio(@PathVariable Long id) {
		 service.deletePortfolio(id);
	}
	
	@GetMapping("/imagens/{nomeImagem}")
    public ResponseEntity<Resource> servirImagem(@PathVariable String nomeImagem) {
        try {
            Path caminho = Paths.get("imagens/").resolve(nomeImagem).normalize();

            if (!Files.exists(caminho)) {
                return ResponseEntity.notFound().build();
            }

            Resource recurso = new UrlResource(caminho.toUri());

            String tipoMime = Files.probeContentType(caminho);
            MediaType mediaType = tipoMime != null ? MediaType.parseMediaType(tipoMime) : MediaType.APPLICATION_OCTET_STREAM;

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(recurso);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
