package com.inthemoon.deeplearning.svhndb.repo;

import com.inthemoon.deeplearning.svhndb.model.Bbox;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dims on 11.03.2017.
 */
public interface BboxRepo extends CrudRepository<Bbox, Long> {
}
