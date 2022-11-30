package com.example.funds.entities;

import com.example.funds.entities.Enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "contribution")
public class Contribution extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "contribution_group_id", referencedColumnName = "id")
    private ContributionGroup contributionGroup;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "date_paid")
    private LocalDateTime datePaid;

    @JsonBackReference
    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    @JsonBackReference
    public ContributionGroup getContributionGroup() {
        return contributionGroup;
    }

    public void setContributionGroup(ContributionGroup contributionGroup) {
        this.contributionGroup = contributionGroup;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDateTime datePaid) {
        this.datePaid = datePaid;
    }
}

