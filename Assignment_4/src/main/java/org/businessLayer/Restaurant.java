package org.businessLayer;
import org.dataLayer.FileWriter;
import org.main.Main;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.*;
import java.util.List;

public class Restaurant implements IRestaurantProcessing, Serializable {
    private PropertyChangeSupport support;
    private String latestOrder;
    private Set<MenuItem> menu;
    private HashMap<Order, List<MenuItem>> orders = new HashMap<>();

    public Restaurant() {
        menu = new HashSet<MenuItem>();
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void sendMessageToChef(String value) {
        support.firePropertyChange("New Order", this.latestOrder, value);
        this.latestOrder = value;
    }

    @Override
    public void createNewMenuItem(String name, int weight, boolean vegetarian, float price) {
        assert  name != null;
        int sizePre = menu.size();
        BaseProduct newProduct = new BaseProduct(name, weight, vegetarian, price);
        menu.add(newProduct);
        String[] product = new String[4];
        product[0] = name;
        product[1] = weight + "";
        product[2] = vegetarian + "";
        product[3] = price + "";
        Main.getAdminGUI().getDtmBP().addRow(product);
        Main.getWaiterGUI().getDtmBP().addRow(product);
        assert menu.size() == sizePre + 1;
    }

    @Override
    public void createCompositeNewMenuItem(String name, List<MenuItem> menuItemList) {
        assert name != null && !menuItemList.isEmpty();
        int sizePre = menu.size();
        CompositeProduct newProduct = new CompositeProduct(name, menuItemList);
        menu.add(newProduct);
        String[] product = new String[4];
        product[0] = name;
        int contor = 0;
        String items = "";
        for(MenuItem item: menuItemList){
            if(contor != 0){
                items += ", ";
            }
            items += item.getProductName();
            contor++;
        }
        product[1] = items;
        product[2] = newProduct.getPrice() + "";
        Main.getAdminGUI().getDtmCP().addRow(product);
        Main.getWaiterGUI().getDtmCP().addRow(product);
        assert menu.size() == sizePre + 1;
    }

    @Override
    public void deleteMenuItem(MenuItem item, int row) {
        assert item != null;
        assert menu.size() > 0;
        int sizePre = menu.size();
        menu.remove(item);
        if(item instanceof BaseProduct){
            System.out.println("del func: " + row);
            Main.getAdminGUI().getDtmBP().removeRow(row);
            Main.getWaiterGUI().getDtmBP().removeRow(row);
        } else {
            Main.getAdminGUI().getDtmCP().removeRow(row);
            Main.getWaiterGUI().getDtmCP().removeRow(row);
        }
        assert menu.size() == sizePre - 1;
    }

    @Override
    public void editMenuItem(MenuItem menuItem,int row) {
        assert menuItem != null;
        int sizePre = menu.size();
        deleteMenuItem(menuItem, row);
        if(menuItem instanceof BaseProduct){
            Main.getAdminGUI().setBaseProductName(((BaseProduct)menuItem).getProductName());
            if(((BaseProduct)menuItem).isVegetarian()){
                Main.getAdminGUI().setBaseProductVegetarian("YES");
            } else {
                Main.getAdminGUI().setBaseProductVegetarian("NO");
            }
            Main.getAdminGUI().setBaseProductWeight(((BaseProduct)menuItem).getWeight()+"");
            Main.getAdminGUI().setBaseProductPrice(((BaseProduct)menuItem).getPrice()+"");
        }
        if(menuItem instanceof CompositeProduct){
            Main.getAdminGUI().setCompositeProductName(((CompositeProduct)menuItem).getProductName());
            StringBuilder products = new StringBuilder();
            int k = 0;
            for(MenuItem item: ((CompositeProduct)menuItem).getMenuItemList()){
                if(k != 0){
                    products.append(", ");
                }
                products.append(item.getProductName());
                k++;
            }
            Main.getAdminGUI().setComponentProducts(products.toString());
        }
       assert menu.size() == sizePre;
    }

    @Override
    public void createNewOrder(int table, List<String> menuItemNames) {
        assert table > -1 && !menuItemNames.isEmpty();
        assert isWellFormed();
        int sizePre = orders.size();
        Order order = new Order(table);
        String messageStart = "New order arrived! The required products are: ";
        String message = "";
        List<MenuItem> orderedProducts = new ArrayList<>();
        int contor = 0;
        for(String itemName: menuItemNames){
            orderedProducts.add(findItemByName(itemName));
            if(contor != 0){
                message += ", ";
            }
            message += itemName;
            contor++;
        }
        String[] product = new String[3];
        product[0] = table + "";
        product[1] = order.getDate();
        product[2] = message;
        MainGUIListeners.WaiterListener.getWaiterGUI().getDtmOrder().addRow(product);
        message += "\n";
        messageStart += message;
        sendMessageToChef(messageStart);
        orders.put(order, orderedProducts);
        assert orders.size() == sizePre + 1;
        assert isWellFormed();
    }

    @Override
    public float computePriceForOrder(Order order) {
        assert order != null;
        assert isWellFormed();
        float orderPrice = 0;
        List<MenuItem> menuItemList = orders.get(order);
        for(MenuItem item: menuItemList){
            orderPrice += item.computePrice();
        }
        assert orderPrice > 0;
        assert isWellFormed();
        return orderPrice;
    }

    @Override
    public void generateBill(Order order) throws IOException {
        assert order != null;
        new FileWriter().writeInFile(order);
    }

    /**
     * se cauta item-ul cu numele primit ca parametru, in meniu
     * @param name numele dupa care se cauta item-ul
     * @return item-ul de tip MenuItem gasit
     */
    public MenuItem findItemByName(String name){
        for(MenuItem item: menu){
            if(item.getProductName().equals(name)){
                return item;
            }
        }
        return null;
    }

    /**
     * se cauta order-ul cu numarul mesei primit ca parametru
     * @param tableNumber numarul mesei
     * @return order-ul cu masa primita ca argument
     */
    public Order findOrderByTableNumber(int tableNumber){
        assert tableNumber > -1;
        assert isWellFormed();
        for(Order o: orders.keySet()){
            if(o.getTable() == tableNumber){
                return o;
            }
        }
        assert isWellFormed();
        return null;
    }

    /**
     * se construieste un String bidimensional cu datele tuturor orderelor
     * @return String bidimensional cu datele tuturor orderelor
     */
    public String[][] getOrders(){
        assert isWellFormed();
        String[][] result = new String[orders.keySet().size()][3];
        Collection<List<MenuItem>> menus = orders.values();
        int i = 0, j = 0;
        for(Order o: orders.keySet()){
            result[i][j] = o.getTable() + "";
            result[i][j+1] = o.getDate();
            i++;
        }
        i = 0; j = 2;
        int k = 0;
        StringBuilder products = new StringBuilder();
        for(List<MenuItem> itemList: menus){
            for(MenuItem item: itemList){
                if(k != 0){
                    products.append(", ");
                }
                products.append(item.getProductName());
                k++;
            }
            result[i][j] = products.toString();
            k = 0;
            i++;
            products = new StringBuilder();
        }
        assert isWellFormed();
        return result;
    }

    /**
     * se construieste un String bidimensional cu datele tuturor produselor de baza
     * @return se construieste un String bidimensional cu datele tuturor produselor de baza
     */
    public String[][] getBaseProducts(){
        int size = 0;
        for(MenuItem item: menu){
            if(item instanceof BaseProduct){
                size++;
            }
        }
        String[][] result = new String[size][4];
        int i = 0, j = 0;
        for(MenuItem item: menu){
            if(item instanceof BaseProduct){
                result[i][j] = item.getProductName();
                result[i][j+1] = ((BaseProduct)item).getWeight() + "";
                result[i][j+2] = ((BaseProduct)item).isVegetarian() + "";
                result[i][j+3] = item.computePrice() + "";
                i++;
                j = 0;
            }
        }
        return result;
    }

    /**
     * se construieste un String bidimensional cu datele tuturor produselor compuse
     * @return se construieste un String bidimensional cu datele tuturor produselor compuse
     */
    public String[][] getCompositeProducts(){
        int size = 0;
        for(MenuItem item: menu){
            if(item instanceof CompositeProduct){
                size++;
            }
        }
        String[][] result = new String[size][3];
        int i = 0, j = 0, k = 0;
        StringBuilder products = new StringBuilder();
        for(MenuItem item: menu){
            if(item instanceof CompositeProduct){
                result[i][j] = item.getProductName();
                for(MenuItem compositeItem: ((CompositeProduct) item).getMenuItemList()){
                    if(k != 0){
                        products.append(", ");
                    }
                    products.append(compositeItem.getProductName());
                    k++;
                }
                result[i][j+1] = products.toString();
                result[i][j+2] = item.computePrice() + "";
                products = new StringBuilder();
                k = 0;
                i++;
                j = 0;
            }
        }
        return result;
    }

    /**
     * se specifica invariantul clasei
     * @return daca orders este well-formed
     */
    protected boolean isWellFormed() {
        for (Order o: orders.keySet()){
            List<MenuItem> menuItemList = orders.get(o);
            for (MenuItem mi: menuItemList){
                if (mi.hashCode() == o.hashCode()){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * metoda de getter pentru atributul orders
     * @return orders
     */
    public HashMap<Order, List<MenuItem>> getOrdersHashMap(){
        return orders;
    }
}
