package com.bbs.mapper;

import com.bbs.domain.File;

/**
 * File 관련 Mapper
 */
public interface FileMapper {

	/**
	 * 파일 정보 등록
	 *
	 * @param file 파일 정보 객체
	 */
	int inputFile(File file);

	/**
	 * 게시글 번호로 파일 정보 조회
	 *
	 * @param articleId 대상 게시글 번호
	 */
	File getFileByArticleId(Long articleId);

	/**
	 * 파일 정보 삭제
	 *
	 * @param previousFile 등록된 파일 정보 객체
	 */
	int deleteFile(File previousFile);
}
