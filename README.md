# Gerenciamento de _layout_ de forma relativa

Este é um projeto de experimento pessoal que aborda a criação de um gerenciador de _layout_ relativo.

Ao declarar o gerenciamento como algo "relativo" dá-se entender que os componentes irão ser posicionados um em relação a outro (ou em alguns casos, posicionados em relação ao contêiner pai). Por exemplo, um componente ```A``` pode ser posicionado embaixo de um componente ```B``` com a instrução ```add(A, rawConstraints().below(B)``` e assim por diante.

É importante notar também que é necessário que ao menos 1 componente tenha seu posicionamento definido de forma absoluta. Por exemplo, na demonstração acima o componente ```A``` foi posicionado abaixo do componente ```B``` porém o componente ```B``` não teve seu posicionado declarado, o que poderia gerar uma ambiguidade como _onde o componente ```A``` deve ser de fato posicionado?_. De qualquer forma, este projeto visa resolver esse problema inicial com o fato de que todos os componentes têm, por padrão, seus posicionamentos definidos nas coordenadas ```[0, 0]``` do contêiner pai.

A intenção desse experimento é ter em mãos uma estrutura de código viável para programação de interfaces visuais de complexidade média, permitindo que o programador possa definir tais interfaces diretamente através de código manual, proporcionando uma escrita relativamente intuitiva e independente de ferramentas de construção de _layouts_.

Além disso, também tem-se como objetivo implementar a mesma ideia em plataformas distintas, podendo ir do _swing_ ao _HTML_, este último com auxílio da linguagem _Javascript_.

O alvo prático disso, neste ponto de desenvolvimento, é implementar em layouts simples de rascunhos ou protótipos.