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
 * com.main.RelativeLayout, que e do Android.
 * 
 * Minha motivacao pra isso e basicamente o fato de que o com.main.RelativeLayout
 * no Android e um gerenciador muito facil de se utilizar. Ou seja,
 * podemos podemos layouts bem complexos apenas por codigo, sem mesmo
 * utilizar a interface de construcao do AndroidStudio.
 * 
 * E fato que ja ha outros gerenciadores de layout no proprio Swing
 * que funcionam bem nesse ponto, como o GridBagLayout. Ha tambem
 * gerenciadores bem conhecidos, como o MigLayout, o qual e o mais pratico
 * que vi ate hoje.
 * 
 * Entretanto, um gerenciador com a funcionalidade do com.main.RelativeLayout do
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
 * na minha experiencia de uso do com.main.RelativeLayout original do Android.
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
 * TODO: criar enumeracao publica e estatica aqui para guardar todos os parametros e valores
 */
@SuppressWarnings("ALL")
public class RelativeLayout implements LayoutManager {

    /**
     * Esse mapa serve pra guardar todos os comandos e
     * associa-los, de forma respectiva, aos nomes de todos
     * os componentes. Isso permitira que os comandos possam
     * ser acessados apenas utilizando o nome do componente.
     */
    private HashMap<String, ComponenteComando> mapa;

    //Isso aqui eu so copiei, nem sei pra q serve.
    private int vgap;
    private int minWidth = 0, minHeight = 0;
    private int preferredWidth = 0, preferredHeight = 0;
    private boolean sizeUnknown = true;

    public static final class Parametros {
        public static final String ABAIXO_DE = "abaixoDe";
        public static final String CENTRO_PARENT = "centroParent";
    }

    public static final class Valores {
        public static final String TRUE = "true";
        public static final String FALSE = "false";
    }

    /**
     * Cria um com.main.RelativeLayout padrao.
     * 
     * No momento n tem nenhuma outra forma de criar um
     * com.main.RelativeLayout que nao seja essa. Tau.
     */
    public RelativeLayout() {
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
        if (!mapa.containsKey(comp.getName())) {
            mapa.put(comp.getName(), new ComponenteComando(constraints));
        }
    }

    //TODO: implementar remocao do comandoCompleto respectivo do {@code mapa}.
    @Override
    public void removeLayoutComponent(Component comp) {

    }

    //Isso aqui eu so copiei, nem sei pra q serve.
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        setSizes(parent);

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = preferredWidth + insets.left + insets.right;
        dim.height = preferredHeight + insets.top + insets.bottom;

        sizeUnknown = false;

        //return dim;
        return parent.getSize();
    }

    //Isso aqui eu so copiei, nem sei pra q serve.
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = minWidth + insets.left + insets.right;
        dim.height = minHeight + insets.top + insets.bottom;

        sizeUnknown = false;

        //return dim;
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
        if (sizeUnknown){
            setSizes(parent);
        }

        double parentWidth = parent.getSize().width;
        double parentHeight = parent.getSize().height;
        Insets insets = parent.getInsets();
        int centerX = (int) (parentWidth - (insets.left + insets.right)) / 2;
        int centerY = (int) (parentHeight - (insets.top + insets.bottom)) / 2;

        //itera sobre todos os componentes adicionados no conteiner.
        for (int i = 0; i < parent.getComponentCount(); i++) {
            //para cada componente...
            Component componenteAtual = parent.getComponent(i);

            if (Objects.isNull(componenteAtual.getName()) ||
                    componenteAtual.getName() == null ||
                    componenteAtual.getName().isEmpty()) {
                try {
                    throw new IllegalAccessException("O componente [[" +
                            componenteAtual.toString() +
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
            ComponenteComando componenteComandoAtual = mapa.get(componenteAtual.getName());

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
                String paramentro = comandos.get(j);
                //obtem-se o valor respectivo ao parametro atual
                String valor = comandos.get(j + 1);

                /**
                 Estrutura de switchs aninhados permite a correta identificacao
                 dos comandos, bem como dos valores que podem ser atribuidos
                 a cada.

                 Este formato e funcional pois uma vez que todos os comandos,
                 bem como seus respctivos possiveis valores, sao instrucoes
                 pre-definidas, podemos subscreve-las aqui explicitamente,
                 ja que esperamos, certamente, que o usuario adicione uma
                 delas.
                 */
                //verifica-se qual parametro foi definido pelo usuário
                switch (paramentro) {
                    case Parametros.CENTRO_PARENT:
                        int compPosX = 0;
                        int compPosY = 0;

                        if (valor.equals(Valores.TRUE)){
                            //calcular a posicao do centro aqui
                            int centroParentW = parent.getWidth() / 2;
                            int centroParentH = parent.getHeight() / 2;

                            compPosX = centroParentW - (componenteAtual.getWidth() / 2);
                            compPosY = centroParentH - (componenteAtual.getHeight() / 2);
                        }

                        componenteAtual.setBounds(
                                compPosX,
                                compPosY,
                                componenteAtual.getPreferredSize().width,
                                componenteAtual.getPreferredSize().height
                        );
                        //

                        break;
                }
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

    //Isso aqui eu so copiei, nem sei pra q serve.
    private void setSizes(Container parent) {
        int nComps = parent.getComponentCount();
        Dimension d = null;

        //Reset preferred/minimum width and height.
        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;

        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                d = c.getPreferredSize();

                if (i > 0) {
                    preferredWidth += d.width/2;
                    preferredHeight += vgap;
                } else {
                    preferredWidth = d.width;
                }
                preferredHeight += d.height;

                minWidth = Math.max(c.getMinimumSize().width,
                        minWidth);
                minHeight = preferredHeight;
            }
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
