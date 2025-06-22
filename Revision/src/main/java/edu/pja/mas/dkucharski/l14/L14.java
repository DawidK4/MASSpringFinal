package edu.pja.mas.dkucharski.l14;

import javax.swing.*;

/**
 * Wykład 14 - GUI
 * podejscie deklaratywne - co komputer ma zrobić, a nie jak ma to zrobić
 *
 * Założenia podejścia deklaratywnego:
 * -programista określa elementy modelu danych Javy (klasy), które powinny być stworzone dla GUI: metody, atrybuty
 * -programista wywołuje metodę, która dla podanego obiektu wyświetli formularz (okno)
 * -system samodzielnie generuje formularze umożliwiające tworzenie nowych instancji danych, ich edycje, itp.
 * -ewentualnie doprecyzowanie szczegółów, m.in.: etykiety tekstowe, rodzaje konkretnych widgetów
 * -obsługujemy głównie typy proste (ale nie tylko): liczby i teksty, T/F, Datę, Typ wyliczeniowy
 * -W celu łatwiejszej implementacji oraz prostrzego użycia, rezygnujemy m.in z: zaawansowanej walidacji danych, wielonarodowości (i18n)
 *
 * Problemy do rozwiązania:
 * -Jak odczytać zawartość klasy (jej strukturę)?
 * -Jak przekazać informacje, które elementy danych powinny mieć swoje odzwierciedlenie w GUI?
 * -Jakie widgety powinne być użyte do poszczególnych rodzajów danych?
 * -Jak połączyć poszczególne elementy GUI (widgety) z danymi (odczyt, zapis)?
 *
 * Odczyt zawartości klasy
 * -wykorzystanie refleksji (reflection)
 * -dostarcza informacje o budowie klas, m.in atrybuty, metody
 * -umożliwia: tworzenie instancji klas (obiektów), wywoływanie metod, modyfikację, odczyt zawartości atrybutów
 * -jest dostępna dla Javy, C# i częściowo C++
 * -Kluczowa klasa (Java): Class i metody: getDeclaredFields(), getDeclaredMethods()
 *
 * Które elemnty powinny mieć GUI?
 * -wyszystkie --> nie, bo nie każdy inwariant powinien być dostępny dla użytkownika
 * -automatycznie --> nie, tak się nie uda dla każdego przypadku
 * -plik konfiguracyjny XML, properties
 * -przekazywanie odpowiednich parametrów dla metody
 * -konwencja nad konfiguracją (Convention over configuration)
 */
public class L14 {
    public static void main(String[] args) {
        JDesktopPane jdp = new JDesktopPane();

        PersonAnnotated person = new PersonAnnotated();
        SingleObjectFrame personFrame = GUIFactory.getObjectFrame(person, jdp, false, true, true);
    }

    // Adnotacje klas
    @Override
    public String toString() {
        return "My own implementation of toString()";
    }
}

