package br.com.senai.controllers;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import br.com.senai.models.Cliente;

public class ClienteController {
    public static ArrayList<Cliente> listaClientes = new ArrayList<>();

    public void exibirCadastroCliente() {
        exibirCamposCliente(null, "Cadastro de cliente");
    }

    public void exibirListagemClientes() {
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
            return;
        }

        String armazenamentoTxtCliente = "Listagem de clientes\n";
        armazenamentoTxtCliente += listarTxtClientes();
        JOptionPane.showMessageDialog(null, armazenamentoTxtCliente);
    }

    public void exibirAlteracaoCliente() {
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
            return;
        }

        String armazenamentoTxtCliente = "Alteração de cliente\n";
        armazenamentoTxtCliente += listarTxtClientes();

        int indiceClienteAlteracao = obterIndiceCliente(armazenamentoTxtCliente, "Escolha o cliente que deseja alterar:");
        if (indiceClienteAlteracao != -1) {
            Cliente clienteAlteracao = listaClientes.get(indiceClienteAlteracao);
            exibirCamposCliente(clienteAlteracao, "Alteração de cliente");
        }
    }

    public void exibirExclusaoCliente() {
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
            return;
        }

        String armazenamentoTxtCliente = "Exclusão de cliente\n";
        armazenamentoTxtCliente += listarTxtClientes();

        int indiceClienteExclusao = obterIndiceCliente(armazenamentoTxtCliente, "Escolha o cliente que deseja excluir:");
        if (indiceClienteExclusao != -1) {
            listaClientes.remove(indiceClienteExclusao);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
        }
    }

    public int obterIndiceCliente(String mensagem, String titulo) {
        int indice = -1;
        while (true) {
            String indiceInput = JOptionPane.showInputDialog(mensagem + "\n\n " + titulo);
            if (indiceInput == null) {
                break; // O usuário clicou no botão "Cancelar" ou fechou a janela
            }

            try {
                indice = Integer.parseInt(indiceInput);
                if (indice >= 1 && indice <= listaClientes.size()) {
                    return indice - 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Índice de cliente inválido! Digite um número correspondente ao índice do cliente.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Índice de cliente inválido! Digite um número correspondente ao índice do cliente.");
            }
        }

        return -1;
    }

    public String listarTxtClientes() {
        String armazenamentoTxtClientes = "";
        for (int i = 0; i < listaClientes.size(); i++) {
            int indice = i + 1;
            armazenamentoTxtClientes += "\nÍndice: " + indice + "\n" + listaClientes.get(i) + "\n";
        }

        return armazenamentoTxtClientes;
    }

    public void exibirCamposCliente(Cliente clienteAlteracao, String titulo) {
        // Título da caixa de diálogo de acordo com a ação (Cadastro ou alteração)
        String mensagemTitulo = (clienteAlteracao != null) ? "Alteração de cliente" : "Cadastro de cliente";

        String nomeClienteAtual = (clienteAlteracao != null) ? clienteAlteracao.getNome() : null;
        String enderecoAtual = (clienteAlteracao != null) ? clienteAlteracao.getEndereco() : null;
        Integer cepAtual = (clienteAlteracao != null) ? clienteAlteracao.getCep() : null;
        Long telefoneAtual = (clienteAlteracao != null) ? clienteAlteracao.getTelefone() : null;
        String cpfAtual = (clienteAlteracao != null) ? clienteAlteracao.getCpf() : null;

        // Solicita os dados do cliente
        String nomeCliente = JOptionPane.showInputDialog(mensagemTitulo + "\nNome do cliente:", nomeClienteAtual);
        if (nomeCliente == null || nomeCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome de cliente inválido!");
            return;
        }

        String enderecoCliente = JOptionPane.showInputDialog("Endereço do cliente:", enderecoAtual);
        if (enderecoCliente == null || enderecoCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Endereço de cliente inválido!");
            return;
        }

        // Tratamento para CEP inválido
        Integer cepCliente = null;
        while (cepCliente == null) {
            String cepInput = JOptionPane.showInputDialog("CEP do cliente:", cepAtual);
            if (cepInput == null || cepInput.isEmpty()) {
                JOptionPane.showMessageDialog(null, "CEP não pode ser vazio!");
            } else {
                try {
                    cepCliente = Integer.parseInt(cepInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "CEP inválido! Digite apenas números.");
                }
            }
        }

        // Tratamento para telefone inválido
        Long telefoneCliente = null;
        while (telefoneCliente == null) {
            String telefoneInput = JOptionPane.showInputDialog("Telefone do cliente:", telefoneAtual);
            if (telefoneInput == null || telefoneInput.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Telefone não pode ser vazio!");
            } else {
                try {
                    telefoneCliente = Long.parseLong(telefoneInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Telefone inválido! Digite apenas números.");
                }
            }
        }

        String cpfCliente = JOptionPane.showInputDialog("CPF do cliente:", cpfAtual);
        
        // Altera ou cadastra um cliente
        if (clienteAlteracao != null) {
            clienteAlteracao.setNome(nomeCliente);
            clienteAlteracao.setEndereco(enderecoCliente);
            clienteAlteracao.setCep(cepCliente);
            clienteAlteracao.setTelefone(telefoneCliente);
            clienteAlteracao.setCpf(cpfCliente);

            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!\n\n" + clienteAlteracao);
        } else {
            Cliente novoCliente = new Cliente(nomeCliente, enderecoCliente, cepCliente, telefoneCliente, cpfCliente);
            listaClientes.add(novoCliente);

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\n\n" + novoCliente);
        }
    }
}
