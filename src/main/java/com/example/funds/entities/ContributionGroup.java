package com.example.funds.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "contribution_group")
public class ContributionGroup extends BaseEntity{

//    @Length(min = 3,max = 20,message = "title length between 3 and 20")
   @Column(name = "group_name",nullable = false,unique = true)
    private String name;
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
   @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "payment_start_date")
    private LocalDateTime paymentStartDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "payment_end_date")
    private LocalDateTime paymentEndDate;

    @Column(name = "Slots")
    private int slots;
//
//    @Length(min = 3,max = 20,message = "title length between 3 and 20")
//    @Column(name = "title",nullable = false)
    private String purpose_title;

    @Column(name = "message",nullable = false)
    private String description;

    @Column(name = "amount",nullable = false)
    private Double group_amount;

    @OneToMany(mappedBy = "contributionGroup")
    private List<Contribution> contributions;

    @OneToMany(mappedBy = "contributionGroup")
    private List<Request> requests;

    @OneToMany(mappedBy = "contributionGroup")
    private List<User> members;

    @OneToMany(mappedBy = "contributionGroup")
    @Column(name = "comments")
    private List<Comment> comments;

    @OneToOne
    @JoinColumn(name = "group_admin")
    private User admin;

    public ContributionGroup(String name, int slots, String purpose_title, String description, Double group_amount, User admin) {
        this.name = name;
        this.slots = slots;
        this.purpose_title = purpose_title;
        this.description = description;
        this.group_amount = group_amount;
        this.admin = admin;
    }

    public ContributionGroup(String name, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime paymentStartDate, LocalDateTime paymentEndDate, int slots, String purpose_title, String description, Double group_amount) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStartDate = paymentStartDate;
        this.paymentEndDate = paymentEndDate;
        this.slots = slots;
        this.purpose_title = purpose_title;
        this.description = description;
        this.group_amount = group_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getPaymentStartDate() {
        return paymentStartDate;
    }

    public void setPaymentStartDate(LocalDateTime paymentStartDate) {
        this.paymentStartDate = paymentStartDate;
    }

    public LocalDateTime getPaymentEndDate() {
        return paymentEndDate;
    }

    public void setPaymentEndDate(LocalDateTime paymentEndDate) {
        this.paymentEndDate = paymentEndDate;
    }

    @JsonManagedReference
    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public String getPurpose_title() {
        return purpose_title;
    }

    public void setPurpose_title(String purpose_title) {
        this.purpose_title = purpose_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGroup_amount() {
        return group_amount;
    }

    public void setGroup_amount(Double group_amount) {
        this.group_amount = group_amount;
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

    @JsonManagedReference
    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
    @JsonManagedReference
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonBackReference
    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
