package iscteiul.ista.battleship;

import java.util.List;

/**
 * Define o comportamento de uma frota no jogo Batalha Naval.
 * <p>
 * Uma frota é composta por um conjunto de navios colocados num
 * tabuleiro de dimensão fixa. Esta interface especifica as operações
 * necessárias para gerir os navios e consultar o seu estado.
 */
public interface IFleet {

    /** Dimensão do tabuleiro (número de linhas/colunas). */
    Integer BOARD_SIZE = 10;

    /** Número máximo de navios permitidos numa frota. */
    Integer FLEET_SIZE = 10;

    /**
     * Devolve todos os navios da frota.
     *
     * @return lista de navios
     */
    List<IShip> getShips();

    /**
     * Tenta adicionar um navio à frota.
     * A implementação deverá garantir que o navio respeita as regras
     * do tabuleiro e não colide com outros navios.
     *
     * @param s navio a adicionar
     * @return {@code true} se foi adicionado com sucesso, {@code false} caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Obtém todos os navios pertencentes a uma determinada categoria.
     *
     * @param category categoria pretendida
     * @return lista de navios dessa categoria
     */
    List<IShip> getShipsLike(String category);

    /**
     * Obtém todos os navios que ainda não foram afundados.
     *
     * @return lista de navios ainda a flutuar
     */
    List<IShip> getFloatingShips();

    /**
     * Devolve o navio que ocupa uma determinada posição do tabuleiro.
     *
     * @param pos posição a verificar
     * @return navio nessa posição ou {@code null} se não existir
     */
    IShip shipAt(IPosition pos);

    /**
     * Mostra no ecrã o estado da frota.
     * A forma de apresentação fica ao critério da implementação.
     */
    void printStatus();
}