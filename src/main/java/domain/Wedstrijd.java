package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
@Table(name = "wedstrijd")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode()
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wedstrijd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWedstrijd;

    private LocalDate datum;
    private LocalTime aanvangUur;
    private Double ticketPrijs;
    private int bezettePlaatsen;
    private int olympischNummer1;
    private int olympischNummer2;
    private int capaciteit;

    @ManyToOne
    @JoinColumn(name = "idSport")
    @JsonBackReference

    private Sport sport;

    public Sport getSport() {
        return this.sport;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idStadium")
    private Stadium stadium;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "wedstrijd_discipline", joinColumns = @JoinColumn(name = "wedstrijd_id"), inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    private Set<Discipline> disciplines;

    public void addDiscipline(Discipline discipline) {
        if (disciplines == null) {
            disciplines = new HashSet<>();
        }
        disciplines.add(discipline);
    }

    public Wedstrijd(int idWedstrijd, LocalDate datum, LocalTime aanvangUur,
            Double ticketPrijs, Sport sport, int bezettePlaatsen,
            Set<Discipline> disciplines, int olympischNummer1, int olympischNummer2,
            int capaciteit) {
        this.idWedstrijd = idWedstrijd;
        this.datum = datum;
        this.aanvangUur = aanvangUur;
        this.ticketPrijs = ticketPrijs;
        this.sport = sport;
        this.bezettePlaatsen = bezettePlaatsen;
        this.disciplines = disciplines;
        this.olympischNummer1 = olympischNummer1;
        this.olympischNummer2 = olympischNummer2;
        this.capaciteit = capaciteit;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public void voegPlaatsToe(int aantal) {
        if (bezettePlaatsen + aantal <= capaciteit) {
            bezettePlaatsen += aantal;
        } else {
            throw new IllegalArgumentException("Er zijn niet genoeg plaatsen beschikbaar.");
        }

    }

    @Override
    public String toString() {
        return "Wedstrijd{" +
                "IdWedstrijd=" + idWedstrijd +
                ", date=" + datum +
                ", time=" + aanvangUur +
                ", ticketPrice=" + ticketPrijs +
                ", occupiedSeats=" + bezettePlaatsen +
                ", olympicNumber1=" + olympischNummer1 +
                ", olympicNumber2=" + olympischNummer2 +
                ", capacity=" + capaciteit + "}";
    }

}
