/**
 * Interface que define o motor de jogo (Game Engine) da Batalha Naval.
 * <p>
 * O {@code IGame} é responsável por processar os disparos, validar a legalidade
 * das jogadas e manter as estatísticas de combate (tiros repetidos, falhados,
 * navios afundados, etc.).
 * </p>
 *
 *
 * @author IGE-122479
 * @version 1.0
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IGame {

    /**
     * Executa um disparo numa coordenada específica do tabuleiro.
     * <p>
     * O método deve verificar se a posição é válida, se já foi atingida e,
     * em caso de acerto, identificar qual o navio atingido.
     * </p>
     *
     * @param pos A coordenada {@link IPosition} onde o tiro é desferido.
     * @return O objeto {@link IShip} que foi atingido, ou {@code null} se o tiro for na água.
     */
    IShip fire(IPosition pos);

    /**
     * Retorna a lista de todas as coordenadas onde já foram efectuados disparos.
     * * @return Uma {@link List} com todas as {@link IPosition} visadas.
     */
    List<IPosition> getShots();

    /**
     * Obtém o contador de tiros efectuados em coordenadas que já tinham sido
     * alvo de disparos anteriormente.
     * * @return O número de tiros repetidos.
     */
    int getRepeatedShots();

    /**
     * Obtém o contador de tiros efectuados fora dos limites do tabuleiro
     * (ex: coordenadas negativas ou superiores ao tamanho da grelha).
     * * @return O número de tiros inválidos.
     */
    int getInvalidShots();

    /**
     * Obtém o número total de tiros que atingiram com sucesso um navio.
     * * @return O número de acertos (hits).
     */
    int getHits();

    /**
     * Obtém o número de navios da frota adversária que já foram totalmente destruídos.
     * * @return O número de navios afundados.
     */
    int getSunkShips();

    /**
     * Obtém o número de navios que ainda possuem pelo menos uma parte intacta.
     * * @return O número de navios restantes na frota.
     */
    int getRemainingShips();

    /**
     * Apresenta na consola (ou log) a lista de todos os tiros válidos efectuados
     * até ao momento, permitindo ao jogador acompanhar o histórico.
     */
    void printValidShots();

    /**
     * Exibe o estado atual de todos os navios da frota.
     * <p>
     * Geralmente utilizado para depuração (debug) ou quando o jogador
     * usa o comando de "batota" (mapa).
     * </p>
     */
    void printFleet();
}