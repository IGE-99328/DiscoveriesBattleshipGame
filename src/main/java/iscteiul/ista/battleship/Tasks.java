
package iscteiul.ista.battleship;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe responsável pela execução e gestão das tarefas de jogo da Batalha Naval.
 * <p>
 * A classe {@code Tasks} contém a lógica de controlo para diferentes níveis de
 * complexidade do jogo (desde o teste de navios individuais até ao jogo completo).
 * Utiliza um sistema de comandos via {@link Scanner} e regista o progresso através
 * de um {@link Logger}.
 * </p>
 * * @author IGE-122479
 * //teste de conflito de merge realizado
 * @version 1.0
 */
public class Tasks {

    /**
     * Logger para registo de eventos e mensagens do jogo.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Número padrão de tiros permitidos numa rajada (round).
     */
    private static final int NUMBER_SHOTS = 3;

    /**
     * Mensagem exibida ao encerrar o programa.
     */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /**
     * Comandos de texto interpretados pelo sistema.
     */
    private static final String NOVAFROTA = "nova";
    private static final String DESISTIR = "desisto";
    private static final String RAJADA = "rajada";
    private static final String VERTIROS = "ver";
    private static final String BATOTA = "mapa";
    private static final String STATUS = "estado";

    /**
     * Executa a Tarefa A: Teste de posicionamento de navios individuais.
     * <p>
     * Lê um navio e verifica se determinadas coordenadas são ocupadas por esse navio.
     * </p>
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Executa a Tarefa B: Teste de construção de frotas e estado.
     * <p>
     * Permite criar uma frota e imprimir o estado básico dos navios nela contidos.
     * </p>
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executa a Tarefa C: Teste de frotas com funcionalidade de visualização total.
     * <p>
     * Semelhante à Tarefa B, mas adiciona o comando de "batota" para ver o mapa completo.
     * </p>
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executa a Tarefa D: Simulação completa de jogo.
     * <p>
     * Implementa o ciclo principal de jogo, permitindo disparar rajadas,
     * verificar o estado da frota e monitorizar tiros repetidos ou inválidos.
     * </p>
     *
     */
    public static void taskD() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Constrói uma frota completa baseada no input do utilizador.
     * <p>
     * O processo continua até que a frota atinja o tamanho definido em {@link Fleet#FLEET_SIZE}.
     * </p>
     *
     * @param in O {@link Scanner} para leitura dos dados dos navios.
     * @return A instância de {@link Fleet} devidamente preenchida.
     */
    static Fleet buildFleet(Scanner in) {
         assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê os dados necessários para criar um objeto do tipo {@link Ship}.
     *
     * @param in O {@link Scanner} para ler o tipo, posição e orientação.
     * @return Uma instância de uma subclasse de {@code Ship}, ou {@code null} se o tipo for inválido.
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê um par de coordenadas (linha e coluna) do input.
     *
     * @param in O {@link Scanner} de input.
     * @return Um objeto {@link Position} com as coordenadas lidas.
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Processa uma ronda de disparos (rajada) sobre a frota em jogo.
     * <p>
     * Lê as coordenadas para o número de tiros definido em {@link #NUMBER_SHOTS}
     * e executa o disparo no contexto do jogo.
     * </p>
     *
     * @param in   O {@link Scanner} para leitura das coordenadas dos tiros.
     * @param game A instância de {@link IGame} onde os tiros serão registados.
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }
    }
}
