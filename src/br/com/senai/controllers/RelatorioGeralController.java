package br.com.senai.controllers;

import javax.swing.JOptionPane;

import br.com.senai.models.Pedido;

public class RelatorioGeralController {

    public void gerarRelatorio() {
        double valorTotalProdutos = calcularValorTotalPedidosEntregues();
        String produtoMaisVendido = calcularProdutoMaisVendido();
        int qtdPedidosEntregues = calcularQuantidadePedidosEntregues();

        String relatorioTxt = "Valor Total dos Pedidos Entregues: " + valorTotalProdutos + "\n"
                             + "Produto Mais Vendido: " + produtoMaisVendido + "\n"
                             + "Quantidade Total de Pedidos Entregues: " + qtdPedidosEntregues + "\n\n";

        for (Pedido pedido : PedidoController.listaPedidos) {
            if ("Entregue".equals(pedido.getStatusPedido())) {
                relatorioTxt += "Data de Emissão: " + pedido.getDataEmissao()
                            + " | Produto: " + pedido.getProduto().getNome()
                            + " | Quantidade: " + pedido.getQuantidade()
                            + " | Valor Unitário: " + pedido.getProduto().getValor()
                            + " | Total: " + pedido.getValorPedido()
                            + "\n";
            }
        }

        if (qtdPedidosEntregues > 0) {
            JOptionPane.showMessageDialog(null, "Relatório de Pedidos Entregues:\n\n" + relatorioTxt);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum pedido entregue encontrado.");
        }
    }

    private double calcularValorTotalPedidosEntregues() {
        double valorTotal = 0.0;
        for (Pedido pedido : PedidoController.listaPedidos) {
            if ("Entregue".equals(pedido.getStatusPedido())) {
                valorTotal += pedido.getValorPedido();
            }
        }
        return valorTotal;
    }

    private String calcularProdutoMaisVendido() {
        if (PedidoController.listaPedidos.isEmpty()) {
            return "Nenhum pedido cadastrado.";
        }

        Pedido pedidoMaisVendido = null;
        Double maxQuantidade = 0.0;

        for (Pedido pedido : PedidoController.listaPedidos) {
            if ("Entregue".equals(pedido.getStatusPedido()) && pedido.getQuantidade() > maxQuantidade) {
                maxQuantidade = pedido.getQuantidade();
                pedidoMaisVendido = pedido;
            }
        }

        if (pedidoMaisVendido != null) {
            return pedidoMaisVendido.getProduto().getNome();
        } else {
            return "Produto mais vendido não encontrado.";
        }
    }

    private int calcularQuantidadePedidosEntregues() {
        int qtdPedidosEntregues = 0;
        for (Pedido pedido : PedidoController.listaPedidos) {
            if ("Entregue".equals(pedido.getStatusPedido())) {
                qtdPedidosEntregues++;
            }
        }
        return qtdPedidosEntregues;
    }
}
