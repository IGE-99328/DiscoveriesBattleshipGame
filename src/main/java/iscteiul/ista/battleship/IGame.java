/**
 * Interface que define as operações básicas de um jogo de Battleship.
 * <p>
 * Fornece métodos para disparar contra posições do tabuleiro, consultar
 * estatísticas de tiros e estados dos navios, e imprimir informações
 * sobre os tiros válidos e a frota.
 * </p>
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IGame {

    /**
     * Dispara contra uma posição no tabuleiro.
     * <p>
     * Retorna o navio atingido, caso haja um, ou {@code null} se o tiro
     * não atingir nenhum navio.
     * </p>
     *
     * @param pos posição alvo do tiro
     * @return o navio atingido ou {@code null} se não houver impacto
     */
    IShip fire(IPosition pos);
    
    /**
     * Retorna a lista de todas as posições já alvo de tiros.
     *
     * @return lista de posições de tiros efetuados
     */
    List<IPosition> getShots();
    
    /**
     * Retorna o número de tiros repetidos, ou seja, disparos em posições
     * já atingidas anteriormente.
     *
     * @return número de tiros repetidos
     */
    int getRepeatedShots();
    
     /**
     * Retorna o número de tiros inválidos, como aqueles fora do tabuleiro.
     *
     * @return número de tiros inválidos
     */
    int getInvalidShots();

    int getHits();

    int getSunkShips();

    int getRemainingShips();

    void printValidShots();

    void printFleet();
}
