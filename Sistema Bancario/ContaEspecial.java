public class ContaEspecial extends Conta {
    //Parametros da classe
    private double limiteConta;
    private double saldo;
    
    //Construtor
    public ContaEspecial(String correntista, int numeroDaConta, String senha, double limite) {
        super(correntista, numeroDaConta, senha);
        this.saldo = 0.00;
        limiteConta = limite;
    }

    //Função GET p/ limite
    public double getAccountLimit() {
        return limiteConta;
    }

    //Declaração das funções abstratas
    void detalhesConta() {
        System.out.printf("Correntista: " + getAccountName() + "\n");
        System.out.printf("Numero da Conta: " + getAccountNumber() + "\n");
        System.out.printf("Tipo de conta: Conta Especial\n");
        System.out.printf("Saldo: R$" + getAccountBalance() + "\n");
        System.out.printf("Limite: R$" + getAccountLimit() + "\n");
    }

    void saque(double valor) {
        if((valor <= getAccountBalance()) || ((getAccountBalance() - valor) <= (-1 * getAccountLimit())))  {
            saldo = saldo - valor;
        } else {
            System.out.printf("Nao ha saldo o suficiente");
        }
    }
}