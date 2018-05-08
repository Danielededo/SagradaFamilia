package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Cards.PublicCard.*;
import it.polimi.ingsw.Cards.SchemeCard.AuroraeMagnificus;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import org.junit.jupiter.api.Test;

public class PublicObject_Test {
        @Test
        void testa_different_rowColors() {
            GlassWindow vetrata = new GlassWindow();
            Slot s = new Slot(0, Colour.WHITE);
            Slot t = new Slot(0, Colour.WHITE);
            Slot u = new Slot(0, Colour.WHITE);
            Slot v = new Slot(0, Colour.WHITE);
            Slot z = new Slot(0, Colour.WHITE);
            Slot x = new Slot(0, Colour.WHITE);
            Die a = new Die();
            Die b = new Die();
            Die c = new Die();
            Die d = new Die();
            Die e = new Die();
            a.randomdado();
            a.setDicecolor(Colour.RED);
            b.randomdado();
            b.setDicecolor(Colour.BLUE);
            c.randomdado();
            c.setDicecolor(Colour.YELLOW);
            d.randomdado();
            d.setDicecolor(Colour.GREEN);
            e.randomdado();
            e.setDicecolor(Colour.PURPLE);
            it.polimi.ingsw.Cards.PublicObject card = new DifferentRowColors();
            s.setDie(a);
            t.setDie(b);
            u.setDie(c);
            v.setDie(d);
            z.setDie(e);
            x.setDie(b);
            for (int i = 1; i < 3; i++) {
                vetrata.setSlot(s, i, 0);
                vetrata.setSlot(t, i, 1);
                vetrata.setSlot(u, i, 2);
                vetrata.setSlot(v, i, 3);
                vetrata.setSlot(z, i, 4);
            }
            vetrata.setSlot(s, 3, 0);
            vetrata.setSlot(t, 3, 1);
            vetrata.setSlot(x, 3, 2);
            vetrata.setSlot(v, 3, 3);
            vetrata.setSlot(z, 3, 4);

            vetrata.setSlot(s, 0, 0);
            vetrata.setSlot(t, 0, 1);
            vetrata.setSlot(s, 0, 2);
            vetrata.setSlot(z, 0, 3);
            vetrata.setSlot(t, 0, 4);
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 4; j++) {
                    if (vetrata.getSlot(i, j).isOccupate()) {
                        System.out.print(vetrata.getSlot(i, j).isOccupate() +
                                "-" + vetrata.getSlot(i, j).getDice().getDicecolor() + "   ");
                    } else System.out.print(vetrata.getSlot(i, j).isOccupate() + " ");
                }
                System.out.println();
            }
            System.out.println("punteggio: " + card.calcola_punteggio(vetrata));
        }

        @Test
        void testa_different_columnColors() {
            GlassWindow vetrata = new GlassWindow();
            Slot s = new Slot(0, Colour.WHITE);
            Slot t = new Slot(0, Colour.WHITE);
            Slot u = new Slot(0, Colour.WHITE);
            Slot v = new Slot(0, Colour.WHITE);
            Slot z = new Slot(0, Colour.WHITE);
            Slot x = new Slot(0, Colour.WHITE);
            Die a = new Die();
            Die b = new Die();
            Die c = new Die();
            Die d = new Die();
            Die e = new Die();
            a.randomdado();
            a.setDicecolor(Colour.RED);
            b.randomdado();
            b.setDicecolor(Colour.BLUE);
            c.randomdado();
            c.setDicecolor(Colour.YELLOW);
            d.randomdado();
            d.setDicecolor(Colour.GREEN);
            e.randomdado();
            e.setDicecolor(Colour.PURPLE);
            it.polimi.ingsw.Cards.PublicObject card = new DifferentColumnsColors();
            s.setDie(a);
            t.setDie(b);
            u.setDie(c);
            v.setDie(d);
            z.setDie(e);
            x.setDie(b);
            for (int i = 1; i < 4; i++) {
                vetrata.setSlot(s, 0, i);
                vetrata.setSlot(t, 1, i);
                vetrata.setSlot(u, 2, i);
                vetrata.setSlot(v, 3, i);
            }
            vetrata.setSlot(s, 0, 0);
            vetrata.setSlot(t, 1, 0);
            vetrata.setSlot(x, 2, 0);
            vetrata.setSlot(v, 3, 0);

            vetrata.setSlot(s, 0, 4);
            vetrata.setSlot(t, 1, 4);
            vetrata.setSlot(s, 2, 4);
            vetrata.setSlot(z, 3, 4);
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 4; j++) {
                    if (vetrata.getSlot(i, j).isOccupate()) {
                        System.out.print(vetrata.getSlot(i, j).isOccupate() +
                                "-" + vetrata.getSlot(i, j).getDice().getDicecolor() + "   ");
                    } else System.out.print(vetrata.getSlot(i, j).isOccupate() + " ");
                }
                System.out.println();
            }
            System.out.println("punteggio: " + card.calcola_punteggio(vetrata));
        }

        @Test
        void testadifferent_rowShades() {
            it.polimi.ingsw.Cards.PublicObject card = new DifferentRowShades();
            GlassWindow vetrata = new GlassWindow();
            Slot s = new Slot(0, Colour.WHITE);
            Slot t = new Slot(0, Colour.WHITE);
            Slot u = new Slot(0, Colour.WHITE);
            Slot v = new Slot(0, Colour.WHITE);
            Slot z = new Slot(0, Colour.WHITE);
            Slot x = new Slot(0, Colour.WHITE);
            Die a = new Die(1, Colour.RED);
            Die b = new Die(2, Colour.PURPLE);
            Die c = new Die(3, Colour.YELLOW);
            Die d = new Die(4, Colour.GREEN);
            Die e = new Die(5, Colour.BLUE);
            Die f = new Die(6, Colour.RED);
            s.setDie(a);
            t.setDie(b);
            u.setDie(c);
            v.setDie(d);
            z.setDie(e);
            x.setDie(f);
            for (int i = 1; i < 3; i++) {
                vetrata.setSlot(s, i, 0);
                vetrata.setSlot(t, i, 1);
                vetrata.setSlot(u, i, 2);
                vetrata.setSlot(v, i, 3);
                vetrata.setSlot(z, i, 4);
            }
            vetrata.setSlot(x, 0, 0);
            vetrata.setSlot(v, 0, 1);
            vetrata.setSlot(t, 0, 2);
            vetrata.setSlot(z, 0, 3);
            vetrata.setSlot(u, 0, 4);

            vetrata.setSlot(x, 3, 0);
            vetrata.setSlot(s, 3, 1);
            vetrata.setSlot(x, 3, 2);
            vetrata.setSlot(v, 3, 3);
            vetrata.setSlot(t, 3, 4);
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 4; j++) {
                    if (vetrata.getSlot(i, j).isOccupate()) {
                        System.out.print(vetrata.getSlot(i, j).isOccupate() +
                                "-" + vetrata.getSlot(i, j).getDice().getFace() + "   ");
                    } else System.out.print(vetrata.getSlot(i, j).isOccupate() + " ");
                }
                System.out.println();
            }
            System.out.println(card.calcola_punteggio(vetrata));
        }

        @Test
        void testadifferent_columnShades() {
            it.polimi.ingsw.Cards.PublicObject card = new DifferentColumnShades();
            GlassWindow vetrata = new GlassWindow();
            Slot s = new Slot(0, Colour.WHITE);
            Slot t = new Slot(0, Colour.WHITE);
            Slot u = new Slot(0, Colour.WHITE);
            Slot v = new Slot(0, Colour.WHITE);
            Slot z = new Slot(0, Colour.WHITE);
            Slot x = new Slot(0, Colour.WHITE);
            Die a = new Die(1, Colour.RED);
            Die b = new Die(2, Colour.PURPLE);
            Die c = new Die(3, Colour.YELLOW);
            Die d = new Die(4, Colour.GREEN);
            Die e = new Die(5, Colour.BLUE);
            Die f = new Die(6, Colour.RED);
            s.setDie(a);
            t.setDie(b);
            u.setDie(c);
            v.setDie(d);
            z.setDie(e);
            x.setDie(f);
            for (int i = 1; i < 4; i++) {
                vetrata.setSlot(s, 0, i);
                vetrata.setSlot(t, 1, i);
                vetrata.setSlot(u, 2, i);
                vetrata.setSlot(v, 3, i);
            }
            vetrata.setSlot(s, 0, 0);
            vetrata.setSlot(t, 1, 0);
            vetrata.setSlot(x, 2, 0);
            vetrata.setSlot(v, 3, 0);

            vetrata.setSlot(s, 0, 4);
            vetrata.setSlot(t, 1, 4);
            vetrata.setSlot(s, 2, 4);
            vetrata.setSlot(z, 3, 4);
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 4; j++) {
                    if (vetrata.getSlot(i, j).isOccupate()) {
                        System.out.print(vetrata.getSlot(i, j).isOccupate() +
                                "-" + vetrata.getSlot(i, j).getDice().getFace() + "   ");
                    } else System.out.print(vetrata.getSlot(i, j).isOccupate() + " ");
                }
                System.out.println();
            }
            System.out.println(card.calcola_punteggio(vetrata));
        }

        @Test
        void test_clear_shades() {
            it.polimi.ingsw.Cards.PublicObject card = new DifferentShades();
            GlassWindow window = new AuroraeMagnificus();
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 4; j++) {
                    Die a = new Die();
                    a.randomdado();
                    System.out.print(a.getFace() + "     ");
                    a.setDicecolor(Colour.BLUE);
                    window.getSlot(i, j).setDie(a);
                }
                System.out.println();
            }
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 4; j++) {
                    System.out.print(window.getSlot(i, j).isOccupate() + "   ");
                }
                System.out.println();
            }
            System.out.println(card.calcola_punteggio(window));
        }

        @Test
        void testa_colored_diagonals() {
            GlassWindow vetrata = new GlassWindow();
            it.polimi.ingsw.Cards.PublicObject card = new ColoredDiagonals();
            Slot s = new Slot(0, Colour.WHITE);
            Slot t = new Slot(0, Colour.WHITE);
            Slot u = new Slot(0, Colour.WHITE);
            Slot v = new Slot(0, Colour.WHITE);
            Slot z = new Slot(0, Colour.WHITE);
            Slot x = new Slot(0, Colour.WHITE);
            Die a = new Die();
            Die b = new Die();
            Die c = new Die();
            Die d = new Die();
            Die e = new Die();
            a.randomdado();
            a.setDicecolor(Colour.RED);
            b.randomdado();
            b.setDicecolor(Colour.BLUE);
            c.randomdado();
            c.setDicecolor(Colour.YELLOW);
            d.randomdado();
            d.setDicecolor(Colour.GREEN);
            e.randomdado();
            e.setDicecolor(Colour.PURPLE);
            s.setDie(a);
            t.setDie(b);
            u.setDie(c);
            v.setDie(d);
            z.setDie(e);
            x.setDie(b);
            for (int i = 1; i < 3; i++) {
                vetrata.setSlot(s, i, 0);
                vetrata.setSlot(t, i, 1);
                vetrata.setSlot(u, i, 2);
                vetrata.setSlot(v, i, 3);
                vetrata.setSlot(z, i, 4);
            }
            vetrata.setSlot(s, 3, 0);
            vetrata.setSlot(t, 3, 1);
            vetrata.setSlot(x, 3, 2);
            vetrata.setSlot(v, 3, 3);
            vetrata.setSlot(z, 3, 4);

            vetrata.setSlot(s, 0, 0);
            vetrata.setSlot(t, 0, 1);
            vetrata.setSlot(s, 0, 2);
            vetrata.setSlot(z, 0, 3);
            vetrata.setSlot(t, 0, 4);
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 4; j++) {
                    if (vetrata.getSlot(i, j).isOccupate()) {
                        System.out.print(vetrata.getSlot(i, j).isOccupate() +
                                "-" + vetrata.getSlot(i, j).getDice().getDicecolor() + "   ");
                    } else System.out.print(vetrata.getSlot(i, j).isOccupate() + " ");
                }
                System.out.println();
            }
            System.out.println("punteggio: " + card.calcola_punteggio(vetrata));
        }
}
