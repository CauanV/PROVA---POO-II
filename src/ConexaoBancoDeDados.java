import java.sql.*;
import java.util.ArrayList;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class ConexaoBancoDeDados {
    private Connection conexao;
    private final String URL_Bancodedados = "jdbc:mysql://localhost:3306/pessoa";
    private final String usuario = "root";
    private final String senha = "root";

    public void ConexaoBancoDeDados() {
        try {
            conexao = DriverManager.getConnection(URL_Bancodedados, usuario, senha);
            System.out.println("Conexao realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Erro ao acessar banco de dados!");
        }
    }

    public void close() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao fechar a conexão com o banco de dados.");
        }
    }

    public String inserirDados(Pessoa objetoPessoa) throws SQLException {
        ConexaoBancoDeDados(); // solicitar conexao com banco de dados

        if (conexao != null) {
            PreparedStatement psInsert = conexao
                    .prepareStatement(
                            "INSERT INTO pessoa(nome,endereco,telefone,cpf,tipoSanguineo,fatorRh,curso,ContatoEmergencia,numeroEmergencia,altura,peso,imc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            psInsert.setString(1, objetoPessoa.getNome());
            psInsert.setString(2, objetoPessoa.getEndereco());
            psInsert.setString(3, objetoPessoa.getTelefone());
            psInsert.setString(4, objetoPessoa.getCpf());
            psInsert.setString(5, objetoPessoa.getTipoSanguineo());
            psInsert.setString(6, objetoPessoa.getFatorRh());
            psInsert.setString(7, objetoPessoa.getCurso());
            psInsert.setString(8, objetoPessoa.getContatoDeEmergencia());
            psInsert.setString(9, objetoPessoa.getTextNumeroEmergencia());
            psInsert.setString(10, objetoPessoa.getTextAltura());
            psInsert.setString(11, objetoPessoa.getTxtPeso());
            psInsert.setString(12, objetoPessoa.calculoImc().toString());
            psInsert.execute();
            close();
            return "Cadastro realizado com sucesso";
        } else
            return "Erro! Nao foi possivel realizar a insercao";
    }

    public String alterarDados(Pessoa objetoPessoa, int id) throws SQLException {
        ConexaoBancoDeDados(); // solicitar conexao com banco de dados
        if (conexao != null) {
            PreparedStatement comandoUpdate = conexao
                    .prepareStatement("UPDATE pessoa SET endereco = ?, telefone = ?,nome = ?, WHERE id = ?");
            comandoUpdate.setString(1, objetoPessoa.getEndereco());
            comandoUpdate.setString(2, objetoPessoa.getTelefone());
            comandoUpdate.setString(3, objetoPessoa.getNome());
            comandoUpdate.setInt(4, id);
            comandoUpdate.execute();

            close();
            return "Alteracao feita!";
        } else
            return "Nao foi possivel fazer alteracao";
    }

    public String removerDados(int id) throws SQLException {
        ConexaoBancoDeDados();
        if (conexao != null) {
            PreparedStatement comandoUpdate = conexao.prepareStatement("DELETE FROM pessoa where id = ?");
            comandoUpdate.setInt(1, id);
            comandoUpdate.execute();

            close();
            return "remocao feita com sucesso!";
        } else
            return "Nao foi possivel remover.";
    }

    public String relatorio() throws SQLException {
        ConexaoBancoDeDados();

        ArrayList<String> relatorioBancoDeDados = new ArrayList<String>();

        if (conexao != null) {
            Statement comandoConsulta = conexao.createStatement();
            ResultSet resultadoConsultaBD = comandoConsulta.executeQuery("SELECT * FROM pessoa");
            String resultado;
            String nome;
            String endereco;
            String telefone;
            String cpf;
            String tipoSanguineo;
            String fatorRh;
            String curso;
            String ContatoDeEmergencia;
            String textNumeroEmergencia;
            String textAltura;
            String txtPeso;
            double imc = 0;
            while (resultadoConsultaBD.next()) {
                nome = resultadoConsultaBD.getString("nome");
                endereco = resultadoConsultaBD.getString("endereco");
                telefone = resultadoConsultaBD.getString("telefone");
                cpf = resultadoConsultaBD.getString("cpf");
                tipoSanguineo = resultadoConsultaBD.getString("tipoSanguineo");
                fatorRh = resultadoConsultaBD.getString("fatorRh");
                curso = resultadoConsultaBD.getString("curso");
                ContatoDeEmergencia = resultadoConsultaBD.getString("ContatoEmergencia");
                textNumeroEmergencia = resultadoConsultaBD.getString("numeroEmergencia");
                textAltura = resultadoConsultaBD.getString("altura");
                txtPeso = resultadoConsultaBD.getString("peso");
                imc = resultadoConsultaBD.getDouble("imc");

                resultado = "# ID: " + "Nome: " + nome + "Endereco: " + endereco + "Telefone: " + telefone + "CPF: "
                        + cpf + "Tipo Sanguineo: " + tipoSanguineo + "Fator Rh: " + fatorRh + "Curso: " + curso
                        + "Contato de Emergencia: " + ContatoDeEmergencia + "Numero de Emergencia: "
                        + textNumeroEmergencia
                        + "Altura: " + textAltura + "Peso: " + txtPeso + "IMC: " + imc;
                relatorioBancoDeDados.add(resultado);
            }
            close();
            return relatorioBancoDeDados.toString();
        }
        return null;
    }

    public String pesquisarDados(int id) throws SQLException {
        ConexaoBancoDeDados();
        String resultado = null;
        if (conexao != null) {
            PreparedStatement comandoConsulta = conexao.prepareStatement("SELECT * FROM pessoa WHERE id = ?");
            comandoConsulta.setInt(1, id);
            ResultSet resultadoConsultaBD = comandoConsulta.executeQuery(); // usado para armazenar os resultados de uma
                                                                            // consulta SQL executada em um banco de
                                                                            // dados. Ele é retornado pelo método
                                                                            // executeQuery() de um objeto Statement ou
                                                                            // PreparedStatement
            if (resultadoConsultaBD.next()) {
                resultado = "ID: " + resultadoConsultaBD.getInt("id") + ", Nome: "
                        + resultadoConsultaBD.getString("nome")
                        + ", Endereco: " + resultadoConsultaBD.getString("endereco") + ", Telefone: "
                        + resultadoConsultaBD.getString("telefone");
            }
            close();
        }
        return resultado;
    }
}