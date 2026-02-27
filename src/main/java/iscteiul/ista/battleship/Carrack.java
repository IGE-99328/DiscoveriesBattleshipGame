package iscteiul.ista.battleship;

/**
 * Representa uma embarcação do tipo Nau (Carrack) no jogo Batalha Naval.
 * <p>
 * A Nau ocupa sempre 3 posições consecutivas no tabuleiro e pode ser colocada
 * na vertical (NORTH/SOUTH) ou na horizontal (EAST/WEST).
 * As posições do navio são geradas a partir de uma posição inicial,
 * estendendo-se na direcção indicada.
 */
public class Carrack extends Ship {

    /** Tamanho fixo da Nau. */
    private static final Integer SIZE = 3;

    /** Nome do navio apresentado no jogo. */
    private static final String NAME = "Nau";

    /**
     * Constrói uma Nau com a orientação e posição inicial indicadas.
     *
     * @param bearing orientação do navio (direcção de colocação)
     * @param pos posição inicial do navio (célula mais acima ou mais à esquerda)
     * @throws IllegalArgumentException se a orientação for inválida
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);

        switch (bearing) {
            case NORTH:
            case SOUTH:
                // Colocação vertical: incrementa a linha
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;

            case EAST:
            case WEST:
                // Colocação horizontal: incrementa a coluna
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Devolve o tamanho da Nau.
     *
     * @return número de posições ocupadas pelo navio (sempre 3)
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}