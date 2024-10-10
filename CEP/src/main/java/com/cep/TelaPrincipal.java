package com.cep;


import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaPrincipal {

    @FXML
    private TextField nomeF;
    @FXML
    private TextField cepF;
    @FXML
    private TextField ruaF;
    @FXML
    private TextField numeroF;
    @FXML
    private TextField cidadeF;
    @FXML
    private TextField estadoF;
    @FXML
    private TextField telefoneF;
    @FXML
    private Button buscar;
    @FXML
    private Button limpar;
    @FXML
    private Button gravar;

    private Buscador buscador = new Buscador();
    
    private ArrayList<Cliente> clientesList = new ArrayList<>();

    @FXML
    private void buscarCep()
    {
        String cep = cepF.getText();
        try
        {
            Endereco endereco = buscador.buscar(cep);
            ruaF.setText(endereco.getRua());
            cidadeF.setText(endereco.getCidade());
            estadoF.setText(endereco.getEstado());
        } catch (IllegalArgumentException e)
        {
            mostrarAlerta(AlertType.WARNING, "Aviso", "CEP inválido.");
        } catch (IOException e)
        {
            mostrarAlerta(AlertType.ERROR, "Erro", "Não conseguimos buscar o CEP: " + e.getMessage());
        } catch (Exception e)
        {
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro desconhecido: " + e.getMessage());
        }
    }

    @FXML
    private void limparCampos()
    {
        nomeF.clear();
        cepF.clear();
        ruaF.clear();
        numeroF.clear();
        cidadeF.clear();
        estadoF.clear();
        telefoneF.clear();
    }

    @FXML
    private void gravarCliente()
    {
        Cliente cliente = new Cliente();
        cliente.setNome(nomeF.getText());
        cliente.setTelefone(telefoneF.getText());
        cliente.setEndereco(new Endereco(cepF.getText(), ruaF.getText(), numeroF.getText(), cidadeF.getText(), estadoF.getText()));
        clientesList.add(cliente);
        mostrarAlerta(AlertType.CONFIRMATION, "Confirmação", "Cadastro feito com sucesso!");
       
        mostrarClientes(cliente);
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensagem)
    {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarClientes(Cliente cliente)
    {
        System.out.println("Cadastrado como:");
        System.out.println("Nome: " + cliente.getNome() + 
                           ", Telefone: " + cliente.getTelefone() + 
                           ", Endereco: " + cliente.getEndereco().getRua() + 
                           ", Numero: " + cliente.getEndereco().getNumero() +
                           ", Cidade: " + cliente.getEndereco().getCidade() + 
                           ", Estado: " + cliente.getEndereco().getEstado());
    }
}
