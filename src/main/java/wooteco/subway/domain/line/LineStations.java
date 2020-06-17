package wooteco.subway.domain.line;

import wooteco.subway.domain.station.Station;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.*;

@Embeddable
public class LineStations {
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "line")
    private Set<LineStation> stations = new HashSet<>();

    public LineStations(Set<LineStation> stations) {
        this.stations = stations;
    }

    protected LineStations() {
    }

    public static LineStations empty() {
        return new LineStations(new HashSet<>());
    }

    public Set<LineStation> getStations() {
        return stations;
    }

    public void add(LineStation targetLineStation) {
        updatePreStationOfNextLineStation(targetLineStation.getPreStation(), targetLineStation.getStation());
        stations.add(targetLineStation);
    }

    private void remove(LineStation targetLineStation) {
        updatePreStationOfNextLineStation(targetLineStation.getStation(), targetLineStation.getPreStation());
        stations.remove(targetLineStation);
    }

    public void removeByStationId(Long targetStationId) {
        extractByStationId(targetStationId)
                .ifPresent(this::remove);
    }

    public List<Station> getStationEntities() {
        List<Station> result = new ArrayList<>();
        extractNext(null, result);
        return result;
    }

    private void extractNext(Station preStation, List<Station> stations) {
        this.stations.stream()
                .filter(it -> Objects.equals(it.getPreStation(), preStation))
                .findFirst()
                .ifPresent(it -> {
                    Station nextStation = it.getStation();
                    stations.add(nextStation);
                    extractNext(nextStation, stations);
                });
    }

    private void updatePreStationOfNextLineStation(Station targetStation, Station newPreStation) {
        extractByPreStationId(targetStation.getId())
                .ifPresent(it -> it.updatePreLineStation(newPreStation));
    }

    private Optional<LineStation> extractByStationId(Long stationId) {
        return stations.stream()
                .filter(it -> Objects.equals(it.getStation().getId(), stationId))
                .findFirst();
    }

    private Optional<LineStation> extractByPreStationId(Long preStationId) {
        return stations.stream()
                .filter(it -> Objects.equals(it.getPreStation().getId(), preStationId))
                .findFirst();
    }

    public int getTotalDistance() {
        return stations.stream().mapToInt(LineStation::getDistance).sum();
    }

    public int getTotalDuration() {
        return stations.stream().mapToInt(LineStation::getDuration).sum();
    }
}
