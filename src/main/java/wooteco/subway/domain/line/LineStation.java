package wooteco.subway.domain.line;

import wooteco.subway.domain.station.Station;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "line_station")
public class LineStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Line.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "line")
    private Line line;
    @OneToOne
    @JoinColumn(name = "pre_Station_id")
    private Station preStation;
    @OneToOne
    @JoinColumn(name = "station_id")
    private Station station;
    @Column(nullable = false)
    private int distance;
    @Column(nullable = false)
    private int duration;

    public LineStation(Station preStation, Station station, int distance, int duration) {
        this.preStation = preStation;
        this.station = station;
        this.distance = distance;
        this.duration = duration;
    }

    protected LineStation() {
    }

    public Station getPreStation() {
        return preStation;
    }

    public Station getStation() {
        return station;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public void updatePreLineStation(Station preStation) {
        this.preStation = preStation;
    }

    public boolean isLineStationOf(Long preStationId, Long stationId) {
        return this.preStation.getId() == preStationId && this.station.getId() == stationId
                || this.preStation.getId() == stationId && this.station.getId() == preStationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineStation that = (LineStation) o;
        return distance == that.distance &&
                duration == that.duration &&
                Objects.equals(preStation, that.preStation) &&
                Objects.equals(station, that.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preStation, station, distance, duration);
    }
}
