import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        //Scanner para leitra do teclado
        Scanner input = new Scanner(System.in);

        //Declaração das arrays de contas
        Conta[] contas = new Conta[30];
        
        //Variaveis para criação de novas contas
        String correntista;
        int numeroConta;
        String senha;
        double limite;
        double taxaDeJuros;
        boolean contaDuplicada = false;

        //Variaveis para métodos
        double deposito;
        String novaSenha;
        double saque;

        //Variaveis para menus
        int menuPrincipal = 0;
        int menuGerente = 0;
        int menuEscolherConta = 0;
        int menuCliente = 0;
        int menuLogin = 0;

        //Variaveis para auxiliar na contagem de contas
        int qtdeContas = 0;

        //Variaveis para busca e autenticação
        int buscaConta;
        String verificaSenha;
        boolean busca = false;
        int posicaoArr = 0;

        while(menuPrincipal != 3) {
            System.out.printf("1- Gerente\n");
            System.out.printf("2- Cliente\n");
            System.out.printf("3- Sair\n");
            menuPrincipal = input.nextInt();
            switch (menuPrincipal) {
                case 1:
                    //Menu Gerente
                    while(menuGerente != 3) {
                        System.out.printf("1- Criar conta\n");
                        System.out.printf("2- Visualizar informacoes de conta\n");
                        System.out.printf("3- Sair\n");
                        menuGerente = input.nextInt();
                        switch (menuGerente) {
                            case 1:
                                //Criar conta
                                System.out.printf("1- Conta Simples\n");
                                System.out.printf("2- Conta Especial\n");
                                System.out.printf("3- Poupanca\n");
                                System.out.printf("4- Sair\n");
                                menuEscolherConta = input.nextInt();
                                input.nextLine();
                                if(menuEscolherConta > 4 || menuEscolherConta < 1) {
                                    System.out.printf("Opção invalida. Por favor, escolha uma opcao valida\n");
                                } else {
                                    if(menuEscolherConta != 4) {
                                        //Leitura dos dados gerais de uma conta
                                        System.out.printf("Digite o nome do correntista: ");
                                        correntista = input.nextLine();
                                        System.out.printf("Digite o numero da conta: ");
                                        numeroConta = input.nextInt();
                                        input.nextLine();
                                        //verifica se o numero de conta ja foi usado
                                        for(int index = 0; index < qtdeContas; index++) {
                                            if(numeroConta == contas[index].getAccountNumber()) {
                                                contaDuplicada = true;
                                                break;
                                            }
                                        }

                                        if(contaDuplicada) {
                                            while(contaDuplicada) {
                                                System.out.printf("Esse numero de conta ja foi usado. Digite outro numero: ");
                                                numeroConta = input.nextInt();
                                                input.nextLine();
                                                for(int index = 0; index < qtdeContas; index++) {
                                                    if(numeroConta == contas[index].getAccountNumber()) {
                                                        contaDuplicada = true;
                                                        break;
                                                    } else {
                                                        contaDuplicada = false;
                                                    }
                                                }
                                            }
                                        }

                                        System.out.printf("Digite a senha da conta: ");
                                        senha = input.nextLine();
                                        //Leitura dos dados especificos e criação das contas
                                        switch (menuEscolherConta) {
                                            case 1:
                                                //Cria uma conta simples
                                                //contasSimples[qtdeSimples] = new ContaSimples(correntista, numeroConta, senha);
                                                //qtdeSimples++;
                                                contas[qtdeContas] = new ContaSimples(correntista, numeroConta, senha);
                                                qtdeContas++;
                                                break;
                                            case 2:
                                                //Leitura do limite
                                                System.out.printf("Digite o limite da conta: ");
                                                limite = input.nextDouble();
                                                //Cria uma conta especial
                                                //contasEspeciais[qtdeEspecial] = new ContaEspecial(correntista, numeroConta, senha, limite);
                                                //qtdeEspecial++;
                                                contas[qtdeContas] = new ContaEspecial(correntista, numeroConta, senha, limite);
                                                qtdeContas++;
                                                break;
                                            case 3:
                                                //Leitura da taxa de juros
                                                System.out.printf("Digite a taxa de juros:  ");
                                                taxaDeJuros = input.nextDouble();
                                                //Cria uma poupanca
                                                //poupancas[qtdePoupanca] = new Poupanca(correntista, numeroConta, senha, taxaDeJuros);
                                                //qtdePoupanca++;
                                                contas[qtdeContas] = new Poupanca(correntista, numeroConta, senha, taxaDeJuros);
                                                qtdeContas++;
                                                break;
                                            case 4:
                                                break;
                                            default:
                                                System.out.printf("This message shouldn't be displayed (Error 1)");
                                                break;
                                            }
                                            contaDuplicada = false;
                                        }
                                    }
                                break;
                            case 2:
                                //Visualizar informação de conta
                                System.out.printf("Digite o numero da conta: ");
                                buscaConta = input.nextInt();
                                //Busca em contas
                                for(int index = 0; index < qtdeContas; index++) {
                                    if(contas[index].getAccountNumber() == buscaConta) {
                                        contas[index].detalhesConta();
                                        busca = true;
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                System.out.printf("\n\nSaindo do menu de gerente\n\n");
                                break;
                            default:
                                System.out.printf("Opção invalida. Por favor, escolha uma opcao valida\n");
                                break;
                        }
                    }
                    menuGerente = 0;
                    break;
                case 2:
                    //Menu Cliente
                    while(menuCliente != 2) {
                        System.out.printf("1- Acessar conta\n");
                        System.out.printf("2- Retornar ao menu principal\n");
                        menuCliente = input.nextInt();
                        input.nextLine();
                        switch(menuCliente) {
                            case 1:
                                //acessar conta
                                System.out.printf("Digite o numero da conta: ");
                                buscaConta = input.nextInt();
                                input.nextLine();
                                //buscar a conta
                                for(int index = 0; index < qtdeContas; index++) {
                                    if(contas[index].getAccountNumber() == buscaConta) {
                                        posicaoArr = index;
                                        busca = true;
                                    }
                                    if(!busca) {
                                        System.out.printf("Esse numero de conta nao foi encontrado\n");
                                    } else {
                                        //verifica a senha
                                        System.out.printf("Digite sua senha: ");
                                        verificaSenha = input.nextLine();
                                        if(verificaSenha.equals(contas[posicaoArr].getAccountPassword())) {
                                            //acesso cedido
                                            System.out.printf("Bem vindo, " + contas[posicaoArr].getAccountName() + "\n");
                                            while (menuLogin != 5) {
                                                System.out.printf("1- Realizar saque\n");
                                                System.out.printf("2- Realizar deposito\n");
                                                System.out.printf("3- Visualizar informações da conta\n");
                                                System.out.printf("4- Alterar senha\n");
                                                System.out.printf("5- Finalizar sessao\n");
                                                menuLogin = input.nextInt();
                                                input.nextLine();

                                                switch(menuLogin) {
                                                    case 1:
                                                        //realizar saque
                                                        System.out.printf("Digite o valor a ser sacado: R$");
                                                        saque = input.nextDouble();
                                                        contas[posicaoArr].saque(saque);   
                                                        break;
                                                    case 2:
                                                        //realizar deposito
                                                        System.out.printf("Digite o valor a ser depositado: R$");
                                                        deposito = input.nextDouble();
                                                        contas[posicaoArr].deposito(deposito); 
                                                        break;
                                                    case 3:
                                                        //informacoes da conta
                                                        contas[posicaoArr].detalhesConta();
                                                        break;
                                                    case 4:
                                                        //alterar senha
                                                        System.out.printf("Digite uma nova senha: ");
                                                        novaSenha = input.nextLine();
                                                        contas[posicaoArr].alterarSenha(novaSenha);
                                                        break;
                                                    case 5:
                                                        //finalizar sessao
                                                        System.out.printf("Finalizar sessao...\n");
                                                        break;
                                                    default:
                                                        System.out.printf("Opção invalida\n");
                                                        break;
                                                }
                                            }
                                            menuLogin = 0;
                                        } else {
                                            System.out.printf("Senha incorreta\n");
                                        }
                                    }
                                }
                                break;
                            case 2:
                                //retornar ao menu principal
                                System.out.printf("Voltando ao menu principal\n");
                                break;
                            default:
                                System.out.printf("Opcao invalida. Por favor, escolha uma opção valida\n");
                                break;
                        }
                    }
                    menuCliente = 0;
                    break;
                case 3:
                //Finaliza o menu principal;    
                System.out.printf("Finalizando o programa...\n");
                    break;
            
                default:
                    //Opção invalida no menu principal
                    System.out.printf("Opção invalida. Por favor, escolha uma opcao valida\n");
                    break;
            }
        }
        input.close();
    }
}

//ERRORS
    // Error 1 - Acesso ao case default em switch menuEscolherConta
    // Error 2 - Erro no switch de busca de conta ao tentar logar no menu cliente