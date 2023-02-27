package com.bbs.domain;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class NestedReply {

	@Setter
	private Long id;
	private Long articleId;
	private Long userId;
	private String account;
	private String content;
	private Date dateRegistered;
	private Byte replyDeleted;
}
