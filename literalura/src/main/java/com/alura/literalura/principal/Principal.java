package com.alura.literalura.principal;

import com.alura.literalura.dto.AutorDto;
import com.alura.literalura.dto.LivroDto;
import com.alura.literalura.models.Autor;
import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.models.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;


    public void exibirMenu(){
        int opcao = -1;
        while (opcao!=0) {

            String menu = """
                    Escolha o número de sua opção:
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    6 - Top 10 livros mais baixados
                    
                    0 - Sair
                    """;

            System.out.println(menu);

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1 -> buscarPorTitulo();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLivrosPorIdioma();
                case 6 -> listarTop10Livros();

                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("opção inválida!\n");

            }
        }
    }

    private LivroDto getDadosLivro() {
        System.out.println("Digite o nome do livro para busca:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(nomeLivro.replace(" ", "+"));
        System.out.println(json);
        LivroDto livroDto = conversor.obterDados(json, LivroDto.class);
        return livroDto;
    }

    @Transactional
    private void buscarPorTitulo() {


        LivroDto livroDto = getDadosLivro();
        if (livroDto.resultados().isEmpty()){
            System.out.println("Livro não encontrado!");
            return;
        }
        DadosLivro dadosLivro = livroDto.resultados().get(0);

        String titulo = dadosLivro.titulo();

        String nomeAutor = dadosLivro.autores().stream()
                .map(AutorDto::nome)
                .collect(Collectors.joining(", "));

        String idioma = dadosLivro.idiomas() == null ? "" : String.join(",", dadosLivro.idiomas());

        Integer downloads = dadosLivro.numeroDownloads();

        System.out.println("""
                         Livro
                -----------------------         
                Título: %s
                Autor: %s
                Idioma: %s
                Dowloads %d
                -----------------------
                """.formatted(titulo, nomeAutor, idioma, downloads));

        AutorDto autorDto = dadosLivro.autores().get(0);

        Autor autor = autorRepository.findByNome(autorDto.nome())
                .orElseGet(() -> autorRepository.save(new Autor(autorDto)));

        Long gutendexId = dadosLivro.gutendexId();

        if (livroRepository.findByGutendexId(gutendexId).isPresent()) {
            System.out.println("Livro já está registrado!");
            return;
        }

        Livro livro = new Livro(dadosLivro, autor);

        livroRepository.save(livro);

    }

    private void listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()){
            System.out.println("Nenhum livro registrado");
            return;
        }

        livros.forEach(livro -> {
            String nomeAutor = livro.getAutor().getNome();
            System.out.println("""
                             
                             Livro
                    -----------------------         
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Downloads %d
                    -----------------------
                    
                    """.formatted(livro.getTitulo(),
                    nomeAutor,
                    livro.getIdioma(),
                    livro.getNumeroDownloads()
            ));
        });
    }

    @Transactional
    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(a -> {
            String livros = a.getLivros()
                    .stream()
                    .map(livro -> livro.getTitulo())
                    .toList()
                    .toString();

            System.out.println("""
                    
                    Autor: %s              
                    Ano de nascimento: %d
                    Ano de falecimento: %d
                    Livros: %s
                    
                    """.formatted(a.getNome(), a.getAnoNascimento(), a.getAnoMorte(), livros));
        });

    }

    private void listarAutoresVivos() {
        System.out.println("Insira o ano que deseja pesquisar: ");
        var ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = autorRepository.encontrarPorAno(ano);
        if (autores.isEmpty()){
            System.out.println("\nNão há autores vivos registrados nesse ano!\n");
            return;
        }
        autores.forEach(a -> {
            String livros = a.getLivros()
                    .stream()
                    .map(livro -> livro.getTitulo())
                    .toList()
                    .toString();

            System.out.println(""" 
                    
                    Autor: %s
                    Ano de nascimento: %d
                    Ano de falecimento: %d
                    Livros: %s
                    
                    """.formatted(a.getNome(), a.getAnoNascimento(), a.getAnoMorte(), livros));
        });

    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Insira o idioma para realizar a busca:
                
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                
                """);

        var idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        livros.forEach(livro -> {
            String nomeAutor = livro.getAutor().getNome();
            System.out.println("""
                             
                             Livro
                    -----------------------         
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Downloads %d
                    -----------------------
                    
                    """.formatted(livro.getTitulo(),
                    nomeAutor,
                    livro.getIdioma(),
                    livro.getNumeroDownloads()
            ));
        });
    }
    public void listarTop10Livros(){
        List<Livro> livros = livroRepository.findTop10ByOrderByNumeroDownloadsDesc();
        System.out.println("\nTop 10 Livros mais baixados: ");
        livros.forEach(livro -> {
            String nomeAutor = livro.getAutor().getNome();
            System.out.println("""
                             
                             Livro
                    -----------------------         
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Downloads %d
                    -----------------------                
                    """.formatted(livro.getTitulo(),
                    nomeAutor,
                    livro.getIdioma(),
                    livro.getNumeroDownloads()
            ));
        });
    }

}