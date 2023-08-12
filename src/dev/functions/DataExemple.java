package dev.functions;

import br.com.senai.models.CategoriaProduto;
import br.com.senai.models.Cliente;
import br.com.senai.models.Produto;
import br.com.senai.controllers.CategoriaProdutoController;
import br.com.senai.controllers.ClienteController;
import br.com.senai.controllers.ProdutoController;

public class DataExemple {
    public static void criacaoCliente() {
        Cliente cliente1 = new Cliente("Deyvid", "Rua A, 123", 88701001, 48123456789L, "12345678900"); // L para indicar long
        Cliente cliente2 = new Cliente("Tiago", "Rua B, 456", 88481023, 48987654321L, "98765432100"); // L para indicar long
        
        ClienteController.listaClientes.add(cliente1);
        ClienteController.listaClientes.add(cliente2);
    }
    
    public static void criacaoCategoriaProduto() {
        CategoriaProduto categoria1 = new CategoriaProduto("Chave");
        CategoriaProduto categoria2 = new CategoriaProduto("Alicate");
        
        CategoriaProdutoController.listaCategorias.add(categoria1);
        CategoriaProdutoController.listaCategorias.add(categoria2);
    }
    
    public static void criacaoProduto() {
        CategoriaProduto categoriaChave = CategoriaProdutoController.listaCategorias.get(0);
        CategoriaProduto categoriaAlicate = CategoriaProdutoController.listaCategorias.get(1);
        
        Produto produto1 = new Produto("Chave de Fenda", 100.0, "UN", 10.0, categoriaChave, 5.0);
        Produto produto2 = new Produto("Alicate de Corte", 50.0, "UN", 15.0, categoriaAlicate, 3.0);
        
        ProdutoController.listaProdutos.add(produto1);
        ProdutoController.listaProdutos.add(produto2);
    }
}
