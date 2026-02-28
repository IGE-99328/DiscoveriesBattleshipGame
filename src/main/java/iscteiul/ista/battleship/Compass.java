package iscteiul.ista.battleship;

/**
 * Enumeração que representa as direções possíveis de orientação
 * de um navio no tabuleiro do jogo Batalha Naval.
 * <p>
 * Cada direção está associada a um carácter:
 * <ul>
 *   <li>n – Norte</li>
 *   <li>s – Sul</li>
 *   <li>e – Este</li>
 *   <li>o – Oeste</li>
 *   <li>u – Desconhecido</li>
 * </ul>
 */
public enum Compass {
    /** Direção Norte. */
    NORTH('n'),

    /** Direção Sul. */
    SOUTH('s'),

    /** Direção Este. */
    EAST('e'),

    /** Direção Oeste. */
    WEST('o'),

    /** Direção desconhecida ou inválida. */
    UNKNOWN('u');

    /** Carácter associado à direção. */
    private final char c;

    /**
     * Constrói uma direção com o carácter associado.
     *
     * @param c carácter representativo da direção
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Devolve o carácter que representa a direção.
     *
     * @return carácter da direção
     */
    public char getDirection() {
        return c;
    }
    /**
     * Devolve a representação textual da direção
     * (o próprio carácter associado).
     *
     * @return string com o carácter da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um carácter numa direção {@code Compass}.
     *
     * @param ch carácter a converter
     * @return direção correspondente ou {@code UNKNOWN} se inválida
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }
        return bearing;
    }
}