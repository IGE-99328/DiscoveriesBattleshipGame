/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição (célula) da grelha do jogo Batalha Naval.
 * 
 * Cada posição é identificada por uma linha e uma coluna.
 * Uma posição pode estar ocupada por parte de um navio
 * e pode ter sido atingida por um tiro durante o jogo.
 * 
 * Esta classe implementa a interface IPosition.
 */

public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Constrói uma nova posição com a linha e coluna indicadas.
     *
     * A posição inicialmente não esta ocupada nem foi atingida.
     *
     *@param row número da linha
     *@param column número da coluna
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Devolve o número da linha da posição
     *
     *@return número da linha
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Devolve o número da coluna da posição
     *
     *@return número da coluna
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gera o código hash da posição.
     * 
     * O hash é baseado apenas na linha e coluna,
     * garantindo consistência com o método equals().
     *
     * @return valor do código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Verifica se esta posição é igual a posição de outro objeto.
     * 
     * Duas posições são consideradas iguais se tiverem
     * a mesma linha e a mesma coluna.
     *
     * @param otherPosition objeto a comparar
     * @return true se as posições tiverem as mesmas coordenadas; false caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se esta posição é adjacente a outra.
     *
     * @param other posição a comparar
     * @return true se for adjacente; false caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca esta posição como ocupada por um navio.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }
     
    /**
     * Marca esta posição como atingida por um tiro.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Indica se a posição está ocupada.
     *
     * @return true se estiver ocupada; false caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Indica se a posição já foi atingida.
     *
     * @return true se já tiver sido atingida; false caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação textual da posição.
     *
     * @return texto com a linha e coluna da posição
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
