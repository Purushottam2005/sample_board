package com.jkonury.www.common.paging;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PageUtil {
	public static final int PAGE_SIZE = 5;
	public static final int BLOCK_SIZE = 15;

	private int pageSize = PAGE_SIZE;
	private int blockSize = BLOCK_SIZE;

	private int numOfRec;
	private int numOfPage;
	private int blockStart;
	private int blockEnd;
	private boolean prePage;
	private boolean nextPage;
	private int length;
	private int offset;
	private int page;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getNumOfRec() {
		return numOfRec;
	}

	public void setNumOfRec(int numOfRec) {
		this.numOfRec = numOfRec;
	}

	public int getNumOfPage() {
		return numOfPage;
	}

	public void setNumOfPage(int numOfPage) {
		this.numOfPage = numOfPage;
	}

	public int getBlockStart() {
		return blockStart;
	}

	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public boolean isPrePage() {
		return prePage;
	}

	public void setPrePage(boolean prePage) {
		this.prePage = prePage;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void init(int numRec) {
		if (numRec > 0) {
			numOfRec = numRec;
			numOfPage = ((numRec - 1) / pageSize) + 1;

			if (page > numOfPage) {
				throw new RuntimeException("wrong page number");
			}

			else {
				offset = (page - 1) * pageSize;
				length = Math.min(pageSize, (page - 1) * pageSize);
				blockStart = ((page - 1) / blockSize) * blockSize + 1;
				blockEnd = Math.min(blockStart + blockSize - 1, numOfPage);
				prePage = (blockStart != 1);
				nextPage = (blockEnd != numOfPage);
			}
		} else {
			numOfRec = 0;
			numOfPage = 0;
			blockStart = 1;
			blockEnd = 1;
			prePage = false;
			nextPage = false;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}
