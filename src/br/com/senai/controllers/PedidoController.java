package br.com.senai.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import br.com.senai.models.Cliente;
import br.com.senai.models.Pedido;
import br.com.senai.models.Produto;

public class PedidoController {
	public static ArrayList<Pedido> listaPedidos = new ArrayList<>();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void exibirListagemPedidos() {
        if (listaPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pedido cadastrado.");
            return;
        }

        String listaPedidosTxt = "Listagem de pedidos:\n";
        listaPedidosTxt += listarTxtPedidos();
        JOptionPane.showMessageDialog(null, listaPedidosTxt);
    }

    public void exibirCadastroPedido() { 
    	if (ClienteController.listaClientes.isEmpty()) { 
        	JOptionPane.showMessageDialog(null, "Para cadastrar um pedido, primeiro cadastre um cliente!");
        	return;
        }
        if (ProdutoController.listaProdutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Para cadastrar um pedido, primeiro cadastre uma produto!");
            return;
        }
        

        exibirCamposPedido(null);
        JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");
    }

    public void exibirAlteracaoPedido() {
        if (listaPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pedido cadastrado.");
            return;
        }

        String listaPedidosTxt = "Listagem de pedidos:\n";
        listaPedidosTxt += listarTxtPedidos();

        int indicePedido = obterInteger(listaPedidosTxt, "Digite o índice do pedido que deseja alterar:");

        if (indicePedido != -1) {
            Pedido pedidoAlteracao = listaPedidos.get(indicePedido);
            exibirCamposPedido(pedidoAlteracao);
            JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso!");
        }
    }

    public void exibirExclusaoPedido() {
        if (listaPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pedido cadastrado.");
            return;
        }

        String listaPedidosTxt = "Listagem de pedidos:\n";
        listaPedidosTxt += listarTxtPedidos();

        int indicePedidoExclusao = obterInteger(listaPedidosTxt, "Digite o índice do pedido que deseja excluir:");

        if (indicePedidoExclusao != -1) {
            listaPedidos.remove(indicePedidoExclusao);
            JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");
        }
    }

    public int obterInteger(String mensagem, String titulo) {
        int indice = -1;
        while (true) {
            String indiceInput = JOptionPane.showInputDialog(mensagem + "\n\n " + titulo);
            if (indiceInput == null) {
                break; // O usuário clicou no botão "Cancelar" ou fechou a janela
            }

            try {
                indice = Integer.parseInt(indiceInput) - 1;
                if (indice >= 0 && indice < listaPedidos.size()) {
                    return indice;
                } else {
                    JOptionPane.showMessageDialog(null, "Índice de pedido inválido!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Índice de pedido inválido!");
            }
        }

        return -1;
    }

    public String listarTxtPedidos() {
        String listaPedidosTxt = "";
        for (int i = 0; i < listaPedidos.size(); i++) {
        	Pedido pedido = listaPedidos.get(i);
            int indice = i + 1;
            listaPedidosTxt += "\nPedido " + indice + ":\n" + pedido.toString() + "\n";
        }
        return listaPedidosTxt;
    }

    public void exibirCamposPedido(Pedido pedidoAlteracao) {
        // Título da caixa de diálogo de acordo com a ação (Cadastro ou alteração)
        String titulo = (pedidoAlteracao != null) ? "Alteração de pedido" : "Cadastro de pedido";

        // Array com os status dos pedidos
        String[] status = {"Preparando", "Enviado", "Entregue", "Cancelado"};

        // Faz uma listagem dos clientes cadastrados
        ClienteController clienteController = new ClienteController();
        String armazenamentoTxtClientes = "\n\nListagem de clientes";
        armazenamentoTxtClientes += clienteController.listarTxtClientes();

        // Faz uma listagem dos produtos cadastrados
        ProdutoController produtoController = new ProdutoController();
        String armazenamentoTxtProdutos = "\n\nListagem de produtos\n";
        armazenamentoTxtProdutos += produtoController.listarTxtProdutos();

        Integer indiceCliente = obterIndice(titulo + armazenamentoTxtClientes + "\n\nÍndice do cliente:", 1);
        if (indiceCliente == null) {
            return; // O usuário cancelou a operação
        }
        Integer indiceProduto = obterIndice(titulo + armazenamentoTxtProdutos + "\n\nÍndice do produto:", 1);
        if (indiceProduto == null) {
            return; // O usuário cancelou a operação
        }

        Cliente cliente = ClienteController.listaClientes.get(indiceCliente);
        Produto produto = ProdutoController.listaProdutos.get(indiceProduto);

        // Faz uma verificação para ver se já existe uma data de emissão ou é um novo pedido,
        // se for novo ele coloca a data do CP automaticamente
        String dataEmissaoAtual = (pedidoAlteracao != null) ? pedidoAlteracao.getDataEmissao() : sdf.format(System.currentTimeMillis());
        String prazoEntregaAtual = (pedidoAlteracao != null) ? pedidoAlteracao.getPrazoEntrega() : null;
        Double quantidadeAtual = (pedidoAlteracao != null) ? pedidoAlteracao.getQuantidade() : null;
        Integer statusPedidoAtual = (pedidoAlteracao != null) ? verificarStatus(pedidoAlteracao.getStatusPedido(), status) : 0;

        String dataEmissao = JOptionPane.showInputDialog(titulo + "\nData emissão:", dataEmissaoAtual);
        String prazoEntrega = JOptionPane.showInputDialog(titulo + "\nPrazo de entrega:", prazoEntregaAtual);

        Double quantidade = obterDouble(titulo + "\nQuantidade do produto:", quantidadeAtual);

        // ComboBox do status
        String statusPedido = (String) JOptionPane.showInputDialog(null,
                "Selecione uma opção:", "Status do pedido",
                JOptionPane.PLAIN_MESSAGE, null,
                status, status[statusPedidoAtual]);

        // Multiplicação para dar o valor total
        Double valorTotal = produto.getValor() * quantidade;

        if (pedidoAlteracao != null) {
            pedidoAlteracao.setDataEmissao(dataEmissao);
            pedidoAlteracao.setPrazoEntrega(prazoEntrega);
            pedidoAlteracao.setCliente(cliente);
            pedidoAlteracao.setProduto(produto);

            if (quantidade > produto.getQtdEstoque()) {
                JOptionPane.showMessageDialog(null, "Quantidade solicitada maior que o estoque disponível!");
                return;
            }

            
            
            // Verificações
            if (("Enviado".equals(statusPedido) || "Entregue".equals(statusPedido))) {
                Double diffQuantidade = quantidade - pedidoAlteracao.getQuantidade();
                if (produto.getQtdEstoque() >= diffQuantidade) {
                    produto.setQtdEstoque(produto.getQtdEstoque() - diffQuantidade);
                    pedidoAlteracao.setQuantidade(quantidade);
                    pedidoAlteracao.setValorPedido(valorTotal);
                    
                    /*
                     * 
                     * 
                     * 
                     * 
                     * 
                     * 
                     */
                
                } else if ((("Enviado".equals(statusPedido) || "Entregue".equals(statusPedido))) && (("Preparando".equals(pedidoAlteracao.getStatusPedido()) || "Cancelado".equals(pedidoAlteracao.getStatusPedido())))) {
                	diferencaQuantidade(quantidade, pedidoAlteracao, produto, valorTotal);
				} else {
                    JOptionPane.showMessageDialog(null, "Quantidade solicitada maior que o estoque disponível!");
                    return;
                }
                
            } else { // Caso não seja 
                pedidoAlteracao.setQuantidade(quantidade);
                pedidoAlteracao.setValorPedido(valorTotal);
            }
            pedidoAlteracao.setStatusPedido(statusPedido);
            
            // Caso seja criação de novo pedido
       	} else {
            Pedido pedido = new Pedido(dataEmissao, prazoEntrega, cliente, produto, quantidade, valorTotal, statusPedido);

            if (quantidade > produto.getQtdEstoque()) {
                JOptionPane.showMessageDialog(null, "Quantidade solicitada maior que o estoque disponível!");
                return;
            }

            if (("Enviado".equals(statusPedido) || "Entregue".equals(statusPedido))) {
                produto.setQtdEstoque(produto.getQtdEstoque() - quantidade);
            }

            listaPedidos.add(pedido);
        }
        
        // Verificar estoque mínimo
        if (produto.getQtdEstoque() <= produto.getQtdMinima()) {
            JOptionPane.showMessageDialog(null, "Atenção: A quantidade está abaixo ou igual à quantidade mínima do produto.");
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
                if ((indice >= 1 && indice <= ClienteController.listaClientes.size() && indice >= 1 && indice <= ProdutoController.listaProdutos.size()) ) {
                    return indice - 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Índice inválido!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Índice inválido!");
            }
        }
    }

    public Integer obterInteger(String mensagem, Integer valorPadrao) {
        while (true) {
            String valorStr = JOptionPane.showInputDialog(mensagem, valorPadrao);
            if (valorStr != null) {
                if (valorStr.matches("^\\d+$")) {
                    try {
                        return Integer.parseInt(valorStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor inválido! Certifique-se de digitar um número válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Valor inválido! Certifique-se de digitar um número inteiro válido.");
                }
            } else {
                break; // O usuário clicou no botão "Cancelar" ou fechou a janela
            }
        }
        return null; // Retorna null para indicar que a entrada não foi válida
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

    public Integer verificarStatus(String statusPedidoAtual, String[] status) {
    	Integer posicaoStatus = null;
    	for (int i = 0; i < status.length; i++) {
            if (status[i] == statusPedidoAtual) {
                posicaoStatus = i;
            }
    	}
		return posicaoStatus;
    }
    
    public void diferencaQuantidade(Double quantidade, Pedido pedidoAlteracao, Produto produto, Double valorTotal){
    	Double diffQuantidade = quantidade - pedidoAlteracao.getQuantidade();
        if (produto.getQtdEstoque() >= diffQuantidade) {
            produto.setQtdEstoque(produto.getQtdEstoque() - diffQuantidade);
            pedidoAlteracao.setQuantidade(quantidade);
            pedidoAlteracao.setValorPedido(valorTotal);
        }
    }
}