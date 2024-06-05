import java.util.HashMap;
import java.util.Map;

interface LaptopComponent {
  String getComponentBrand();

  String getComponentModel();
}

class IntelProcessor implements LaptopComponent {
  private String brand;
  private String model;

  public IntelProcessor(String model) {
    this.brand = "Intel";
    this.model = model;
  }

  @Override

  public String getComponentBrand() {
    return brand;
  }

  @Override
  public String getComponentModel() {
    return model;
  }
}

class AMDProcessor implements LaptopComponent {
  private String brand;
  private String model;

  public AMDProcessor(String model) {
    this.brand = "AMD";
    this.model = model;
  }

  @Override
  public String getComponentBrand() {
    return brand;
  }

  @Override
  public String getComponentModel() {
    return model;
  }
}

class LaptopComponentFactory {
  private Map<String, LaptopComponent> componentMap;

  public LaptopComponentFactory() {
    componentMap = new HashMap<>();
    componentMap.put("Intel", new IntelProcessor("Core i8"));
    componentMap.put("AMD", new AMDProcessor("Ryzen 7Uhe"));
    // Add more components and brands here
  }

  public LaptopComponent getComponent(String brand) {
    return componentMap.get(brand);
  }
}

public class LaptopFactoryImplementation {
  private LaptopComponent processor;
  private LaptopComponent ram;
  private LaptopComponent storage;
  private LaptopComponent graphicsCard;

  public LaptopFactoryImplementation(LaptopComponentFactory factory, String processorBrand, String ramBrand,
      String storageBrand, String graphicsCardBrand) {
    this.processor = factory.getComponent(processorBrand);
    this.ram = factory.getComponent(ramBrand);
    this.storage = factory.getComponent(storageBrand);
    this.graphicsCard = factory.getComponent(graphicsCardBrand);
  }

  public String getProcessorInfo() {
    return "Processor: " + processor.getComponentBrand() + " " +
        processor.getComponentModel();
  }

  public String getRAMInfo() {
    return "RAM: " + ram.getComponentBrand() + " " + ram.getComponentModel();
  }

  public String getStorageInfo() {
    return "Storage: " + storage.getComponentBrand() + " " +
        storage.getComponentModel();
  }

  public String getGraphicsCardInfo() {
    return "Graphics Card: " + graphicsCard.getComponentBrand() + " " +
        graphicsCard.getComponentModel();
  }

  public static void main(String[] args) {
    LaptopComponentFactory factory = new LaptopComponentFactory();

    LaptopFactoryImplementation laptop = new LaptopFactoryImplementation(factory, "Intel", "Intel",
        "Intel", "AMD");
    System.out.println(laptop.getProcessorInfo());
    System.out.println(laptop.getRAMInfo());
    System.out.println(laptop.getStorageInfo());
    System.out.println(laptop.getGraphicsCardInfo());
  }
}