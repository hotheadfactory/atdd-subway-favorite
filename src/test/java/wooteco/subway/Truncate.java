package wooteco.subway;

import org.springframework.stereotype.Component;
import wooteco.subway.domain.line.LineRepository;
import wooteco.subway.domain.member.MemberRepository;
import wooteco.subway.domain.station.StationRepository;

@Component
public class Truncate {
    private LineRepository lineRepository;
    private StationRepository stationRepository;
    private MemberRepository memberRepository;

    public Truncate(LineRepository lineRepository, StationRepository stationRepository, MemberRepository memberRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
        this.memberRepository = memberRepository;
    }

    public void deleteAll() {
        lineRepository.deleteAll();
        stationRepository.deleteAll();
        memberRepository.deleteAll();
    }
}
