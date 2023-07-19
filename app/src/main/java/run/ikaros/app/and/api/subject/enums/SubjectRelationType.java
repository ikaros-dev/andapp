package run.ikaros.app.and.api.subject.enums;

import java.util.Arrays;

import run.ikaros.app.and.Utils.Assert;

public enum SubjectRelationType {
    OTHER(1),
    ANIME(2),
    COMIC(3),
    GAME(4),
    MUSIC(5),
    NOVEL(6),
    REAL(7),
    /**
     * 前传.
     */
    BEFORE(8),
    /**
     * 后传.
     */
    AFTER(9),
    /**
     * The same worldview.
     */
    SAME_WORLDVIEW(10),
    /**
     * OST.
     */
    ORIGINAL_SOUND_TRACK(11);
    // todo more subject relation type.

    private final int code;

    SubjectRelationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * Get {@link SubjectRelationType} by type code.
     *
     * @param code type code, a {@link Integer} instance
     * @return {@link SubjectRelationType} instance
     */
    public static SubjectRelationType codeOf(Integer code) {
        Assert.isTrue(code > 0, "'code' must gt 0.");
        return Arrays.stream(SubjectRelationType.values())
                .filter(subjectRelationType -> code.equals(subjectRelationType.getCode()))
                .findFirst().orElse(SubjectRelationType.OTHER);
    }
}
