package br.com.senai.models;

public class RelatorioGeral extends Pedido {
    private Double valorTotal;
    private String produtoMaisVendido;
    private Integer qtdPedidosTotal;

    // Construtor
    public RelatorioGeral() {
        super();
    }

    public RelatorioGeral(Double valorTotal, String produtoMaisVendido, Integer qtdPedidosTotal,
    						String dataEmissao, String prazoEntrega, Cliente cliente, Produto produto, Double quantidade,
    						Double valorPedido, String statusPedido) {
    	super(dataEmissao, prazoEntrega, cliente, produto, quantidade, valorPedido, statusPedido);
        this.valorTotal = valorTotal;
        this.produtoMaisVendido = produtoMaisVendido;
        this.qtdPedidosTotal = qtdPedidosTotal;
    }

    // Getters e setters
    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getProdutoMaisVendido() {
        return produtoMaisVendido;
    }

    public void setProdutoMaisVendido(String produtoMaisVendido) {
        this.produtoMaisVendido = produtoMaisVendido;
    }

    public Integer getQtdPedidoTotal() {
        return qtdPedidosTotal;
    }

    public void setQtdPedidosTotal(Integer qtdPedidosTotal) {
        this.qtdPedidosTotal = qtdPedidosTotal;
    }

	public Integer getQtdPedidosTotal() {
		return qtdPedidosTotal;
	}
}
