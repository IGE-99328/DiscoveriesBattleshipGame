/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo Galeão.
 * 
 * O Galeão é um navio com tamanho fixo de 5 posições.
 * A sua forma varia consoante a orientação (NORTH, SOUTH, EAST, WEST).
 * 
 * Esta classe estende a classe abstrata Ship.
 */

public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Constrói um Galeão com a orientação e posição inicial indicadas.
     * 
     * Consoante a orientação, as posições ocupadas
     * pelo navio são calculadas e adicionadas à lista de posições.
     *
     * @param bearing
     * @param pos
     * @throws NullPointerException se a orientação for null
     * @throws IllegalArgumentException se a orientação for inválida
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Devolve o tamanho do Galeão.
     *
     * @return número de posições ocupadas: 5
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a Norte.
     *
     * @param pos posição inicial
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a Sul.
     *
     * @param pos posição inicial
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a Este.
     *
     * @param pos posição inicial
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a Oeste.
     *
     * @param pos posição inicial
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
