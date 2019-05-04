
# A IDÉIA 
 
 ESSA CLASSE TO FAZENDO PRA TESTAR UMA COISA SO...
 
 Assim, minha intencao e verificar se e possivel ter um gerenciador
 de layout no Swing que funcione de forma parecida com o proprio
 RelativeLayout, que e do Android.
 
 Minha motivacao pra isso e basicamente o fato de que o RelativeLayout
 no Android e um gerenciador muito facil de se utilizar. Ou seja,
 podemos podemos layouts bem complexos apenas por codigo, sem mesmo
 utilizar a interface de construcao do AndroidStudio.
 
 E fato que ja ha outros gerenciadores de layout no proprio Swing
 que funcionam bem nesse ponto, como o GridBagLayout. Ha tambem
 gerenciadores bem conhecidos, como o MigLayout, o qual e o mais pratico
 que vi ate hoje.
 
 Entretanto, um gerenciador com a funcionalidade do RelativeLayout do
 Android para Swing seria de grande ajuda para mim, visto que tenho
 muita dificuldade em construir telas.
 
 Considerando o fato de esta classe vir a ser funcional algum dia
 tambem e possivel almejar a possibilidade de ela ser util para outras pessoas
 que venham a ter as mesmas dificuldades que eu. Assim, este projeto
 de classe ficara guardado virtualmente no GitHub.
 
 Para efeitos de estudo, e importante saber que comecei o desenvolvimento
 disso baseado no exemplo de gerenciador de layout customizado feito
 pela propria Oracle. Nesse exemplo que eles fizeram, alem da explicacao
 eles disponibilizaram a fonte do gerenciador, que no caso trata-se
 de um "DiagonalLayout", que alinha todos os itens de forma diagonal.
 
 Aqui tem o link para a pagina do exemplo que to falando:
 https://docs.oracle.com/javase/tutorial/uiswing/layout/custom.html
 
 Diante disso, copiei alguns trechos que achei basicos para um gerenciador
 e estou adicionado, entao, minhas proprias implementacoes baseando-me
 na minha experiencia de uso do RelativeLayout original do Android.
 
# PERSPECTIVA PARA A FORMA DE USAR.
 
 Eu planejo fazer forma de utilizacao desse gerenciador ser bem simples,
 onde o usuario devera, basicamente, ao adicionar o componente X a um
 conteiner, escrever todos os parametros que deseja, em um formato de String.
 
 Por exemplo:
 
`conteiner.add(bt1, "alinharNoTopo=true alinharAbaixoDe=botao3")`

Obs.: possivelmente os parametros serao nomeados assim, mas ainda nao estao
 definidos.
 
 Alem disso, obrigatoriamente ANTES DE ADICONAR os componentes ao conteiner
 o usuario devera definir a propriedade NOME de cada componente. Assim, um
 trecho de codigo de utilizacao devera ser da seguinte forma:
 
```
JButton bt1 = new JButton();
 bt1.setName("botao");
 
 JButton bt3 = new JButton();
 bt3.setName("botao3");
 
 ...
 
 conteiner.add(bt1, "alinharNoTopo=true alinharAbaixoDe=botao3");
```

 