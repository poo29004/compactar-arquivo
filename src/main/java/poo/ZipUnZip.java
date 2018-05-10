package poo;


import java.io.*;
import java.util.zip.*;

/**
 * Essa classe utilitária foi construída com base nos exemplos disponibilizados pela documentação oficial
 * da Oracle sobre Zip e UnZip usando a API Java IO.
 * <p>
 * http://www.oracle.com/technetwork/articles/java/compress-1565076.html
 */
public abstract class ZipUnZip {
    public static final int BUFFER = 2048;


    /**
     * Adiciona arquivos em um ZIP que está sendo criado. Se o arquivo passado for um diretório, então
     * o método é chamado de forma recursiva.
     *
     * @param zos        arquivo ZIP que estará sendo criado
     * @param fileZip    arquivo que se deseja colocar no ZIP
     * @param parentPath caminho do diretório onde o arquivo se encontra
     */
    private static void addFilesRecursively(ZipOutputStream zos, File fileZip, String parentPath) throws IOException {

        String entryFullPath = parentPath + "/" + fileZip.getName();

        if (fileZip.isDirectory()) {
            for (File arquivo : fileZip.listFiles()) {
                addFilesRecursively(zos, arquivo, entryFullPath);
            }
        } else {
            byte data[] = new byte[BUFFER];
            FileInputStream fi = new FileInputStream(fileZip);
            BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);
            zos.putNextEntry(new ZipEntry(entryFullPath));
            int count;
            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                zos.write(data, 0, count);
            }
            zos.closeEntry();
            origin.close();
            fi.close();
        }
    }

    /**
     * Cria um arquivo ZIP contendo todos os arquivos e diretórios que estão contidos no diretório informado
     * como parâmetro.
     *
     * @param diretorio caminho do diretório que contém os arquivos que deverão ser colocados no ZIP
     * @return true se o processo ocorreu com sucesso, false se acontecer algum problema.
     */
    public static boolean compressDirectoryRecursively(String diretorio, String arquivoZip) {
        try {
            FileOutputStream dest = new FileOutputStream(arquivoZip);
            CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));

            addFilesRecursively(out, new File(diretorio), "");

            out.flush();
            dest.flush();
            out.close();
            dest.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Comprime um único arquivo regular. Se o arquivo informado for um diretório, então o processo
     * é encerrado e retorna false.
     *
     * @param arquivo caminho do arquivo que deverá ser comprido
     * @return true se o processo ocorreu com sucesso, false se acontecer algum problema.
     */
    public static boolean compressOneFile(String arquivo, String arquivoZip) {
        File saida = new File(arquivoZip);
        if (saida.exists()){
            return false;
        }
        try {
            byte data[] = new byte[BUFFER];
            File f = new File(arquivo);
            if (f.isDirectory()) {
                return false;
            }

            BufferedInputStream origin = null;
            FileOutputStream dest = null;
            dest = new FileOutputStream(saida);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            FileInputStream fi = new FileInputStream(f);
            origin = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(arquivo);
            out.putNextEntry(entry);
            int count;
            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            origin.close();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Descompacta o arquivo ZIP informado.
     *
     * @param arquivoZIP caminho completo do arquivo ZIP a ser descompactado.
     * @return true se o processo ocorreu com sucesso, false se acontecer algum problema.
     */
    public static boolean uncompress(String arquivoZIP) {
        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(arquivoZIP);
            CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Extracting: " + entry);
                int count;
                byte data[] = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(entry.getName());
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}