package factory;

import entity.bike.Bike;

public class BikeGenerator {

    private BikeFactory bikeFactory;

    public void setTypeBikeFactory(BikeFactory bikeFactory) {
        this.bikeFactory = bikeFactory;
    }

    public Bike createBike() {
        return bikeFactory.createBike();
    }
}