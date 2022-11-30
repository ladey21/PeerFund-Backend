package com.example.funds.entities;

import com.example.funds.entities.Enums.RequestStatus;
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
@Table(name = "request")
public class Request extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User member;

    @Column(name = "date_of_request")
    private LocalDateTime dateOfRequest;

    @Column(name = "date_of_approval")
    private LocalDateTime dateOfRequestApproval;

    @ManyToOne
    @JoinColumn(name = "contribution_group_id")
    private ContributionGroup contributionGroup;

    @Column(name = "status_of_request")
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

    public Request(User member, LocalDateTime dateOfRequest, ContributionGroup contributionGroup, RequestStatus requestStatus) {
        this.member = member;
        this.dateOfRequest = dateOfRequest;
        this.contributionGroup = contributionGroup;
        this.requestStatus = requestStatus;
    }

    @JsonBackReference
    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public LocalDateTime getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(LocalDateTime dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public LocalDateTime getDateOfRequestApproval() {
        return dateOfRequestApproval;
    }

    public void setDateOfRequestApproval(LocalDateTime dateOfRequestApproval) {
        this.dateOfRequestApproval = dateOfRequestApproval;
    }

    @JsonBackReference
    public ContributionGroup getContributionGroup() {
        return contributionGroup;
    }

    public void setContributionGroup(ContributionGroup contributionGroup) {
        this.contributionGroup = contributionGroup;
    }

    @JsonBackReference
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
