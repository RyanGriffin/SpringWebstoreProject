package com.ryansstore.store.services;

import com.ryansstore.store.entities.Address;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.repositories.AddressRepository;
import com.ryansstore.store.repositories.ProfileRepository;
import com.ryansstore.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("ryan")
                .email("ryan@ryan.com")
                .password("badpass")
                .phoneNumber("(555)555-5555")
                .build();

        if(entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient/Detached");

        userRepository.save(user);

        if(entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient/Detached");
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());

        // only selects columns from "profiles" table, no join statements in SQL
        profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getBio());
    }

    public Address fetchAddress(Long userId) {
        return addressRepository.findById(userId).orElseThrow();
    }

    public void persistRelated() {
        var user = User.builder()
                .name("ryan")
                .email("ryan@ryan.com")
                .password("badpass")
                .phoneNumber("(555)555-5555")
                .build();

        var address = Address.builder()
                .street("1027 E Fairmount Rd")
                .city("Burbank")
                .state("CA")
                .zip("91501")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }
}
