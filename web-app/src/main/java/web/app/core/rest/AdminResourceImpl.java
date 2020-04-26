package web.app.core.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import web.app.client.api.AdminResouorce;
import web.app.client.api.dto.AdminDto;

@Component
public class AdminResourceImpl implements AdminResouorce  {

	private static final Logger LOGGER =  LoggerFactory.getLogger(AdminResourceImpl.class);

	@Override
	public List<AdminDto> findAll() {
		
		return null;
	}
	
	
}
