package huaminglin.demo.mongodb.repo;

import huaminglin.demo.mongodb.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
