package com.example.funds.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment extends BaseEntity{

   @Column(name = "message",nullable = false)
    private String message;

   @Column(name = "date_of_comment")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "contribution_group_id")
    private ContributionGroup contributionGroup;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

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
}
