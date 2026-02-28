/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa um navio do jogo Batalha Naval.
 * Um navio possui um tipo, uma orientação e uma posição inicial
 * Esta classe é abstrata e serve de base para os diferentes tipos
 * de navios concretos
 * Implementa a interface IShip.
 */

public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Método fábrica responsável por construir um navio
     * com base no seu tipo
     * @param shipKind tipo de navio
     * @param bearing orientação do navio
     * @param pos posição inicial
     * @return instância do navio correspondente ou null se inválido
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;


    /**
     * Constrói um navio com a categoria, orientação e posição inicial indicadas.
     * @param category categoria do navio
     * @param bearing orientação
     * @param pos posição inicial
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Devolve a categoria do navio
     * @return categoria
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Devolve a lista de posições ocupadas pelo navio.
     * @return lista de posições
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Devolve a posição inicial do navio.
     * @return posição inicial
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Devolve a orientação do navio.
     * @return orientação
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Indica se o navio ainda está a flutuar.
     * Um navio continua a flutuar enquanto existir pelo menos
     * uma posição que ainda não tenha sido atingida.
     * @return true se ainda estiver a flutuar; false se estiver afundado
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Devolve a linha mais superior ocupada pelo navio.
     * @return número da linha superior
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Devolve a linha mais inferior ocupada pelo navio.
     * @return número da linha inferior
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Devolve a coluna mais à esquerda ocupada pelo navio.
     * @return número da coluna mais à esquerda
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Devolve a coluna mais à direita ocupada pelo navio.
     * @return número da coluna mais à direita
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se o navio ocupa uma determinada posição.
     * @param pos posição a verificar
     * @return true se o navio ocupar essa posição; false caso contrário
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo de outro navio.
     * @param other outro navio
     * @return true se estiver demasiado próximo; false caso contrário
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se este navio está demasiado próximo de uma posição.
     * @param pos posição a verificar
     * @return true se estiver adjacente; false caso contrário
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * Regista um tiro numa determinada posição.
     * @param pos posição atingida
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Devolve uma representação textual do navio.
     * @return texto com categoria, orientação e posição inicial
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
