package com.bbs.dao;

import com.bbs.domain.File;
import com.bbs.config.MybatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 파일 관련 DB 접근 객체
 */
public class FileDAO {

	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();

	/**
	 * file-mapper namespace
	 */
	private final String FILE_MAPPER = "com.bbs.dao.FileDAO.";

	/**
	 * DB에 파일 정보 저장
	 * @param file 파일 정보 객체
	 * @return 수행 결과
	 */
	public int inputFile(File file) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.insert(FILE_MAPPER + "inputFile", file);
		}
	}

	/**
	 * 대상 게시글 파일 정보 조회
	 *
	 * @param articleId 대상 게시글 번호
	 * @return
	 */
	public File getFileByArticleId(Long articleId) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			return session.selectOne(FILE_MAPPER + "getFileByArticleId", articleId);
		}
	}
}
