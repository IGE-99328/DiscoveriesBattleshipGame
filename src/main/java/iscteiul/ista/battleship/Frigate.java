package iscteiul.ista.battleship;

/**
 * Classe que representa uma Frigate no jogo Battleship.
 * <p>
 * A Fragata é um navio de tamanho 4, ocupando quatro posições
 * consecutivas no tabuleiro. A disposição das posições depende
 * da orientação (bearing) fornecida no momento da sua criação.
 * </p>
 *
 * <ul>
 *   <li>NORTH ou SOUTH → ocupa quatro posições na vertical</li>
 *   <li>EAST ou WEST → ocupa quatro posições na horizontal</li>
 * </ul>
 *
 * Caso a orientação seja inválida, é lançada uma exceção.
 */
public class Frigate extends Ship {

     /**
     * Tamanho fixo da Fragata.
     * Ocupa exatamente 4 posições no tabuleiro.
     */
    private static final Integer SIZE = 4;

    /**
     * Nome identificador do navio.
     */
    private static final String NAME = "Fragata";

    /**
     * Construtor da Fragata.
     * <p>
     * Inicializa a fragata com a orientação e posição inicial indicadas.
     * A partir da posição inicial, as restantes posições são calculadas
     * de acordo com o bearing:
     * </p>
     *
     * <ul>
     *   <li>Vertical (NORTH, SOUTH) → incrementa a linha</li>
     *   <li>Horizontal (EAST, WEST) → incrementa a coluna</li>
     * </ul>
     *
     * @param bearing orientação da fragata
     * @param pos     posição inicial (canto superior/esquerdo) da fragata
     *
     * @throws IllegalArgumentException se o bearing for inválido
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Retorna o tamanho da Fragata.
     *
     * @return o valor 4, correspondente ao número de posições ocupadas
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
