//TO-DO
    //1. Fix password bug when logging out of account

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

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
        double addTaxa;
        double juros;

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
                    while(menuGerente != 6) {
                        System.out.printf("1- Criar conta\n");
                        System.out.printf("2- Visualizar informacoes de conta\n");
                        System.out.printf("3- Incrementar rendimentos\n");
                        System.out.printf("4- Cobranca de juros\n");
                        System.out.printf("5- Imprimir informacoes de todas as contas\n");
                        System.out.printf("6- Sair\n");
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
                                        try {
                                            numeroConta = input.nextInt();
                                        }
                                        catch(InputMismatchException e) {
                                            input.nextLine();
                                            System.out.printf("Numero invalido\nDigite um numero de conta valido: ");
                                            numeroConta = input.nextInt();
                                        }
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
                                                contas[qtdeContas] = new ContaSimples(correntista, numeroConta, senha);
                                                qtdeContas++;
                                                break;
                                            case 2:
                                                //Leitura do limite
                                                System.out.printf("Digite o limite da conta: ");
                                                limite = input.nextDouble();
                                                //Cria uma conta especial
                                                contas[qtdeContas] = new ContaEspecial(correntista, numeroConta, senha, limite);
                                                qtdeContas++;
                                                break;
                                            case 3:
                                                //Leitura da taxa de juros
                                                System.out.printf("Digite a taxa de juros:  ");
                                                taxaDeJuros = input.nextDouble();
                                                //Cria uma poupanca
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
                                try {
                                    buscaConta = input.nextInt();
                                }
                                catch(InputMismatchException e) {
                                    input.nextLine();
                                    System.out.printf("Valor invalido\nDigite um numero de conta valido: ");
                                    buscaConta = input.nextInt();
                                }
                                //Busca em contas
                                for(int index = 0; index < qtdeContas; index++) {
                                    if(contas[index].getAccountNumber() == buscaConta) {
                                        contas[index].detalhesConta();
                                        busca = true;
                                        break;
                                    }
                                }
                                if(!busca) {
                                    System.out.printf("Conta inexsitente\n");
                                }
                                break;
                            case 3:
                                //Incrementar redimentos
                                System.out.printf("O quanto você quer incrementar o rendimento? ");
                                //CRIAR EXCECAO PARA LEITURA DE addTaxa
                                try {
                                    addTaxa = input.nextDouble();
                                }
                                catch (InputMismatchException e) {
                                    input.nextLine();
                                    System.out.printf("Numero invalido. Digite um valor valido: ");
                                    addTaxa = input.nextDouble();
                                }
                                for(int index = 0; index < qtdeContas; index++) {
                                    if(contas[index] instanceof Poupanca) {
                                        contas[index].increaseInterestRate(addTaxa);
                                    }
                                }
                                break;
                            case 4:
                                //Realizar cobrança de juros
                                System.out.printf("O quanto de juros você deseja cobrar? ");
                                //CRIAR EXCECAO PARA LEITURA TAXA DE JUROS
                                try {
                                    juros = input.nextDouble();
                                }
                                catch (InputMismatchException e) {
                                    input.nextLine();
                                    System.out.printf("Valor invalido. Digite um numero valido: ");
                                    juros = input.nextDouble();
                                }
                                for(int index = 0; index < 30; index++) {
                                    if(contas[index] instanceof ContaEspecial && contas[index].getAccountBalance() > 0) {
                                        double calculaSaldo = contas[index].getAccountBalance() + (contas[index].getAccountBalance() * juros);
                                        contas[index].setSaldo(calculaSaldo);
                                    }
                                }
                                break;
                            case 5:
                                //Imprimir informações de todas as contas
                                for(int index = 0; index < 30; index++) {
                                    if(contas[index] != null) {
                                        System.out.print("==================================\n");
                                        contas[index].detalhesConta();
                                        System.out.print("==================================\n\n");
                                    }
                                }
                                break;
                            case 6:
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
                                //EXCECAO PARA LEITURA DE NUMERO DE CONTA
                                try {
                                    buscaConta = input.nextInt();
                                }
                                catch (InputMismatchException e) {
                                    input.nextLine();
                                    System.out.printf("Numero invalido. Digite um valor valido: ");
                                    buscaConta = input.nextInt();
                                }
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
                                                        //EXCECAO PARA VALOR DE SAQUE
                                                        try {
                                                            saque = input.nextDouble();
                                                        }
                                                        catch (InputMismatchException e) {
                                                            input.nextLine();
                                                            System.out.printf("Valor invalido. Por favor, digite um valor valido: R$ ");
                                                            saque = input.nextDouble();
                                                        }
                                                        contas[posicaoArr].saque(saque);   
                                                        break;
                                                    case 2:
                                                        //realizar deposito
                                                        System.out.printf("Digite o valor a ser depositado: R$");
                                                        //EXCECAO PARA VALOR DE DEPOSITO
                                                        try {
                                                            deposito = input.nextDouble();
                                                        }
                                                        catch (InputMismatchException e) {
                                                            input.nextLine();
                                                            System.out.printf("Valor invalido. Por favor, digite um valor valido: R$ ");
                                                            deposito = input.nextDouble();
                                                        }
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
