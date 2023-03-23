package ssvv.project.repository;

import ssvv.project.domain.*;
import ssvv.project.validation.*;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
