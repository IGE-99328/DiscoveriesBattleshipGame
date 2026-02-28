/**
 * Interface que define o contrato para uma posição (célula) no tabuleiro de Batalha Naval.
 * <p>
 * Uma posição é definida por um par de coordenadas (linha e coluna) e mantém o rasto
 * do seu estado: se contém um navio e se já foi alvo de um disparo.
 * </p>
 *
 * @author IGE-122479
 *
 * @version 1.0
 */
package iscteiul.ista.battleship;

public interface IPosition {

    /**
     * Obtém o índice da linha desta posição.
     * * @return O número da linha (geralmente de 0 a 9).
     */
    int getRow();

    /**
     * Obtém o índice da coluna desta posição.
     * * @return O número da coluna (geralmente de 0 a 9).
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto para verificar a igualdade.
     * <p>
     * Duas posições são consideradas iguais se tiverem a mesma linha e a mesma coluna.
     * </p>
     * * @param other O objeto a comparar.
     * @return {@code true} se as coordenadas forem idênticas.
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente a outra (incluindo diagonais).
     * <p>
     * É crucial para validar as regras de proximidade entre navios.
     * </p>
     *
     *
     * @param other A outra posição a verificar.
     * @return {@code true} se a distância máxima entre as coordenadas for igual a 1.
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Altera o estado da célula para "ocupada" por um navio.
     */
    void occupy();

    /**
     * Regista que esta célula foi alvo de um disparo.
     * <p>
     * Este método deve ser chamado independentemente de haver um navio na posição ou não.
     * </p>
     */
    void shoot();

    /**
     * Verifica se existe um navio nesta coordenada.
     * * @return {@code true} se a posição estiver ocupada por uma parte de um navio.
     */
    boolean isOccupied();

    /**
     * Verifica se esta coordenada já foi disparada.
     * * @return {@code true} se o método {@link #shoot()} já tiver sido invocado para esta célula.
     */
    boolean isHit();
}