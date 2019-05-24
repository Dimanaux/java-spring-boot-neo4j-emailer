package com.example.emailer.db.entities;

import com.example.emailer.db.entities.id.AccountIdStrategy;
import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"folders", "groups", "password"/*, "contacts"*/})
@NodeEntity(label = "Account")
public class Account implements Comparable<Account> {
    @Id
    @GeneratedValue(strategy = AccountIdStrategy.class)
    private String accountId;

    private String firstName;
    private String lastName;

    private String email;

    private String password;

    private String signature;

    @Relationship(type = "IN")
    private Set<Group> groups = new TreeSet<>();

    @Relationship(type = "OWNS")
    private Set<Folder> folders = new TreeSet<>();

    @Relationship(type = "HAS")
    private Set<Message> messages = new TreeSet<>();

//    @Relationship(type = "IN_CONTACT", direction = Relationship.UNDIRECTED)
//    private Set<Account> contacts = new TreeSet<>();

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public int compareTo(Account o) {
        return accountId.compareTo(o.accountId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) &&
                email.equals(account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, email);
    }
}
