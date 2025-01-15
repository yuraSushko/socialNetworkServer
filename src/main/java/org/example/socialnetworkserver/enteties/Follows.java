package org.example.socialnetworkserver.enteties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="FOLLOWS")
@Data
public class Follows {
    int userId;

    int followsId;
    @Id
    private Long id;


}
