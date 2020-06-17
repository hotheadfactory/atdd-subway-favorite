package wooteco.subway.domain.line;

import wooteco.subway.domain.station.Station;

import java.util.List;
import java.util.stream.Collectors;

public class Lines {
    private List<Line> lines;

    public Lines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Station> getStationEntities() {
        return lines.stream()
                .flatMap(it -> it.getStations().stream())
                .map(LineStation::getStation)
                .collect(Collectors.toList());
    }
}