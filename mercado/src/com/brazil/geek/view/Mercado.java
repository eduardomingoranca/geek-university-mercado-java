package com.brazil.geek.view;

import com.brazil.geek.model.Produto;

import java.util.*;

import static com.brazil.geek.helper.Utils.doubleToString;
import static com.brazil.geek.helper.Utils.pausar;
import static java.lang.Integer.parseInt;
import static java.lang.System.exit;

public class Mercado {

    private static final Scanner teclado = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu() {
        System.out.println("==========================================");
        System.out.println("============== BEM-VINDO(A) ==============");
        System.out.println("============== GEEK SHOP =================");
        System.out.println("==========================================");
        System.out.println("====== 1 - CADASTRAR PRODUTO =============");
        System.out.println("====== 2 - LISTAR PRODUTOS ===============");
        System.out.println("====== 3 - COMPRAR PRODUTO ===============");
        System.out.println("====== 4 - VISUALIZAR CARRINHO ===========");
        System.out.println("====== 5 - SAIR DO SISTEMA ===============");
        System.out.println("==========================================");

        int opcao = 0;

        try {
            System.out.println("Informe uma opcao: ");
            opcao = parseInt(teclado.nextLine());
        } catch (InputMismatchException | NumberFormatException e) {
            menu();
        }

        switch (opcao) {
            case 1 -> cadastrarProduto();
            case 2 -> listarProduto();
            case 3 -> comprarProduto();
            case 4 -> visualizarCarrinho();
            case 5 -> {
                System.out.println("Volte sempre!");
                pausar(2);
                exit(0);
            }
            default -> {
                System.out.println("Opcao invalida!");
                pausar(2);
                menu();
            }
        }

    }

    private static void cadastrarProduto() {
        System.out.println("==========================================");
        System.out.println("========== CADASTRO DE PRODUTO ===========");
        System.out.println("==========================================");

        System.out.print("Informe o nome do produto: ");
        String nome = teclado.nextLine();

        System.out.print("Informe o preco do produto R$ ");
        Double preco = teclado.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println("O produto " + produto.getNome() + " foi cadastrado com sucesso.");
        pausar(2);
        menu();
    }

    private static void listarProduto() {
        if (produtos.size() > 0) {
            System.out.println("===========================================");
            System.out.println("========== LISTAGEM DE PRODUTOS ===========");
            System.out.println("===========================================");

            for (Produto p: produtos) {
                System.out.println(p);
                System.out.println();
            }
        } else System.out.println("Ainda nao existem produtos cadastrados!");

        pausar(2);
        menu();
    }

    private static void comprarProduto() {
        if (produtos.size() > 0) {
            System.out.println("===========================================");
            System.out.println("========== PRODUTOS DISPONIVEIS ===========");
            System.out.println("===========================================");

            for (Produto p: produtos) {
                System.out.println(p);
                System.out.println();
            }

            System.out.print("Informe o codigo do produto que desejar comprar: ");
            int codigo = parseInt(teclado.nextLine());
            boolean tem = false;

            for (Produto p: produtos) {
                if (p.getCodigo() == codigo) {
                    int quantidade = 0;
                    try {
                        quantidade = carrinho.get(p);
                        // ja tem este produto no carrinho, atualiza quantidade
                        carrinho.put(p, quantidade + 1);
                    } catch (NullPointerException e) {
                        // primeiro produto no carrinho
                        carrinho.put(p, 1);
                    }

                    System.out.println("O produto " + p.getNome() + " foi adicionado no carrinho.");
                    tem = true;
                }

                if (tem) {
                    System.out.println("Deseja adicionar outros produtos ao carrinho? ");
                    System.out.println("Informe 1 para sim ou 0 para nao: ");
                    int op = parseInt(teclado.nextLine());

                    if (op == 1) {
                        comprarProduto();
                    } else {
                        System.out.println("Por favor, aguarde enquanto fechamos seu pedido...");
                        pausar(2);
                        fecharPedido();
                    }
                } else {
                    System.out.println("Nao foi encontrado o produto com o codigo " + codigo);
                    pausar(2);
                    menu();
                }
            }
        } else {
            System.out.println("Ainda nao existem produto cadastrado no mercado!");
            pausar(2);
            menu();
        }
    }

    private static void visualizarCarrinho() {
        if (carrinho.size() > 0) {
            System.out.println("===========================================");
            System.out.println("========== PRODUTOS NO CARRINHO ===========");
            System.out.println("===========================================");

            for (Produto p: carrinho.keySet())
                System.out.println("Produto: " + p +
                        "\nQuantidade: " + carrinho.get(p));
        } else System.out.println("Ainda nao existem produtos no carrinho!");

        pausar(2);
        menu();
    }

    private static void fecharPedido() {
        System.out.println("===========================================");
        System.out.println("========== PRODUTOS NO CARRINHO ===========");
        System.out.println("===========================================");

        double valorTotal = 0.0;
        for (Produto p: carrinho.keySet()) {
            int quant = carrinho.get(p);
            valorTotal += p.getPreco() * quant;
            System.out.println(p);
            System.out.println("Quantidade: " + quant);
            System.out.println();
        }

        System.out.println("Sua fatura eh " + doubleToString(valorTotal));
        carrinho.clear();

        System.out.println("Obrigado pela preferencia.");
        pausar(5);
        menu();
    }

}
