package factory;

import entity.bike.Bike;
import entity.bike.TwinsBike;

public class TwinsBikeFactory implements BikeFactory {

    @Override
    public Bike createBike() {
        return new TwinsBike();
    }

}