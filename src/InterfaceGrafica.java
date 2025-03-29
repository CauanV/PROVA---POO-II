import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class InterfaceGrafica extends JFrame implements ActionListener {

    // ConexaoBancoDeDados objBancoDeDados;
    private JLabel lblPeso;
    private JTextField txtPeso;
    private JLabel lblAltura;
    private JTextField txtAltura; // conferir na hora da conversao-> passar pra double
    private JLabel lblImc;
    private JTextField txtImc;
    private JLabel labelCpf;
    private JLabel labelCurso;
    private JComboBox curso;
    private final String[] cursos = { "Direito", "Ciencia da Computacao", "Sistemas de informacao", "Medicina",
            "Psicologia", "Nutricao" };
    private JTextField textCpf;
    private JLabel msgCadastroSucesso;
    private JLabel labelTipoSang;
    private JLabel labelFator;
    private final String[] tiposSanguineos = { "A", "B", "AB", "O" };
    private JComboBox tipoSanguineo;
    private final String[] fatoresRh = { "+", "-" };
    private JComboBox fatorRh;
    private JLabel msgRelatorio;
    private JLabel labelNome;
    private JLabel labelEndereco;
    private JLabel labelTelefone;
    private JLabel labelAltura;
    private JTextField textAltura;
    private JLabel labelResultadoPesquisa;
    private JLabel labelContatoDeEmergencia;
    private JTextField textContatoDeEmergencia;
    private JTextField labelNumeroEmergencia;
    private JLabel labelMensagem;
    private JLabel labelId;
    private JButton btnCadastrar;
    private JButton btnRelatorio;
    private JButton botaoInserir;
    private JButton botaoRemover;
    private JFormattedTextField textTelefone;
    private JButton botaoAlterar;
    private JButton botaoPesquisar;
    private JTextField textNome;
    private JTextField textEndereco;
    private JTextField textId;
    private JTextArea listaPesquisaBancoDeDados;
    private JScrollPane scrollPesquisaBancoDeDados;
    private Container ctn;

    public InterfaceGrafica() {

        labelNome = new JLabel("Nome");
        textNome = new JTextField();
        labelEndereco = new JLabel("Endereço");
        textEndereco = new JTextField();
        labelTelefone = new JLabel("Telefone");
        textTelefone = new JFormattedTextField();
        scrollPesquisaBancoDeDados = new JScrollPane(listaPesquisaBancoDeDados);
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
        textContatoDeEmergencia = new JTextField();
        try {
            JFormattedTextField textNumeroEmergencia = new JFormattedTextField(new MaskFormatter("##-#####-####"));
        } catch (java.text.ParseException e) {
            e.printStackTrace(); // Exibe o erro no console para depuração
            JOptionPane.showMessageDialog(this, "Erro ao formatar o campo de telefone.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        labelAltura = new JLabel("Altura em cm");
        textAltura = new JTextField();
        // peso e imc
        lblPeso = new JLabel("Peso em kg");
        txtPeso = new JTextField();
        lblImc = new JLabel("IMC");
        txtImc = new JTextField();
        textNome = new JTextField();
        textEndereco = new JTextField();
        textId = new JTextField();
        botaoInserir = new JButton("Inserir");
        botaoRemover = new JButton("Remover");
        botaoAlterar = new JButton("Alterar");
        botaoPesquisar = new JButton("Pesquisar");
        labelMensagem = new JLabel(" ----");

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
        ctn.add(botaoInserir);
        ctn.add(botaoRemover);
        ctn.add(botaoAlterar);
        ctn.add(botaoPesquisar);
        setVisible(true);
        setSize(900, 370);
    }

    public void actionPerformed(ActionEvent e) {
        // if (e.getActionCommand().equals("")) {
        // }
    }

}