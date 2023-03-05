package com.bbs.config;


import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 마이바티스 데이터베이스 사용을 위한 세션 생성 객체
 */
public abstract class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			String resource = "mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
