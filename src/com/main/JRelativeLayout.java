package com.main;

import java.awt.*;
import java.util.HashMap;

/**
 * ESSA CLASSE TO FAZENDO PRA TESTAR UMA COISA SO...
 * <p>
 * Assim, minha intencao e verificar se e possivel ter um gerenciador
 * de layout no Swing que funcione de forma parecida com o proprio
 * com.main.JRelativeLayout, que e do Android.
 * <p>
 * Minha motivacao pra isso e basicamente o fato de que o com.main.JRelativeLayout
 * no Android e um gerenciador muito facil de se utilizar. Ou seja,
 * podemos podemos layouts bem complexos apenas por codigo, sem mesmo
 * utilizar a interface de construcao do AndroidStudio.
 * <p>
 * E fato que ja ha outros gerenciadores de layout no proprio Swing
 * que funcionam bem nesse ponto, como o GridBagLayout. Ha tambem
 * gerenciadores bem conhecidos, como o MigLayout, o qual e o mais pratico
 * que vi ate hoje.
 * <p>
 * Entretanto, um gerenciador com a funcionalidade do com.main.JRelativeLayout do
 * Android para Swing seria de grande ajuda para mim, visto que tenho
 * muita dificuldade em construir telas.
 * <p>
 * Considerando o fato de esta classe vir a ser funcional algum dia
 * tambem e possivel almejar a possibilidade de ela ser util para outras pessoas
 * que venham a ter as mesmas dificuldades que eu. Assim, este projeto
 * de classe ficara guardado virtualmente no GitHub.
 * <p>
 * Para efeitos de estudo, e importante saber que comecei o desenvolvimento
 * disso baseado no exemplo de gerenciador de layout customizado feito
 * pela propria Oracle. Nesse exemplo que eles fizeram, alem da explicacao
 * eles disponibilizaram a fonte do gerenciador, que no caso trata-se
 * de um "com.main.DiagonalLayout", que alinha todos os itens de forma diagonal.
 * <p>
 * Aqui tem o link para a pagina do exemplo que to falando:
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/custom.html
 * <p>
 * Diante disso, copiei alguns trechos que achei basicos para um gerenciador
 * e estou adicionado, entao, minhas proprias implementacoes baseando-me
 * na minha experiencia de uso do com.main.JRelativeLayout original do Android.
 * <p>
 * *------------------------------------------------------------------*
 * *------------------------------------------------------------------*
 * <p>
 * Eu planejo fazer forma de utilizacao desse gerenciador ser bem simples,
 * onde o usuario devera, basicamente, ao adicionar o componente X a um
 * conteiner, escrever todos os parametros que deseja, em um formato de String.
 * <p>
 * Por exemplo:
 * <p>
 * {@code conteiner.add(bt1, "alinharNoTopo=true alinharAbaixoDe=botao3")}
 * Obs.: possivelmente os parametros serao nomeados assim, mas ainda nao estao
 * definidos.
 * <p>
 * Alem disso, obrigatoriamente ANTES DE ADICONAR os componentes ao conteiner
 * o usuario devera definir a propriedade NOME de cada componente. Assim, um
 * trecho de codigo de utilizacao devera ser da seguinte forma:
 * <p>
 * {@code
 * JButton bt1 = new JButton();
 * bt1.setName("botao");
 * <p>
 * JButton bt3 = new JButton();
 * bt3.setName("botao3");
 * <p>
 * ...
 * <p>
 * conteiner.add(bt1, "alinharNoTopo=true alinharAbaixoDe=botao3");
 * }
 * <p>
 * TODO: implementar manipulação básica nos componentes em si
 */
public class JRelativeLayout implements LayoutManager2 {

    private final HashMap<Component, JRelativeLayoutConstraints> compTable;

    public JRelativeLayout() {
        compTable = new HashMap<>();
    }

    @Override
    public void layoutContainer(Container parent) {
        Component[] components = parent.getComponents();

        for (Component c : components) {
            JRelativeLayoutConstraints constraints = lookupConstraints(c);
            int x = c.getX(), y = c.getY(), width = c.getPreferredSize().width, height = c.getPreferredSize().height;

            //updates booleans constraints
            if (constraints.centerHorizontal) x = (parent.getWidth() / 2) - (width / 2);
            if (constraints.centerVertical) y = (parent.getHeight() / 2) - (height / 2);
            if (constraints.parentTop) y = 0;
            if (constraints.parentBottom) y = parent.getHeight() - height;
            if (constraints.parentStart) x = 0;
            if (constraints.parentEnd) x = parent.getWidth() - width;

            Component aux;
            //updates relativeComp constraints
            aux = constraints.start;
            if (aux != null) x = aux.getX();

            aux = constraints.end;
            if (aux != null) x = aux.getX() + (Math.abs(width - aux.getWidth()));

            aux = constraints.top;
            if (aux != null) y = aux.getY();

            aux = constraints.bottom;
            if (aux != null) y = (aux.getY() + aux.getHeight()) - height;

            aux = constraints.above;
            if (aux != null) y = aux.getY() - height;

            aux = constraints.bellow;
            if (aux != null) y = aux.getY() + aux.getHeight();

            aux = constraints.endOf;
            if (aux != null) x = aux.getX() + aux.getWidth();

            aux = constraints.leftOf;
            if (aux != null) x = aux.getX() - width;

            if (constraints.marginTop >= 0) y += constraints.marginTop;
            if (constraints.marginBottom >= 0) y -= constraints.marginBottom;
            if (constraints.marginStart >= 0) x += constraints.marginStart;
            if (constraints.marginEnd >= 0) x -= constraints.marginEnd;

            c.setBounds(x, y, width, height);
        }
    }

    public void setConstraints(Component comp, JRelativeLayoutConstraints constraints) {
        compTable.put(comp, constraints);
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof JRelativeLayoutConstraints) {
            comp.setSize(comp.getPreferredSize());
            setConstraints(comp, (JRelativeLayoutConstraints) constraints);
        }
    }

    public JRelativeLayoutConstraints lookupConstraints(Component comp) {
        return compTable.get(comp);
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        compTable.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {

    }
}
