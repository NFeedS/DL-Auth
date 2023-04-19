package com.nfeeds.data.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "\"user\"")
@Entity
public class User {

    /** String Identified chosen by the client to identify itself during the authentication */
    @Id
    @Column(nullable = false)
    private String id;

    /** Random bytes generated at the creation of the client, used for authentication. */
    @Column(nullable = false)
    private String salt;

    /** Password of the client hashed together with the salt, used for authentication. */
    @Column(nullable = false)
    private String hashpsw;
}
