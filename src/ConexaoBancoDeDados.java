import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBancoDeDados {
    private static Connection conexao;
    private final String URL_Bancodedados = "***";
    private final String usuario = "***";
    private final String senha = "***";

    public ConexaoBancoDeDados() {
        try {
            conexao = DriverManager.getConnection(URL_Bancodedados, usuario, senha);
            System.out.println("Conexao realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Erro ao acessar banco de dados!");
        }
    }

    public static void close() {
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
        if (conexao != null) {
            PreparedStatement psInsert = conexao
                    .prepareStatement(
                            "INSERT INTO pessoa(nome,endereco,telefone,cpf,tipoSanguineo,fatorRh,curso,contatoEmergencia,numeroEmergencia,altura,peso,imc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
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
        if (conexao != null) {
            PreparedStatement comandoUpdate = conexao
                    .prepareStatement("UPDATE pessoa SET endereco = ?, telefone = ?, nome = ?, WHERE id = ?");
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
            int id;
            while (resultadoConsultaBD.next()) {
                id = resultadoConsultaBD.getInt("id");
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

                resultado = "# ID: " + id + " Nome: " + nome + " Endereco: " + endereco + " Telefone: " + telefone
                        + " CPF: "
                        + cpf + " Tipo Sanguineo: " + tipoSanguineo + " Fator Rh: " + fatorRh + "\nCurso: " + curso
                        + " Contato de Emergencia: " + ContatoDeEmergencia + " Numero de Emergencia: "
                        + textNumeroEmergencia
                        + " Altura: " + textAltura + " Peso: " + txtPeso + " IMC: " + imc + "\n\n"; // ver se esse \n
                                                                                                    // resolve
                // o problema de nao
                // separacao de pesssoas
                relatorioBancoDeDados.add(resultado);
            }
            close();
            return relatorioBancoDeDados.toString();
        }
        return null;
    }

    public static String gerarRelatorio() throws SQLException {
        List<Double> pesos = new ArrayList<>();
        List<Double> alturas = new ArrayList<>();
        List<Double> imcs = new ArrayList<>();

        String maiorPeso = "", menorPeso = "", maiorAltura = "", menorAltura = "", maiorIMC = "", menorIMC = "";
        double maxPeso = Double.MIN_VALUE, minPeso = Double.MAX_VALUE;
        double maxAltura = Double.MIN_VALUE, minAltura = Double.MAX_VALUE;
        double maxIMC = Double.MIN_VALUE, minIMC = Double.MAX_VALUE;

        String sql = "SELECT nome, peso, altura, tipoSanguineo, fatorRH, curso, imc FROM pessoa";

        if (conexao != null) {
            try (Statement stmt = conexao.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String tipoSanguineo = rs.getString("tipoSanguineo");
                    String fatorRH = rs.getString("fatorRH");
                    String curso = rs.getString("curso");

                    double peso = Double.parseDouble(rs.getString("peso"));
                    double altura = Double.parseDouble(rs.getString("altura"));
                    double imc = rs.getDouble("imc");

                    // Processa Peso
                    if (peso > maxPeso) {
                        maxPeso = peso;
                        maiorPeso = nome + " - " + tipoSanguineo + fatorRH;
                    }
                    if (peso < minPeso) {
                        minPeso = peso;
                        menorPeso = nome + " - " + tipoSanguineo + fatorRH;
                    }
                    pesos.add(peso);

                    // Processa Altura
                    if (altura > maxAltura) {
                        maxAltura = altura;
                        maiorAltura = nome + " - " + curso;
                    }
                    if (altura < minAltura) {
                        minAltura = altura;
                        menorAltura = nome + " - " + curso;
                    }
                    alturas.add(altura);

                    // Processa IMC
                    if (imc > maxIMC) {
                        maxIMC = imc;
                        maiorIMC = nome;
                    }
                    if (imc < minIMC) {
                        minIMC = imc;
                        menorIMC = nome;
                    }
                    imcs.add(imc);
                }
            }

            // Cálculo das médias
            double mediaPeso = pesos.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double mediaAltura = alturas.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double mediaIMC = imcs.stream().mapToDouble(Double::doubleValue).average().orElse(0);

            // Construção da String do Relatório
            String relatorio = " ****** Relatório de Pessoas ****** \n\n"
                    + " Maior Peso: " + maxPeso + "kg (" + maiorPeso + ")\n"
                    + " Menor Peso: " + minPeso + "kg (" + menorPeso + ")\n"
                    + " Média dos Pesos: " + String.format("%.2f", mediaPeso) + "kg\n\n"
                    + " Maior Altura: " + maxAltura + "m (" + maiorAltura + ")\n"
                    + " Menor Altura: " + minAltura + "m (" + menorAltura + ")\n"
                    + " Média das Alturas: " + String.format("%.2f", mediaAltura) + "m\n\n"
                    + " Média dos IMCs: " + String.format("%.2f", mediaIMC) + "\n"
                    + " Maior IMC: " + maxIMC + " (" + maiorIMC + ")\n"
                    + " Menor IMC: " + minIMC + " (" + menorIMC + ")\n\n\n";

            close();
            return relatorio; // Retorna a string do relatório
        }
        return "Erro: Conexão com o banco de dados não disponível.";
    }
}