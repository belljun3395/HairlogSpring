package jongjun.hairlog.initConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final MemberInit memberInit;

    // todo @PostConstruct 알아보기
    /**
     * 초기 데이터 세팅을 위한 메서드 모음
     */
    @PostConstruct
    @Transactional
    public void init() {
        memberInit.init();
    }
}
