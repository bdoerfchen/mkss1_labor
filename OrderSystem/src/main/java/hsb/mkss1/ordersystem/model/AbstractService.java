package hsb.mkss1.ordersystem.model;

public abstract class AbstractService extends Item{
    

    protected AbstractService(String name) {
        super(name);
    }

    public abstract int getPersons();

    public abstract int getHours();
    

}
