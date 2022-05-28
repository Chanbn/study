package com.board.configruation.auth;

import java.util.Map;

import com.board.domain.user.Ouser;
import com.board.domain.user.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

	public Map<String,Object> attributes;
	private String nameAttributeKey, name, email;
	
	@Builder
	public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey,
			String name, String email) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name=name;
		this.email=email;
	}
	
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String,Object> attributes) {
		return ofGoogle(userNameAttributeName,attributes);
	}
	
	public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes) {
		return OAuthAttributes.builder()
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	public Ouser toEntity() {
		return Ouser.builder()
				.name(name)
				.email(email)
				.role(Role.GUEST)
				.build();
	}
}
