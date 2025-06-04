package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import model.Livro;
import dao.LivroDAO;

public class LivroController {
    @FXML private TextField txtTitulo, txtAutor, txtAno, txtGenero, txtIsbn;
    @FXML private TableView<Livro> table;
    @FXML private TableColumn<Livro, String> colTitulo, colAutor, colGenero, colIsbn;
    @FXML private TableColumn<Livro, Integer> colAno;

    private Livro livroSelecionado;

    @FXML
    private void initialize() {
        colTitulo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitulo()));
        colAutor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAutor()));
        colAno.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getAno()).asObject());
        colGenero.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenero()));
        colIsbn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIsbn()));

        atualizarTabela();

        table.setOnMouseClicked(e -> {
            livroSelecionado = table.getSelectionModel().getSelectedItem();
            if (livroSelecionado != null) {
                txtTitulo.setText(livroSelecionado.getTitulo());
                txtAutor.setText(livroSelecionado.getAutor());
                txtAno.setText(String.valueOf(livroSelecionado.getAno()));
                txtGenero.setText(livroSelecionado.getGenero());
                txtIsbn.setText(livroSelecionado.getIsbn());
            }
        });
    }

    @FXML
    private void adicionar() {
        Livro livro = new Livro(
            txtTitulo.getText(),
            txtAutor.getText(),
            Integer.parseInt(txtAno.getText()),
            txtGenero.getText(),
            txtIsbn.getText()
        );
        LivroDAO.inserir(livro);
        limparCampos();
        atualizarTabela();
    }

    @FXML
    private void editar() {
        if (livroSelecionado != null) {
            livroSelecionado.setTitulo(txtTitulo.getText());
            livroSelecionado.setAutor(txtAutor.getText());
            livroSelecionado.setAno(Integer.parseInt(txtAno.getText()));
            livroSelecionado.setGenero(txtGenero.getText());
            livroSelecionado.setIsbn(txtIsbn.getText());
            LivroDAO.atualizar(livroSelecionado);
            limparCampos();
            atualizarTabela();
        }
    }

    @FXML
    private void excluir() {
        if (livroSelecionado != null) {
            LivroDAO.excluir(livroSelecionado.getId());
            limparCampos();
            atualizarTabela();
        }
    }

    private void atualizarTabela() {
        table.setItems(FXCollections.observableArrayList(LivroDAO.listar()));
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtAno.clear();
        txtGenero.clear();
        txtIsbn.clear();
        livroSelecionado = null;
    }
}
