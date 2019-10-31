package poo;

import org.junit.Test;
import poo.ZipUnZip;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.Assert.assertTrue;

public class TesteDeUnidade {

    @Test
    public void comprimirUmArquivo(){
        String arquivoASerCompactado = "/tmp/aa.dat";
        String arquivoZIP = "/tmp/saida.zip";

        // criando arquivo de 100Mb
        if (criarArquivo(arquivoASerCompactado,1024 * 1024 * 100)){
            assertTrue("Não foi possível compactar arquivo",ZipUnZip.compressOneFile(arquivoASerCompactado,arquivoZIP));
        }
        removerArquivo(arquivoASerCompactado);
        removerArquivo(arquivoZIP);
    }

    /**
     * Hierarquia de diretórios e arquivos que será criada e adicionada no arquivo ZIP
     * /tmp/
     * `-- a
     *     |-- b
     *     |   `-- terceiro.dat
     *     |-- c
     *     |   `-- d
     *     |       `-- ultimo.dat
     *     |-- primeiro.dat
     *     `-- segundo.dat
     */
    @Test
    public void comprimirUmaHierarquiaDeDiretorios(){
        long tamanho = 1024 * 1024 * 100;
        criarDiretorio("/tmp/a/b");
        criarDiretorio("/tmp/a/c/d");
        String primeiro = "/tmp/a/primeiro.dat";
        String segundo = "/tmp/a/segundo.dat";
        String terceiro = "/tmp/a/b/terceiro.dat";
        String ultimo = "/tmp/a/c/d/ultimo.dat";
        criarArquivo(primeiro, tamanho);
        criarArquivo(segundo, tamanho);
        criarArquivo(terceiro, tamanho);
        criarArquivo(ultimo, tamanho);

        String arquivoZIP = "/tmp/saidaRecursivo.zip";
        assertTrue("Erro ao compactar diretórios e subdiretórios",ZipUnZip.compressDirectoryRecursively("/tmp/a", arquivoZIP));
        removerArquivo(arquivoZIP);
    }


    /**
     * Remover um arquivo do disco
     * @param nomeDoArquivo nome do arquivo a ser removido
     * @return true se sucesso, false caso contrário
     */
    private boolean removerArquivo(String nomeDoArquivo){
        File arquivo = new File(nomeDoArquivo);
        return arquivo.delete();
    }


    /**
     * Cria um arquivo regular com o tamanho informado
     * @param nomeDoArquivo nome do arquivo
     * @param tamanho tamanho a ser criado. I.e. 1024 * 1024 * 100 ~= 100Mb.
     * @return true se conseguir compactar
     */
    private boolean criarArquivo(String nomeDoArquivo, long tamanho){
        File f = new File(nomeDoArquivo);
        if (f.exists()){
            return false;
        }
        try {
            RandomAccessFile rf = new RandomAccessFile(nomeDoArquivo, "rw");
            rf.setLength(tamanho);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }
    }

    /**
     * Cria um diretório, que pode conter subdiretórios dentro
     * @param nomeDoDiretorio caminho do diretório a ser criado
     * @return true se o processo ocorreu com sucesso, false se o arquivo já existir no disco
     */
    private boolean criarDiretorio(String nomeDoDiretorio){
        return  (new File(nomeDoDiretorio)).mkdirs();
    }


}
