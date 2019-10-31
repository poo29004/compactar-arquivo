# Programação Orientada a Objetos - POO29004

> Engenharia de Telecomunicações
> 
> Instituto Federal de Santa Catarina - campus São José

## Compactar e descompactar arquivos ZIP

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

Feito isso, basta executar a tarefa `jar` com o gradle e isso pode ser feito usando o próprio IntelliJ ou usando diretamente o gradle no terminal. 

Com o IntelliJ basta seguir os passos:

1. Abra o projeto no IntelliJ
2. Abra o painel do Gradle (clique no botão gradle que fica no painel lateral à direita)
3. Abra o projeto (ex: `compactador`), abra o grupo `Tasks`-> `build`
4. Clique duas vezes na tarefa `jar`
5. Será criado o pacote `compactador-1.0.jar` dentro do subdiretório `build/libs`.

### Executando uma aplicação Java empacotada em um arquivo `.jar`

Para executar a aplicação java empacotada no arquivo `.jar` basta fazer:

```
java -jar compactador-1.0.jar
```



