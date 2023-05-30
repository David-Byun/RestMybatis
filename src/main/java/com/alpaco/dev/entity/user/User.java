package com.alpaco.dev.entity.user;

import com.alpaco.dev.entity.BaseTimeEntity;
import com.alpaco.dev.entity.user.oauth.OauthProvider;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    private long user_id;
    @NonNull
    private String username;
    @NonNull
    private String nickname;
    private String birth;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Status status;
    @NonNull
    private boolean privacyAgreement;
    @NonNull
    private boolean marketingAgreement;
    @NonNull
    private boolean hostPermission;
    private OauthProvider oauthProvider;

    @Builder
    public User(String username, String nickname, String birth, String email,
                String password, Status status, boolean privacyAgreement,
                boolean marketingAgreement, boolean hostPermission, OauthProvider oauthProvider) {
        this.username = username;
        this.nickname = nickname;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.status = status;
        this.privacyAgreement = privacyAgreement;
        this.marketingAgreement = marketingAgreement;
        this.hostPermission = hostPermission;
        this.oauthProvider = oauthProvider;
    }

    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}
