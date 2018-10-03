public class ContaSimples extends Conta{
    //Parâmetros da classe
    private double saldo;
    
    //Construtor
    public ContaSimples(String correntista, int numeroDaConta, String senha) {
        super(correntista, numeroDaConta, senha);
        this.saldo = 0.00;
    }
    
    //Declaração das funções abstratas
    void detalhesConta() {
        System.out.printf("Correntista: " + getAccountName() + "\n");
        System.out.printf("Numero da Conta: " + getAccountNumber() + "\n");
        System.out.printf("Tipo de conta: Conta Simples\n");
        System.out.printf("Saldo: R$" + getAccountBalance() + "\n");
    }


    void saque(double valor) {
        if (valor <= getAccountBalance()) {
            saldo = getAccountBalance();
            saldo = saldo - valor;
            setSaldo(saldo);
        } else {
            System.out.printf("Nao ha saldo o suficiente");
        }
    }

    void increaseInterestRate(double valor) {
    }
}
