package com.example.funds.appInit;

import com.example.funds.entities.ContributionGroup;
import com.example.funds.entities.Enums.RequestStatus;
import com.example.funds.entities.Enums.RoleType;
import com.example.funds.entities.Request;
import com.example.funds.entities.Role;
import com.example.funds.entities.User;
import com.example.funds.repositories.ContributionGroupRepository;
import com.example.funds.repositories.RequestRepository;
import com.example.funds.repositories.RoleRepository;
import com.example.funds.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class AppInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final ContributionGroupRepository groupRepository;


    public AppInit(RoleRepository roleRepository, UserRepository userRepository, RequestRepository requestRepository, ContributionGroupRepository groupRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.groupRepository = groupRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role(1L, RoleType.Admin);
        roleRepository.save(admin);

        Role user = new Role(2L, RoleType.Member);
        roleRepository.save(user);

        User user1 = new User("Tayo","Martins", "tayo","tayomartins@gmail.com","123456789", RoleType.Member);
       User user2 = new User("Bayo","Alfred", "bayo","bayoalfred@gmail.com","123456789",RoleType.Member);
        User user3 = new User("Sade","Adu", "sade","sadeadu@gmail.com","123456789", RoleType.Member);
        User user4 = new User("Davido","Adeleke", "davido","davido@gmail.com","123456789", RoleType.Member);
        User user5 = new User("Buju","Benson", "buju","buju@gmail.com","123456789", RoleType.Member);
        User user6 = new User("Michael","Jackson", "michael","mj@gmail.com","123456789", RoleType.Member);
       User user7 = new User("Ololade","Asake", "asake","ololademiasake@gmail.com","123456789", RoleType.Member);
       User user8 = new User("Alhaji","Techno", "techno","techno@gmail.com","123456789", RoleType.Member);

         userRepository.saveAll(List.of(user1,user2,user3,user4,user5,user6,user7,user8));

        ContributionGroup group1 = new ContributionGroup("shirts group",6,"Bulk TM Lewis shirts","bulk shirts at good discount for 10 shirts",5000.00,user1);
        ContributionGroup group2 = new ContributionGroup("Food group",10,"Rice,Spaghetti,Beans,Milk","a week stock of food",10000.00,user5);
        ContributionGroup group3 = new ContributionGroup("Beverages group",5,"Provisions ","provisions for students, cornflakes,golden-morn,milk,milo",8000.00,user1);
        ContributionGroup group4 = new ContributionGroup("Bus trip",4,"4 seater bus to Ibadan","bus trip to Ibadan at a cheap price",500.00,user3);
        ContributionGroup group5 = new ContributionGroup("picnic group",7,"Outing meal service discount","picnic meal for 10 people",50000.00,user3);
        ContributionGroup group6 = new ContributionGroup("trouser group",6,"3 new trousers sales","sew 3 new trousers at a discount",10000.00,user3);
        groupRepository.saveAll(List.of(group1,group2,group3,group4,group5,group6));

      Request request1 = new Request(user1, LocalDateTime.now(),group6, RequestStatus.APPLIED);
        Request request2 = new Request(user8, LocalDateTime.now(),group2, RequestStatus.APPLIED);
        Request request3 = new Request(user7, LocalDateTime.now(),group2, RequestStatus.APPLIED);
        Request request4 = new Request(user8, LocalDateTime.now(),group2, RequestStatus.APPLIED);
       Request request5 = new Request(user7, LocalDateTime.now(),group6, RequestStatus.APPLIED);

      requestRepository.saveAll(List.of(request1,request2,request3,request4,request5));
   }
}
