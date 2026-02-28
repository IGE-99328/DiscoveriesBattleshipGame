package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a frota de um jogador no jogo Batalha Naval.
 * <p>
 * A frota é composta por um conjunto de navios e disponibiliza operações
 * para adicionar navios, consultar estados e obter subconjuntos da frota,
 * como navios ainda a flutuar ou de determinada categoria.
 */
public class Fleet implements IFleet {

    /**
     * Imprime no ecrã todos os navios de uma lista.
     *
     * @param ships lista de navios a imprimir
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** Lista de navios que compõem a frota. */
    private List<IShip> ships;

    /**
     * Cria uma frota vazia.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Devolve a lista de navios da frota.
     *
     * @return lista de navios
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um navio à frota, caso respeite as regras do jogo:
     * <ul>
     * <li>não exceder o tamanho máximo da frota</li>
     * <li>estar dentro dos limites do tabuleiro</li>
     * <li>não colidir nem ficar demasiado próximo de outros navios</li>
     * </ul>
     *
     * @param s navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Devolve todos os navios da frota pertencentes a uma determinada categoria.
     *
     * @param category categoria pretendida
     * @return lista de navios dessa categoria
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Devolve todos os navios que ainda não foram afundados.
     *
     * @return lista de navios ainda a flutuar
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Devolve o navio que ocupa uma determinada posição do tabuleiro.
     *
     * @param pos posição a verificar
     * @return navio nessa posição ou {@code null} se não existir
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se um navio está totalmente dentro dos limites do tabuleiro.
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1
                && s.getTopMostPos() >= 0 && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se existe risco de colisão ou proximidade excessiva
     * entre o navio dado e os navios já existentes na frota.
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Mostra no ecrã o estado geral da frota, incluindo:
     * <ul>
     * <li>todos os navios</li>
     * <li>navios ainda a flutuar</li>
     * <li>navios por categoria</li>
     * </ul>
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }
    /**
     * Imprime todos os navios de uma determinada categoria.
     *
     * @param category categoria pretendida
     */
    public void printShipsByCategory(String category) {
        assert category != null;
        printShips(getShipsLike(category));
    }
    /**
     * Imprime todos os navios da frota que ainda não foram atingidos totalmente.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime todos os navios da frota.
     */
    void printAllShips() {
        printShips(ships);
    }
}