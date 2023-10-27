import java.util.*;

class StockExchange {
    List<Order> buyOrders;
    List<Order> sellOrders;
    List<Deal> deals;

    public StockExchange() {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        deals = new ArrayList<>();
    }

    public void addOrder(String type, double price, int volume) {
        Order order = new Order(type, price, volume);

        if (type.equals("BUY")) {
            processBuyOrder(order);
        } else {
            processSellOrder(order);
        }
    }

    public List<String> getOrders() {
        List<String> orderList = new ArrayList<>();
        for (Order order : buyOrders) {
            orderList.add(order.toString());
        }
        for (Order order : sellOrders) {
            orderList.add(order.toString());
        }
        return orderList;
    }

    public String deleteOrder(int orderId) {
        for (Order order : buyOrders) {
            if (order.getId() == orderId) {
                buyOrders.remove(order);
                return "DELETED";
            }
        }
        for (Order order : sellOrders) {
            if (order.getId() == orderId) {
                sellOrders.remove(order);
                return "DELETED";
            }
        }
        return "NOT FOUND";
    }

    public List<String> showOperations(int amount) {
        List<String> operationList = new ArrayList<>();
        for (int i = deals.size() - 1; i >= Math.max(0, deals.size() - amount); i--) {
            operationList.add(deals.get(i).toString());
        }
        return operationList;
    }

    private void processBuyOrder(Order order) {
        for (Order sellOrder : sellOrders) {
            if (sellOrder.getPrice() <= order.getPrice() && sellOrder.getVolume() >= order.getVolume()) {
                int volume = Math.min(sellOrder.getVolume(), order.getVolume());
                deals.add(new Deal(order.getId(), sellOrder.getId(), sellOrder.getPrice(), volume));
                sellOrder.reduceVolume(volume);
                order.reduceVolume(volume);
                if (sellOrder.getVolume() == 0) {
                    sellOrders.remove(sellOrder);
                }
                if (order.getVolume() == 0) {
                    return;
                }
            }
        }
        buyOrders.add(order);
    }

    private void processSellOrder(Order order) {
        for (Order buyOrder : buyOrders) {
            if (buyOrder.getPrice() >= order.getPrice() && buyOrder.getVolume() >= order.getVolume()) {
                int volume = Math.min(buyOrder.getVolume(), order.getVolume());
                deals.add(new Deal(buyOrder.getId(), order.getId(), order.getPrice(), volume));
                buyOrder.reduceVolume(volume);
                order.reduceVolume(volume);
                if (buyOrder.getVolume() == 0) {
                    buyOrders.remove(buyOrder);
                }
                if (order.getVolume() == 0) {
                    return;
                }
            }
        }
        sellOrders.add(order);
    }
}

class Order {
    private static int idCounter = 1;

    private int id;
    private String type;
    private double price;
    private int volume;

    public Order(String type, double price, int volume) {
        this.id = idCounter++;
        this.type = type;
        this.price = price;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void reduceVolume(int amount) {
        volume -= amount;
    }

    @Override
    public String toString() {
        return id + " " + type + " " + String.format("%.2f", price) + " " + volume;
    }
}

class Deal {
    private int buyOrderId;
    private int sellOrderId;
    private double price;
    private int volume;

    public Deal(int buyOrderId, int sellOrderId, double price, int volume) {
        this.buyOrderId = buyOrderId;
        this.
sellOrderId = sellOrderId;
        this.price = price;
        this.volume = volume;
        }

@Override
public String toString() {
        return buyOrderId + " " + sellOrderId + " " + String.format("%.2f", price) + " " + volume;
        }
        }

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockExchange stockExchange = new StockExchange();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split(" ");

            if (command[0].equals("ADD")) {
                String type = command[1];
                double price = Double.parseDouble(command[2]);
                int volume = Integer.parseInt(command[3]);
                stockExchange.addOrder(type, price, volume);
            } else if (command[0].equals("GET")) {
                List<String> orders = stockExchange.getOrders();
                for (String order : orders) {
                    System.out.println(order);
                }
            } else if (command[0].equals("DELETE")) {
                int orderId = Integer.parseInt(command[1]);
                System.out.println(stockExchange.deleteOrder(orderId));
            } else if (command[0].equals("SHOW_OPERATIONS")) {
                int amount = Integer.parseInt(command[1]);
                List<String> operations = stockExchange.showOperations(amount);
                for (String operation : operations) {
                    System.out.println(operation);
                }
            }
        }
    }
}