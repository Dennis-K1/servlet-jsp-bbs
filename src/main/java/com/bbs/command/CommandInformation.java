package com.bbs.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommandInformation {

	String path;
	boolean isRedirect;
}
