# Battleship

Basic academic version of Battleship game to build upon.

Contributors:
- António Moura - 122622
- Gonçalo Batista - 122623
- Afonso Lopes - 122631
- Rafael Silva - 122638

Code Smells encontrados através das funções de Teste:
- Dead Code encontrado na classe ShipTest.java, na função getTopMostPos(), já que alteração de posição não servia para nada, já que a posição dada é sempre uma das mais acima. 
- Dead Code encontrado na classe ShipTest.java, na função getLeftMostPos(), já que alteração de posição não servia para nada, já que a posição dada é a mais à esquerda, excepto na construção dos galeões. 