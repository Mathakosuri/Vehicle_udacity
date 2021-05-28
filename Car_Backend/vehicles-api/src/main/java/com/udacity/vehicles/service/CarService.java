package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    private final CarRepository repository;
    private final MapsClient mapClient;
    private final PriceClient priceClient;

    public CarService(CarRepository repository,MapsClient mapClient,PriceClient priceClient) {
        /**
         * TODO: Add the Maps and Pricing Web Clients you create
         *   in `VehiclesApiApplication` as arguments and set them here.
         */
    	this.mapClient=mapClient;
    	this.priceClient=priceClient;
        this.repository = repository;
        
    }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        return repository.findAll();
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) throws CarNotFoundException{
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         *   Remove the below code as part of your implementation.
         *   
         */
    	Car carEntity;
        Optional<Car> optionalEntity = repository.findById(id);
    	if (optionalEntity.isPresent()) {
    	     carEntity = optionalEntity.get();
    	    String price= priceClient.getPrice(carEntity.getId());
    	    //setting the price from price service 
    	      carEntity.setPrice(price);
    	      carEntity.setLocation(mapClient.getAddress(carEntity.getLocation()));
    	     
    	  }else {
    		  throw new CarNotFoundException();
    	  }
    	
    	return  carEntity;
    	}

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
        	System.out.println("the carid"+car.getId());
            return repository.findById(car.getId())
            		.map(carToBeUpdated -> {
            			carToBeUpdated.setCondition(car.getCondition());
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }
        System.out.println("saving a new car");
        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) throws CarNotFoundException{
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         */
    	  Optional<Car> optionalEntity = repository.findById(id);
      	if (optionalEntity.isPresent()) {
      		repository.deleteById(id);
      	}else {
      		throw new CarNotFoundException();
      	}

        /**
         * TODO: Delete the car from the repository.
         */


    }
}
