package ru.nnmotors.eip.web.ui.component.paging;

import java.util.ArrayList;
import java.util.List;

final public class PagindUtils {

	private PagindUtils() {

	}

	public static int startIndex(int page, int pageSize) {
		return page * pageSize - pageSize;
	}

	public static List<PagingElementData> pagingData(int page, int pageSize, int count) {
		List<PagingElementData> pagingItems = new ArrayList<>(count / pageSize + 2);
		PagingElementData prev = new PagingElementData(page - 1, "<<", false);
		prev.setDisable(page <= 1);
		pagingItems.add(prev);
		int pageCount = count / pageSize + ((count % pageSize) > 0 ? 1 : 0);
		for (int i = 1; i <= pageCount; i++) {
			PagingElementData p = new PagingElementData(i, String.valueOf(i), (i) == page);
			pagingItems.add(p);

		}
		PagingElementData next = new PagingElementData(page + 1, ">>", false);
		next.setDisable(page >= pageCount);
		pagingItems.add(next);
		return pagingItems;
	}

}
