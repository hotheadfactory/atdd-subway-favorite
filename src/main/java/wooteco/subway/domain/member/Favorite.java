package wooteco.subway.domain.member;

import wooteco.subway.domain.station.Station;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;
    @ManyToOne(targetEntity = Station.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Station sourceStation;
    @ManyToOne(targetEntity = Station.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Station destinationStation;

    public Favorite() {
    }

    public Favorite(Station sourceStation, Station destinationStation) {
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
    }

    public static Favorite of(Station sourceStation, Station destinationStation) {
        return new Favorite(sourceStation, destinationStation);
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(member, favorite.member) &&
                Objects.equals(sourceStation, favorite.sourceStation) &&
                Objects.equals(destinationStation, favorite.destinationStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, sourceStation, destinationStation);
    }
}
