package com.board.configruation.auth;

import java.util.List;
import java.util.Map;

import com.board.domain.AuthVO;
import com.board.domain.user.Ouser;
import com.board.domain.user.Role;
import com.board.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	public Map<String,Object> attributes;
	private String nameAttributeKey,userid, name, email,password,nickname;
	private List<AuthVO> authList;
	private AuthVO authVO;
	
	@Builder
	public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey,
			String name, String email,String userid,String password,List<AuthVO> authList,String nickname) {
		System.out.println("???"+authList);
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name=name;
		this.email=email;
		this.userid=userid;
		this.password=password;
		this.authList = authList;
		this.nickname=nickname;
	}
	
	public static OAuthAttributes of(String registrationId, String useridAttributeName,
			Map<String,Object> attributes) {
		return ofGoogle(useridAttributeName,attributes);
	}
	
	public static OAuthAttributes ofGoogle(String useridAttributeName, Map<String,Object> attributes) {

		System.out.println("attri?? :: "+attributes.toString());
		return OAuthAttributes.builder()
				.userid((String) attributes.get("sub"))
				.password((String) attributes.get("sub"))
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.attributes(attributes)
				.nameAttributeKey(useridAttributeName)
				.build();
	}
	
	public User toEntity() {

		return User.builder()
				.userid(userid)
				.password(password)
				.name(name)
				.email(email)
				.authList(authList)
				.nickname(nickname)
				.build();
	}
}
