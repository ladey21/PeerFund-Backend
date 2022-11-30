package com.example.funds.entities;

import com.example.funds.entities.Enums.DeliveryType;
import com.example.funds.entities.Enums.RoleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity{

//   @NotBlank(message = "first name can not be blank")
//   @Column(name = "first_name", nullable = false)
   public String firstName;

//   @NotBlank(message = "last name can not be blank")
//   @Column(name = "last_name",nullable = false)
   public String lastName;

//   @Size(min = 6, message="password length must be more than 5")
//   @Column(name = "password", nullable = false)
   public String password;

//   @Email(message = "email must have valid format")
//   @Column(name = "email_address", nullable = false, unique = true)
    public String email;

    @Column(name = "phone_number")
    private String phoneNumber;


   @ManyToOne
   @JoinColumn(name = "contribution_group_id",referencedColumnName = "id")
   private ContributionGroup contributionGroup;


    @OneToMany(mappedBy = "member")
    private List<Contribution> contributions;


    @OneToMany(mappedBy = "member")
    private List<Request> requests;

    @Column(name = "delivery_type")
    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;

    @OneToMany
    @Column(name = "comment")
    private List<Comment> comments;

//    @ManyToMany( fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    private RoleType role;

    public User(String firstName, String lastName, String password, String email, String phoneNumber, RoleType role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User(Long id, String firstName, String lastName, String password, String email, RoleType role) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonBackReference
    public ContributionGroup getContributionGroup() {
        return contributionGroup;
    }

    public void setContributionGroup(ContributionGroup contributionGroup) {
        this.contributionGroup = contributionGroup;
    }

    @JsonManagedReference
    public List<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<Contribution> contributions) {
        this.contributions = contributions;
    }

    @JsonManagedReference
    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @JsonBackReference
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonManagedReference
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
