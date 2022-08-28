package back.jjowin.vo;

import lombok.Getter;

@Getter
public class UserInfoVO {
    private Long id;

    private String name;

    private String nickname;

    private boolean isCert;

    private boolean isSchool;

    private String schoolName;

    private boolean isDeleted;

    public UserInfoVO(Long id, String name, String nickname, boolean isCert, boolean isSchool, String schoolName, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.isCert = isCert;
        this.isSchool = isSchool;
        this.schoolName = schoolName;
        this.isDeleted = isDeleted;
    }
}
