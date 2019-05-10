package com.example.emailer.db.entities;

import com.example.emailer.db.entities.id.GroupIdStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"owner", "contacts"})
@NodeEntity(label = "Group")
public class Group implements Comparable<Group> {
    @Id
    @GeneratedValue(strategy = GroupIdStrategy.class)
    private Long groupId;

    private String name;

    @Relationship(type = "OWNED_BY", direction = Relationship.UNDIRECTED)
    private Account owner;

    @Relationship(type = "CONTAINS")
    private Set<Account> contacts = new TreeSet<>();

    @Override
    public int compareTo(Group o) {
        return Long.compare(groupId, o.groupId);
    }
}
