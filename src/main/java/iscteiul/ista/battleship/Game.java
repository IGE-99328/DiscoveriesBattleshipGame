/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fba
 *
 */

/**
 * Representa o jogo da Batalha Naval.
 * 
 * A classe Game é responsável por gerir os tiros efetuados, validar os tiros,
 * contabilizar as estatísticas do jogo e interagir com a frota.
 * 
 * Implementa a interface IGame.
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;


    /**
     * Constrói um novo jogo com a frota indicada.
     * @param fleet frota de navios
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Efetua um tiro numa determinada posição.
     * 
     * O método verifica se o tiro é valido, verifica se é repetido, regista o tiro,
     * atualiza estatísticas e verifica se um navio foi atingido ou afundado.
     *
     * @param pos posição onde disparar
     * @return o navio afundado, caso exista; null caso contrário
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Devolve a lista de tiros válidos efetuados.
     *
     * @return lista de posições disparadas
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Devolve o número de tiros repetidos.
     *
     * @return número de tiros repetidos
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Devolve o número de tiros inválidos.
     *
     * @return número de tiros inválidos
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Devolve o número de tiros que atingiram navios.
     *
     * @return número de acertos
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Devolve o número de navios afundados.
     *
     * @return número de navios afundados
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /*
     * Devolve o número de navios ainda a flutuar.
     *
     * @return número de navios restantes
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }
    
    /**
     * Verifica se um tiro é válido (está dentro dos limites do tabuleiro).
     *
     * @param pos posição do tiro
     * @return true se for válido; false caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }
    
    /**
     * Verifica se um tiro já foi anteriormente efetuado.
     *
     * @param pos posição do tiro
     * @return true se já tiver sido disparado; false caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime o tabuleiro com um marcador específico
     * nas posições indicadas.
     *
     * @param positions lista de posições a marcar
     * @param marker carácter a utilizar como marcador
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }


    /**
     * Prints the board showing valid shots that have been fired
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }


    /**
     * Prints the board showing the fleet
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
