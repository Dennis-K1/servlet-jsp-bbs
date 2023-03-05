package com.bbs.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 파일 정보 객체
 */
@Getter
@NoArgsConstructor
public class File {

	/**
	 * File 테이블 PK
	 */
	private Long id;

	/**
	 * 업로드된 게시글 PK
	 */
	private Long articleId;

	/**
	 * 클라이언트 업로드 파일명
	 */
	private String originalName;

	/**
	 * DB 저장 파일명
	 */
	private String saveName;

	/**
	 * 저장 디렉토리
	 */
	private String directory;

	/**
	 * 확장자
	 */
	private String extension;

	@Builder
	public File(Long articleId, String originalName, String saveName, String directory, String extension) {
		this.articleId = articleId;
		this.originalName = originalName;
		this.saveName = saveName;
		this.directory = directory;
		this.extension = extension;
	}
}
