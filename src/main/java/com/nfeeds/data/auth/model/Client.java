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
     * String Id chosen by the client to identify itself during the authentication
     */
    @Id
    @Column(nullable = false)
    private String clientId;
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
