import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.senai.controllers.CategoriaProdutoController;
import br.com.senai.controllers.ClienteController;
import br.com.senai.controllers.PedidoController;
import br.com.senai.controllers.ProdutoController;
import br.com.senai.controllers.RelatorioGeralController;
import dev.functions.DataExemple;

public class Main {

    public static void main(String[] args) {
        CategoriaProdutoController categoriaProdutoController = new CategoriaProdutoController();
        ClienteController clienteController = new ClienteController();
        ProdutoController produtoController = new ProdutoController();
        PedidoController pedidoController = new PedidoController();
        RelatorioGeralController relatorioGeralController = new RelatorioGeralController();

        // Criação automatica de itens 
        DataExemple.criacaoCliente();
        DataExemple.criacaoCategoriaProduto();
        DataExemple.criacaoProduto();

        JFrame frame = new JFrame("Sistema de Cadastro");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(frame, "Deseja sair do programa?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    System.exit(0);
                }
            }
        });

        int opcao;

        do {
            String entrada = JOptionPane.showInputDialog(
                    frame,
                    "=== Menu ===\n"
                            + "\n1 - Cadastro de cliente"
                            + "\n2 - Listagem de clientes"
                            + "\n3 - Alteração de cliente"
                            + "\n4 - Exclusão de cliente"
                            + "\n\n5 - Cadastro de categoria"
                            + "\n6 - Listagem de categorias"
                            + "\n7 - Alteração de categoria"
                            + "\n8 - Exclusão de categoria"
                            + "\n\n9 - Cadastro de produto"
                            + "\n10 - Listagem de produtos"
                            + "\n11 - Alteração de produto"
                            + "\n12 - Exclusão de produto"
                            + "\n\n13 - Cadastro de Pedido"
                            + "\n14 - Listagem de pedidos"
                            + "\n15 - Alteração de pedido"
                            + "\n16 - Exclusão de pedido"
                            + "\n\n17 - Relatório geral"
                            + "\n\n0 - Sair"
            );

            if (entrada == null) {
                int resposta = JOptionPane.showConfirmDialog(frame, "Deseja sair do programa?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    System.exit(0);
                }
            } else if (entrada.matches("-?\\d+(\\.\\d+)?")) {
                opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        clienteController.exibirCadastroCliente();
                        break;

                    case 2:
                        clienteController.exibirListagemClientes();
                        break;

                    case 3:
                        clienteController.exibirAlteracaoCliente();
                        break;

                    case 4:
                        clienteController.exibirExclusaoCliente();
                        break;

                    case 5:
                        categoriaProdutoController.exibirCadastroCategoria();
                        break;

                    case 6:
                        categoriaProdutoController.exibirListagemCategorias();
                        break;

                    case 7:
                        categoriaProdutoController.exibirAlteracaoCategoria();
                        break;

                    case 8:
                        categoriaProdutoController.exibirExclusaoCategoria();
                        break;

                    case 9:
                        produtoController.exibirCadastroProduto();
                        break;

                    case 10:
                        produtoController.exibirListagemProdutos();
                        break;

                    case 11:
                        produtoController.exibirAlteracaoProduto();
                        break;

                    case 12:
                        produtoController.exibirExclusaoProduto();
                        break;
                    case 13:
                        pedidoController.exibirCadastroPedido();
                        break;
                    case 14:
                        pedidoController.exibirListagemPedidos();
                        break;
                    case 15:
                        pedidoController.exibirAlteracaoPedido();
                        break;
                    case 16:
                        pedidoController.exibirExclusaoPedido();
                        break;
                    case 17:
                        relatorioGeralController.gerarRelatorio();
                        break;
                    case 0:
                        int resposta = JOptionPane.showConfirmDialog(frame, "Deseja sair do programa?", "Confirmação", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            frame.dispose();
                            System.exit(0);
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(frame, "Opção inexistente!");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Opção inválida!");
            }

        } while (true);
    }
}
