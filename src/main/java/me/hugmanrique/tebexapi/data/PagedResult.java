package me.hugmanrique.tebexapi.data;

import java.util.List;

public class PagedResult<T> {

	private final List<T> results;
	private final int resultsPerPage;
	private final int currentPage;

	private final int totalResults;
	private final int totalPages;

	private final int indexFrom;
	private final int indexTo;

	public PagedResult(List<T> results, int resultsPerPage, int currentPage, int totalResults, int totalPages, int indexFrom, int indexTo) {
		this.results = results;
		this.resultsPerPage = resultsPerPage;
		this.currentPage = currentPage;
		this.totalResults = totalResults;
		this.totalPages = totalPages;
		this.indexFrom = indexFrom;
		this.indexTo = indexTo;
	}

	/**
	 * @return the number of results per page.
	 */
	public int getResultsPerPage() {
		return resultsPerPage;
	}

	/**
	 * @return the page number of this PagedResult.
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the total number of results over all pages.
	 */
	public int getTotalResults() {
		return totalResults;
	}

	/**
	 * @return the total number of pages.
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @return the index of the first result in this page.
	 */
	public int getIndexFrom() {
		return indexFrom;
	}

	/**
	 * @return the index of the last result in this page.
	 */
	public int getIndexTo() {
		return indexTo;
	}

	public List<T> getResults() {
		return results;
	}

}
