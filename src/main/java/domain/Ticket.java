
package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table
@EqualsAndHashCode(of = "idTicket")
public class Ticket implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idTicket;

	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	@ManyToOne
	@JoinColumn(name = "idWedstrijd")
	private Wedstrijd wedstrijd;

	public Wedstrijd getWedstrijd() {
		return this.wedstrijd;
	}

}
