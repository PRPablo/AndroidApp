package web.app.core.mapper;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

@Component
public class RegisterMapper {

	@Inject
	private MapperFactory mapperFactory;
	
	@Inject
	private Mapper mapper;
	
	
	@PostConstruct
	public void initialize() {
		
	}

}
