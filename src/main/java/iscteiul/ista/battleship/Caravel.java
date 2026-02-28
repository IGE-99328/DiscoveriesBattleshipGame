package iscteiul.ista.battleship;

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
     * Construtor da Caravela.
     * <p>
     * Inicializa a caravela com a orientação e posição inicial indicadas.
     * A partir da posição inicial, as restantes posições são calculadas
     * de acordo com o bearing:
     * </p>
     *
     * <ul>
     *   <li>Vertical (NORTH, SOUTH) → incrementa a linha</li>
     *   <li>Horizontal (EAST, WEST) → incrementa a coluna</li>
     * </ul>
     *
     * @param bearing orientação da caravela
     * @param pos     posição inicial (canto superior/esquerdo) da caravela
     *
     * @throws NullPointerException     se o bearing for {@code null}
     * @throws IllegalArgumentException se o bearing for inválido
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

    /**
     * Retorna o tamanho da Caravela.
     *
     * @return o valor 2, correspondente ao número de posições ocupadas
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
