package edu.utep.cs.roach.TicketManagerBackend.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findById(int id);

    Optional<User> findByName(String username);


//    User findyByUsernameInAndPasswordIn(String username, String password);
}
