package ru.nnmotors.eip.web.ui.component.chosen;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.nnmotors.eip.business.api.model.entity.Location;
import ru.nnmotors.eip.business.api.model.param.ListParam;
import ru.nnmotors.eip.business.api.service.LocationService;
import ru.nnmotors.eip.web.ui.component.paging.PagindUtils;

@Controller
@RequestMapping("chosen")
@Transactional
public class ChosenLocationController {
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "location", method = RequestMethod.GET)
	public @ResponseBody List<ChosenElementData> location(@RequestParam(required = false) String filter) {	
		ListParam<String, String> listParam = new ListParam<>(PagindUtils.startIndex(1, 2), 2, filter);
		List<Location> deps = locationService.getList(listParam);
		List<ChosenElementData> users = deps.stream()
				.map(dep -> assebleChosen(dep)).collect(Collectors.toList());
		return users;
	}
	
	ChosenElementData assebleChosen(Location department) {
		ChosenElementData userListElementData = new ChosenElementData();
		userListElementData.setId(department.getId().toString());
		userListElementData.setName(department.getName());
		return userListElementData;
	}

}
