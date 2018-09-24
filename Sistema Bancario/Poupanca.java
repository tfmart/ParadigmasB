public class Poupanca extends Conta {
   //Parametros da classe
    private double taxaJuros;
    private double saldo;

    //Construtor
    public Poupanca(String correntista, int numeroDaConta, String senha, double juros) {
        super(correntista, numeroDaConta, senha);
        this.saldo = 0.00;
        taxaJuros = juros;
    }

    //Função GET p/ taxa de juros
    public double getAccountInterestRate() {
        return taxaJuros;
    }

    //Declaração das funções abstratas
    void detalhesConta() {
        System.out.printf("Correntista: " + getAccountName() + "\n");
        System.out.printf("Numero da Conta: " + getAccountNumber() + "\n");
        System.out.printf("Tipo de conta: Poupanca\n");
        System.out.printf("Saldo: R$" + getAccountBalance() + "\n");
        System.out.printf("Taxa Juros: R$" + getAccountInterestRate() + "\n");
    }

    void saque(double valor) {
        if(valor <= getAccountBalance())  {
            saldo = saldo - valor;
        } else {
            System.out.printf("Nao ha saldo o suficiente");
        }
    }
}