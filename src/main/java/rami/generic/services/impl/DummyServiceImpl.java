package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.dtos.dummy.DummyDtoFilter;
import rami.generic.dtos.dummy.DummyDtoPost;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.repositories.DummyRepository;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.specs.GenericSpecification;
import rami.generic.repositories.specs.SpecificationBuilder;
import rami.generic.services.DummyService;
import rami.generic.services.PersonService;
import rami.generic.services.genericSegregation.utils.RelationConfig;

import java.util.List;

@Service
@Profile({"dev", "mysql", "postgre"})
public class DummyServiceImpl implements DummyService {

    //#region Autowired
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private GenericSpecification<DummyEntity> dummySpecification;

    @Autowired
    private SpecificationBuilder<DummyEntity> specificationBuilder;
    //#endregion

    //#region Override for Generic
    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<DummyEntity, Long> getRepository() {
        return dummyRepository;
    }

    @Override
    public Class<DummyEntity> entityClass() {
        return DummyEntity.class;
    }

    @Override
    public Class<DummyModel> modelClass() {
        return DummyModel.class;
    }

    @Override
    public SpecificationBuilder<DummyEntity> specificationBuilder() {
        return specificationBuilder;
    }
    //#endregion

    @Override
    public DummyModel create(DummyDtoPost dtoPost) {
        return DummyService.super.createWithRelations(dtoPost,
                new RelationConfig<>("person", personService, dtoPost.getPersonId())
        );
    }

    @Override
    public List<DummyModel> dummyLike(DummyDtoFilter filter) {
        List<DummyEntity> entityList = getRepository().findAll(specificationBuilder
                                                    .withDynamicFilterLike(this.getFilterMap(filter))
                                                    .build());
        if (!entityList.isEmpty()) {
            return getMapper().map(entityList, new TypeToken<List<DummyModel>>() {}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }
}
