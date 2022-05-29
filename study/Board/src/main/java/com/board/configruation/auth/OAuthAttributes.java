package com.board.configruation.auth;

import java.util.Map;

import com.board.domain.user.Ouser;
import com.board.domain.user.Role;
import com.board.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	public Map<String,Object> attributes;
	private String nameAttributeKey,username, name, email,password;
	
	@Builder
	public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey,
			String name, String email,String username,String password) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name=name;
		this.email=email;
		this.username=username;
		this.password=password;
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
		System.out.println("toEntity username?? :"+username);
		return User.builder()
				.username(username)
				.password(password)
				.name(name)
				.email(email)
				.role(Role.GUEST)
				.build();
	}
}
