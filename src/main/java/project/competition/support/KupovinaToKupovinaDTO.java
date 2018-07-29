package project.competition.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.competition.model.Kupovina;
import project.competition.web.dto.KupovinaDTO;

@Component
public class KupovinaToKupovinaDTO implements Converter<Kupovina, KupovinaDTO> {

	@Override
	public KupovinaDTO convert(Kupovina source) {
		KupovinaDTO dto = new KupovinaDTO();
		dto.setId(source.getId());
		return dto;
	}
}
