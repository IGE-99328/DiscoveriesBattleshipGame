package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define o contrato para um navio no jogo de Batalha Naval.
 * <p>
 * Qualquer classe que implemente {@code IShip} deve ser capaz de gerir as suas
 * coordenadas, a sua orientação ({@link Compass}), e o seu estado de integridade
 * face aos disparos recebidos.
 * </p>
 * *
 *
 * @author IGE-122479
 *
 * @version 1.0
 */
public interface  IShip {

    /**
     * Obtém a categoria ou nome do navio (ex: "Caravela", "Fragata").
     * * @return String com o nome da categoria.
     */
    String getCategory();

    /**
     * Obtém o tamanho (número de células) que o navio ocupa.
     * * @return O tamanho do navio como um {@link Integer}.
     */
    Integer getSize();

    /**
     * Retorna a lista de todas as posições geográficas ocupadas pelo navio no tabuleiro.
     * * @return Uma {@link List} contendo objetos {@link IPosition}.
     */
    List<IPosition> getPositions();

    /**
     * Obtém a posição de referência (âncora) a partir da qual o navio foi colocado.
     * * @return A {@link IPosition} inicial do navio.
     */
    IPosition getPosition();

    /**
     * Obtém a orientação cardinal para a qual o navio está apontado.
     * * @return O {@link Compass} (ponto cardeal) correspondente.
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda tem partes intactas (não atingidas).
     * * @return {@code true} se o navio ainda estiver a flutuar,
     * {@code false} se todas as suas posições foram atingidas (afundado).
     */
    boolean stillFloating();

    /**
     * Determina a linha mais a norte (valor mínimo de linha) ocupada pelo navio.
     * * @return Índice da linha superior.
     */
    int getTopMostPos();

    /**
     * Determina a linha mais a sul (valor máximo de linha) ocupada pelo navio.
     * * @return Índice da linha inferior.
     */
    int getBottomMostPos();

    /**
     * Determina a coluna mais a oeste (valor mínimo de coluna) ocupada pelo navio.
     * * @return Índice da coluna mais à esquerda.
     */
    int getLeftMostPos();

    /**
     * Determina a coluna mais a leste (valor máximo de coluna) ocupada pelo navio.
     * * @return Índice da coluna mais à direita.
     */
    int getRightMostPos();

    /**
     * Verifica se uma determinada posição no tabuleiro coincide com o espaço ocupado pelo navio.
     * * @param pos A posição a verificar.
     * @return {@code true} se o navio ocupar esta posição, {@code false} caso contrário.
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado próximo de outro navio,
     * violando as regras de distanciamento (geralmente não podem estar adjacentes).
     * * @param other O outro navio para comparação.
     * @return {@code true} se houver sobreposição ou proximidade excessiva.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se este navio está numa posição adjacente ou sobreposta a uma coordenada específica.
     * * @param pos A posição a validar.
     * @return {@code true} se a posição estiver "perto demais" das fronteiras do navio.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um disparo numa coordenada específica.
     * <p>
     * Se a posição coincidir com uma das partes do navio, este deve atualizar o seu
     * estado interno para refletir o dano.
     * </p>
     * * @param pos A coordenada onde o tiro foi desferido.
     */
    void shoot(IPosition pos);
}