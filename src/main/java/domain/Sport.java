package domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table
@EqualsAndHashCode(of = "naam")

public class Sport implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSport;

	@Column
	private String naam;

	@Column
	private String png;

	@OneToMany(mappedBy = "sport", fetch = FetchType.EAGER)
	@JsonManagedReference
	@JsonIgnore
	private List<Wedstrijd> wedstrijden;

	public void addWedstrijd(Wedstrijd wedstrijd) {
		this.wedstrijden.add(wedstrijd);
	}

	@Override
	public String toString() {
		return "Sport{" +
				"idSport=" + idSport +
				", naam='" + naam + '\'' +
				", png='" + png + '\'' +
				", wedstrijden=" + wedstrijden +
				'}';
	}

}