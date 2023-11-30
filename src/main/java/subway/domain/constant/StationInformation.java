package subway.domain.constant;

public enum StationInformation {
    GYODAE("교대역"),
    GANGNAM("강남역"),
    YEOKSAM("역삼역"),
    NAMBU_TERMINAL("남부터미널역"),
    YANGJAE("양재역"),
    YANGJAE_CITIZEN_FOREST("양재시민의숲역"),
    MAEBONG("매봉역");

    private final String koreanName; // 한글 역 이름

    StationInformation(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
