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
	private String nameAttributeKey,username, name, email,password;
	private List<AuthVO> authList;
	private AuthVO authVO;
	
	@Builder
	public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey,
			String name, String email,String username,String password,List<AuthVO> authList) {
		System.out.println("???"+authList);
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name=name;
		this.email=email;
		this.username=username;
		this.password=password;
		this.authList = authList;
	}
	
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String,Object> attributes) {
		return ofGoogle(userNameAttributeName,attributes);
	}
	
	public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes) {

		System.out.println("attri?? :: "+attributes.toString());
		return OAuthAttributes.builder()
				.username((String) attributes.get("sub"))
				.password((String) attributes.get("sub"))
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	public User toEntity() {

		return User.builder()
				.username(username)
				.password(password)
				.name(name)
				.email(email)
				.authList(authList)
				.build();
	}
}
