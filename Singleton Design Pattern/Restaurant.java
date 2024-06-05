abstract class Menu {
  abstract void generateMenu();

  abstract String getDescription();
}

abstract class Drink {
  abstract void serveDrink();

  abstract String getDescription();
}

class SteakHouseMenu extends Menu {

  @Override
  public void generateMenu() {
    System.out.println("Steakhouse Menu Generated...");
  }

  @Override
  public String getDescription() {
    return "Steakhouse Menu";
  }

}

class SeafoodMenu extends Menu {
  @Override
  public void generateMenu() {
    System.out.println("Seafood Menu Generated...");
  }

  @Override
  public String getDescription() {
    return "Seafood Menu";
  }

}

class Margarita extends Drink {

  @Override
  public void serveDrink() {
    System.out.println("Margaritta Served...");
  }

  @Override
  public String getDescription() {
    return "Margarita";
  }

}

class Mocktails extends Drink {

  @Override
  public void serveDrink() {
    System.out.println("Mocktails Served...");
  }

  @Override
  public String getDescription() {
    return "Mocktails";
  }

}

interface MenuFactory {
  Menu CreateMenu();

  Drink CreateDrink();
}

class StandardMenuFactory implements MenuFactory {

  private static StandardMenuFactory instance = new StandardMenuFactory();

  private StandardMenuFactory() {
  }

  public static synchronized StandardMenuFactory getInstance() {
    return instance;
  }

  @Override
  public Menu CreateMenu() {
    return new SteakHouseMenu();
  }

  @Override
  public Drink CreateDrink() {
    return new Margarita();
  }

}

class VipMenuFactory implements MenuFactory {

  private static VipMenuFactory instance = new VipMenuFactory();

  private VipMenuFactory() {
  }

  public static synchronized VipMenuFactory getInstance() {
    return instance;
  }

  @Override
  public Menu CreateMenu() {
    return new SeafoodMenu();
  }

  @Override
  public Drink CreateDrink() {
    return new Mocktails();
  }

}

public class Restaurant {
  public static void main(String[] args) {
    // Standard dining experience
    MenuFactory standardMenuFactory = StandardMenuFactory.getInstance();
    Menu standardMenu = standardMenuFactory.CreateMenu();
    Drink standardDrink = standardMenuFactory.CreateDrink();

    standardMenu.generateMenu();
    standardDrink.serveDrink();

    // VIP dining experience
    MenuFactory vipMenuFactory = VipMenuFactory.getInstance();
    Menu vipMenu = vipMenuFactory.CreateMenu();
    Drink vipDrink = vipMenuFactory.CreateDrink();

    vipMenu.generateMenu();
    vipDrink.serveDrink();
  }
}