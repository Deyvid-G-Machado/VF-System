package br.com.senai.controllers;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import br.com.senai.models.Produto;
import br.com.senai.models.CategoriaProduto;

public class ProdutoController {
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();

    public void exibirListagemProdutos() {
        if (listaProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.");
            return;
        }

        String listaProdutosTxt = "Listagem de produtos:\n";
        listaProdutosTxt += listarTxtProdutos();
        JOptionPane.showMessageDialog(null, listaProdutosTxt);
    }

    public void exibirCadastroProduto() {
        if (CategoriaProdutoController.listaCategorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Para cadastrar um produto, primeiro cadastre uma Categoria!");
            return;
        }

        exibirCamposProduto(null);
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    }

    public void exibirAlteracaoProduto() {
        if (listaProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.");
            return;
        }

        String listaProdutosTxt = "Listagem de produtos:\n";
        listaProdutosTxt += listarTxtProdutos();

        int indiceProduto = obterIndiceProduto(listaProdutosTxt, "Digite o índice do produto que deseja alterar:");

        if (indiceProduto != -1) {
            Produto produtoAlteracao = listaProdutos.get(indiceProduto);
            exibirCamposProduto(produtoAlteracao);
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
        }
    }

    public void exibirExclusaoProduto() {
        if (listaProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.");
            return;
        }

        String listaProdutosTxt = "Listagem de produtos:\n";
        listaProdutosTxt += listarTxtProdutos();

        int indiceProdutoExclusao = obterIndiceProduto(listaProdutosTxt, "Digite o índice do produto que deseja excluir:");

        if (indiceProdutoExclusao != -1) {
            listaProdutos.remove(indiceProdutoExclusao);
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        }
    }

    public int obterIndiceProduto(String mensagem, String titulo) {
        int indice = -1;
        while (true) {
            String indiceInput = JOptionPane.showInputDialog(mensagem + "\n\n " + titulo);
            if (indiceInput == null) {
                break; // O usuário clicou no botão "Cancelar" ou fechou a janela
            }

            try {
                indice = Integer.parseInt(indiceInput) - 1;
                if (indice >= 0 && indice < listaProdutos.size()) {
                    return indice;
                } else {
                    JOptionPane.showMessageDialog(null, "Índice de produto inválido! Digite um número correspondente ao índice do produto.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Índice de produto inválido! Digite um número correspondente ao índice do produto.");
            }
        }

        return -1;
    }

    public String listarTxtProdutos() {
        String listaProdutosTxt = "";
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            int indice = i + 1;
            listaProdutosTxt += "\nProduto " + indice + ":\n" + produto.toString() + "\n";
        }
        return listaProdutosTxt;
    }

    public void exibirCamposProduto(Produto produtoAlteracao) {
        // Título da caixa de diálogo de acordo com a ação (Cadastro ou alteração)
        String titulo = (produtoAlteracao != null) ? "Alteração de produto" : "Cadastro de produto";

        // Array com as unidades de medida
        String[] undMedida = {"UN", "m", "m2", "m3", "Kg"};
        
        //  Faz uma listagem das categorias cadastradas
        CategoriaProdutoController categoriaProdutoController = new CategoriaProdutoController();
        String armazenamentoTxtCategorias = "\n\nListagem de categorias\n";
        armazenamentoTxtCategorias += categoriaProdutoController.listarTxtCategorias();

        Integer indiceCategoria = obterIndice(titulo + armazenamentoTxtCategorias + "\n\nÍndice da categoria:", 1);
        if (indiceCategoria == null) {
            return; // O usuário cancelou a operação
        }

        CategoriaProduto categoriaProduto = categoriaProdutoController.listaCategorias.get(indiceCategoria);

        String nomeAtual = (produtoAlteracao != null) ? produtoAlteracao.getNome() : null;
        Double qtdEstoqueAtual = (produtoAlteracao != null) ? produtoAlteracao.getQtdEstoque() : null;
        Integer unidadeMedidaAtual = (produtoAlteracao != null) ? verificarUndMedida(produtoAlteracao.getUnidadeMedida(), undMedida) : 0;
        Double valorAtual = (produtoAlteracao != null) ? produtoAlteracao.getValor() : null;
        Double qtdMinimaAtual = (produtoAlteracao != null) ? produtoAlteracao.getQtdMinima() : null;

        String nome = JOptionPane.showInputDialog(titulo + "\nNome:", nomeAtual);
        Double qtdEstoque = obterDouble(titulo + "\nQuandidade em estoque:", qtdEstoqueAtual);
        
	     // ComboBox da unidade de medida
        String unidadeMedida = (String) JOptionPane.showInputDialog(null,
					"Selecione uma opcao:", "Unidade de medida",
					JOptionPane.PLAIN_MESSAGE, null,
					undMedida, undMedida[unidadeMedidaAtual]);
       
        Double valor = obterDouble(titulo + "\nValor:", valorAtual);
        Double qtdMinima = obterDouble(titulo + "\nQuantidade mínima :", qtdMinimaAtual);

        if (produtoAlteracao != null) {
            produtoAlteracao.setNome(nome);
            produtoAlteracao.setQtdEstoque(qtdEstoque);
            produtoAlteracao.setUnidadeMedida(unidadeMedida);
            produtoAlteracao.setValor(valor);
            produtoAlteracao.setCategoriaProduto(categoriaProduto);
            produtoAlteracao.setQtdMinima(qtdMinima);
        } else {
            Produto produto = new Produto(nome, qtdEstoque, unidadeMedida, valor, categoriaProduto, qtdMinima);
            listaProdutos.add(produto);
        }
    }

    public Integer obterIndice(String mensagem, int valorPadrao) {
        int indice = -1;
        while (true) {
            String valorStr = JOptionPane.showInputDialog(mensagem, valorPadrao);
            if (valorStr == null) {
                return null; // O usuário cancelou a operação
            }

            try {
                indice = Integer.parseInt(valorStr);
                if (indice >= 1 && indice <= CategoriaProdutoController.listaCategorias.size()) {
                    return indice - 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Índice de categoria inválido!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Índice de categoria inválido!");
            }
        }
    }

    public Double obterDouble(String mensagem, Double valorPadrao) {
        while (true) {
            String valorStr = JOptionPane.showInputDialog(mensagem, valorPadrao);
            if (valorStr != null) {
                if (valorStr.matches("^[-+]?\\d*\\.?\\d+$")) {
                    try {
                        return Double.parseDouble(valorStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor inválido! Certifique-se de digitar um número válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Valor inválido! Certifique-se de digitar um número decimal válido.");
                }
            } else {
                break; // O usuário clicou no botão "Cancelar" ou fechou a janela
            }
        }
        return null; // Retorna null para indicar que a entrada não foi válida
    }


    
    public Integer verificarUndMedida(String unidadeMedidaAtual, String[] undMedida) {
        Integer posicaoUnidadeMedida = null;
        for (int i = 0; i < undMedida.length; i++) {
            if (undMedida[i].equals(unidadeMedidaAtual)) {  // Use equals() em vez de ==
                posicaoUnidadeMedida = i;
                break;
            }
        }
        return posicaoUnidadeMedida;
    }

}
