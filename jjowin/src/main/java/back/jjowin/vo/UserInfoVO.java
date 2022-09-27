package back.jjowin.vo;

import lombok.Getter;

@Getter
public class UserInfoVO {
    private Long id;

    private String name;

    private String nickname;

    private boolean isCertPhone;

    private boolean isCertMail;

    private boolean isSchool;

    private String schoolName;

    private boolean isDeleted;

    public UserInfoVO(Long id, String name, String nickname, boolean isCertPhone, boolean isCertMail, boolean isSchool, String schoolName, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.isCertPhone = isCertPhone;
        this.isCertMail = isCertMail;
        this.isSchool = isSchool;
        this.schoolName = schoolName;
        this.isDeleted = isDeleted;
    }
}
