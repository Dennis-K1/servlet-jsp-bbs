package com.bbs.service;

import com.bbs.config.MybatisSqlSessionFactory;
import com.bbs.mapper.FileMapper;
import com.bbs.domain.File;
import com.bbs.properties.FileProperties;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 파일 관련 서비스
 */
public class FileService {

	/**
	 * 마이바티스 및 매퍼 실행을 위한 SqlSessionFactory
	 */
	private SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();

	/**
	 * 게시글 번호로 파일 정보 조회
	 * @param articleId 게시글 번호
	 * @return File
	 */
	public File getFileByArticleId(Long articleId) {
		try(SqlSession session = sqlSessionFactory.openSession(true)){
			FileMapper fileMapper = session.getMapper(FileMapper.class);
			return fileMapper.getFileByArticleId(articleId);
		}
	}

	/**
	 * 저장된 파일 (이미지) 를 base64 인코딩하여 반환
	 * @param file 파일 정보 객체
	 * @return
	 * @throws IOException
	 */
	public String getEncodedImageFromFile(File file) throws IOException {

		//개발용
		String filePath = file.getDirectory() + "\\" + file.getSaveName();

		// 서버용
//		String filePath = file.getDirectory() + "/" + file.getSaveName();
		byte[] fileContent = FileUtils.readFileToByteArray(new java.io.File(filePath));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		return encodedString;
	}

	/**
	 * DB 파일 정보 저장
	 * @param file 파일 정보 객체
	 * @return 수행 결과
	 */
	public int inputFile(File file) {
		try(SqlSession session = sqlSessionFactory.openSession(true)){
			FileMapper fileMapper = session.getMapper(FileMapper.class);
			return fileMapper.inputFile(file);
		}
	}

	/**
	 * 서버 파일 및 DB 파일 정보 삭제
	 *
	 * @param previousFile 게시물 파일 정보 객체
	 * @return
	 * @throws IOException
	 */
	public int deleteFile(File previousFile) throws IOException {
		Files.delete(Paths.get(previousFile.getDirectory() + previousFile.getSaveName()));
		try(SqlSession session = sqlSessionFactory.openSession(true)){
			FileMapper fileMapper = session.getMapper(FileMapper.class);
			return fileMapper.deleteFile(previousFile);
		}
	}

	/**
	 * 있는 경우 서버 파일, 디렉토리 및 DB 파일 정보 삭제
	 *
	 * @param previousFile 게시물 파일 정보 객체
	 * @return
	 * @throws IOException
	 */
	public int deleteDirectory(File previousFile) throws IOException {
		Files.deleteIfExists(Paths.get(previousFile.getDirectory() + previousFile.getSaveName()));
		Files.deleteIfExists(Paths.get(previousFile.getDirectory()));
		try(SqlSession session = sqlSessionFactory.openSession(true)){
			FileMapper fileMapper = session.getMapper(FileMapper.class);
			return fileMapper.deleteFile(previousFile);
		}
	}

	/**
	 * 업로드 파일 temp 폴더에 저장하며 MultipartRequest 생성
	 * @param request 요청 객체
	 * @return MultipartRequest
	 * @throws IOException
	 */
	public MultipartRequest getMultipartRequest(HttpServletRequest request) throws IOException {
		return new MultipartRequest(request,
			FileProperties.tempDirectory,
			FileProperties.maxPostSize,
			FileProperties.encoding,
			new DefaultFileRenamePolicy());
	}

	/**
	 * 업로드된 파일 temp 폴더에서 게시글 PK에 해당하는 폴더 생성하여 이동
	 * @param file 파일 정보 객체
	 * @throws IOException
	 */
	public void relocateFileFromTempDirectory(File file)
		throws IOException {
		Path tempFilePath = Paths.get(FileProperties.tempDirectory + file.getOriginalName());
		Path savePath = Paths.get(file.getDirectory());
		Files.createDirectories(savePath);
		Files.move(tempFilePath, savePath.resolve(file.getSaveName()));
	}

	/**
	 * 게시글 번호 기반 파일 정보 객체 생성
	 * @param multipartRequest 요청 객체
	 * @param articleId 게시글 번호
	 * @return File
	 */
	public File createFileVO(MultipartRequest multipartRequest, Long articleId) {

		String saveDirectory = FileProperties.fileDirectory + articleId;
		String fileName = multipartRequest.getFilesystemName("file");
		String extension = fileName.split("\\.")[1];
		String saveName = UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;

		return File.builder()
			.articleId(articleId)
			.originalName(fileName)
			.saveName(saveName)
			.directory(saveDirectory)
			.extension(extension)
			.build();
	}

	/**
	 * 파일 첨부 여부 확인
	 *
	 * @param articleId 대상 게시글 번호
	 * @return
	 */
	public Boolean isFileAttached(Long articleId) {
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			FileMapper fileMapper = session.getMapper(FileMapper.class);
			File file = fileMapper.getFileByArticleId(articleId);
			if (Objects.equals(null, file)) {
				return false;
			}
			return true;
		}
	}


}
