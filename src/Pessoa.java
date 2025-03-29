public class Pessoa {
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private String tipoSanguineo;
    private String fatorRh;
    private String curso;
    private String ContatoDeEmergencia;
    private String textNumeroEmergencia;
    private String textAltura;
    private String txtPeso;
    private double imc = 0;
    private int id;

    public Pessoa(String nome, String endereco, String telefone, String cpf, String tipoSanguineo, String fatorRh,
            String curso, String ContatoDeEmergencia, String textNumeroEmergencia, String textAltura, String txtPeso) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipoSanguineo = tipoSanguineo;
        this.fatorRh = fatorRh;
        this.curso = curso;
        this.ContatoDeEmergencia = ContatoDeEmergencia;
        this.textNumeroEmergencia = textNumeroEmergencia;
        this.textAltura = textAltura;
        this.txtPeso = txtPeso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getFatorRh() {
        return fatorRh;
    }

    public void setFatorRh(String fatorRh) {
        this.fatorRh = fatorRh;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getContatoDeEmergencia() {
        return ContatoDeEmergencia;
    }

    public void setContatoDeEmergencia(String contatoDeEmergencia) {
        ContatoDeEmergencia = contatoDeEmergencia;
    }

    public String getTextNumeroEmergencia() {
        return textNumeroEmergencia;
    }

    public void setTextNumeroEmergencia(String textNumeroEmergencia) {
        this.textNumeroEmergencia = textNumeroEmergencia;
    }

    public String getTextAltura() {
        return textAltura;
    }

    public void setTextAltura(String textAltura) {
        this.textAltura = textAltura;
    }

    public String getTxtPeso() {
        return txtPeso;
    }

    public void setTxtPeso(String txtPeso) {
        this.txtPeso = txtPeso;
    }

    public int getId() {
        return id;
    }

    public Double calculoImc() {
        Double peso = Double.parseDouble(txtPeso);
        Double altura = Double.parseDouble(txtPeso); // Convertendo cm para m
        this.imc = peso / (altura * altura);
        return this.imc;
    }
}
