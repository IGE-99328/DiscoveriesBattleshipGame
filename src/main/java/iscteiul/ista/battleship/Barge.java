package iscteiul.ista.battleship;

/**
 * Classe que representa uma Barge no jogo Battleship.
 * <p>
 * A Barge é o navio mais pequeno do jogo, ocupando apenas uma posição
 * no tabuleiro (tamanho 1). A sua posição é definida pela coordenada
 * superior esquerda fornecida no momento da criação.
 * </p>
 *
 * <p>
 * Apesar de receber uma orientação (bearing), esta não influencia
 * o posicionamento do navio, pois a Barge ocupa apenas uma célula.
 * </p>
 */
public class Barge extends Ship {
    
     /** 
     * Tamanho fixo da Barge.
     * Como ocupa apenas uma célula, o tamanho é 1.
     */
    private static final Integer SIZE = 1;
    
    /** 
     * Nome identificador do navio.
     */
    private static final String NAME = "Barca";

    /**
     * Construtor da Barge.
     * <p>
     * Inicializa a barca com a orientação e posição indicadas.
     * Adiciona automaticamente a única posição ocupada pela barca
     * à lista de posições do navio.
     * </p>
     *
     * @param bearing orientação da barca (não afeta o posicionamento,
     *                pois o tamanho é 1)
     * @param pos     posição inicial (e única) da barca no tabuleiro
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Retorna o tamanho da Barge.
     *
     * @return o valor 1, correspondente ao número de posições ocupadas
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
