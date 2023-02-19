package com.bbs.mapper;

import com.bbs.domain.File;

public interface FileMapper {

	public int inputFile(File file);

	public File getFileByArticleId(Long articleId);
}
