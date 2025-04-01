import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class InterfaceGrafica extends JFrame implements ActionListener {

    ConexaoBancoDeDados objBancoDeDados;
    private JLabel lblPeso;
    private JTextField txtPeso;
    private JLabel lblImc;
    private JTextField txtImc;
    private JLabel labelCpf;
    private JLabel labelCurso;
    private JComboBox curso;
    private final String[] cursos = { "Direito", "Ciencia da Computacao", "Sistemas de informacao", "Medicina",
            "Psicologia", "Nutricao" };
    private JTextField textCpf;
    double imc;
    private JLabel msgCadastroSucesso;
    private JLabel labelTipoSang;
    private JLabel labelFator;
    private final String[] tiposSanguineos = { "A", "B", "AB", "O" };
    private JComboBox tipoSanguineo;
    private final String[] fatoresRh = { "+", "-" };
    private JComboBox fatorRh;
    private JLabel msglistagem;
    private JLabel labelNome;
    private JLabel labelEndereco;
    private JLabel labelTelefone;
    private JLabel labelAltura;
    private JTextField textAltura;
    private JLabel labelResultadoPesquisa;
    private JLabel labelContatoDeEmergencia;
    private JTextField contatoDeEmergencia;
    private JLabel labelNumeroEmergencia;
    private JLabel labelId;
    private JButton btnlistagem;
    private JButton calcularImc;
    private JButton botaoCadastrar;
    private JButton botaoRemover;
    private JFormattedTextField textTelefone;
    private JButton botaoAlterar;
    private JButton botaoPesquisar;
    private JTextField textNome;
    private JTextField textEndereco;
    private JTextField textId;
    private JTextArea listaPesquisaBancoDeDados;
    private JScrollPane scrollPesquisaBancoDeDados;
    private JFormattedTextField textNumeroEmergencia;
    private JLabel labelTextNumeroEmergencia;
    private Container ctn;

    public InterfaceGrafica() {

        msgCadastroSucesso = new JLabel("Cadastro realizado com sucesso!");
        msglistagem = new JLabel("Relatório gerado com sucesso!");
        labelResultadoPesquisa = new JLabel("Resultado da Pesquisa no Banco de Dados");
        labelNumeroEmergencia = new JLabel("Número de Emergência");
        labelId = new JLabel("ID");
        labelNome = new JLabel("Nome");
        textNome = new JTextField();
        labelEndereco = new JLabel("Endereço");
        textEndereco = new JTextField();
        labelTelefone = new JLabel("Telefone");
        textTelefone = new JFormattedTextField();
        textNumeroEmergencia = new JFormattedTextField();
        listaPesquisaBancoDeDados = new JTextArea();
        scrollPesquisaBancoDeDados = new JScrollPane(listaPesquisaBancoDeDados);
        contatoDeEmergencia = new JTextField();
        try {
            textTelefone = new JFormattedTextField(new MaskFormatter("##-#####-####"));
        } catch (java.text.ParseException e) {
            e.printStackTrace(); // Exibe o erro no console para depuração
            JOptionPane.showMessageDialog(this, "Erro ao formatar o campo de telefone.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        labelCpf = new JLabel("CPF");
        try {
            textCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (java.text.ParseException e) { // se nao der certo ParseException e e depois e.printStackTrace();
            e.printStackTrace();
        }
        labelTipoSang = new JLabel("Tipo Sanguíneo");
        tipoSanguineo = new JComboBox(tiposSanguineos);
        labelFator = new JLabel("Fator RH");
        fatorRh = new JComboBox(fatoresRh);
        labelCurso = new JLabel("Cursos");
        curso = new JComboBox(cursos);
        labelContatoDeEmergencia = new JLabel("Contato de Emergência");
        try {
            textNumeroEmergencia = new JFormattedTextField(new MaskFormatter("##-#####-####"));
        } catch (java.text.ParseException e) {
            e.printStackTrace(); // Exibe o erro no console para depuração
            JOptionPane.showMessageDialog(this, "Erro ao formatar o campo de telefone.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        labelTextNumeroEmergencia = new JLabel("Número de Emergência");
        labelAltura = new JLabel("Altura(m)");
        textAltura = new JTextField();
        // peso e imc
        lblPeso = new JLabel("Peso(kg)");
        txtPeso = new JTextField();
        lblImc = new JLabel("IMC");
        txtImc = new JTextField();
        textNome = new JTextField();
        textEndereco = new JTextField();
        textId = new JTextField();
        botaoCadastrar = new JButton("Cadastrar");
        botaoRemover = new JButton("Remover");
        botaoAlterar = new JButton("Alterar");
        botaoPesquisar = new JButton("Relatorio");
        btnlistagem = new JButton("Listagem");
        calcularImc = new JButton("Calcular IMC");

        // Seção: Dados de Peso, Altura e IMC
        lblPeso.setBounds(20, 20, 100, 25);
        txtPeso.setBounds(130, 20, 100, 25);
        labelAltura.setBounds(20, 60, 100, 25);
        textAltura.setBounds(130, 60, 100, 25);
        lblImc.setBounds(20, 100, 100, 25);
        txtImc.setBounds(130, 100, 100, 25);

        // Seção: Dados de CPF e Curso
        labelCpf.setBounds(20, 140, 100, 25);
        textCpf.setBounds(130, 140, 150, 25);
        labelCurso.setBounds(20, 180, 100, 25);
        curso.setBounds(130, 180, 180, 25);

        // Seção: Mensagens de Cadastro e Relatório
        msgCadastroSucesso.setBounds(20, 220, 300, 25);
        msglistagem.setBounds(20, 300, 300, 25);

        // Seção: Dados de Tipo Sanguíneo
        labelTipoSang.setBounds(20, 260, 120, 25);
        tipoSanguineo.setBounds(150, 260, 60, 25);
        labelFator.setBounds(220, 260, 50, 25);
        fatorRh.setBounds(280, 260, 50, 25);

        // Seção: Dados Pessoais
        labelNome.setBounds(20, 340, 100, 25);
        textNome.setBounds(130, 340, 200, 25);
        labelEndereco.setBounds(20, 380, 100, 25);
        textEndereco.setBounds(130, 380, 200, 25);
        labelTelefone.setBounds(20, 420, 100, 25);
        textTelefone.setBounds(130, 420, 150, 25);

        // Seção: Dados de Pesquisa e Contato de Emergência
        labelContatoDeEmergencia.setBounds(20, 540, 180, 25);
        labelNumeroEmergencia.setBounds(360, 540, 150, 25);
        textNumeroEmergencia.setBounds(480, 540, 150, 25);
        labelTextNumeroEmergencia.setBounds(330, 540, 150, 25);
        contatoDeEmergencia.setBounds(160, 540, 160, 25);

        // Seção: Identificação e Mensagens
        // labelMensagem.setBounds(20, 580, 300, 25);
        labelId.setBounds(20, 620, 100, 25);
        textId.setBounds(130, 620, 100, 25);

        // Seção: Botões
        btnlistagem.setBounds(150, 660, 120, 30);
        botaoCadastrar.setBounds(280, 660, 100, 30);
        botaoRemover.setBounds(390, 660, 100, 30);
        botaoAlterar.setBounds(500, 660, 100, 30);
        botaoPesquisar.setBounds(610, 660, 120, 30);
        calcularImc.setBounds(20, 660, 120, 30);

        // Seção: Área de Pesquisa (Lista e Scroll)
        listaPesquisaBancoDeDados.setBounds(760, 20, 900, 800);
        scrollPesquisaBancoDeDados.setBounds(760, 20, 900, 800);

        ctn = getContentPane(); // pegar uma referência para à janela principal
        ctn.setLayout(null); // limpar todo o conteúdo da janela

        ctn.add(labelNome);
        ctn.add(textNome);
        ctn.add(labelEndereco);
        ctn.add(textEndereco);
        ctn.add(labelTelefone);
        ctn.add(textTelefone);
        ctn.add(labelId);
        ctn.add(textId);
        ctn.add(labelCpf);
        ctn.add(textCpf);
        ctn.add(labelTipoSang);
        ctn.add(tipoSanguineo);
        ctn.add(labelFator);
        ctn.add(fatorRh);
        ctn.add(labelCurso);
        ctn.add(curso);
        ctn.add(labelContatoDeEmergencia);
        ctn.add(contatoDeEmergencia);
        ctn.add(textNumeroEmergencia);
        ctn.add(labelAltura);
        ctn.add(textAltura);
        ctn.add(lblPeso);
        ctn.add(txtPeso);
        ctn.add(lblImc);
        ctn.add(txtImc);
        ctn.add(labelResultadoPesquisa);
        ctn.add(scrollPesquisaBancoDeDados);
        ctn.add(btnlistagem);
        ctn.add(botaoCadastrar);
        ctn.add(botaoRemover);
        ctn.add(botaoAlterar);
        ctn.add(botaoPesquisar);
        ctn.add(listaPesquisaBancoDeDados);
        ctn.add(labelTextNumeroEmergencia);
        ctn.add(calcularImc);
        setVisible(true);
        setSize(900, 370);
        botaoCadastrar.addActionListener(this);
        botaoRemover.addActionListener(this);
        botaoAlterar.addActionListener(this);
        botaoPesquisar.addActionListener(this);
        btnlistagem.addActionListener(this);
        calcularImc.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Calcular IMC")) {
            Double peso = Double.parseDouble(txtPeso.getText());
            Double altura = Double.parseDouble(textAltura.getText()); // Convertendo cm para m
            imc = peso / (altura * altura);
            if (imc >= 18.5 && imc <= 25) {
                JOptionPane.showMessageDialog(null, "IMC: " + imc + " - Peso IDEAL");
            } else if (imc < 18.5) {
                JOptionPane.showMessageDialog(null, "IMC: " + imc + " - Abaixo do peso IDEAL");
            } else if (imc >= 25) {
                JOptionPane.showMessageDialog(null, "IMC: " + imc + " - acima do peso IDEAL");
            }
        }
        if (e.getActionCommand().equals("Cadastrar")) { // cadastrar
            objBancoDeDados = new ConexaoBancoDeDados();
            Pessoa objPessoa = new Pessoa(textNome.getText(), textEndereco.getText(), textTelefone.getText(),
                    textCpf.getText(), (String) tipoSanguineo.getSelectedItem(), (String) fatorRh.getSelectedItem(),
                    (String) curso.getSelectedItem(), contatoDeEmergencia.getText(), textNumeroEmergencia.getText(),
                    textAltura.getText(), txtPeso.getText());
            try {
                objBancoDeDados.inserirDados(objPessoa);
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                objBancoDeDados.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Nao foi possivel realizar insercao!" + ex.getMessage());
                objBancoDeDados.close();
            }
        }
        if (e.getActionCommand().equals("Remover")) {
            objBancoDeDados = new ConexaoBancoDeDados();
            int id = Integer.parseInt(textId.getText());
            try {
                objBancoDeDados.removerDados(id);
                JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso!");
                objBancoDeDados.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao remover cadastro: " + ex.getMessage());
                objBancoDeDados.close();
            }
        }
        if (e.getActionCommand().equals("Listagem")) {
            objBancoDeDados = new ConexaoBancoDeDados();
            try {
                String listagem = objBancoDeDados.relatorio();
                listaPesquisaBancoDeDados.setText(listagem);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
                objBancoDeDados.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + ex.getMessage());
                objBancoDeDados.close();
            }
        }
        if (e.getActionCommand().equals("Alterar")) {
            objBancoDeDados = new ConexaoBancoDeDados();
            int id = Integer.parseInt(textId.getText());
            Pessoa objPessoa = new Pessoa(textNome.getText(), textEndereco.getText(), textTelefone.getText(),
                    textCpf.getText(), (String) tipoSanguineo.getSelectedItem(), (String) fatorRh.getSelectedItem(),
                    (String) curso.getSelectedItem(), contatoDeEmergencia.getText(), textNumeroEmergencia.getText(),
                    textAltura.getText(), txtPeso.getText());
            try {
                objBancoDeDados.alterarDados(objPessoa, id);
                JOptionPane.showMessageDialog(null, "Alteracao feita com sucesso!");
                objBancoDeDados.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro: " + ex.getMessage());
                objBancoDeDados.close();
            }
        }

        if (e.getActionCommand().equals("Relatorio")) { // relatorio esta errado
            objBancoDeDados = new ConexaoBancoDeDados();
            try {
                String relatorio = objBancoDeDados.gerarRelatorio();
                listaPesquisaBancoDeDados.setText(relatorio);
                JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
                objBancoDeDados.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + ex.getMessage());
                objBancoDeDados.close();
            }
        }
    }
}