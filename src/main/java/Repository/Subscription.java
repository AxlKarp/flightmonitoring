package Repository;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {
private static final long serialVersionUID = 1;

@Id
@GeneratedValue
private long id;

@Column(name = "email")
private String email;

@Column(name = "currency")
private String currency;

}
