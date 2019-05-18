package com.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * ESSA CLASSE TO FAZENDO PRA TESTAR UMA COISA SO...
 * 
 * Assim, minha intencao e verificar se e possivel ter um gerenciador
 * de layout no Swing que funcione de forma parecida com o proprio
 * com.main.JRelativeLayout, que e do Android.
 * 
 * Minha motivacao pra isso e basicamente o fato de que o com.main.JRelativeLayout
 * no Android e um gerenciador muito facil de se utilizar. Ou seja,
 * podemos podemos layouts bem complexos apenas por codigo, sem mesmo
 * utilizar a interface de construcao do AndroidStudio.
 * 
 * E fato que ja ha outros gerenciadores de layout no proprio Swing
 * que funcionam bem nesse ponto, como o GridBagLayout. Ha tambem
 * gerenciadores bem conhecidos, como o MigLayout, o qual e o mais pratico
 * que vi ate hoje.
 * 
 * Entretanto, um gerenciador com a funcionalidade do com.main.JRelativeLayout do
 * Android para Swing seria de grande ajuda para mim, visto que tenho
 * muita dificuldade em construir telas.
 * 
 * Considerando o fato de esta classe vir a ser funcional algum dia
 * tambem e possivel almejar a possibilidade de ela ser util para outras pessoas
 * que venham a ter as mesmas dificuldades que eu. Assim, este projeto
 * de classe ficara guardado virtualmente no GitHub.
 * 
 * Para efeitos de estudo, e importante saber que comecei o desenvolvimento
 * disso baseado no exemplo de gerenciador de layout customizado feito
 * pela propria Oracle. Nesse exemplo que eles fizeram, alem da explicacao
 * eles disponibilizaram a fonte do gerenciador, que no caso trata-se
 * de um "com.main.DiagonalLayout", que alinha todos os itens de forma diagonal.
 * 
 * Aqui tem o link para a pagina do exemplo que to falando:
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/custom.html
 * 
 * Diante disso, copiei alguns trechos que achei basicos para um gerenciador
 * e estou adicionado, entao, minhas proprias implementacoes baseando-me
 * na minha experiencia de uso do com.main.JRelativeLayout original do Android.
 * 
 * *------------------------------------------------------------------*
 * *------------------------------------------------------------------*
 * 
 * Eu planejo fazer forma de utilizacao desse gerenciador ser bem simples,
 * onde o usuario devera, basicamente, ao adicionar o componente X a um
 * conteiner, escrever todos os parametros que deseja, em um formato de String.
 * 
 * Por exemplo:
 * 
 * {@code conteiner.add(bt1, "alinharNoTopo=true alinharAbaixoDe=botao3")}
 * Obs.: possivelmente os parametros serao nomeados assim, mas ainda nao estao
 * definidos.
 * 
 * Alem disso, obrigatoriamente ANTES DE ADICONAR os componentes ao conteiner
 * o usuario devera definir a propriedade NOME de cada componente. Assim, um
 * trecho de codigo de utilizacao devera ser da seguinte forma:
 * 
 * {@code
 * JButton bt1 = new JButton();
 * bt1.setName("botao");
 * 
 * JButton bt3 = new JButton();
 * bt3.setName("botao3");
 * 
 * ...
 * 
 * conteiner.add(bt1, "alinharNoTopo=true alinharAbaixoDe=botao3");
 * }
 * 
 * TODO: implementar manipulação básica nos componentes em si
 */
public class JRelativeLayout implements LayoutManager {

    /**
     * Esse mapa serve pra guardar todos os comandos e
     * associa-los, de forma respectiva, aos nomes de todos
     * os componentes. Isso permitira que os comandos possam
     * ser acessados apenas utilizando o nome do componente.
     */
    private HashMap<String, ComponenteComando> mapa;

    public static final class Parametros {

        /*
         =============================================================
        \| AREA DE COMANDOS PARA MANIPULACAO DA POSICAO DO COMPONENTE ||
         =============================================================
        */

        /**
         * Os paramentros abaixo aceitam valores true e false.
         */
        public static final String CENTER_IN_PARENT = "centerInParent";//centroParent
        public static final String PARENT_TOP = "parentTop";//topoParent
        public static final String PARENT_BOTTOM = "parentBottom";//baseParent
        public static final String PARENT_START = "parentStart";//esquerdaParent
        public static final String PARENT_END = "parentEnd";//direitaParent

        public static final String CENTER_VERTICAL = "centerVertical";//centroVertical
        public static final String CENTER_HORIZONTAL = "centerHorizontal";//centroHorizontal

        /**
         * Os valores abaixo aceitam valores de nomes de componentes.
         */
        public static final String START = "start";//esquerda com esquerda
        public static final String END = "end";//direita com direita
        public static final String TOP = "top";//topo com topo
        public static final String BOTTOM = "bottom";//base com base

        public static final String ABOVE = "above";//base com topo
        public static final String BELLOW = "bellow";//topo com base
        public static final String END_OF = "endOf";//esquerda com direita
        public static final String LEFT_OF = "leftOf";//direita com esquerda

        /**
         * Os valores abaixo aceitam valores numericos.
         */
        public static final String MARGIN_TOP = "marginTop";
        public static final String MARGIN_BOTTOM = "marginBottom";
        public static final String MARGIN_END = "marginEnd";
        public static final String MARGIN_START = "marginStart";

        /**
         * Esses parametros aceitam tanto valores numericos
         * quanto valores "wrap" e "match"
         */
        public static final String WIDTH = "width";
        public static final String HEIGTH = "heigth";
    }

    public static final class Valores {
        public static final String TRUE = "true";
        public static final String FALSE = "false";

        public static final String WRAP_CONTENT = "wrapContent";
        public static final String MATCH_PARENT = "matchParent";
    }

    /**
     * Cria um com.main.JRelativeLayout padrao.
     * 
     * No momento n tem nenhuma outra forma de criar um
     * com.main.JRelativeLayout que nao seja essa. Tau.
     */
    public JRelativeLayout() {
        mapa = new HashMap<>();
    }

    /**
     * Esse metodo faz parte da interface LayoutManager e
     * aqui eu uso ele para obter as "constraints" de cada
     * componente.
     * 
     * Vale esclarecer que essas "constraints" serao tratadas
     * como os comandos que o usuario ira definir para esse
     * componente em questao. Vale lembrar, tambem, que esse
     * metodo e chamado sempre que um componente e adicionado
     * em um container.
     *
     * @param constraints os comandos definidos pelo usuario
     * @param comp        o componente associado a tais comandos
     */
    @Override
    public void addLayoutComponent(String constraints, Component comp) {
        if (comp.getName().contains(" ")){
            throw new IllegalArgumentException("O nome do componente + [" + comp.getName() + "] " +
                    "contém espaços em branco. Por favor, remova os espaços e tente novamente.");
        }

        if (!mapa.containsKey(comp.getName())) {
            mapa.put(comp.getName(), new ComponenteComando(constraints));
        }
    }

    //TODO: implementar remocao do comandoCompleto respectivo do {@code mapa}.
    @Override
    public void removeLayoutComponent(Component comp) {
        if (comp.getName().contains(" ")){
            throw new IllegalArgumentException("O nome do componente + [" + comp.getName() + "] " +
                    "contém espaços em branco. Por favor, remova os espaços e tente novamente.");
        }
        mapa.remove(comp.getName());
    }

    //Isso aqui eu so copiei, nem sei pra q serve.
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    //Isso aqui eu so copiei, nem sei pra q serve.
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getSize();
    }

    /**
     * Esse metodo aqui faz parte da interface LayoutManager e
     * e chamado sempre que os componentes sao desenhados no
     * conteirer.
     * 
     * A idea desse metodo e basicamente "movimentar"/"posicionar"
     * esses componentes DENTRO do conteiner os quais estao
     * adicionados.
     * 
     * Assim, fica a cargo deste metodo tratar todos os comandos
     * definidos pelo usuario, como tambem realizar tais movimentacoes
     * e posicionamentos.
     *
     * @param parent este e o conteiner onde os componentes estao inseridos.
     */
    @Override
    public void layoutContainer(Container parent) {
        //itera sobre todos os componentes adicionados no conteiner.
        for (int i = 0; i < parent.getComponentCount(); i++) {
            //para cada componente...
            Component compAtual = parent.getComponent(i);

            if (Objects.isNull(compAtual.getName()) ||
                    compAtual.getName() == null ||
                    compAtual.getName().isEmpty()) {
                try {
                    throw new IllegalAccessException("O componente [[" +
                            compAtual.toString() +
                            "]] NAO possui um NOME definido. Defina um nome para" +
                            " esse componente e tente novamente.");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            /**
             ...obtem-se seus respectivos comandos (constraints)
             definidos quando o componente em questão foi adicionado
             através do metodo {@code add(component, constraints),
            desde que tenha sido definido um nome para tal componente.

            Essa obtenção e possivel pois todos os comandos sao
            mapeados em relacao aos respectivos nomes de componentes
            durante a chamada do metodo {@code addLayoutComponent},
            subscrito nesta classe. Dessa forma.
             */
            ComponenteComando componenteComandoAtual = mapa.get(compAtual.getName());

            ArrayList<String> comandos = componenteComandoAtual.comandos();

            /**
             Itera-se sobre todos os comandos obtidos para o componente
             em questao nessa iteracao.

             Este loop itera de 2 em 2 itens, pois o formato esperado
             e:

             - [item0: parametro, item1: valor do parametro de item0, ...].
             */
            for (int j = 0; j < comandos.size() - 1; j += 2) {
                //obtem-se o parametro respecitivo ao comandoCompleto atual
                String parametro = comandos.get(j);
                //obtem-se o valor respectivo ao parametro atual
                String valor = comandos.get(j + 1);

                int
                        x = compAtual.getX(),
                        y = compAtual.getY(),
                        w = compAtual.getSize().width,
                        h = compAtual.getSize().height;

                /**
                 Estrutura de ifs aninhados permite a correta identificacao
                 dos comandos, bem como dos valores que podem ser atribuidos
                 a cada.

                 Este formato e funcional pois uma vez que todos os comandos,
                 bem como seus respctivos possiveis valores, sao instrucoes
                 pre-definidas, podemos subscreve-las aqui explicitamente,
                 ja que esperamos, certamente, que o usuario adicione uma
                 delas.
                 */
                if (parametro.equals(Parametros.CENTER_IN_PARENT)){
                    if (valor.equals(Valores.TRUE)){
                        int centroParentW = parent.getWidth() / 2;
                        int centroParentH = parent.getHeight() / 2;

                        x = centroParentW - (compAtual.getWidth() / 2);
                        y = centroParentH - (compAtual.getHeight() / 2);
                    }
                } else if (parametro.equals(Parametros.PARENT_TOP)){
                    if (valor.equals(Valores.TRUE)){
                        y = 0;
                    }
                } else if (parametro.equals(Parametros.PARENT_BOTTOM)){
                    if (valor.equals(Valores.TRUE)) {
                        y = parent.getHeight() - compAtual.getHeight();
                    }
                } else if (parametro.equals(Parametros.PARENT_START)){
                    if (valor.equals(Valores.TRUE)) {
                        x = 0;
                    }
                } else if (parametro.equals(Parametros.PARENT_END)){
                    if (valor.equals(Valores.TRUE)) {
                        x = parent.getWidth() - compAtual.getWidth();
                    }
                } else if(parametro.equals(Parametros.CENTER_VERTICAL)){
                    if (valor.equals(Valores.TRUE)){
                        y = (parent.getHeight() / 2) - (compAtual.getHeight() / 2);
                    }
                } else if (parametro.equals(Parametros.CENTER_HORIZONTAL)){
                    if (valor.equals(Valores.TRUE)){
                        x = (parent.getWidth() / 2) - (compAtual.getWidth() / 2);
                    }
                } else if (parametro.equals(Parametros.START)){
                    Component compRelacionado = getComponentByName(valor, parent);
                    x = compRelacionado.getX();
                } else if (parametro.equals(Parametros.TOP)){
                    Component compRelacionado = getComponentByName(valor, parent);
                    y = compRelacionado.getY();
                } else if(parametro.equals(Parametros.END)){
                    Component cmpRelacionado = getComponentByName(valor, parent);
                    x = cmpRelacionado.getX() + (Math.abs(compAtual.getWidth() - cmpRelacionado.getWidth()));
                } else if(parametro.equals(Parametros.BOTTOM)){
                    Component compRelacionado = getComponentByName(valor, parent);
                    y = (compRelacionado.getY() + compRelacionado.getHeight()) - compAtual.getHeight();
                } else if (parametro.equals(Parametros.ABOVE)){
                    Component compRelacionado = getComponentByName(valor, parent);
                    x = compRelacionado.getY() - compAtual.getHeight();
                } else if (parametro.equals(Parametros.BELLOW)){
                    Component compRelacionado = getComponentByName(valor, parent);
                    y = compRelacionado.getY() + compRelacionado.getHeight();
                } else if (parametro.equals(Parametros.END_OF)){
                    Component compRelacionado = getComponentByName(valor, parent);
                    x = compRelacionado.getX() + compRelacionado.getWidth();
                } else if (parametro.equals(Parametros.LEFT_OF)){
                    Component compRelacionado = getComponentByName(valor, parent);
                    x = compRelacionado.getX() - compAtual.getWidth();
                } else if (parametro.equals(Parametros.HEIGTH)) {
                    if (valor.equals(Valores.WRAP_CONTENT)) {
                        h = compAtual.getPreferredSize().height;
                    } else if (valor.equals(Valores.MATCH_PARENT)) {
                        h = parent.getHeight();
                    } else {
                        h = Integer.parseInt(valor);
                    }
                } else if (parametro.equals(Parametros.WIDTH)) {
                    if (valor.equals(Valores.WRAP_CONTENT)) {
                        w = compAtual.getPreferredSize().width;
                    } else if (valor.equals(Valores.MATCH_PARENT)) {
                        w = parent.getWidth();
                    } else {
                        w = Integer.parseInt(valor);
                    }
                } else if (parametro.equals(Parametros.MARGIN_TOP)) {
                    //TODO
                } else if (parametro.equals(Parametros.MARGIN_BOTTOM)){
                    //TODO
                } else if (parametro.equals(Parametros.MARGIN_END)){
                    //TODO
                } else if (parametro.equals(Parametros.MARGIN_START)){
                    //TODO
                }

                //TODO: incluir manipulação do componente

                compAtual.setBounds(x, y, w, h);
            }
        }
    }

    private Component getComponentByName(String name, Container parent){
        for (Component c : parent.getComponents()){
            if (c.getName().equals(name)){
                return c;
            }
        }

        return null;
    }

    /**
     * Esse metodo altera o comando do componente especificado fazendo com que, assim
     * o componente em questao seja reposicionado.
     *
     * Apos a alteracao do comando e feita uma chamada do metodo {@code layoutContainer()},
     * o que faz com que o container repassado no parametro seja totalmente atualizado.
     *
     * @param compName o nome do componente a ter seu comando alterado
     * @param comando o novo comando a ser definido
     * @param container o conteiner que contem o componente especificado
     */
    public void alterarComandoDeComponente(String compName, String comando, Container container){
        if (mapa.containsKey(compName)){
            mapa.get(compName).comandoCompleto = comando;
            this.layoutContainer(container);
        } else {
            throw new IllegalArgumentException("O comando não pode ser alterado pois o componente " +
                    "de nome [" + compName + "] não existe!");
        }
    }

    /**
     * Essa classe serve para auxiliar na obtencao dos comandos.
     */
    private class ComponenteComando {

        public String comandoCompleto;

        public ComponenteComando(String comando) {
            this.comandoCompleto = comando;
        }

        public ArrayList<String> comandos() {
            String[] comandos = comandoCompleto.split(" ");
            ArrayList<String> comandosSeparados = new ArrayList<>();

            for (String s : comandos) {
                String[] p = s.split("=");

                if (p.length != 2){
                    throw new IllegalArgumentException(
                            "Algum comando de algum componente nao foi escrito no formato " +
                                    "correto (comando=valor). Verifique e tente novamente.");
                } else {
                    comandosSeparados.add(s.split("=")[0]);
                    comandosSeparados.add(s.split("=")[1]);
                }
            }

            return comandosSeparados;
        }

        @Override
        public String toString() {
            return "comandoCompleto repassado: [" + comandoCompleto + "]";
        }
    }
}
