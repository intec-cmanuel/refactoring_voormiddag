
/**
 * Write a description of class Bord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bord {
    public boolean hasToTake;
    public boolean turnEnded;
    public boolean selected;
    private boolean itsWhitesTurn;
    private Stuk[][] playField;
    private int amountOfBlocsInRow, amountOfBlocsInColumn;
    private int column, row;

    public Bord() {
        amountOfBlocsInRow = amountOfBlocsInColumn = 10;
        playField = new Stuk[amountOfBlocsInRow][amountOfBlocsInColumn];
        playField[1][0] = new Wit();
        playField[3][0] = new Wit();
        playField[3][0] = new Wit();
        playField[5][0] = new Wit();
        playField[7][0] = new Wit();
        playField[9][0] = new Wit();
        playField[0][1] = new Wit();
        playField[2][1] = new Wit();
        playField[4][1] = new Wit();
        playField[6][1] = new Wit();
        playField[8][1] = new Wit();
        playField[0][3] = new Wit();
        playField[2][3] = new Wit();
        playField[4][3] = new Wit();
        playField[6][3] = new Wit();
        playField[8][3] = new Wit();
        playField[1][2] = new Wit();
        playField[3][2] = new Wit();
        playField[3][2] = new Wit();
        playField[5][2] = new Wit();
        playField[7][2] = new Wit();
        playField[9][2] = new Wit();
        playField[0][9] = new Paars();
        playField[2][9] = new Paars();
        playField[4][9] = new Paars();
        playField[6][9] = new Paars();
        playField[8][9] = new Paars();
        playField[1][8] = new Paars();
        playField[3][8] = new Paars();
        playField[5][8] = new Paars();
        playField[7][8] = new Paars();
        playField[9][8] = new Paars();
        playField[1][6] = new Paars();
        playField[3][6] = new Paars();
        playField[5][6] = new Paars();
        playField[7][6] = new Paars();
        playField[9][6] = new Paars();
        playField[0][7] = new Paars();
        playField[2][7] = new Paars();
        playField[4][7] = new Paars();
        playField[6][7] = new Paars();
        playField[8][7] = new Paars();

    }

    public int getAmountOfBlocsInRow() {
        return amountOfBlocsInRow;
    }

    public int getAmountOfBlocsInColumn() {
        return amountOfBlocsInColumn;
    }

    public Stuk getInhoud(int row, int column) {
        return playField[row][column];
    }

    /**
     * Methode voor het starten van een activerings-
     * proces. De methode kijkt of de betsemming leeg is, en als dit niet zo is,
     * of het geaactiveerd kan worden.
     * een actief blokje is een blokje waar je een handeling mee kan uitvoeren.
     */
    public void activeren(int row, int column) {
        turnEnded = false;

        Stuk st = getInhoud(row, column);
        if (st == null) {
        } else if (st.activeerbaar()) {
            st.ActiveerHetNu();
            selected = true;

        }

    }

    /**
     * deze methode leert ons dat we op een bepaald moment moeten pakken.
     * <p>
     * Het zorgt ervoor dat enkel de objecten die een ander stuk kunnen slaan;
     * geselecteerd kunnen worden. po.stro() verwijst naar een methode die
     * het vakje dat moet pakken beschermd van de onbruikbaarheid.
     */

    public enum Direction {
        FIRSTLEFT(-1), SECONDLEFT(-2), FIRSTRIGHT(1), SECONDRIGHT(2);

        private final int direction;

        private Direction(int direction){
            this.direction = direction;
        }

        public int getDirection(){
            return direction;
        }
    }

    public void moetPakken() {
        // Even rows -> begin column aan 1
        // Odd rows -> begin column aan 0
        for (int row = 0; row < amountOfBlocsInRow; row ++){
            for (int column = (row + 1) % 2; column < amountOfBlocsInColumn; column += 2){
                Stuk pawn = getInhoud(row, column);
                boolean whitePawnPurpleTurn = pawn instanceof Wit && !itsWhitesTurn;
                boolean purplePawnWhiteTurn = pawn instanceof Paars && itsWhitesTurn;
                boolean pawnIsNull = pawn == null;

                if (pawnIsNull || whitePawnPurpleTurn || purplePawnWhiteTurn) {
                    continue;
                }

                Stuk firstPawnLeft = getAdjacentPawn(row, column, Direction.FIRSTLEFT);
                Stuk firstPawnRight = getAdjacentPawn(row, column, Direction.FIRSTRIGHT);




            }
        }















        for (int topRow = 0; topRow < 10; topRow++) {
            for (int topColumn = 0; topColumn < 10; topColumn++) {
                for (int bottomColumn = 0; bottomColumn < 10; bottomColumn++) {
                    for (int bottomRow = 0; bottomRow < 10; bottomRow++) {
                        Stuk topPawn = getInhoud(topRow, topColumn);
                        Stuk bottomPawn = getInhoud(bottomRow, bottomColumn);


                        if ((!itsWhitesTurn && topColumn == bottomColumn - 1 && bottomPawn instanceof Wit) || (!itsWhitesTurn && topColumn == bottomColumn + 1 && bottomPawn instanceof Wit)) {

                            if ((topRow == bottomRow + 1 && topPawn instanceof Paars) || (topRow == bottomRow - 1 && topPawn instanceof Paars)) {

                                if ((bottomRow + (topRow - bottomRow) * 2) < 10 && (bottomRow + (topRow - bottomRow) * 2) > -1 && (bottomColumn + (topColumn - bottomColumn) * 2) < 10 && (bottomColumn + (topColumn - bottomColumn) * 2) > -1) {
                                    Stuk vrij = getInhoud(bottomRow + (topRow - bottomRow) * 2, bottomColumn + (topColumn - bottomColumn) * 2);
                                    if (vrij == null) {
                                        if (bottomPawn != null) {
                                            bottomPawn.stro();

                                            hasToTake = true;
                                        }

                                    }
                                }
                            }
                        } else if ((itsWhitesTurn && topColumn == bottomColumn - 1 && bottomPawn instanceof Paars) || (itsWhitesTurn && topColumn == bottomColumn + 1 && bottomPawn instanceof Paars)) {

                            if ((topRow == bottomRow + 1 && topPawn instanceof Wit) || (topRow == bottomRow - 1 && topPawn instanceof Wit)) {

                                if ((bottomRow + (topRow - bottomRow) * 2) < 10 && (bottomRow + (topRow - bottomRow) * 2) > -1 && (bottomColumn + (topColumn - bottomColumn) * 2) < 10 && (bottomColumn + (topColumn - bottomColumn) * 2) > -1) {
                                    Stuk vrij = getInhoud(bottomRow + (topRow - bottomRow) * 2, bottomColumn + (topColumn - bottomColumn) * 2);
                                    if (vrij == null) {
                                        if (bottomPawn != null) {

                                            bottomPawn.stro();
                                            hasToTake = true;
                                        }
                                    }
                                }


                            }
                        }

                    }
                }
            }
        }
    }

    private Stuk getAdjacentPawn(int row, int column, Direction direction){
        int columnNextPawn = itsWhitesTurn ? column + Math.abs(direction.getDirection()) : column - Math.abs(direction.getDirection());
        int rowNextPawn = row + direction.getDirection();
        return getInhoud(rowNextPawn, columnNextPawn);
    }

    /**
     * deze methode wordt gestart om te kunnen bewegen naar een bepaalde locatie.
     * ze is opgedeeld in 2 grote gevallen:
     * -indien er een schijfje geslagen moet worden
     * -indien dit niet het geval is
     */


    public void bewegen(int x, int y) {
        selected = false;
        if (hasToTake) {

            pakken(x, y);

            moetPakken();
            Stuk st = getInhoud(x, y);

            if (hasToTake) {
                if (st == null) {
                } else {
                    st.ActiveerHetNu();
                    turnEnded = false;
                }
            } else {
                if (itsWhitesTurn) {
                    itsWhitesTurn = false;
                } else {
                    itsWhitesTurn = true;
                }
            }

        } else {
            doeDeZet(x, y);

        }
    }


    /**
     * de naam is vrij evident,
     * belangrijk hier was het disactiveren en de booleans klaar en witaanzet oppereren.
     * klaar is een essentiele boolean voor de controller.
     * wit aan zet betekend dat bij het wisselen van beurt paars aan zet wordt, en dat paarse
     * schijfjes dus kunnen bewegen.
     */
    public void doeDeZet(int x, int y) {
        Stuk st = playField[x][y];
        Stuk ac = getInhoud(column, row);
        if (y == row + 1 && x == column + 1 || y == row + 1 && x == column - 1) {
            if (st == null && ac instanceof Wit || st == null && ac.isDam) {

                if (ac.isDam) {
                    ac.Disactiveren();
                    playField[x][y] = ac;
                } else {
                    playField[x][y] = new Wit();
                }
                playField[column][row] = null;
                turnEnded = true;
                selected = false;
                if (ac.isDam && ac instanceof Paars) {
                    itsWhitesTurn = false;
                } else {
                    itsWhitesTurn = true;
                }
            }
        } else if (y == row - 1 && x == column + 1 || y == row - 1 && x == column - 1) {
            if (st == null && ac instanceof Paars || st == null && ac.isDam) {

                ac.Disactiveren();
                playField[x][y] = ac;
                playField[column][row] = null;
                turnEnded = true;
                selected = false;
                if (ac.isDam && ac instanceof Wit) {
                    itsWhitesTurn = true;
                } else {
                    itsWhitesTurn = false;
                }
            }
        }
    }


    //scanner
    public void scannen() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (st == null) {
                } else if (st.geKlikt() && st instanceof Wit) {
                    column = m;
                    row = n;
                } else if (st.geKlikt() && st instanceof Paars) {
                    column = m;
                    row = n;
                } else if (hasToTake) {
                    st.aanZet = false;
                }
            }
        }

    }

    //beurtwissel
    public void beurtwissel() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (st == null) {
                } else if (itsWhitesTurn) {

                    if (st instanceof Wit) {

                        st.aanZet = false;
                    } else {
                        st.aanZet = true;
                    }
                } else {
                    System.out.println("raheu");
                    if (st instanceof Wit) {
                        st.aanZet = true;
                    } else {
                        st.aanZet = false;
                    }

                }
            }
        }
        // pakken
    }

    public void pakken(int x, int y) {

        int u = (x + column) / 2;
        int h = (y + row) / 2;
        Stuk ac = getInhoud(column, row);
        Stuk bo = getInhoud((x + column) / 2, (y + row) / 2);
        Stuk ga = getInhoud(x, y);


        if (ac instanceof Wit && bo instanceof Paars || bo instanceof Wit && ac instanceof Paars) {
            if (ga == null) {
                if (x == 2 + column && y == 2 + row || x == 2 + column && y == row - 2 || x == column - 2 && y == 2 + row || x == column - 2 && y == row - 2) {

                    playField[(x - column) / 2 + column][(y - row) / 2 + row] = null;
                    playField[x][y] = ac;
                    playField[column][row] = null;
                    turnEnded = true;
                    Stuk st = getInhoud(x, y);
                    st.Disactiveren();
                    hasToTake = false;
                }
            }
        }
    }                                       //checkitout

    public void checkItOut() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (n == 9 && st instanceof Wit) {
                    st.isDam = true;
                } else if (n == 0 && st instanceof Paars) {
                    st.isDam = true;
                }
            }
        }
    }

}

