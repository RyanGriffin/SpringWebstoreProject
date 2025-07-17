package com.ryansstore.store.services;

import com.ryansstore.store.entities.Address;
import com.ryansstore.store.entities.Profile;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.repositories.AddressRepository;
import com.ryansstore.store.repositories.ProfileRepository;
import com.ryansstore.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

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

    public Address fetchAddress(Long userId) { return addressRepository.findById(userId).orElseThrow(); }

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

    public void deleteRelated(Long userId) { userRepository.deleteById(userId); }

    @Transactional
    public void deleteAddressOfUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow();
        var address = user.getAddresses().stream().findFirst().orElseThrow();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void fetchUser(String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void addUserWithProfile(User user, Profile profile) {
        userRepository.save(user);
        profileRepository.save(profile);
    }

    @Transactional
    public void populateDatabase() {
        var user1 = User.builder()
                .name("Bryan")
                .email("Bryan@ryan.com")
                .password("badpassword")
                .phoneNumber("(555)555-5555")
                .build();
        var profile1 = Profile.builder()
                .bio("Freaking Bryans... This guy sucks!")
                .dob(LocalDate.now())
                .loyaltyPoints(5)
                .user(user1)
                .build();

        var user2 = User.builder()
                .name("Ryan")
                .email("ryan@ryan.com")
                .password("password")
                .phoneNumber("(555)555-5555")
                .build();
        var profile2 = Profile.builder()
                .bio("A normal Ryan. Hey, this guy's alright!")
                .dob(LocalDate.now())
                .loyaltyPoints(10)
                .user(user2)
                .build();

        var user3 = User.builder()
                .name("Super Ryan")
                .email("asuperryan@ryan.com")
                .password("greatpassword")
                .phoneNumber("(555)555-5555")
                .build();
        var profile3 = Profile.builder()
                .bio("A super Ryan?! This guy's so cool!")
                .dob(LocalDate.now())
                .loyaltyPoints(20)
                .user(user3)
                .build();

        userRepository.save(user1);
        profileRepository.save(profile1);
        userRepository.save(user2);
        profileRepository.save(profile2);
        userRepository.save(user3);
        profileRepository.save(profile3);
    }

    @Transactional
    public void printLoyalProfiles(int loyaltyPoints) {
        // var profiles = profileRepository.findByLoyaltyPointsGreaterThanOrderByUserEmail(loyaltyPoints); // old method
        var users = userRepository.findQualifiedUsers(loyaltyPoints); // same as above but has concise name
        users.forEach(u -> System.out.println("ID: " + u.getId() + "\nEmail: " + u.getEmail()));
    }
}
