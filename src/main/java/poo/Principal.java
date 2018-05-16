package poo;

public class Principal {
    public static void main(String[] args) {
        ProgressBar.limparTelaTerminalVT100();
        System.out.println("Início do exemplo de barra de progresso");

        ProgressBar.draw();
        ProgressBar.limparTelaTerminalVT100();
        System.out.println("Fim!");
    }
}
