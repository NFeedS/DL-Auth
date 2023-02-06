package com.nfeeds.data.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Client {
    /**
     * Numeric id used to identify uniquely a client in the database.
     */
    @Id
    @Column(nullable = false)
    private Integer id;
    /**
     * Type of the client, it can be Sender or Receiver.
     */
    @Column(nullable = false)
    private ClientType type;
    /**
     * Random bytes generated at the creation of the client, used for authentication.
     */
    @Column(nullable = false)
    private String salt;
    /**
     * Password of the client hashed together with the salt, used for authentication.
     */
    @Column(nullable = false)
    private String hashpsw;
}
