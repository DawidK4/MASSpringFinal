package edu.pja.mas.dkucharski.l14;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GUIGenerateAttribute {
    /**
     * Podaje nazwe etykiety dla pozycji w GUI. Gdy "" to bedzie użyta nazwa atrybutu (pierwsza litera
     * skonwertowana do dużej).
     * @return
     */
    String label() default "";

    /**
     * Definiuje widget, ktory bedzie stworzony dla obslugi danego atrybutu.
     * Musi dziedziczyc z "java.awt.Component" ("javax.swing.JComponent")
     * W tym elemencie MUSI byc metoda "String getText()" oraz "setText(String)".
     * @return
     */
    String widgetClass() default "javax.swing.JTextField";

    /**
     * Definiuje tooltip wyswietlany dla widgetu.
     * @return
     */
    String tooltip() default "";

    /**
     * Definiuje metode do pobierania wartosci dla atrybutu. Jezeli jest ""
     oznacza to wykorzystanie metody "String get<NazwaAtrybutu>()"
     * @return
     */
    String getMethod() default "";
    /**
     * Definiuje metodę do ustawiania wartosci dla atrybutu. Jezeli jest ""
     oznacza to wykorzystanie metody "set<NazwaAtrybutu>(String)"
     * @return
     */
    String setMethod() default "";

    /**
     * Definiuje czy generowac widget przy widoku w polach (przegladanie lub
     edycja).
     * @return
     */
    boolean showInFields() default true;

    /**
     * Definiuje czy generowac widget przy widoku tabeli.
     * @return
     */
    boolean showInTable() default true;
    /**
     * Definiuje czy generowac widget przy widoku w polach do wyszukiwania.
     * @return
     */
    boolean showInSearch() default true;
    /**
     * Okresla kolejnosc wygenerowanegu widgetu wsrod innych wygenerowanych
     widgetow.
     * @return
     */
    int order() default 0;
    /**
     * Gdy True to wartosc atrybutu nie moze byc zmieniona.
     * @return
     */
    boolean readOnly() default false;
    /**
     * Gdy true to w czasie zmian wielkosci formy dostosowuje wielkosc widgetu.
     * @return
     */
    boolean scaleWidget() default false;
}
