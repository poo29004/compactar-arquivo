package poo;

/**
 * Classe utilitária para simular uma barra de progresso no terminal.
 */
public abstract class ProgressBar {

    private static final char progressChar = '#';

    /**
     * Desenha uma simples barra de progresso
     */
    public static void draw(){
        StringBuilder sb = new StringBuilder();
        int end = 100;
        for (int i = 0; i <= end; i++) {
            if (i%4 == 0){
                sb.append(progressChar);
            }
            try {
                System.out.print("\r"+i+"% "+sb+"|");
                Thread.sleep(75);
                System.out.print("\r"+i+"% "+sb+"/");
                Thread.sleep(75);
                System.out.print("\r"+i+"% "+sb+"-");
                Thread.sleep(75);
                System.out.print("\r"+i+"% "+sb+"\\");
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\r"+end+"% "+sb);
    }

}