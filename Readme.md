# Compactar e descompactar arquivos ZIP

Esse repositório apresenta um exemplo de como descompactar, compactar um arquivo ou um diretório de forma recursiva em Java. 

Nos testes de unidade é criado um conjunto de um arquivos binários e diretórios temporários no diretório `/tmp` da máquina. 

## Barra de progresso no console Unix

Nesse repositório também é apresentado um exemplo de como fazer uma barra de progresso no console, usando o `System.out`. Porém, a solução apresentada só funciona em terminais Unix. Ou seja, o código aqui apresentado não funcionará, por exemplo, quando se executa o programa dentro do IntelliJ ou mesmo no prompt do Windows

![progress bar](progress-bar.gif)

## Empacotando aplicação em um arquivo `.jar` executável

Uma aplicação java pode ser composta por diversos arquivos `.class` e esses poderiam ser colocados em um arquivo `.jar`, que trata-se de um arquivo compactado, semelhante a um arquivo `.zip`. 

O arquivo `.jar` pode ser gerado automaticamente pelo gradle. Para tal, deve-se adicionar um bloco de instruções no arquivo [build.grade](build.gradle) do projeto para informar o nome da classe que contém o método `main` que deverá ser executada.

```groovy
jar {
  manifest {
  attributes 'Main-Class': 'poo.Principal'
  }
}
```

Feito isso, execute a tarefa `jar` do gradle:

```bash
./gradlew jar
```
Será criado o arquivo `compactador.jar` dentro do subdiretório `build/libs`

### Executando uma aplicação Java empacotada em um arquivo `.jar`

Para executar a aplicação java empacotada no arquivo `.jar` basta fazer:

```
java -jar compactador.jar
```



