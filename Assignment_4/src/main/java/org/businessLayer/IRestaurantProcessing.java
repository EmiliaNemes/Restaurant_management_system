package org.businessLayer;

import java.io.IOException;
import java.util.List;

public interface IRestaurantProcessing {

    /**
     * se face un produs nou pe baza proprietatilor primite ca parametri
     * se insereaza in meniu
     * @param name numele produsului
     * @param weight gramajul produsului
     * @param vegetarian tipul produsului
     * @param price pretul produsului
     * <pre>
     * <b>Precondition:</b> name != null
     * <b>Postcondition:</b> menu.size() == menu.size()@pre + 1
     * </pre>
     */
    void createNewMenuItem(String name, int weight, boolean vegetarian, float price);

    /**
     *
     * @param name numele composite product-ului
     * @param menuItemList produsele componente
     * <pre>
     * <b>Precondition:</b> name != null
     *                 !menuItemList.isEmpty()
     * <b>Postcondition:</b> menu.size() == menu.size()@pre + 1
     * </pre>
     */
    void createCompositeNewMenuItem(String name, List<MenuItem> menuItemList);

    /**
     * se sterge din meniu item-ul primit ca parametru
     * @param item item-ul ce trebuie sters
     * @param row numarul de rand al item-ului din tabel
     * <pre>
     * <b>Precondition:</b> item != null
     *           menu.size() != 0
     * <b>Postcondition:</b> menu.size() == menu.size()@pre - 1
     * </pre>
     */
    void deleteMenuItem(MenuItem item, int row);

    /**
     * se sterge item-ul veche
     * se face un produs nou cu proprietatile noi
     * se insereaza item-ul nou in meniu
     * @param menuItem item-ul(in forma veche) ce va fi editat
     * @param row randul item-ului din tabel
     * <pre>
     * <b>Precondition:</b> item != null
     * </pre>
     */
    void editMenuItem(MenuItem menuItem,int row);

    /**
     * se cauta produsele in meniu dupa nume, si se stocheaza intr-o lista
     * se pune in hashMap-ul orders un element nou care are ca si cheie
     * order-ul si ca valoare o lista de item-uri cu item-urile comandate
     * @param table numarul mesei, care a facut comanda
     * @param menuItemNames numele produselor comandate din meniu
     * <pre>
     * <b>Precondition:</b> table != -1
     *                   !menuItemNames.isEmpty()
     * <b>Postcondition:</b> orders.size() == orders.size()@pre + 1
     * </pre>
     */
    void createNewOrder(int table, List<String> menuItemNames);

    /**
     * se cauta lista de produse pentru order-ul primit ca parametru
     * se parcurge lista de produse asociata order-ului
     * se calculeaza suma totala a produselor
     * @param order order-ul pentru care se cere pretul
     * @return pretul
     * <pre>
     * <b>Precondition:</b> order != null
     * <b>Postcondition:</b> orderPrice != 0
     * </pre>
     */
    float computePriceForOrder(Order order);

    /**
     * se apeleaza metoda writeInFile din clasa FileWriter, in care
     * se face un fisier in care se scriu informatiile despre order
     * @param order order-ul pentru care se face nota de plata
     * @throws IOException exceptie de scriere/citire in/din fisier
     * <pre>
     * <b>Precondition:</b> order != null
     * <b>Postcondition:</b> billContor == billContor@pre + 1
     * </pre>
     */
    void generateBill(Order order) throws IOException;

}
