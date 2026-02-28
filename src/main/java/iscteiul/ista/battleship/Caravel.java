/**
 * Classe que representa uma Caravel (Caravela) no jogo Battleship.
 * <p>
 * A Caravela é um navio de tamanho 2, ocupando duas posições consecutivas
 * no tabuleiro. A sua disposição depende da orientação (bearing) fornecida
 * no momento da criação.
 * </p>
 *
 * <ul>
 *   <li>NORTH ou SOUTH → ocupa duas posições na vertical</li>
 *   <li>EAST ou WEST → ocupa duas posições na horizontal</li>
 * </ul>
 *
 * Caso a orientação seja inválida ou nula, é lançada uma exceção.
 */
package iscteiul.ista.battleship;

public class Caravel extends Ship {

    /**
     * Tamanho fixo da Caravela.
     * Ocupa exatamente 2 posições no tabuleiro.
     */
    private static final Integer SIZE = 2;

    /**
     * Nome identificador do navio.
     */
    private static final String NAME = "Caravela";

    /**
     * @param bearing the bearing where the Caravel heads to
     * @param pos     initial point for positioning the Caravel
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
