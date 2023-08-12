package br.com.senai.models;

public class Pedido { // fazer heranca (extends) ou ver interface para ligar a classe pedidos a produtos
	// Atributos
	private String dataEmissao;
	private String prazoEntrega;
	private Cliente cliente;
	private Produto produto;
	private Double quantidade;
	private Double valorPedido;
	private String statusPedido;
	
	
	// Construtor
	public Pedido() {
		
	}
	public Pedido(String dataEmissao, String prazoEntrega, Cliente cliente, Produto produto, Double quantidade,
			Double valorPedido, String statusPedido) {
		this.dataEmissao = dataEmissao;
		this.prazoEntrega = prazoEntrega;
		this.cliente = cliente;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorPedido = valorPedido;
		this.statusPedido = statusPedido;
	}
	
	// Getters e setters
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getPrazoEntrega() {
		return prazoEntrega;
	}
	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorPedido() {
		return valorPedido;
	}
	public void setValorPedido(Double valorPedido) {
		this.valorPedido = valorPedido;
	}
	public String getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	// toString
	@Override
	public String toString() {
		return "Data de emissão: " + dataEmissao
				+ "\nPrazo de entrega: " + prazoEntrega 
				+ "\nNome do cliente: " + cliente.getNome()
				+ "\nTelefone: " + cliente.getTelefone()
				+ "\nEndereço: " + cliente.getEndereco() + " | " + cliente.getCep()
				+ "\nNome do produto: " + produto.getNome()
				+ "\nValor do produto: " + produto.getValor()
				+ "\nQuantidade do produto: " + quantidade + " " +  produto.getUnidadeMedida() 
				+ "\nValor total: " + valorPedido
				+ "\nStatus do pedido: " + statusPedido;
	}
}
