package br.com.senai.controllers;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import br.com.senai.models.CategoriaProduto;

public class CategoriaProdutoController {
    public static ArrayList<CategoriaProduto> listaCategorias = new ArrayList<>();

    public void exibirCadastroCategoria() {
        exibirCamposCategoria(null, "Cadastro de categoria");
        JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
    }

    public void exibirListagemCategorias() {
        if (listaCategorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria cadastrada.");
            return;
        }

        String armazenamentoTxtCategoria = "Listagem de categoria\n";
        armazenamentoTxtCategoria += listarTxtCategorias();
        JOptionPane.showMessageDialog(null, armazenamentoTxtCategoria);
    }

    public void exibirAlteracaoCategoria() {
        if (listaCategorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria cadastrada.");
            return;
        }

        String armazenamentoTxtCategoria = "Alteração de categoria\n";
        armazenamentoTxtCategoria += listarTxtCategorias();

        int indiceCategoriaAlteracao = obterIndiceCategoria(armazenamentoTxtCategoria, "Escolha a categoria que deseja alterar:");
        if (indiceCategoriaAlteracao != -1) {
            CategoriaProduto categoriaAlteracao = listaCategorias.get(indiceCategoriaAlteracao);
            exibirCamposCategoria(categoriaAlteracao, "Alteração de categoria");
            JOptionPane.showMessageDialog(null, "Categoria alterada com sucesso!");
        }
    }

    public void exibirExclusaoCategoria() {
        if (listaCategorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria cadastrada.");
            return;
        }

        String armazenamentoTxtCategoria = "Exclusão de categoria\n";
        armazenamentoTxtCategoria += listarTxtCategorias();

        int indiceCategoriaExclusao = obterIndiceCategoria(armazenamentoTxtCategoria, "Escolha a categoria que deseja excluir:");
        if (indiceCategoriaExclusao != -1) {
            listaCategorias.remove(indiceCategoriaExclusao);
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
        }
    }

    public int obterIndiceCategoria(String mensagem, String titulo) {
        int indice = -1;
        while (true) {
            String indiceInput = JOptionPane.showInputDialog(mensagem + "\n\n " + titulo);
            if (indiceInput == null) {
                break; // O usuário clicou no botão "Cancelar" ou fechou a janela
            }

            try {
                indice = Integer.parseInt(indiceInput);
                if (indice >= 1 && indice <= listaCategorias.size()) {
                    return indice - 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Índice de categoria inválido! Digite um número correspondente ao índice da categoria.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Índice de categoria inválido! Digite um número correspondente ao índice da categoria.");
            }
        }

        return -1;
    }

    public String listarTxtCategorias() {
        String armazenamentoTxtCategorias = "";
        for (int i = 0; i < listaCategorias.size(); i++) {
            int indice = i + 1;
            armazenamentoTxtCategorias += "\nÍndice: " + indice + " " + listaCategorias.get(i);
        }

        return armazenamentoTxtCategorias;
    }

    public void exibirCamposCategoria(CategoriaProduto categoriaAlteracao, String titulo) {
        // Título da caixa de diálogo de acordo com a ação (Cadastro ou alteração)
        String mensagemTitulo = (categoriaAlteracao != null) ? "Alteração de categoria" : "Cadastro de categoria";

        String nomeCategoriaAtual = (categoriaAlteracao != null) ? categoriaAlteracao.getNomeCategoria() : null;

        String nomeCategoria = JOptionPane.showInputDialog(mensagemTitulo + "\nNome da categoria:", nomeCategoriaAtual);

        // Altera ou cadastra uma categoria
        if (nomeCategoria != null && !nomeCategoria.isEmpty()) {
            if (categoriaAlteracao != null) {
                categoriaAlteracao.setNomeCategoria(nomeCategoria);
            } else {
                CategoriaProduto novaCategoria = new CategoriaProduto(nomeCategoria);
                listaCategorias.add(novaCategoria);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nome de categoria inválido!");
        }
    }

	public static String[] getCategoriasArray() {
		return null;
	}
}
