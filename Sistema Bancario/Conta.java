public abstract class Conta {
    private int numeroConta;
    private String nomeCorrentista;
    private double saldoConta;
    private String senhaConta;

    //Construtor da classe
    //Funções set da classe
    public Conta(String nome, int numeroDaConta, String senha) {
        nomeCorrentista = nome;
        numeroConta = numeroDaConta;
        senhaConta = senha;
        saldoConta = 0.0;
    }

    //Funções para obter parâmetros (GET)
    public int getAccountNumber() {
        return numeroConta;
    }
    public String getAccountName() {
        return nomeCorrentista;
    }
    public double getAccountBalance() {
        return saldoConta;
    }
    public String getAccountPassword() {
        return senhaConta;
    }

    //Métodos da classe
    public void deposito(double valor) {
        //return saldoConta + valor;
        saldoConta = saldoConta + valor;
    }

    public void alterarSenha(String novaSenha) {
        senhaConta =  novaSenha;
    }

    abstract void detalhesConta();
    abstract void saque(double valor);

}