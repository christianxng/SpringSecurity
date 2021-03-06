package com.curso.security.service;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.security.datatables.Datatables;
import com.curso.security.datatables.DatatablesColunas;
import com.curso.security.domain.Especialidade;
import com.curso.security.repository.EspecialidadeRepository;


@Service
public class EspecialidadeService {
	
	
	@Autowired
	private EspecialidadeRepository repository;
	
	@Autowired
	private Datatables dataTables;
	
	
	@Transactional(readOnly = false)
	public void salvar(Especialidade especialidade) {
		
		repository.save(especialidade);
	}

	@Transactional(readOnly = true)
	public Map<String, Object > buscarEspecialidades(HttpServletRequest request) {
			
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.ESPECIALIDADES);
		Page<?> page = dataTables.getSearch().isEmpty() 
				? repository.findAll(dataTables.getPageable())
						: repository.findAllByTitulo(dataTables.getSearch(),dataTables.getPageable());
			
		return dataTables.getResponse(page);
	}
	
	@Transactional(readOnly = true)
	public Especialidade buscarPorId(Long id) {
		
		return repository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		repository.deleteById(id);
		
	}

}
